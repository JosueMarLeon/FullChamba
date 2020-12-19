package com.example.fullchamba.entidad;

import java.io.Serializable;

public class Tarea implements Serializable {

    private int id;
    private String nombre;
    private String complejidad;
    private Double horas;
    private String fecha;
    private int estado;
    private int proyecto_id;
    private int actividad_id;
    private int empleado_id;
    private String cliente;
    private String proyecto;
    private String actividad;
    private double costo;

    public Tarea() {
    }

    public Tarea(int id, String nombre, String complejidad, Double horas, String fecha, int estado, int proyecto_id, int actividad_id, int empleado_id, String cliente, String proyecto, String actividad, double costo) {
        this.id = id;
        this.nombre = nombre;
        this.complejidad = complejidad;
        this.horas = horas;
        this.fecha = fecha;
        this.estado = estado;
        this.proyecto_id = proyecto_id;
        this.actividad_id = actividad_id;
        this.empleado_id = empleado_id;
        this.cliente = cliente;
        this.proyecto = proyecto;
        this.actividad = actividad;
        this.costo = costo;
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

    public String getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(String complejidad) {
        this.complejidad = complejidad;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(int proyecto_id) {
        this.proyecto_id = proyecto_id;
    }

    public int getActividad_id() {
        return actividad_id;
    }

    public void setActividad_id(int actividad_id) {
        this.actividad_id = actividad_id;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

}
