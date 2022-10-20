package com.biblioteca.agendamvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.biblioteca.agendamvp.Adaptadores.ListaContactoAdapter;
import com.biblioteca.agendamvp.Metodos.Metodos;
import com.biblioteca.agendamvp.Model.VerInteractorImpl;
import com.biblioteca.agendamvp.Presenter.VerPresenter;
import com.biblioteca.agendamvp.Presenter.VerPresenterImprl;
import com.biblioteca.agendamvp.View.VerView;
import com.biblioteca.agendamvp.entidades.Contactos;
import com.biblioteca.agendamvp.entidades.SharedPreference;

import java.util.ArrayList;

public class UsuVerContact extends Activity implements VerView {
    RecyclerView listacontactos;
    private VerPresenter presenter;
    private SharedPreference sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_ver_contact);
        listacontactos = findViewById(R.id.listaContactos);
        listacontactos.setLayoutManager(new LinearLayoutManager(this));
        presenter = new VerPresenterImprl(this, new VerInteractorImpl(this));
        sp = new SharedPreference(this);
        String correo = sp.getCorreoUsuario();
        ListaContactoAdapter adapter = new ListaContactoAdapter(presenter.mostrarcontactos(correo));
        listacontactos.setAdapter(adapter);
    }
    public void mostrarpopup(View v){
        ImageView img = findViewById(R.id.image_menupopup);
        PopupMenu pp = new PopupMenu(this, img);
        pp.getMenuInflater().inflate(R.menu.menupopup, pp.getMenu());
        pp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_menu_agregar:
                        Intent i5 = new Intent(UsuVerContact.this, NuevoActivity.class);
                        startActivity(i5);
                        return true;
                    case R.id.item_menu_cerrar:
                        Toast.makeText(UsuVerContact.this, "Cerrando Sesion...", Toast.LENGTH_SHORT).show();
                        Intent i4 = new Intent(UsuVerContact.this, LoginActivity.class);
                        startActivity(i4);
                        finish();
                        return true;
                }
                return false;
            }
        });
        pp.show();
    }


    @Override
    public void navigateTohome() {

    }
}