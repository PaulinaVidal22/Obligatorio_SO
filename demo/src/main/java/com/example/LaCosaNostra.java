package com.example;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public final class LaCosaNostra {
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
    
    public void hacerPedido(int id, IPiatto piatto) {
        Ordine ordine = new Ordine(id, piatto);
        ordini.add(ordine);
    }
}
