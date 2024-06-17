package com.example;

import javax.swing.JProgressBar;

public class Margherita extends Thread implements IPiatto {
    private final String nome = "pizza Margherita";
    long tempoDiCottura = 8000;
    final long timeOut = LaCosaNostra.getInstance().timeOut;
    long alForno = 4000;
    boolean alFornoDone = false;
    private JProgressBar progressBar;

    public long getTempoDiCottura() {
        return tempoDiCottura;
    }

    public String getNome() {
        return nome;
    }

    public void setProgressBar(JProgressBar progressBar){
        this.progressBar = progressBar;
    }

    private void updateProgressBar(){
        if (progressBar != null){
            progressBar.setValue((int) (8000 - tempoDiCottura));
        }
    }

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
