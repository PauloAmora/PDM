package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPref.edit();
        ed.putString("username", "admin");
        ed.putString("password", "root");
        ed.apply();

    }

    public void login(View button){
        String username = ((EditText)findViewById(R.id.editText)).getText().toString();
        String password = ((EditText)findViewById(R.id.editText2)).getText().toString();
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        String s1 = sp.getString("username","");
        String s2 = sp.getString("password", "");
        if(username.equals(s1) && password.equals(s2)){
                Intent i = new Intent(this, Details.class);
                i.putExtra("name", "User Admin");
                i.putExtra("username", username);
                this.startActivity(i);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Login errado", Toast.LENGTH_SHORT);
            toast.show();
        }


    }
}
