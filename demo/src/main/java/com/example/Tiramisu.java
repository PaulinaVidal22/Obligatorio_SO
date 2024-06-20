/**
 * @author Lucía Olviera, Belén Tellechea, Paulina Vidal
 */

package com.example;

import javax.swing.JProgressBar;

public class Tiramisu extends Thread implements IPiatto {
    private final String nome = "tiramisu";
    long tempoDiCottura = 4000;
    final long timeOut = LaCosaNostra.getInstance().timeOut;
    private JProgressBar progressBar;

    /**
     * @return tiempo de cocción del tiramisu
     */
    public long getTempoDiCottura() {
        return tempoDiCottura;
    }

    /**
     * @return nombre del plato. 
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Método que setea la barra de progreso de la GUI.
     */
    public void setProgressBar(JProgressBar progressBar){
        this.progressBar = progressBar;
    }

    /**
     * Método que actualiza la barra de progreso de la GUI.
     */
    private void updateProgressBar(){
        if (progressBar != null){
            progressBar.setValue((int) (4000 - tempoDiCottura));
        }
    }

    /**
     * Método que simula el procesamiento del pedido. 
     * El bucle se encarga de la cocción total. 
     */
    public void run() {
        long elapsedTime = 0;
        while ((tempoDiCottura > 0) && (elapsedTime < timeOut)) {
            elapsedTime += 1000;
            tempoDiCottura -= 1000;
            updateProgressBar();

            try {
                Thread.sleep(1000); // Simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
