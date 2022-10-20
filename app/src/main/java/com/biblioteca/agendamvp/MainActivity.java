package com.biblioteca.agendamvp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.biblioteca.agendamvp.DBAgenda.DBAgenda;

public class MainActivity extends AppCompatActivity {

    Button crear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crear = findViewById(R.id.crear);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAgenda dbAgenta = new DBAgenda(MainActivity.this);
                SQLiteDatabase db = dbAgenta.getWritableDatabase();
                if (db != null) {
                    Toast.makeText(MainActivity.this, "Base de datos creeda", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
            });

        }


}

