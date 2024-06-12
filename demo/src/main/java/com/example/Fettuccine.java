package com.example;

public class Fettuccine extends Thread implements IPiatto {
    long tempoDiCottura = 6000;
    final long timeOut = LaCosaNostra.getInstance().timeOut;

    public long getTempoDiCottura() {
        return tempoDiCottura;
    }

    public void run() {
        long initialTime = System.currentTimeMillis();
        long currentTime = 0;
        long elapsedTime = 0;

        while (elapsedTime < timeOut && elapsedTime < tempoDiCottura) {
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - initialTime;
            tempoDiCottura = tempoDiCottura - elapsedTime;
        }
    }
}
