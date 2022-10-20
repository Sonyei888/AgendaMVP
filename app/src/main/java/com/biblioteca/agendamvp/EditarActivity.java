package com.biblioteca.agendamvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.biblioteca.agendamvp.Model.VerInteractorImpl;
import com.biblioteca.agendamvp.Presenter.VerPresenter;
import com.biblioteca.agendamvp.Presenter.VerPresenterImprl;
import com.biblioteca.agendamvp.View.VerView;
import com.biblioteca.agendamvp.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends Activity implements VerView {

    private EditText edit_nombre;
    private EditText edit_telefono;
    private EditText edit_correo;
    private Button btnGuardar;
    private FloatingActionButton fabeditar;
    private FloatingActionButton fabeliminar;
    private Contactos contactos;
    private int id =0;
    private boolean correcto;
    private VerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        edit_nombre = findViewById(R.id.edit_nombre_ver);
        edit_telefono = findViewById(R.id.edit_telefono);
        edit_correo = findViewById(R.id.edit_correo_ver);
        btnGuardar = findViewById(R.id.btn_guardar_ver);
        fabeditar = findViewById(R.id.fabEditar);
        fabeditar.setVisibility(View.INVISIBLE);
        fabeliminar = findViewById(R.id.fabEliminar);
        fabeliminar.setVisibility(View.INVISIBLE);


        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();

            if(extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("ID");
            }
        }else {
            id = (int)  savedInstanceState.getSerializable("ID");

        }

        presenter = new VerPresenterImprl(this, new VerInteractorImpl(this));
        contactos = presenter.vercontacto(id);

        if(contactos != null){
            edit_nombre.setText(contactos.getNombrecontact());
            edit_telefono.setText(contactos.getTelefonocontact());
            edit_correo.setText(contactos.getCorreocontact());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactos.setNombrecontact(edit_nombre.getText().toString());
                contactos.setTelefonocontact(edit_telefono.getText().toString());
                contactos.setCorreocontact(edit_correo.getText().toString());
                if(!edit_nombre.getText().toString().isEmpty() && !edit_telefono.getText().toString().isEmpty()){
                    correcto = presenter.editarcontactos(id, contactos);

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EditarActivity.this, UsuVerContact.class);
                        intent.putExtra("ID", id);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
                }

        });
    }

    @Override
    public void navigateTohome() {

    }
}
