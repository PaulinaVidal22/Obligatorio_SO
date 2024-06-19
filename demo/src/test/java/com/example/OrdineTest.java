package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdineTest {
    private Ordine ordine;
    private IPiatto piatto;

    @BeforeEach
    void setUp() {
        piatto = new Fettuccine();
        ordine = new Ordine(1, piatto);
    }

    @Test
    void testGetId() {
        assertEquals(1, ordine.getId());
    }

    @Test
    void testGetPiatto() {
        assertEquals(piatto, ordine.getPiatto());
    }
}