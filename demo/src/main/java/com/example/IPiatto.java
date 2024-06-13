package com.example;

public interface IPiatto {
    long tempoDiCottura = 0;

    public long getTempoDiCottura();
    
    public String getNome();

    public void run();
}