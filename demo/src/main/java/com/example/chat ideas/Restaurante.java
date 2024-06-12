package com.example;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Restaurante {
    private ConcurrentLinkedQueue<Pedido> colaPedidos;
    private static final int TIME_SLICE = 1000; // 1 segundo por time slice

    public Restaurante() {
        this.colaPedidos = new ConcurrentLinkedQueue<>();
    }

    public void agregarPedido(Pedido pedido) {
        colaPedidos.add(pedido);
        System.out.println("Pedido agregado: " + pedido);
    }

    public void procesarPedidos() {
        while (!colaPedidos.isEmpty()) {
            Pedido pedido = colaPedidos.poll();
            if (pedido != null) {
                System.out.println("Procesando " + pedido);
                // Procesa el pedido por un time slice
                pedido.procesar(TIME_SLICE);
                try {
                    Thread.sleep(TIME_SLICE); // Simula el tiempo de procesamiento
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (pedido.estaCompleto()) {
                    entregarPedido(pedido);
                } else {
                    // Si no está completo, vuelve a añadirlo al final de la cola
                    colaPedidos.add(pedido);
                }
            }
        }
    }

    private void entregarPedido(Pedido pedido) {
        System.out.println("Entregando " + pedido);
        // Aquí podrías agregar lógica adicional para la entrega del pedido
    }

    public boolean hayPedidos() {
        return !colaPedidos.isEmpty();
    }
}




