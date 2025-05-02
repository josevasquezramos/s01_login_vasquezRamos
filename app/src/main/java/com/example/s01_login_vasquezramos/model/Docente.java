package com.example.s01_login_vasquezramos.model;

public class Docente {

    private int id;
    private String usuario;
    private String tarjeta;
    private String clave;

    public Docente() {
    }

    public Docente(String usuario, String tarjeta, String clave) {
        this.usuario = usuario;
        this.tarjeta = tarjeta;
        this.clave = clave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
