package com.biblioteca.agendamvp.Presenter;

import com.biblioteca.agendamvp.Model.VerInteractor;
import com.biblioteca.agendamvp.View.VerView;
import com.biblioteca.agendamvp.entidades.Contactos;

import java.util.ArrayList;

public class VerPresenterImprl implements VerPresenter, VerInteractor.OnVerError {
    private VerView verView;
    private VerInteractor verInteractor;

    public VerPresenterImprl(VerView verView, VerInteractor verInteractor) {
        this.verView = verView;
        this.verInteractor = verInteractor;
    }

    @Override
    public void onSucces() {
        if(verView != null){
            verView.navigateTohome();
        }
    }

    @Override
    public ArrayList<Contactos> mostrarcontactos(String prubea) {
        ArrayList<Contactos> contactos;
        contactos = verInteractor.mostrar(prubea, this);
        return contactos;
    }

    @Override
    public Contactos vercontacto(int id) {

        Contactos contactos = verInteractor.ver(id, this);
        return  contactos;
       // return null;
    }

    @Override
    public boolean eliminarcontactos(int id) {
        verInteractor.eliminar(id, this);
        return false;
    }

    @Override
    public boolean editarcontactos(int id, Contactos contactos) {
        boolean correcto;
        correcto = verInteractor.editar(id, contactos, this);
        return correcto;
    }


    @Override
    public void onDestroy() {
        verView = null;
    }
}
