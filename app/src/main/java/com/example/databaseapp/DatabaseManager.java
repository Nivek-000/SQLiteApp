package com.example.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx) {
        context = ctx;

    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert (String name, String description, String cost, String date, String available){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_NAME, name);
        contentValues.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        contentValues.put(DatabaseHelper.COLUMN_COST, cost);
        contentValues.put(DatabaseHelper.COLUMN_DATE, date);
        contentValues.put(DatabaseHelper.COLUMN_AVAILABLE, available);
        database.insert(DatabaseHelper.ACCOUNT_TABLE, null, contentValues);

    }

    public Cursor fetch(){
        String [] columns = new String[] {DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_DESCRIPTION,
                DatabaseHelper.COLUMN_COST,
                DatabaseHelper.COLUMN_DATE,
                DatabaseHelper.COLUMN_AVAILABLE};
        Cursor cursor = database.query(DatabaseHelper.ACCOUNT_TABLE, columns, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String description, String cost, String date, String available){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_NAME, name);
        contentValues.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        contentValues.put(DatabaseHelper.COLUMN_COST, cost);
        contentValues.put(DatabaseHelper.COLUMN_DATE, date);
        contentValues.put(DatabaseHelper.COLUMN_AVAILABLE, available);
        int ret = database.update(DatabaseHelper.ACCOUNT_TABLE, contentValues, DatabaseHelper.COLUMN_ID + "=" + _id, null);
        return ret;
    }

    public void delete(long _id){
        database.delete(DatabaseHelper.ACCOUNT_TABLE, DatabaseHelper.COLUMN_ID + "=" + _id , null);
    }
}
