package com.biblioteca.agendamvp.entidades;

public class Contactos {
    private int id;
    private String nombrecontact;
    private String telefonocontact;
    private String correocontact;

    public Contactos() {
    }

    public Contactos(String nombrecontact, String telefonocontact, String correocontact) {
        this.nombrecontact = nombrecontact;
        this.telefonocontact = telefonocontact;
        this.correocontact = correocontact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrecontact() {
        return nombrecontact;
    }

    public void setNombrecontact(String nombrecontact) {
        this.nombrecontact = nombrecontact;
    }

    public String getTelefonocontact() {
        return telefonocontact;
    }

    public void setTelefonocontact(String telefonocontact) {
        this.telefonocontact = telefonocontact;
    }

    public String getCorreocontact() {
        return correocontact;
    }

    public void setCorreocontact(String correocontact) {
        this.correocontact = correocontact;
    }
}
