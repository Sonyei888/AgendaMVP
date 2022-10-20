package com.biblioteca.agendamvp.Model;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.biblioteca.agendamvp.Metodos.Metodos;

public class LoginInteractorImpl implements LoginInteractor{
    Context context;
    Metodos mtd;

    public LoginInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {

        mtd = new Metodos(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(username)){
                    listener.onUsernameError();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    return;
                }

                if (mtd.loginuser(username, password)==1) {

                    listener.onSucces();
                }else{
                    listener.onUsernameError();
                    listener.onPasswordError();
                    return;
                }
            }
        }, 1000);
    }
}
