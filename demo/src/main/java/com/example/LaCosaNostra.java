package com.example;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public final class LaCosaNostra extends Thread {
    private Queue<Ordine> ordini;

    private static LaCosaNostra instance;

    private LaCosaNostra() {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.ordini = new LinkedList<Ordine>();
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

    public void run() {
        while (true) {
            procesarPedidoFIFO();
        }
    }
}
