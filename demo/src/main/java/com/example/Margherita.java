/**
 * @author Lucía Olviera, Belén Tellechea, Paulina Vidal
 */

package com.example;

import javax.swing.JProgressBar;

public class Margherita extends Thread implements IPiatto {
    private final String nome = "pizza Margherita";
    long tempoDiCottura = 8000;
    final long timeOut = LaCosaNostra.getInstance().timeOut;
    long alForno = 4000;
    boolean alFornoDone = false;
    private JProgressBar progressBar;

    /**
     * @return tiempo de cocción de la pizza.
     */
    public long getTempoDiCottura() {
        return tempoDiCottura;
    }

    /**
     * @return nombre del plato. 
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
            progressBar.setValue((int) (8000 - tempoDiCottura));
        }
    }

    /**
     * Método que simula el procesamiento del pedido. 
     * Primero, inicializa el tiempo transcurrido. Luego, el primer
     * bucle se ejecuta mientras el tiempo de cocción y al forno sean 
     * mayores a cero y no se haya superado el tiempo de timeout. Dentro 
     * de él, se actualizan los tiempos correspondientemente al igual que
     * la barra de progreso propia del pedido. 
     * El segundo bucle se encarga de la cocción total. 
     */
    public void run() {
        long elapsedTime = 0;

        // Se encarga de e/s
        while ((tempoDiCottura > 0) && (elapsedTime < timeOut) && (alForno > 0)) {
            elapsedTime += 1000;
            tempoDiCottura -= 1000;
            alForno -= 1000;
            updateProgressBar();

            try {
                Thread.sleep(1000); // Simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Se encarga exclusivamente del tiempo total y timeOut
        while ((tempoDiCottura > 0) && (elapsedTime < timeOut) && alFornoDone) {
            elapsedTime += 1000;
            tempoDiCottura -= 1000;
            alForno = 0;
            updateProgressBar();

            try {
                Thread.sleep(1000); // Simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (alForno == 0) alFornoDone = true;
    }
}
