package com.example;

public class Ordine {
    private final int id;
    private IPiatto piatto;
    private float tempoDiCottura;

    public Ordine(int ID, IPiatto Piatto) {
        this.id = ID;
        this.piatto = Piatto;
        this.tempoDiCottura = Piatto.getTempoDiCottura();
    }
}
