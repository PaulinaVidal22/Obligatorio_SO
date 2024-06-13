package com.example;

import java.util.LinkedList;
import java.util.Queue;

// Administrador, CPU
public final class LaCosaNostra {
    private Queue<Ordine> ordini;
    public final long timeOut;
    private static LaCosaNostra instance;

    private LaCosaNostra() {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.ordini = new LinkedList<Ordine>();
        this.timeOut = 3000; // tiempo en milliseconds.
    }

    public static LaCosaNostra getInstance() {
        if (instance == null) {
            instance = new LaCosaNostra();
        }
        return instance;
    }

    public Queue<Ordine> getOrdini() {
        return ordini;
    }
    
    public void hacerPedido(int id, IPiatto piatto) {
        Ordine ordine = new Ordine(id, piatto);
        ordini.add(ordine);
    }

    public void procesarPedidoFIFO() {
        Ordine ordine = ordini.poll();

        Stampa stampa = new Stampa();
        stampa.comienzoProceso(ordine);

        long initialTime = System.currentTimeMillis();
        long cookingTime = ordine.getTempoDiCottura()*1000;

        long currentTime = 0;

        while ((currentTime - initialTime) != cookingTime) {
            currentTime = System.currentTimeMillis();
        }
        stampa.pedidoProcesado(ordine);
    }

    public void roundRobin() {
        Stampa stampa = new Stampa();

        while (!ordini.isEmpty()){
            Ordine ordine = ordini.poll();
            stampa.comienzoProceso(ordine);
            ordine.run();

            try {
                Thread.sleep(1000); // Simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (ordine.getTempoDiCottura() > 0) {
                ordini.add(ordine);
            } else {
                stampa.pedidoProcesado(ordine);
            }
        }
    }
}
