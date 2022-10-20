package com.biblioteca.agendamvp.Model;

import com.biblioteca.agendamvp.entidades.Contactos;

import java.util.ArrayList;

public interface VerInteractor {
    interface OnVerError{
        void onSucces();
    }
    ArrayList<Contactos> mostrar(String prueba, OnVerError listener);
    void eliminar(int id, OnVerError listener);
    boolean editar(int id, Contactos contactos, OnVerError listener);
    Contactos ver(int id, OnVerError listener);
}
