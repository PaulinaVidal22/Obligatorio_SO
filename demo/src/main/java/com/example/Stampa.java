package com.example;

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
        System.out.println("Ingrese 1 para ordenar unos deliciosos fettuccine, 2 para deleitarse frente a nuestro maravilloso tiramisu, o 3 para degustar nuestra querida pizza Margherita: ");
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
            case 3:
                this.idCounter++;
                LaCosaNostra.getInstance().hacerPedido(idCounter, new Margherita());
                break;
                
            default:
                System.out.println("Opción inválida, seleccione 1, 2 o 3.");
                hacerPedido();
                break;
        }
    }

    public void pedidoProcesado(Ordine ordine) {
        System.out.println("El pedido número " + ordine.getId() + " ha sido procesado correctamente.");
    }

    public void comienzoProceso(Ordine ordine) {
        Tiramisu tiramisu = new Tiramisu();
        Fettuccine fettuccine = new Fettuccine();
        Margherita margherita = new Margherita();
        long tempoDiCotturaIniziale = 0;

        if (ordine.getPiatto().getClass().equals(tiramisu.getClass())) {
            tempoDiCotturaIniziale = tiramisu.tempoDiCottura;
        } else if (ordine.getPiatto().getClass().equals(fettuccine.getClass())) {
            tempoDiCotturaIniziale = fettuccine.tempoDiCottura;
        } else if (ordine.getPiatto().getClass().equals(margherita.getClass())) {
            tempoDiCotturaIniziale = margherita.tempoDiCottura;
        }

        if (ordine.getTempoDiCottura() < tempoDiCotturaIniziale) {
            System.out.println("El pedido número " + ordine.getId() + " ha comenzado a procesarse nuevamente.");
        } else {
            System.out.println("El pedido número " + ordine.getId() + " ha comenzado a procesarse.");
        }
    }
}
