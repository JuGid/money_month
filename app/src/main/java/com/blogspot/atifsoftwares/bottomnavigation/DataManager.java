package com.blogspot.atifsoftwares.bottomnavigation;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class DataManager{
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

    public boolean isNull(){
        if(database==null)
            return true;
        return false;
    }

    public String[] getColumns(){
        SQLiteDatabase mDataBase;
        mDataBase = dbHelper.getReadableDatabase();
        Cursor dbCursor = mDataBase.query(TABLE_MONTANT, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;
    }

    public void insertMontant(Montants mtn){
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(SQLAdapter.COLUMN_MONTANT, mtn.getMontant());
        values.put(SQLAdapter.COLUMN_NAME, mtn.getName());
        values.put(SQLAdapter.COLUMN_TYPE, mtn.getType());
        //on insère l'objet dans la BDD via le ContentValues
        database.insert(TABLE_MONTANT, null, values);
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
        Cursor cursor = database.query(TABLE_MONTANT, new String[] {SQLAdapter.COLUMN_ID, SQLAdapter.COLUMN_MONTANT, SQLAdapter.COLUMN_NAME,SQLAdapter.COLUMN_TYPE}, SQLAdapter.COLUMN_ID + " LIKE \"" + ID +"\"", null, null, null, null);
        cursor.moveToFirst();
        Montants mtn = new Montants(cursor.getString(2),cursor.getInt(1),cursor.getInt(3));
        return mtn;
    }

    public List<Montants> getAllMontants() {
        Log.e("TEST","GETALLMONTANTS");
        List<Montants> mtnList = new ArrayList<Montants>();

        Cursor cursor = database.query(SQLAdapter.TABLE_MONTANT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Montants mtn = new Montants(cursor.getString(2),cursor.getInt(1),cursor.getInt(3));
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
            Montants mtn = new Montants(cursor.getString(2),cursor.getInt(1),cursor.getInt(3));
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
            Montants mtn = new Montants(cursor.getString(2),cursor.getInt(1),cursor.getInt(3));
            mtnList.add(mtn);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return mtnList;
    }



}
