package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.ContactContract;
import db.ContactDBHelper;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContactDBHelper dbHelper = new ContactDBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] colunas = {BaseColumns._ID, ContactContract.ContactEntry.COLUMN_NAME_NOME};
        Cursor cursor = db.query(ContactContract.ContactEntry.TABLE_NAME,colunas,null, null,null, null, null,null);
        ArrayList<Contato> contatos = new ArrayList<Contato>();
        while(cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID));
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_NAME_NOME));
            Contato c = new Contato();
            c.nome = itemId;
            c.id = id;
            contatos.add(c);
        }
        cursor.close();

        ListView lv = (ListView) findViewById(R.id.listview);

        ArrayAdapter<Contato> adapter = new ContactAdapter(this, contatos);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);

                Intent intent = new Intent(MainActivity.this,ShowItemActivity.class);
                //based on item add info to intent
                Intent id = intent.putExtra("id", ((Contato)item).id);
                startActivity(intent);
            }
        });

    }



    void addToList(View view){
        Intent i = new Intent(this, AddItemActivity.class);
        startActivity(i);

    }






}
