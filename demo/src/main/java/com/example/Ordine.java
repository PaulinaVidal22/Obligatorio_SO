/**
 * @author Lucía Olviera, Belén Tellechea, Paulina Vidal
 */

package com.example;

public class Ordine {
    private final int id;
    private IPiatto piatto;

    public Ordine(int ID, IPiatto Piatto) {
        this.id = ID;
        this.piatto = Piatto;
    }

    /**
     * Método que retorna el tiempo de cocción drl plato asociado al pedido. 
     * @return tiempo de cocción del plato
     */
    public long getTempoDiCottura() {
        return piatto.getTempoDiCottura();
    }

    /**
     * Método que retorna el id del pedido
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Método que retorna el plato asociado al pedido.
     * @return plato
     */
    public IPiatto getPiatto() {
        return this.piatto;
    }

    /**
     * Método que invoca al método run del plato para 
     * simular su cocción. 
     */
    public void run() {
        piatto.run();
    }

    @Override
    public String toString() {
        return "Pedido" + id;
    }
}
