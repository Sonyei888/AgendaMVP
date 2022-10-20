package com.biblioteca.agendamvp.Metodos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.biblioteca.agendamvp.DBAgenda.DBAgenda;
import com.biblioteca.agendamvp.entidades.Contactos;
import com.biblioteca.agendamvp.entidades.SharedPreference;
import com.biblioteca.agendamvp.entidades.Usuarios;

import java.sql.Array;
import java.util.ArrayList;

public class Metodos {
    ArrayList<Usuarios> listauser;
    Context c;
    SharedPreference sp;

    public Metodos(Context c) {
        this.c = c;
        sp = new SharedPreference(c);
    }

    public boolean insertUsuario(Usuarios u){
        if(buscar(u.getCorreo())==0) {


            boolean id = false;

            try {


                DBAgenda dbAgenda = new DBAgenda(c);
                SQLiteDatabase db = dbAgenda.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put("nombre", u.getNombre());
                values.put("correo", u.getCorreo());
                values.put("telefono", u.getTelefono());
                values.put("direccion", u.getDireccion());
                values.put("contrasena", u.getContrasena());

                id = (db.insert("usuarios", null, values)>0);

            } catch (Exception ex) {
                ex.toString();
            }
            return id;
        }else{
            return false;
        }
    }
    //recorrer tabla usuarios
    public ArrayList<Usuarios> selectUsuarios(){

        Cursor user;
        DBAgenda dbAgenda= new DBAgenda(c);
        SQLiteDatabase db = dbAgenda.getWritableDatabase();


        ArrayList<Usuarios> listauser =new ArrayList<>();
        listauser.clear();

        user = db.rawQuery("SELECT * FROM usuarios" , null);

        if(user != null && user.moveToFirst()){
            do{
                Usuarios u = new Usuarios();
                u.setId(user.getInt(0));
                u.setNombre(user.getString(1));
                u.setCorreo(user.getString(2));
                u.setTelefono(user.getString(3));
                u.setDireccion(user.getString(4));
                u.setContrasena(user.getString(5));


                listauser.add(u);
            }while (user.moveToNext());

        }
        return listauser;
    }

    //buscar usuario ya registrado
    public int buscar (String u){
        int x=0;
        listauser=selectUsuarios();
        for (Usuarios us:listauser) {
            if(us.getCorreo().equals(u)){
                x++;
            }

        }
        return x;
    }
    //login user
    public int loginuser(String u, String p) {
        int a = 0;
        Cursor user;
        DBAgenda dbAgenda= new DBAgenda(c);
        SQLiteDatabase db = dbAgenda.getWritableDatabase();

        user = db.rawQuery(" SELECT * FROM usuarios", null);
        if (user != null && user.moveToFirst()) {
            do {

                if (user.getString(2).equals(u) && user.getString(5).equals(p)) {
                    a++;
                }
            } while (user.moveToNext());
        }
        return a;
    }
    //insertar contactos
    public long insertarContacto(Contactos contactos) {

        long id = 0;

        try {
            DBAgenda dbHelper = new DBAgenda(c);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", contactos.getNombrecontact());
            values.put("telefono", contactos.getTelefonocontact());
            values.put("correo", contactos.getCorreocontact());
            values.put("correo_usuario", sp.getCorreoUsuario());

            id = db.insert("contactos", null, values);

        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }
    //mostrar contactos

    public Contactos verContacto(int id){

        DBAgenda dbHelper = new DBAgenda(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM contactos WHERE id = " + id + " LIMIT 1 ", null);

        if(cursorContactos.moveToFirst()){
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombrecontact(cursorContactos.getString(1));
            contacto.setTelefonocontact(cursorContactos.getString(2));
            contacto.setCorreocontact(cursorContactos.getString(3));
        }
        cursorContactos.close();

        return contacto;
    }
    //editar contacto
    public boolean editarContacto(int id, Contactos contacto) {

        boolean correcto = false;

        DBAgenda dbHelper = new DBAgenda(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL(" UPDATE contactos SET nombre = '"+contacto.getNombrecontact()+"',  telefono = '"+contacto.getTelefonocontact()+"', correo = '"+contacto.getCorreocontact()+"' WHERE id= '"+id+"' ");
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

    //eliminar Contacto
    public boolean eliminarContacto(int id) {

        boolean correcto = false;

        DBAgenda dbHelper = new DBAgenda(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL(" DELETE FROM contactos WHERE id ='"+id+"'");
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

    //mostrar contactos array
    public ArrayList<Contactos> mostrarContactos(String correo_user){

        DBAgenda dbHelper = new DBAgenda(c);
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

}
