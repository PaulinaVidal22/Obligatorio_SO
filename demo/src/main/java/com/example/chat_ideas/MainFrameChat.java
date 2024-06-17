package com.example.chat_ideas;

import javax.swing.*;

import com.example.Fettuccine;
import com.example.IPiatto;
import com.example.LaCosaNostra;
import com.example.Margherita;
import com.example.Ordine;
import com.example.Sorrentino;
import com.example.Tiramisu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameChat extends JFrame {
    private JTextField cantidadTextField;
    private JComboBox<String> tipoComboBox;
    private JButton agregarButton;
    private JButton iniciarButton;
    private JPanel progressPanel;
    private DefaultListModel<Ordine> ordineListModel;
    private JList<Ordine> ordineJList;

    public MainFrameChat() {
        setTitle("La Cosa Nostra");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Tipo de piatto:"));
        String[] tipos = {"Fettuccine", "Tiramisu", "Pizza Margherita", "Sorrentino"};
        tipoComboBox = new JComboBox<>(tipos);
        inputPanel.add(tipoComboBox);

        inputPanel.add(new JLabel("Cantidad a preparar:"));
        cantidadTextField = new JTextField();
        inputPanel.add(cantidadTextField);

        agregarButton = new JButton("Agregar pedido");
        inputPanel.add(agregarButton);

        iniciarButton = new JButton("Iniciar procesamiento");
        inputPanel.add(iniciarButton);

        add(inputPanel, BorderLayout.NORTH);

        // Panel de progreso
        progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(progressPanel), BorderLayout.CENTER);

        // Modelo de lista de pedidos
        ordineListModel = new DefaultListModel<>();
        ordineJList = new JList<>(ordineListModel);
        add(new JScrollPane(ordineJList), BorderLayout.EAST);

        // Acción de botón agregar
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = Integer.parseInt(cantidadTextField.getText());
                String tipo = (String) tipoComboBox.getSelectedItem();
                for (int i = 0; i < cantidad; i++) {
                    agregarPedido(tipo);
                }
            }
        });

        // Acción de botón iniciar
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarProcesamiento();
            }
        });
    }

    private void agregarPedido(String tipo) {
        IPiatto piatto = null;
        switch (tipo) {
            case "Fettuccine":
                piatto = new Fettuccine();
                break;
            case "Tiramisu":
                piatto = new Tiramisu();
                break;
            case "Pizza Margherita":
                piatto = new Margherita();
                break;
            case "Sorrentino":
                piatto = new Sorrentino();
                break;
        }

        if (piatto != null) {
            Ordine ordine = new Ordine(ordineListModel.size() + 1, piatto);
            ordineListModel.addElement(ordine);
            LaCosaNostra.getInstance().hacerPedido(ordine.getId(), piatto);

            // Barra de progreso
            JProgressBar progressBar = new JProgressBar(0, (int) piatto.getTempoDiCottura());
            progressPanel.add(new JLabel("Pedido " + ordine.getId() + ": " + piatto.getNome()));
            progressPanel.add(progressBar);
            piatto.setProgressBar(progressBar);
        }

        revalidate();
        repaint();
    }

    private void iniciarProcesamiento() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LaCosaNostra.getInstance().roundRobin();
            }
        }).start();
    }

    /*public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrameChat().setVisible(true);
            }
        });
        MainFrameChat main = new MainFrameChat();
        main.setVisible(true);
        main.iniciarProcesamiento();
    }*/
}
