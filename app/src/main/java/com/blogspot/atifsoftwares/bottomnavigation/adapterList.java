package com.blogspot.atifsoftwares.bottomnavigation;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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

    public adapterList(Context context, ArrayList<String> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview, parent, false);

        TextView name = (TextView) rowView.findViewById(R.id.titre);
        TextView montant = (TextView) rowView.findViewById(R.id.montant);
        Button buttonDelete = (Button) rowView.findViewById(R.id.boutonDelete);

        String [] s = values.get(position).split(";");

        name.setText(s[0]);
        montant.setText(String.format("%s %s€",s[2],s[1]));


        // change the icon for Windows and iPhone

        return rowView;
    }
}
