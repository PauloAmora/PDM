package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

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
        String val = ed2.getText().toString();
        if(val.contains("@") && val.split("@")[1].contains(".")){
            SharedPreferences sp = getSharedPreferences("Agenda",Context.MODE_PRIVATE);
            int count = sp.getInt("contactSize",0);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("nome"+count, ed1.getText().toString());
            ed.putString("email"+count, ed2.getText().toString());
            ed.putString("tel"+count, ed3.getText().toString());
            count += 1;
            ed.putInt("contactSize",count);
            ed.commit();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            ed2.setError("Email inválido");
            return;
        }

    }

}
