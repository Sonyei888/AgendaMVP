package com.biblioteca.agendamvp.Presenter;

import com.biblioteca.agendamvp.entidades.Contactos;

import java.util.ArrayList;

public interface VerPresenter {
    ArrayList<Contactos> mostrarcontactos(String correouser);
    Contactos vercontacto(int id);
    boolean eliminarcontactos(int id);
    boolean editarcontactos(int id, Contactos contactos);
    void onDestroy();
}
