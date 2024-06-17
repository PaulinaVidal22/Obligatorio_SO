package com.example;

import javax.swing.JProgressBar;

public class Tiramisu extends Thread implements IPiatto {
    private final String nome = "tiramisu";
    long tempoDiCottura = 4000;
    final long timeOut = LaCosaNostra.getInstance().timeOut;
    private JProgressBar progressBar;

    public long getTempoDiCottura() {
        return tempoDiCottura;
    }

    public String getNome() {
        return this.nome;
    }

    public void setProgressBar(JProgressBar progressBar){
        this.progressBar = progressBar;
    }

    private void updateProgressBar(){
        if (progressBar != null){
            progressBar.setValue((int) (4000 - tempoDiCottura));
        }
    }

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
