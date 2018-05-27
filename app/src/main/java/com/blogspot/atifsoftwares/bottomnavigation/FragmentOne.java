package com.blogspot.atifsoftwares.bottomnavigation;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


/**
 * Fragment accueil
 */
public class FragmentOne extends Fragment {


    public DataManager dtManager;

    public FragmentOne() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);

        dtManager = ((MainActivity)getActivity()).dtManager;
        TextView txtCred = view.findViewById(R.id.TextCredit);
        TextView txtDeb = view.findViewById(R.id.TextDebit);
        TextView txtRestant = view.findViewById(R.id.TextTotal);
        List<Montants> mtnsIncome = dtManager.getIncome();
        List<Montants> mtnsOutCOme = dtManager.getOutcome();

        int totalIncome = 0;
        int totalOutcome = 0;

        for(Montants mtn : mtnsIncome){
            totalIncome += mtn.getMontant();
        }

        for(Montants mtn : mtnsOutCOme){
            totalOutcome += mtn.getMontant();
        }

        txtCred.setText(String.format("%d",totalIncome));
        txtDeb.setText(String.format("%d",totalOutcome));
        txtRestant.setText(String.format("%d",totalIncome-totalOutcome));

        if(totalIncome-totalOutcome < 0){
            txtRestant.setTextColor(ContextCompat.getColor(view.getContext(), android.R.color.holo_red_dark));
        }
        else{
            txtRestant.setTextColor(ContextCompat.getColor(view.getContext(), android.R.color.holo_green_dark));
        }



        // Inflate the layout for this fragment
        return view;
    }

    public void setDtManager(DataManager dt){
        dtManager = dt;
    }

}
