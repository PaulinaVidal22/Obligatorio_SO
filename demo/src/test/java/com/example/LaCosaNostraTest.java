package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LaCosaNostraTest {
    private LaCosaNostra laCosaNostra;

    @BeforeEach
    void setUp() {
        laCosaNostra = LaCosaNostra.getInstance();
        laCosaNostra.getOrdini().clear(); // Limpiamos la cola de pedidos antes de cada prueba
    }

    @Test
    void testHacerPedido() {
        IPiatto piatto = new Fettuccine();
        laCosaNostra.hacerPedido(1, piatto);

        Queue<Ordine> ordini = laCosaNostra.getOrdini();
        assertEquals(1, ordini.size());

        Ordine ordine = ordini.peek();
        assertNotNull(ordine);
        assertEquals(1, ordine.getId());
        assertEquals(piatto, ordine.getPiatto());
    }

    @Test
    void testRoundRobin() {
        IPiatto piatto1 = new Fettuccine();
        IPiatto piatto2 = new Tiramisu();
        laCosaNostra.hacerPedido(1, piatto1);
        laCosaNostra.hacerPedido(2, piatto2);

        laCosaNostra.roundRobin();

        assertTrue(laCosaNostra.getOrdini().isEmpty());
    }
}
