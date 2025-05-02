package com.example.s01_login_vasquezramos.model;

public class Alumno {

    private int id;
    private String codigo;
    private String dni;
    private String clave;

    public Alumno() {
    }

    public Alumno(String codigo, String dni, String clave) {
        this.codigo = codigo;
        this.dni = dni;
        this.clave = clave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
