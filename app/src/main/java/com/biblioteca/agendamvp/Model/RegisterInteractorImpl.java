package com.biblioteca.agendamvp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.biblioteca.agendamvp.DBAgenda.DBAgenda;
import com.biblioteca.agendamvp.Metodos.Metodos;
import com.biblioteca.agendamvp.entidades.Usuarios;

public class RegisterInteractorImpl implements RegisterInteractor{
    Context context;
    Metodos mtd;

    public RegisterInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean register(Usuarios usuarios, OnRegisternameError listener) {
        if(TextUtils.isEmpty(usuarios.getNombre())){
            listener.onUsernameError();
            return false;
        }
        if(TextUtils.isEmpty(usuarios.getCorreo())){
            listener.onCorreoError();
            return false;
        }
        if(TextUtils.isEmpty(usuarios.getContrasena())){
            listener.onPasswordError();
            return false;
        }
        if(usuarios != null){
            mtd = new Metodos(context);
           mtd.insertUsuario(usuarios);
            listener.onSucces();
        }else{
            return false;
        }
        return false;
    }
}
