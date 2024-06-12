package com.example;

public class Pedido {
    private static int contador = 0;
    private int id;
    private String descripcion;

    public Pedido(String descripcion) {
        this.id = ++contador;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Pedido [ID=" + id + ", Descripcion=" + descripcion + "]";
    }
}


