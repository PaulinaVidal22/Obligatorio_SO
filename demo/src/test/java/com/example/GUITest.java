/*package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class GUITest {
    private GUI1 gui;

    @BeforeEach
    void setUp() throws IOException {
        gui = new GUI1();
    }

    @Test
    void testComponentsPresence() {
        assertNotNull(gui.getContentPane().getComponent(0));
        assertTrue(gui.getContentPane().getComponent(0) instanceof JPanel);

        JPanel inputPanel = (JPanel) gui.getContentPane().getComponent(0);
        assertEquals(3, inputPanel.getComponentCount());

        assertTrue(inputPanel.getComponent(1) instanceof JPanel);
        JPanel buttonPanel = (JPanel) inputPanel.getComponent(1);
        assertEquals(4, buttonPanel.getComponentCount());

        for (int i = 0; i < 4; i++) {
            assertTrue(buttonPanel.getComponent(i) instanceof JButton);
        }
    }

    @Test
    void testAddOrderButton() {
        JTextField quantityField = (JTextField) ((JPanel) gui.getContentPane().getComponent(0)).getComponent(2);
        quantityField.setText("2");

        JButton addOrderButton = (JButton) ((JPanel) gui.getContentPane().getComponent(0)).getComponent(4);
        addOrderButton.doClick();

        assertEquals(2, LaCosaNostra.getInstance().getOrdini().size());
    }

    @Test
    void testStartProcessingButton() {
        JButton startProcessingButton = (JButton) ((JPanel) gui.getContentPane().getComponent(0)).getComponent(5);
        startProcessingButton.doClick();

        assertTrue(LaCosaNostra.getInstance().getOrdini().isEmpty());
    }
}
*/
