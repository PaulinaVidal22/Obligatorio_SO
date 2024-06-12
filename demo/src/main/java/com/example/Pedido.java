package com.example;

public class Pedido {
    private static int contador = 0;
    private int id;
    private String descripcion;
    private int tiempoRestante;

    public Pedido(String descripcion, int tiempoProceso) {
        this.id = ++contador;
        this.descripcion = descripcion;
        this.tiempoRestante = tiempoProceso;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void procesar(int timeSlice) {
        tiempoRestante -= timeSlice;
        if (tiempoRestante < 0) {
            tiempoRestante = 0;
        }
    }

    public boolean estaCompleto() {
        return tiempoRestante == 0;
    }

    @Override
    public String toString() {
        return "Pedido [ID=" + id + ", Descripcion=" + descripcion + ", Tiempo restante=" + tiempoRestante + "]";
    }
}



