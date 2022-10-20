package com.biblioteca.agendamvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.biblioteca.agendamvp.DBAgenda.DBAgenda;
import com.biblioteca.agendamvp.Model.NuevoInteractorImpl;
import com.biblioteca.agendamvp.Presenter.NuevoPresenter;
import com.biblioteca.agendamvp.Presenter.NuevoPresenterImprl;
import com.biblioteca.agendamvp.View.NuevoView;
import com.biblioteca.agendamvp.entidades.Contactos;

public class NuevoActivity extends AppCompatActivity implements NuevoView, View.OnClickListener {
    private EditText nombre_contact;
    private EditText telefono_contact;
    private EditText correo_contact;
    private NuevoPresenter presenter;
    DBAgenda dbAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        nombre_contact = findViewById(R.id.edit_name_contact);
        telefono_contact = findViewById(R.id.edit_phone_contact);
        correo_contact = findViewById(R.id.edit_email_contact);

        findViewById(R.id.btn_guardar).setOnClickListener(this);

        presenter = new NuevoPresenterImprl(this, new NuevoInteractorImpl(this));
        dbAgenda = new DBAgenda(this);
    }

    @Override
    public void onClick(View v) {
        Contactos contactos = new Contactos();
        contactos.setNombrecontact(nombre_contact.getText().toString());
        contactos.setTelefonocontact(telefono_contact.getText().toString());
        contactos.setCorreocontact(correo_contact.getText().toString());
        presenter.insertContact(contactos);

    }
    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setNameError() {
        nombre_contact.setError("Error Nombre");
    }

    @Override
    public void setTelefonoError() {
        telefono_contact.setError("Error Telefono");
    }

    @Override
    public void setCorreoError() {
        correo_contact.setError("Error Correo");
    }

    @Override
    public void navigatetohome() {
        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
        Intent i6 = new Intent(NuevoActivity.this, UsuVerContact.class);
        startActivity(i6);
    }
}