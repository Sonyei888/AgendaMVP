package com.biblioteca.agendamvp.Presenter;

import com.biblioteca.agendamvp.Model.NuevoInteractor;
import com.biblioteca.agendamvp.View.NuevoView;
import com.biblioteca.agendamvp.entidades.Contactos;

public class NuevoPresenterImprl implements NuevoPresenter, NuevoInteractor.OnNuevoError {

    private NuevoView nuevoView;
    private NuevoInteractor nuevoInteractor;

    public NuevoPresenterImprl(NuevoView nuevoView, NuevoInteractor nuevoInteractor) {
        this.nuevoView = nuevoView;
        this.nuevoInteractor = nuevoInteractor;
    }
    @Override
    public void insertContact(Contactos contactos) {
        nuevoInteractor.insertcontact(contactos, this);

    }

    @Override
    public void onDestroy() {
        nuevoView = null;
    }

    @Override
    public void onNameError() {
        if(nuevoView != null){
            nuevoView.setNameError();
        }
    }

    @Override
    public void onPhoneError() {
        if(nuevoView != null){
            nuevoView.setTelefonoError();
        }
    }

    @Override
    public void onEmailError() {
        if(nuevoView != null){
            nuevoView.setCorreoError();
        }
    }

    @Override
    public void onSucces() {
        if(nuevoView != null){
            nuevoView.navigatetohome();
        }
    }


}
