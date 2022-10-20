package com.biblioteca.agendamvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.biblioteca.agendamvp.DBAgenda.DBAgenda;
import com.biblioteca.agendamvp.Model.RegisterInteractorImpl;
import com.biblioteca.agendamvp.Presenter.RegisterPresenter;
import com.biblioteca.agendamvp.Presenter.RegisterPresenterImprl;
import com.biblioteca.agendamvp.View.RegisterView;
import com.biblioteca.agendamvp.entidades.Usuarios;

public class RegisterActivity extends Activity implements RegisterView, View.OnClickListener {
    private ProgressBar progressBar;
    private EditText nombre;
    private EditText correo;
    private EditText telefono;
    private EditText direccion;
    private EditText contrasena;
    private RegisterPresenter presenter;
    DBAgenda dbAgenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressBar = findViewById(R.id.progressBar2);
        nombre = findViewById(R.id.editnombreregister);
        correo = findViewById(R.id.editcorreoregister);
        telefono = findViewById(R.id.edittelefonoregister);
        direccion = findViewById(R.id.editdireccionregister);
        contrasena = findViewById(R.id.editcontrasenaregister);

        findViewById(R.id.btnCancelar).setOnClickListener(this);
        findViewById(R.id.btnRegistrar).setOnClickListener(this);

        presenter = new RegisterPresenterImprl(this, new RegisterInteractorImpl(this));
        dbAgenta = new DBAgenda(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrar:

                Usuarios usuarios = new Usuarios();
                usuarios.setNombre(nombre.getText().toString());
                usuarios.setCorreo(correo.getText().toString());
                usuarios.setTelefono(telefono.getText().toString());
                usuarios.setDireccion(direccion.getText().toString());
                usuarios.setContrasena(contrasena.getText().toString());
                presenter.insertUser(usuarios);
                break;
            case R.id.btnCancelar:
                Intent i2 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i2);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
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
    public void setEmailnameError() {
        correo.setError("Error de Correo");
    }

    @Override
    public void setNameError() {
        nombre.setError("Error de nombre");
    }

    @Override
    public void setPasswordError() {
        contrasena.setError("Error de Contraseña");
    }

    @Override
    public void navigateTohome() {
        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
    }
}