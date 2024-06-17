package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.management.openmbean.InvalidOpenTypeException;

public class Stampa {
    
    Scanner scanner;
    private int idCounter = 0;

    public Stampa() {
        this.scanner = new Scanner(System.in);
    }

    public int cantidadPedidos() {
        System.out.println("¿Cuántos procesos/pedidos desea ejecutar?");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Dígito inválido, ingrese un número entero por favor :)");
            return 0;
        }
    }

    public void hacerPedido() {
        System.out.println("Ingrese... \n 1 para ordenar unos deliciosos fettuccine \n 2 para deleitarse frente a nuestro maravilloso tiramisu \n 3 para degustar nuestra querida pizza Margherita \n 4 para asommbrarse con nuestros equisitos sorrentinos ");
        int opcionPedido = scanner.nextInt();

        switch (opcionPedido) {
            case 1:
                this.idCounter++;
                LaCosaNostra.getInstance().hacerPedido(idCounter, new Fettuccine());
                System.out.println("Pedido número " + idCounter + "de fettuccine agregado a la cola.");
                break;
            case 2:
                this.idCounter++;
                LaCosaNostra.getInstance().hacerPedido(idCounter, new Tiramisu());
                System.out.println("Pedido número " + idCounter + "de tiramisu agregado a la cola.");
                break;
            case 3:
                this.idCounter++;
                LaCosaNostra.getInstance().hacerPedido(idCounter, new Margherita());
                System.out.println("Pedido número " + idCounter + "de pizza Margherita agregado a la cola.");
                break;

            case 4:
                this.idCounter++;
                LaCosaNostra.getInstance().hacerPedido(idCounter, new Sorrentino());
                System.out.println("Pedido número " + idCounter + "de sorrentino agregado a la cola.");
                break;

            default:
                System.out.println("Opción inválida, seleccione 1, 2, 3 o 4.");
                hacerPedido();
                break;
        }
    }

    public void pedidoProcesado(Ordine ordine) {
        System.out.println("El pedido número " + ordine.getId() + " de " + ordine.getPiatto().getNome() + " ha sido procesado correctamente.");
    }

    public void comienzoProceso(Ordine ordine) {
        Tiramisu tiramisu = new Tiramisu();
        Fettuccine fettuccine = new Fettuccine();
        Margherita margherita = new Margherita();
        Sorrentino sorrentino = new Sorrentino();
        long tempoDiCotturaIniziale = 0;

        if (ordine.getPiatto().getClass().equals(tiramisu.getClass())) {
            tempoDiCotturaIniziale = tiramisu.tempoDiCottura;
        } else if (ordine.getPiatto().getClass().equals(fettuccine.getClass())) {
            tempoDiCotturaIniziale = fettuccine.tempoDiCottura;
        } else if (ordine.getPiatto().getClass().equals(margherita.getClass())) {
            tempoDiCotturaIniziale = margherita.tempoDiCottura;
        } else if (ordine.getPiatto().getClass().equals(sorrentino.getClass())) {
            tempoDiCotturaIniziale = sorrentino.tempoDiCottura;
        }

        if (ordine.getTempoDiCottura() < tempoDiCotturaIniziale) {
            System.out.println("El pedido número " + ordine.getId() + " de " + ordine.getPiatto().getNome() + " ha comenzado a procesarse nuevamente.");
        } else {
            System.out.println("El pedido número " + ordine.getId() + " de " + ordine.getPiatto().getNome() + " ha comenzado a procesarse.");
        }
    }
}
