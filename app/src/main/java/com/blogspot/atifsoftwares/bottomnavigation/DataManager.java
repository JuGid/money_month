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

    public DataManager(Context context) {
        dbHelper = new SQLAdapter(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
