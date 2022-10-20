package com.biblioteca.agendamvp.View;

public interface LoginView {

    void showProgress();
    void hideProgress();
    void setUsernameError();
    void setPasswordError();
    void navigateTohome();
}
