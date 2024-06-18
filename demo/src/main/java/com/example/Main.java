package com.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        /*Stampa stampa = new Stampa();
        int cantidadOrdine = stampa.cantidadPedidos();

        while (cantidadOrdine > 0) {
            stampa.hacerPedido();
            cantidadOrdine -= 1;
        }*/

        // LaCosaNostra con política FIFO
        /*while (!LaCosaNostra.getInstance().getOrdini().isEmpty()) {
            LaCosaNostra.getInstance().procesarPedidoFIFO();
        }*/

        // LaCosaNostra con política Round Robin
        //LaCosaNostra.getInstance().roundRobin();

        // Añadiendo GUI
        GUI guiiiiii;
        try {
            guiiiiii = new GUI();
            guiiiiii.setVisible(true);
            guiiiiii.iniciarProcesamiento();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
