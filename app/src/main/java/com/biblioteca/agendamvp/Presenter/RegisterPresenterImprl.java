package com.biblioteca.agendamvp.Presenter;

import com.biblioteca.agendamvp.Model.LoginInteractor;
import com.biblioteca.agendamvp.Model.RegisterInteractor;
import com.biblioteca.agendamvp.View.RegisterView;
import com.biblioteca.agendamvp.entidades.Usuarios;

public class RegisterPresenterImprl implements RegisterPresenter, RegisterInteractor.OnRegisternameError {
    private RegisterView registerView;
    private RegisterInteractor registerInteractor;

    public RegisterPresenterImprl(RegisterView registerView, RegisterInteractor registerInteractor) {
        this.registerView = registerView;
        this.registerInteractor = registerInteractor;
    }
    @Override
    public void insertUser(Usuarios usuarios) {
        if(registerView != null){
            registerView.showProgress();
        }
        registerInteractor.register(usuarios, this);
    }

    @Override
    public void onDestroy() {
        registerView = null;

    }

    @Override
    public void onUsernameError() {
        if(registerView != null){
            registerView.setNameError();
            registerView.hideProgress();
        }

    }

    @Override
    public void onCorreoError() {
        if (registerView != null){
            registerView.setEmailnameError();
            registerView.hideProgress();
        }


    }

    @Override
    public void onPasswordError() {
        if(registerView != null){
            registerView.setPasswordError();
            registerView.hideProgress();
        }

    }

    @Override
    public void onSucces() {
        if(registerView != null){
            registerView.navigateTohome();
            registerView.hideProgress();
        }

    }


}
