package com.example;

public class Tiramisu extends Thread implements IPiatto {
    long tempoDiCottura = 4000;
    final long timeOut = LaCosaNostra.getInstance().timeOut;

    public long getTempoDiCottura() {
        return tempoDiCottura;
    }

    public void run() {
        /*long initialTime = System.currentTimeMillis();
        long currentTime = initialTime;
        long elapsedTime = 0;

        while (elapsedTime < timeOut && elapsedTime < tempoDiCottura) {
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - initialTime;
            tempoDiCottura = tempoDiCottura - elapsedTime;
        }*/
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
