package com.biblioteca.agendamvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.biblioteca.agendamvp.DBAgenda.DBAgenda;
import com.biblioteca.agendamvp.Model.LoginInteractorImpl;
import com.biblioteca.agendamvp.Presenter.LoginPresenter;
import com.biblioteca.agendamvp.Presenter.LoginPresenterImpl;
import com.biblioteca.agendamvp.View.LoginView;
import com.biblioteca.agendamvp.entidades.SharedPreference;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText correo;
    private EditText password;
    private LoginPresenter presenter;
    private SharedPreference sp;
    DBAgenda dbAgenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);
        correo = findViewById(R.id.editusername);
        password = findViewById(R.id.editpassword);

        findViewById(R.id.btningresar).setOnClickListener(this);
        findViewById(R.id.btnRegistro).setOnClickListener(this);

        presenter = new LoginPresenterImpl(this, new LoginInteractorImpl(this));
        dbAgenta = new DBAgenda(this);
        sp = new SharedPreference(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btningresar:
                String correo_user = correo.getText().toString();
                sp.setCorreoUsuario(correo_user);
                presenter.validateCredentials(correo.getText().toString(), password.getText().toString());
                break;
            case R.id.btnRegistro:
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                break;
        }
        }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        correo.setError("Error de usuario");
    }

    @Override
    public void setPasswordError() {
        password.setError("Error de password");

    }

    @Override
    public void navigateTohome() {
        Toast.makeText(this, "Ingresando...", Toast.LENGTH_SHORT).show();
        Intent i3 = new Intent(LoginActivity.this,UsuVerContact.class);
        startActivity(i3);

    }

}