package com.blogspot.atifsoftwares.bottomnavigation;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class adapterList extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> values;
    DataManager dtManager;
    String [] s;
    int lastPosition;

    public adapterList(Context context, ArrayList<String> values,DataManager dt) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.dtManager = dt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.listview, parent, false);
        lastPosition=position;

        s = values.get(position).split(";");

        TextView name = (TextView) rowView.findViewById(R.id.titre);
        TextView montant = (TextView) rowView.findViewById(R.id.montant);
        Button buttonDelete = (Button) rowView.findViewById(R.id.boutonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Confirmation");
                builder.setMessage("Êtes-vous sûr de supprimer l'élément ?");
                builder.setPositiveButton("Oui",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dtManager.deleteAt(s[3].toString());
                                values.remove(lastPosition);
                                notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                ////0

            }
        });

        name.setText(s[0]);
        montant.setText(String.format("%s %s€",s[2],s[1]));


        // change the icon for Windows and iPhone

        return rowView;
    }
}
