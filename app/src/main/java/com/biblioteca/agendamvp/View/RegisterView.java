package com.biblioteca.agendamvp.View;

public interface RegisterView {
    void showProgress();
    void hideProgress();
    void setEmailnameError();
    void setNameError();
    void setPasswordError();
    void navigateTohome();
}
