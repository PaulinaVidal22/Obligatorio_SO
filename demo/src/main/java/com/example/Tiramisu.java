package com.example;

public class Tiramisu extends Thread implements IPiatto {
    private final String nome = "tiramisu";
    long tempoDiCottura = 4000;
    final long timeOut = LaCosaNostra.getInstance().timeOut;

    public long getTempoDiCottura() {
        return tempoDiCottura;
    }

    public String getNome() {
        return this.nome;
    }

    public void run() {
        long elapsedTime = 0;
        while ((tempoDiCottura > 0) && (elapsedTime < timeOut)) {
            elapsedTime += 1000;
            tempoDiCottura -= 1000;

            try {
                Thread.sleep(1000); // Simula el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
