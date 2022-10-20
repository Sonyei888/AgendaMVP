package com.biblioteca.agendamvp.Model;

import android.content.Context;
import android.text.TextUtils;

import com.biblioteca.agendamvp.Metodos.Metodos;
import com.biblioteca.agendamvp.entidades.Contactos;

public class NuevoInteractorImpl implements NuevoInteractor{
    Context context;
    Metodos mtd;

    public NuevoInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void insertcontact(Contactos contactos, OnNuevoError listener) {
        if(TextUtils.isEmpty(contactos.getNombrecontact())){
            listener.onNameError();
            return;
        }
        if(TextUtils.isEmpty(contactos.getTelefonocontact())){
            listener.onPhoneError();
            return;
        }
        if(TextUtils.isEmpty(contactos.getCorreocontact())){
            listener.onEmailError();
            return;
        }
        if(contactos != null){
            mtd = new Metodos(context);
            mtd.insertarContacto(contactos);
            listener.onSucces();
        }
    }
}
