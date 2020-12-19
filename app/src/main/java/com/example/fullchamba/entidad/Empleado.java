package com.example.fullchamba.entidad;

import java.io.Serializable;

public class Empleado implements Serializable {

    private int id;
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String clave;
    private int estado;
    private int cargo_Id;

    public Empleado() {
    }

    public Empleado(int id, String nombre, String apellido, String celular, String correo, String clave, int estado, int cargo_Id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.clave = clave;
        this.estado = estado;
        this.cargo_Id = cargo_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCargo_Id() {
        return cargo_Id;
    }

    public void setCargo_Id(int cargo_Id) {
        this.cargo_Id = cargo_Id;
    }

}
