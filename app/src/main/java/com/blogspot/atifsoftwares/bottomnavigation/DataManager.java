package com.blogspot.atifsoftwares.bottomnavigation;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {
    private SQLiteDatabase database;
    private SQLAdapter dbHelper;
    private String[] allColumns = { SQLAdapter.COLUMN_ID,
            SQLAdapter.COLUMN_MONTANT,SQLAdapter.COLUMN_NAME,SQLAdapter.COLUMN_TYPE };

    public static final String TABLE_MONTANT = "montants";

    public DataManager(Context context) {
        dbHelper = new SQLAdapter(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertMontant(Montants mtn){
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(SQLAdapter.COLUMN_MONTANT, mtn.getMontant());
        values.put(SQLAdapter.COLUMN_NAME, mtn.getName());
        values.put(SQLAdapter.COLUMN_TYPE, mtn.getType());
        //on insère l'objet dans la BDD via le ContentValues
        return database.insert(TABLE_MONTANT, null, values);
    }

    public int updateLivre(int id, Montants mtn){
        //La mise à jour d'un montant dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel montant on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(SQLAdapter.COLUMN_MONTANT, mtn.getMontant());
        values.put(SQLAdapter.COLUMN_NAME, mtn.getName());
        return database.update(TABLE_MONTANT, values, SQLAdapter.COLUMN_ID + " = " +id, null);
    }

    public int removeMontantID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return database.delete(TABLE_MONTANT, SQLAdapter.COLUMN_ID + " = " +id, null);
    }

    public Montants getMontants(int ID){
        //Récupère dans un Cursor les valeurs correspondant aux montants
        Cursor c = database.query(TABLE_MONTANT, new String[] {SQLAdapter.COLUMN_ID, SQLAdapter.COLUMN_MONTANT, SQLAdapter.COLUMN_NAME,SQLAdapter.COLUMN_TYPE}, SQLAdapter.COLUMN_ID + " LIKE \"" + ID +"\"", null, null, null, null);
        return cursorToMontants(c);
    }

    public List<Montants> getAllMontants() {
        List<Montants> mtnList = new ArrayList<Montants>();

        Cursor cursor = database.query(SQLAdapter.TABLE_MONTANT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Montants mtn = cursorToMontants(cursor);
            mtnList.add(mtn);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return mtnList;
    }

    public List<Montants> getIncome() {
        List<Montants> mtnList = new ArrayList<Montants>();

        Cursor cursor = database.query(SQLAdapter.TABLE_MONTANT,
                allColumns, SQLAdapter.COLUMN_TYPE + "= 1", null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Montants mtn = cursorToMontants(cursor);
            mtnList.add(mtn);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return mtnList;
    }

    public List<Montants> getOutcome() {
        List<Montants> mtnList = new ArrayList<Montants>();

        Cursor cursor = database.query(SQLAdapter.TABLE_MONTANT,
                allColumns, SQLAdapter.COLUMN_TYPE + "= 0", null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Montants mtn = cursorToMontants(cursor);
            mtnList.add(mtn);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return mtnList;
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Montants cursorToMontants(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Montants mtn = new Montants();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        mtn.setMontant(c.getInt(1));
        mtn.setName(c.getString(2));
        mtn.setType(c.getInt(3));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return mtn;
    }
}
