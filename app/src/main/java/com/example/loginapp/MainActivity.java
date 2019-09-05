package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("Agenda", Context.MODE_PRIVATE);
        int count = sp.getInt("contactSize", 0);
        ArrayList<Contato> contatos = new ArrayList<Contato>();
        for (int i = 0; i<count; i++){
            Contato c = new Contato();
            c.nome = sp.getString("nome"+i, "");
            c.email = sp.getString("email"+i, "");
            c.telefone = sp.getString("telefone"+i, "");
            contatos.add(c);
        }
        ListView lv = (ListView) findViewById(R.id.listview);

        ArrayAdapter<Contato> adapter = new ContactAdapter(this, contatos);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);

                Intent intent = new Intent(MainActivity.this,ShowItemActivity.class);
                //based on item add info to intent
                Intent id = intent.putExtra("id", i);
                startActivity(intent);
            }
        });

    }



    void addToList(View view){
        Intent i = new Intent(this, AddItemActivity.class);
        startActivity(i);

    }






}
