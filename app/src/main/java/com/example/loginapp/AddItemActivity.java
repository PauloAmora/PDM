package com.example.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

import db.ContactContract;
import db.ContactDBHelper;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

    }
    void validateAndSaveData(View view){
        EditText ed1 = (EditText) findViewById(R.id.editText2);
        EditText ed2 = (EditText) findViewById(R.id.editText3);
        EditText ed3 = (EditText) findViewById(R.id.editText4);
        ContactDBHelper dbHelper = new ContactDBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String val = ed2.getText().toString();
        if(val.contains("@") && val.split("@")[1].contains(".")){
            ContentValues cv = new ContentValues();
            cv.put(ContactContract.ContactEntry.COLUMN_NAME_NOME, ed1.getText().toString());
            cv.put(ContactContract.ContactEntry.COLUMN_NAME_EMAIL, ed2.getText().toString());
            cv.put(ContactContract.ContactEntry.COLUMN_NAME_TELEFONE, ed3.getText().toString());
            db.insert(ContactContract.ContactEntry.TABLE_NAME,"", cv);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            ed2.setError("Email inválido");
            return;
        }

    }

}
