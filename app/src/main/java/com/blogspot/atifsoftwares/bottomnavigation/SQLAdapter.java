package com.blogspot.atifsoftwares.bottomnavigation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLAdapter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "montants.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MONTANT = "montants";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MONTANT = "montant";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";

    private static final String DATABASE_CREATE =  "CREATE TABLE"+ TABLE_MONTANT +" ("
            +COLUMN_ID+ " INTEGER  NOT NULL AUTOINCREMENT PRIMARY KEY,"
            +COLUMN_MONTANT +" int not null,"
            +COLUMN_NAME + " text not null,"
            +COLUMN_TYPE + " int not null);";

    public SQLAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(SQLAdapter.class.getName(),
                "Upgrading database from version " + i + " to "
                        + i1 + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MONTANT);
        onCreate(sqLiteDatabase);
    }
}