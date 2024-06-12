package com.example;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Restaurante {
    private ConcurrentLinkedQueue<Pedido> colaPedidos;

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
                // Simula el tiempo de procesamiento
                try {
                    Thread.sleep(1000); // 1 segundo por pedido
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                entregarPedido(pedido);
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



