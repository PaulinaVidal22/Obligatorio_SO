/**
 * @author Lucía Olviera, Belén Tellechea, Paulina Vidal
 */

package com.example;

import javax.swing.JProgressBar;

public class Fettuccine extends Thread implements IPiatto {
    private final String nome = "fettuccine";
    long tempoDiCottura = 6000;
    final long timeOut = LaCosaNostra.getInstance().timeOut;
    long alDente = 4000;
    boolean alDenteDone = false;
    private JProgressBar progressBar;

    /**
     * @return tiempo de cocción del fettuccine
     */
    public long getTempoDiCottura() {
        return tempoDiCottura;
    }

    /**
     * @return nombre del plato
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para setear la barra de progreso de la GUI. 
     */
    public void setProgressBar(JProgressBar progressBar){
        this.progressBar = progressBar;
    }

    /**
     * Método para actualizar la barra de progreso de la GUI.
     */
    private void updateProgressBar(){
        if (progressBar != null){
            progressBar.setValue((int) (6000 - tempoDiCottura));
        }
    }

    /**
     * Método que simula el procesamiento del pedido. 
     * Primero, inicializa el tiempo transcurrido. Luego, el primer
     * bucle se ejecuta mientras el tiempo de cocción y al dente sean 
     * mayores a cero y no se haya superado el tiempo de timeout. Dentro 
     * de él, se actualizan los tiempos correspondientemente al igual que
     * la barra de progreso propia del pedido. 
     * El segundo bucle se encarga de la cocción total. 
     * Al marcar alDenteDone como verdadero, se consume el único recurso 
     * de la olla para simular la utilización de un RSR. 
     */
    public void run() {
        long elapsedTime = 0;

        // Se encarga de e/s
        while ((tempoDiCottura > 0) && (elapsedTime < timeOut) && (alDente > 0)) {
            elapsedTime += 1000;
            tempoDiCottura -= 1000;
            alDente -= 1000;
            updateProgressBar();

            try {
                Thread.sleep(1000); // Simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Se encarga exclusivamente del tiempo total y timeOut
        while ((tempoDiCottura > 0) && (elapsedTime < timeOut) && alDenteDone) {
            elapsedTime += 1000;
            tempoDiCottura -= 1000;
            alDente = 0;
            updateProgressBar();

            try {
                Thread.sleep(1000); // Simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (alDente == 0) {
            alDenteDone = true;
            try {
                Pentola.getInstance().consume(this);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }
        }
    }
}
