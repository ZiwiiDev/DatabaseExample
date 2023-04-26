package com.example.practica3oliver;

import java.io.Serializable;

public class Producto implements Serializable {

    private int id;
    private String nombre;
    private String ingredientes;
    private double gr;
    private double precio;
    private int disponible;

    public Producto (int id, String nombre, String ingredientes, double gr, double precio, int disponible) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.gr = gr;
        this.precio = precio;
        this.disponible = disponible;
    }

    public Producto (String nombre, String ingredientes, double gr, double precio, int disponible) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.gr = gr;
        this.precio = precio;
        this.disponible = disponible;
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

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public double getGr() {
        return gr;
    }

    public void setGr(double gr) {
        this.gr = gr;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }
}
