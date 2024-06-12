package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Stampa {
    
    Scanner scanner;
    private int idCounter = 0;

    public Stampa() {
        this.scanner = new Scanner(System.in);
    }

    public int cantidadPedidos() {
        System.out.println("¿Cuántos procesos/pedidos desea ejecutar?");
        return scanner.nextInt();
    }

    public void hacerPedido() {
        System.out.println("Ingrese 1 para ordenar unos deliciosos fettuccine, o 2 para deleitarse frente a nuestro maravilloso tiramisu: ");
        int opcionPedido = scanner.nextInt();

        switch (opcionPedido) {
            case 1:
                this.idCounter++;
                LaCosaNostra.getInstance().hacerPedido(idCounter, new Fettuccine());
                System.out.println("Pedido número " + idCounter + " agregado a la cola.");
                break;
            case 2:
                this.idCounter++;
                LaCosaNostra.getInstance().hacerPedido(idCounter, new Tiramisu());
                System.out.println("Pedido número " + idCounter + " agregado a la cola.");
                break;
    
            default:
                System.out.println("Opción inválida, seleccione 1 o 2.");
                hacerPedido();
                break;
        }
    }
}
