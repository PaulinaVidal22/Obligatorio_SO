/**
 * @author Lucía Olviera, Belén Tellechea, Paulina Vidal
 * Fuentes : https://refactoring.guru/es/design-patterns/singleton 
 */

package com.example;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Pentola {
    ArrayList<IPiatto> blocked;
    long alDente;
    private static Pentola instance;

    private Pentola() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.blocked = new ArrayList<IPiatto>();
        this.alDente = 2000; 
    }

    /**
     * Método para obtener la única instancia de Pentola.
     * En caso de no haber una, la crea. 
     * @return única instancia de Pentola.
     */
    public static Pentola getInstance() {
        if (instance == null) {
            instance = new Pentola();
        }
        return instance;
    }
    
    Semaphore mutex = new Semaphore(1);

    /**
     * Método que simula el uso de un RSR (en este caso, Pentola). 
     * Como dos pedidos no pueden estar en la olla a la vez, se 
     * utiliza un semáforo de mutua exclusión. 
     * @param piatto plato que necesita del recurso
     * @throws InterruptedException
     */
    public void consume(IPiatto piatto) throws InterruptedException {
        long elapsedTime = 0;
        blocked.add(piatto);

        mutex.acquire(); //Se toma el único recurso. En caso de estar utilizandose, espera a que se libere el espacio para ser ejecutado.
        blocked.remove(piatto); //se saca el primer elemento que entró a consumir el recurso. 

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
