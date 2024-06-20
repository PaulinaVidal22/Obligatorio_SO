/**
 * @author Lucía Olviera, Belén Tellechea, Paulina Vidal
 * Fuentes : https://refactoring.guru/es/design-patterns/singleton 
 */

package com.example;

import java.util.LinkedList;
import java.util.Queue;

public final class LaCosaNostra {
    private Queue<Ordine> ordini;
    public final long timeOut;
    private static LaCosaNostra instance;

    private LaCosaNostra() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.ordini = new LinkedList<Ordine>();
        this.timeOut = 3000; // tiempo en milisegundos.
    }

    /**
     * Método para obtener la única instancia de LaCosaNostra.
     * En caso de no haber una, la crea. 
     * @return única instancia de LaCosaNostra.
     */
    public static LaCosaNostra getInstance() {
        if (instance == null) {
            instance = new LaCosaNostra();
        }
        return instance;
    }

    /**
     * Método para obtener la cola de pedidos. 
     * @return cola de pedidos
     */
    public Queue<Ordine> getOrdini() {
        return ordini;
    }
    
    /**
     * Método para crear un nuevo pedido (Ordine). 
     * Luego de creado, se lo añade a la cola de pedidos. 
     * @param id entero identificador del pedido
     * @param piatto plato del pedido
     */
    public void hacerPedido(int id, IPiatto piatto) {
        Ordine ordine = new Ordine(id, piatto);
        ordini.add(ordine);
    }

    /**
     * Procesa el pedido al frente de la cola con política 
     * FIFO ("First In, First Out"). 
     * Imprime los mensajes de comienzo y fin de proceso mediante
     * la clase Stampa. 
     */
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

    /**
     * Procesa los pedidos en la cola utilizando la política 
     * Round Robin. Saca el primer pedido de la cola y lo ejecuta. 
     * El bucle se ejecuta mientras la lista de pedidos no esté
     * vacía. 
     * En caso de que un pedido no haya acabado con su tiempo de
     * cocción, lo vuelve a insertar al final de la cola. 
     */
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
