package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        TextView nome = (TextView)findViewById(R.id.textView2);
        TextView email = (TextView)findViewById(R.id.textView4);
        TextView telefone = (TextView)findViewById(R.id.textView5);
        SharedPreferences sp = getSharedPreferences("Agenda", Context.MODE_PRIVATE);
        int index = this.getIntent().getIntExtra("id", 0);
        nome.setText(sp.getString("nome"+index, null));
        email.setText(sp.getString("email"+index, null));
        telefone.setText(sp.getString("tel"+index, null));

    }
    void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    void dialNumber (View view){
        Uri number = Uri.parse("tel:"+((TextView)findViewById(R.id.textView5)).getText().toString());
        Intent i = new Intent(Intent.ACTION_DIAL, number);
        startActivity(i);
    }
}
