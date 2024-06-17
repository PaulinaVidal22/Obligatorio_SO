package com.example;

import javax.swing.JProgressBar;

public interface IPiatto {
    long tempoDiCottura = 0;

    long getTempoDiCottura();
    
    String getNome();

    void run();

    void setProgressBar(JProgressBar progressBar);
}