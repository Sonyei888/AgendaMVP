package com.biblioteca.agendamvp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.biblioteca.agendamvp.Model.VerInteractor;
import com.biblioteca.agendamvp.Model.VerInteractorImpl;
import com.biblioteca.agendamvp.Presenter.VerPresenter;
import com.biblioteca.agendamvp.Presenter.VerPresenterImprl;
import com.biblioteca.agendamvp.View.VerView;
import com.biblioteca.agendamvp.entidades.Contactos;

public class VerActivity extends Activity implements VerView, View.OnClickListener {
    private EditText edit_nombre;
    private EditText edit_telefono;
    private EditText edit_correo;
    private Button btnGuardar;
    Contactos contactos;
    private VerPresenter presenter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        edit_nombre = findViewById(R.id.edit_nombre_ver);
        edit_telefono = findViewById(R.id.edit_telefono);
        edit_correo = findViewById(R.id.edit_correo_ver);
        btnGuardar = findViewById(R.id.btn_guardar_ver);


        findViewById(R.id.fabEditar).setOnClickListener(this);
        findViewById(R.id.fabEliminar).setOnClickListener(this);

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
            btnGuardar.setVisibility(View.INVISIBLE);
            edit_nombre.setInputType(InputType.TYPE_NULL);
            edit_telefono.setInputType(InputType.TYPE_NULL);
            edit_correo.setInputType(InputType.TYPE_NULL);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabEditar:
                Intent i6 = new Intent(VerActivity.this, EditarActivity.class);
                i6.putExtra("ID", id);
                startActivity(i6);
                finish();
                break;
            case R.id.fabEliminar:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Desea elminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if(presenter.eliminarcontactos(id)){

                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                break;
        }
    }




    @Override
    public void navigateTohome() {
        Toast.makeText(this, "Eliminado", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(VerActivity.this, UsuVerContact.class);
        startActivity(intent);
    }
}