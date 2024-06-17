package com.example;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Pentola {
    ArrayList<IPiatto> blocked;
    long alDente;
    private static Pentola instance;

    private Pentola() {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.blocked = new ArrayList<IPiatto>();
        this.alDente = 2000; // tiempo en milliseconds.
    }

    public static Pentola getInstance() {
        if (instance == null) {
            instance = new Pentola();
        }
        return instance;
    }
    
    Semaphore mutex = new Semaphore(1);

    public void consume(IPiatto piatto) throws InterruptedException {
        long elapsedTime = 0;
        blocked.add(piatto);

        mutex.acquire();
        blocked.remove(piatto);

        while (alDente > 0) {
            elapsedTime += 1000;
            alDente -= 1000;

            try {
                Thread.sleep(1000); // Simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mutex.release();
    }
}
