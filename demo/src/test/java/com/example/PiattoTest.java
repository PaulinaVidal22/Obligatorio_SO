package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PiattoTest {

    @Test
    void testFettuccine() {
        IPiatto fettuccine = new Fettuccine();
        assertEquals("fettuccine", fettuccine.getNome());
        assertEquals(6000, fettuccine.getTempoDiCottura());
    }

    @Test
    void testTiramisu() {
        IPiatto tiramisu = new Tiramisu();
        assertEquals("tiramisu", tiramisu.getNome());
        assertEquals(4000, tiramisu.getTempoDiCottura());
    }

    @Test
    void testMargherita() {
        IPiatto margherita = new Margherita();
        assertEquals("pizza Margherita", margherita.getNome());
        assertEquals(8000, margherita.getTempoDiCottura());
    }

    @Test
    void testSorrentino() {
        IPiatto sorrentino = new Sorrentino();
        assertEquals("sorrentinos", sorrentino.getNome());
        assertEquals(3000, sorrentino.getTempoDiCottura());
    }
}

