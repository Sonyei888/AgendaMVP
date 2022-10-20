package com.biblioteca.agendamvp.Model;

import com.biblioteca.agendamvp.entidades.Usuarios;

public interface RegisterInteractor {
    interface OnRegisternameError{
        void onUsernameError();
        void onCorreoError();
        void onPasswordError();
        void onSucces();

    }
    boolean register(Usuarios usuarios, OnRegisternameError listener);
}
