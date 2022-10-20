package com.biblioteca.agendamvp.DBAgenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBAgenda extends SQLiteOpenHelper {

    public static final String DBNAME="agenda.db";

    public DBAgenda(@Nullable Context context) {
        super(context, "agenda.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(" +
                "id INTEGER primary key autoincrement," +
                "nombre TEXT," +
                "correo TEXT not null," +
                "telefono TEXT," +
                "direccion TEXT," +
                "contrasena TEXT not null)");
        db.execSQL("create table contactos(" +
                "id INTEGER primary key autoincrement," +
                "nombre TEXT," +
                "telefono TEXT, " +
                "correo Text," +
                "correo_usuario TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuarios");
        db.execSQL("drop table if exists contactos");
    }
}
