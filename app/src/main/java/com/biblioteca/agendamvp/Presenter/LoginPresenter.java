package com.biblioteca.agendamvp.Presenter;

public interface LoginPresenter {
    void validateCredentials(String username, String password);
    void onDestroy();
}
