package com.biblioteca.agendamvp.Presenter;

import com.biblioteca.agendamvp.entidades.Usuarios;

public interface RegisterPresenter {
    void insertUser(Usuarios usuarios);
    void onDestroy();
}
