package com.example;

import javax.swing.JProgressBar;

public class Sorrentino extends Thread implements IPiatto {
    private final String nome = "sorrentino";
    long tempoDiCottura = 3000;
    final long timeOut = LaCosaNostra.getInstance().timeOut;
    long alDente = 2000;
    boolean alDenteDone = false;
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
            progressBar.setValue((int) (3000 - tempoDiCottura));
        }
    }

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
