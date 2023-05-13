package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editAccountID;
    EditText editAccountName;
    EditText editAccountDescription;
    EditText editAccountCost;
    EditText editAccountDate;
    EditText editAccountAvailable;

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAccountID = findViewById(R.id.editTextID);
        editAccountName = findViewById(R.id.editTextName);
        editAccountDescription = findViewById(R.id.editTextDescription);
        editAccountCost = findViewById(R.id.editTextCost);
        editAccountDate = findViewById(R.id.editTextDate);
        editAccountAvailable = findViewById(R.id.editTextAvailable);

        dbManager = new DatabaseManager(this);
        try{
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void btnInsertPressed(View v) {
        dbManager.insert(editAccountName.getText().toString(), editAccountDescription.getText().toString(), editAccountCost.getText().toString(), editAccountDate.getText().toString(), editAccountAvailable.getText().toString());
    }

    public void btnFetchPressed(View v){

        Cursor cursor = dbManager.fetch();

        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION));
                @SuppressLint("Range") String cost = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE));
                @SuppressLint("Range") String available = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_AVAILABLE));
                Log.i("DATABASE_TAG", "ID: " + id + ", Name: " + name + ", Description: " + description
                        + ", Cost: " + cost + ", Date: " + date + ", Available: " + available);
            } while (cursor.moveToNext());

        }

    }

    public void btnUpdatePressed(View v){
        dbManager.update(Long.parseLong(editAccountID.getText().toString()), editAccountName.getText().toString(), editAccountDescription.getText().toString(), editAccountCost.getText().toString(), editAccountDate.getText().toString(), editAccountAvailable.getText().toString());

    }

    public void btnDeletePressed(View v){
        dbManager.delete(Long.parseLong(editAccountID.getText().toString()));

    }
}