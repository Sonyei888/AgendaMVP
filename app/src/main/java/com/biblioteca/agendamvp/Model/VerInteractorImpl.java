package com.biblioteca.agendamvp.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.biblioteca.agendamvp.DBAgenda.DBAgenda;
import com.biblioteca.agendamvp.Metodos.Metodos;
import com.biblioteca.agendamvp.Presenter.VerPresenter;
import com.biblioteca.agendamvp.entidades.Contactos;

import java.util.ArrayList;

public class VerInteractorImpl implements VerInteractor {
    VerPresenter presenter;
    Context context;
    Metodos mtd;

    public VerInteractorImpl(Context context) {
        this.context = context;
        mtd = new Metodos(context);
    }

    @Override
    public ArrayList<Contactos> mostrar(String correo_user, OnVerError listener) {
        DBAgenda dbHelper = new DBAgenda(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM contactos WHERE correo_usuario = '"+ correo_user +"' " , null);

        if(cursorContactos.moveToFirst()){
            do{
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombrecontact(cursorContactos.getString(1));
                contacto.setTelefonocontact(cursorContactos.getString(2));
                contacto.setCorreocontact(cursorContactos.getString(3));

                listaContactos.add(contacto);
            }while (cursorContactos.moveToNext());


        }
        cursorContactos.close();

        return listaContactos;
    }


    @Override
    public void eliminar(int id, OnVerError listener) {
        mtd.eliminarContacto(id);
        listener.onSucces();
    }

    @Override
    public boolean editar(int id, Contactos contactos, OnVerError listener) {
        boolean correcto = false;

        DBAgenda dbHelper = new DBAgenda(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL(" UPDATE contactos SET nombre = '"+contactos.getNombrecontact()+"',  telefono = '"+contactos.getTelefonocontact()+"', correo = '"+contactos.getCorreocontact()+"' WHERE id= '"+id+"' ");
            correcto = true;

        } catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            {
                db.close();
            }
        }
        return correcto;
    }

    @Override
    public Contactos ver(int id, OnVerError listener) {

        DBAgenda dbHelper = new DBAgenda(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM contactos WHERE id = " + id + " LIMIT 1 ", null);

        if (cursorContactos.moveToFirst()) {
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombrecontact(cursorContactos.getString(1));
            contacto.setTelefonocontact(cursorContactos.getString(2));
            contacto.setCorreocontact(cursorContactos.getString(3));
        }
        cursorContactos.close();
        return  contacto;
        //listener.onSucces();

       /*  presenter.send_result(contacto);*/
    }

}


