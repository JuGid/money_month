package com.blogspot.atifsoftwares.bottomnavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragment récap
 */
public class FragmentTwo extends Fragment {


    Button btnRefresh ;
    DataManager dtManager;
    List<Montants> listMontants;
    ArrayList<String> listItems;
    adapterList listMontantsAdapter;
    ListView  listViewMontants;
    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_two, container, false);

        dtManager = ((MainActivity)getActivity()).dtManager;


        listItems=new ArrayList<String>();
        listViewMontants= (ListView) view.findViewById(R.id.listMontants);

        listMontants = dtManager.getAllMontants();

        for(Montants mtn : listMontants){
            listItems.add(mtn.getName() + ";" + mtn.getMontant() + ";" + mtn.getTypeString());
        }

        listMontantsAdapter = new adapterList(getContext(),listItems);
        listViewMontants.setAdapter(listMontantsAdapter);
        listMontantsAdapter.notifyDataSetChanged();

        //GESTION DU SPINNER
        Spinner listFiltres = view.findViewById(R.id.listFiltre);

        ArrayAdapter<CharSequence> adapterSpin = ArrayAdapter.createFromResource(getContext(),
                R.array.filtres, R.layout.spinner_adapter);

        adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listFiltres.setAdapter(adapterSpin);

        //GESTION DE L'EVENEMENT POUR LE SPINNER
        listFiltres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                listItems = new ArrayList<String>();
                switch(position){
                    case 0: //Tout
                        for(Montants mtn : listMontants){
                            listItems.add(mtn.getName() + ";" + mtn.getMontant() + ";" + mtn.getTypeString());
                        }
                        break;
                    case 1: //Débit
                        for(Montants mtn : listMontants){
                            if(mtn.getType()==0)
                                listItems.add(mtn.getName() + ";" + mtn.getMontant() + ";" + mtn.getTypeString());
                        }
                        break;
                    case 2: //Crédit
                        for(Montants mtn : listMontants){
                            if(mtn.getType()==1)
                            listItems.add(mtn.getName() + ";" + mtn.getMontant() + ";" + mtn.getTypeString());
                        }
                        break;
                }
                listMontantsAdapter = new adapterList(getContext(),listItems);
                listViewMontants.setAdapter(listMontantsAdapter);
                listMontantsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        // Inflate the layout for this fragment
        return view;
    }


    public void setDtManager(DataManager dt){
        dtManager = dt;
    }

}
