package com.blogspot.atifsoftwares.bottomnavigation;


import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


/**
 * Fragment ajout.
 */
public class FragmentThree extends Fragment {


    Button btnValidation ;

    DataManager dtManager;

    public FragmentThree() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_three, container, false);

        btnValidation= (Button) view.findViewById(R.id.validerMontant);
        btnValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).validationAddMontant();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void setDtManager(DataManager dt){
        dtManager = dt;
    }
}
