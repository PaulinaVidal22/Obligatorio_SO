package com.example;

public class Fettuccine extends Thread implements IPiatto {
    long tempoDiCottura = 6000;
    final long timeOut = LaCosaNostra.getInstance().timeOut;
    long alDente = 4000;
    boolean alDenteDone = false;

    public long getTempoDiCottura() {
        return tempoDiCottura;
    }

    public void run() {
        //long initialTime = System.currentTimeMillis();
        //long currentTime = initialTime;
        long elapsedTime = 0;

        // Se encarga de e/s
        while ((tempoDiCottura > 0) && (elapsedTime < timeOut) && (alDente > 0)) {
            elapsedTime += 1000;
            tempoDiCottura -= 1000;
            alDente -= 1000;
        }

        // Se encarga exclusivamente del tiempo total y timeOut
        while ((tempoDiCottura > 0) && (elapsedTime < timeOut) && alDenteDone) {
            elapsedTime += 1000;
            tempoDiCottura -= 1000;
            alDente = 0;
        }

        if (alDente == 0) alDenteDone = true;

        try {
            Thread.sleep(elapsedTime); // Simula el tiempo de procesamiento
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Se encarga de e/s
        /*while (elapsedTime < timeOut && elapsedTime < alDente) {
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - initialTime;
            tempoDiCottura = tempoDiCottura - elapsedTime;
            alDente = alDente - elapsedTime;
        }
        alDente = 0;

        // Se encarga exclusivamente del tiempo total y timeOut
        while (elapsedTime < timeOut && elapsedTime < tempoDiCottura) {
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - initialTime;
            tempoDiCottura = tempoDiCottura - elapsedTime;
        }*/
    }
}
