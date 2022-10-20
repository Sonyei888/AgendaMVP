package com.biblioteca.agendamvp.entidades;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPreference(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences("contactos", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }
    public void setCorreoUsuario(String correoUsuario){
        editor.putString("correo_usuario", correoUsuario);
        editor.apply();
    }

    public String getCorreoUsuario(){
        return sharedPreferences.getString("correo_usuario", "NO ENCONTRADO");
    }

}
