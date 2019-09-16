package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import db.ContactContract;
import db.ContactDBHelper;

public class ShowItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        ContactDBHelper dbHelper = new ContactDBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        TextView nome = (TextView)findViewById(R.id.textView2);
        TextView email = (TextView)findViewById(R.id.textView4);
        TextView telefone = (TextView)findViewById(R.id.textView5);
        Integer nomeConsulta = this.getIntent().getIntExtra("id", 0);

        Cursor cursor = db.query(ContactContract.ContactEntry.TABLE_NAME,null, BaseColumns._ID +"="+nomeConsulta,null, null,null,null);
        while(cursor.moveToNext()) {
            nome.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_NAME_NOME)));
            email.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_NAME_EMAIL)));
            telefone.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow(ContactContract.ContactEntry.COLUMN_NAME_TELEFONE)));

        }
        cursor.close();

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
