/**
 * @author Lucía Olviera, Belén Tellechea, Paulina Vidal
 */

package com.example;

import javax.swing.JProgressBar;

/**
 * Interfaz de platos con sus métodos principales. 
 */
public interface IPiatto {
    long tempoDiCottura = 0;

    long getTempoDiCottura();
    
    String getNome();

    void run();

    void setProgressBar(JProgressBar progressBar);
}