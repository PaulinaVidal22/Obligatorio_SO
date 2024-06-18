package com.example;

public class Ordine {
    private final int id;
    private IPiatto piatto;

    public Ordine(int ID, IPiatto Piatto) {
        this.id = ID;
        this.piatto = Piatto;
    }

    public long getTempoDiCottura() {
        return piatto.getTempoDiCottura();
    }

    public int getId() {
        return id;
    }

    public IPiatto getPiatto() {
        return this.piatto;
    }

    public void run() {
        piatto.run();
    }

    @Override
    public String toString() {
        return "Pedido" + id;
    }
}
