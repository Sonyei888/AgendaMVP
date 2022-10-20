package com.biblioteca.agendamvp.Presenter;

import com.biblioteca.agendamvp.entidades.Contactos;

public interface NuevoPresenter {
    void insertContact(Contactos contactos);
    void onDestroy();
}
