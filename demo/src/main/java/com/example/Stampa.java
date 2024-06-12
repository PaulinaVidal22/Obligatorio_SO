package com.example;

import java.io.BufferedReader;
import java.io.IOException;

public class Stampa {
    
    private BufferedReader reader;
    private int idCounter = 0;

    public void hacerPedido() {
        System.out.println("Ingrese 1 para ordenar unos deliciosos fettuccine, o 2 para deleitarse frente a nuestro maravilloso tiramisu: ");
        int opcionPedido = 0;
        try {
            opcionPedido = reader.read();
        } catch (IOException e) {
            System.out.println("Excepción al leer línea.");
        }

        if (opcionPedido == 1) {
            Ordine ordine = new Ordine(idCounter++, new Fettuccine());
            System.out.println("Pedido número " + idCounter + " agregado a la cola.");
        } else if (opcionPedido == 2) {
            Ordine ordine = new Ordine(idCounter++, new Tiramisu());
            
            System.out.println("Pedido número " + idCounter + " agregado a la cola.");
        } else {
            System.out.println("Opción inválida, seleccione 1 o 2.");
            hacerPedido();
        }
    }
}
