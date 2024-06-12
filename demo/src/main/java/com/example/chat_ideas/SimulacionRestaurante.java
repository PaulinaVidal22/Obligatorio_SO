//package com.example.chat_ideas;

//import java.util.Random;
//import java.util.Scanner;

//public class SimulacionRestaurante {
   /*  public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        Thread productor = new Thread(() -> {
            while (true) {
                try {
                    // Simula la llegada de un pedido aleatorio cada 2-5 segundos
                    Thread.sleep((random.nextInt(4) + 2) * 1000);
                    Pedido pedido = new Pedido("Pedido aleatorio " + (random.nextInt(100) + 1), (random.nextInt(5) + 1) * 1000);
                    restaurante.agregarPedido(pedido);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        productor.start();

        Thread consumidor = new Thread(() -> {
            while (true) {
                if (restaurante.hayPedidos()) {
                    restaurante.procesarPedidos();
                }
                try {
                    // Evita que el hilo consuma demasiado CPU cuando no hay pedidos
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        consumidor.start();

        // Permite agregar pedidos por consola
        while (true) {
            System.out.println("Ingrese la descripción del pedido y el tiempo de procesamiento (en segundos, separado por espacio, o 'salir' para terminar): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("salir")) {
                System.out.println("Terminando la simulación...");
                productor.interrupt();
                consumidor.interrupt();
                break;
            }
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                String descripcion = parts[0];
                int tiempoProceso = Integer.parseInt(parts[1]) * 1000; // Convertimos a milisegundos
                Pedido pedido = new Pedido(descripcion, tiempoProceso);
                restaurante.agregarPedido(pedido);
            } else {
                System.out.println("Entrada inválida. Inténtelo de nuevo.");
            }
        }

        scanner.close();
    }*/
//}



