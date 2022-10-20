package com.biblioteca.agendamvp.Model;

public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void onUsernameError();
        void onPasswordError();
        void onSucces();

    }

    void login(String username, String password, OnLoginFinishedListener listener);
}
