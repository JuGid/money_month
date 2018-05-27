package com.blogspot.atifsoftwares.bottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public DataManager dtManager;
    private Button btnValidation;
    private EditText nomMontant;
    private EditText montantChiffre;
    private RadioButton rbCred;
    private RadioButton rbDeb;
    private Toolbar toolbar;
    private Menu menuGeneral;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.genToolbar);
        //menuGeneral = (Menu)findViewById(R.menu.menu_gen);
        //menuGeneral.addSubMenu("Plus");
        setSupportActionBar(toolbar);
        //if (getSupportActionBar() != null) {
        //    getSupportActionBar().setTitle("Main Page");
        //}
        //toolbar.setSubtitle("Test Subtitle");
        //toolbar.inflateMenu(R.menu.menu_gen);

        dtManager = new DataManager(this);
        dtManager.open();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        setTitle("Accueil"); //this will set title of Action Bar
                        FragmentOne fragment1 = new FragmentOne();
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.fram, fragment1, "Accueil");  //create first framelayout with id fram in the activity where fragments will be displayed
                        fragmentTransaction1.commit();
                        return true;
                    case R.id.navigation_dashboard:
                        setTitle("Gestion");
                        FragmentTwo fragment2 = new FragmentTwo();
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.fram, fragment2, "Gestion");  //create first framelayout with id fram in the activity where fragments will be displayed
                        fragmentTransaction2.commit();
                        return true;
                    case R.id.navigation_notifications:
                        setTitle("Ajouter");
                        FragmentThree fragment3 = new FragmentThree();
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.fram, fragment3, "Ajouter");  //create first framelayout with id fram in the activity where fragments will be displayed
                        fragmentTransaction3.commit();
                        return true;
                }

                return false;
            }
        };

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setTitle("Accueil"); //this will set title of Action Bar
        FragmentOne fragment1 = new FragmentOne();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.fram, fragment1, "Accueil");  //create first framelayout with id fram in the activity where fragments will be displayed
        fragmentTransaction1.commit();
    }

    public void setAllMontantsInFragmentTwo(){

        ArrayList<String> listItems=new ArrayList<String>();
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        ListView  listViewMontants= (ListView) findViewById(R.id.listMontants);

        listViewMontants.setAdapter(adapter);

        List<Montants> listMontants = dtManager.getAllMontants();
        for(Montants mtn : listMontants){
            listItems.add(mtn.getName() + " | " + mtn.getMontant() + " | " + mtn.getType());
            Log.e("TEST",mtn.getName());
        }
        adapter.notifyDataSetChanged();
    }

    public void validationAddMontant(){
        if(dtManager.isNull()){
            Log.e("TEST","La base de données n'a pas été construite");
        }

        btnValidation = findViewById(R.id.validerMontant);
        nomMontant = findViewById(R.id.nomMontant);
        montantChiffre = findViewById(R.id.montant);
        rbCred = findViewById(R.id.rbCredit);
        rbDeb = findViewById(R.id.rbDebit);

        Montants mtn = new Montants();
        mtn.setName(nomMontant.getText().toString());
        mtn.setMontant(Integer.parseInt(montantChiffre.getText().toString()));
        if(rbCred.isChecked()){
            mtn.setType(1);
        }
        else{
            mtn.setType(0);
        }
        dtManager.insertMontant(mtn);
        
        nomMontant.setText("");
        montantChiffre.setText("");
        Toast.makeText(this, "Montant ajouté.",
                Toast.LENGTH_LONG).show();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gen, menu);
        return true;
    }
    */
}
