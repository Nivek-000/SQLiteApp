package com.example.databaseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATBASE_NAME = "accounting";
    static final int DATABSE_VERSION = 2;

    static final String ACCOUNT_TABLE = "account";
    static final String COLUMN_ID = "id";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_DESCRIPTION = "description";
    static final String COLUMN_COST = "cost";
    static final String COLUMN_DATE = "date";
    static final String COLUMN_AVAILABLE = "available";

    private static final String CREATE_ACCOUNT_TABLE_QUERY = "CREATE TABLE " + ACCOUNT_TABLE + " ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + COLUMN_COST + " TEXT NOT NULL, "
            + COLUMN_DATE + " TEXT NOT NULL, "
            + COLUMN_AVAILABLE + " TEXT NOT NULL );";

    public DatabaseHelper(Context context) {
        super(context, DATBASE_NAME, null, DATABSE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DATABSE_TAG", CREATE_ACCOUNT_TABLE_QUERY);
        db.execSQL(CREATE_ACCOUNT_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);
    }

//    private void insertInitialTransactions(SQLiteDatabase db) {
//        String initialTransactions = INSERT_TRANSACTION_QUERY +
//                "('Income', 'May 1st', 5000.00, '2022-05-01', 5000.00), " +
//                "('Food', 'Groceries', -100.00, '2022-05-02', 4900.00), " +
//                "('Gas', 'Fill up', -50.00, '2022-05-03', 4850.00), " +
//                "('Entertainment', 'Movie', -20.00, '2022-05-04', 4830.00), " +
//                "('Expenses', 'Rent', -1000.00, '2022-05-05', 3830.00), " +
//                "('Food', 'Eating out', -75.00, '2022-05-06', 3755.00), " +
//                "('Expenses', 'Utilities', -150.00, '2022-05-07', 3605.00), " +
//                "('Entertainment', 'Concert', -150.00, '2022-05-08', 3455.00), " +
//                "('Gas', 'Fill up', -40.00, '2022-05-09', 3415.00), " +
//                "('Expenses', 'Insurance', -200.00, '2022-05-10', 3215.00), " +
//                "('Food', 'Groceries', -80.00, '2022-05-11', 3135.00), " +
//                "('Entertainment', 'Games', -50.00, '2022-05-12', 3085.00), " +
//                "('Expenses', 'Phone bill', -80.00, '2022-05-13', 3005.00), " +
//                "('Gas', 'Fill up', -35.00, '2022-05-18', 2755.00), " +
//                "('Expenses', 'Car payment', -500.00, '2022-05-19', 2255.00), " +
//                "('Food', 'Groceries', -120.00, '2022-05-20', 2135.00), " +
//                "('Entertainment', 'Games', -40.00, '2022-05-21', 2095.00) ";
//    }
}
