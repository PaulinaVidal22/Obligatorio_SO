package com.example;

public class Main {
    public static void main(String[] args) {

        Stampa stampa = new Stampa();
        int cantidadOrdine = stampa.cantidadPedidos();

        while (cantidadOrdine > 0) {
            stampa.hacerPedido();
            cantidadOrdine -= 1;
        }

        while (!LaCosaNostra.getInstance().getOrdini().isEmpty()) {
            LaCosaNostra.getInstance().procesarPedidoFIFO();
        }
    }
}
