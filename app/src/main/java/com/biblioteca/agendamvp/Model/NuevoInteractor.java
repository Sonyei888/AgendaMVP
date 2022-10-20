package com.biblioteca.agendamvp.Model;

import com.biblioteca.agendamvp.entidades.Contactos;

public interface NuevoInteractor {
    interface OnNuevoError {
        void onNameError();
        void onPhoneError();
        void onEmailError();
        void onSucces();
    }
    void insertcontact(Contactos contactos, OnNuevoError listener);
}
