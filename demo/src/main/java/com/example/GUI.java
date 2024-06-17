package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JPanel inputPanel;
    private JPanel ordiniPanel;
    private JTextField quantitaDaPreparare_text;
    private JComboBox<String> menu_opBox;
    private JButton addPiatti_Button;
    private JButton effettuare_Button;
    private JList<Ordine> ordine_list;
    private DefaultListModel<Ordine> ordineListModel;

    public GUI() {
        setSize(900, 600);
        setTitle("La Cosa Nostra");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Definimos input panel.
        this.inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Tipo de piatto:"));
        String[] piatti = {"Fettuccine", "Tiramisu", "Pizza Margherita", "Sorrentino"};
        this.menu_opBox = new JComboBox<>(piatti);
        inputPanel.add(this.menu_opBox);

        inputPanel.add(new JLabel("Cantidad a preparar:"));
        this.quantitaDaPreparare_text = new JTextField();
        inputPanel.add(this.quantitaDaPreparare_text);

        this.addPiatti_Button = new JButton("Aggiungi ordine");
        inputPanel.add(addPiatti_Button);

        this.effettuare_Button = new JButton("Avviare lèlaborazione");
        inputPanel.add(effettuare_Button);

        add(inputPanel, BorderLayout.NORTH);

        // Definimos ordini panel.
        this.ordiniPanel = new JPanel();
        ordiniPanel.setLayout(new BoxLayout(ordiniPanel, BoxLayout.Y_AXIS)); // .Y_AXIS define que todos los componentes se agregan from top to bottom.
        add(new JScrollPane(ordiniPanel), BorderLayout.CENTER);

        // Lista de pedidos.
        this.ordineListModel = new DefaultListModel<>();
        this.ordine_list = new JList<>(ordineListModel);
        add(new JScrollPane(ordine_list), BorderLayout.EAST);

        // Especificamos acción del botón para agragar ordine (pedidos).
        addPiatti_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = Integer.parseInt(quantitaDaPreparare_text.getText());
                String piatti = (String) menu_opBox.getSelectedItem();
                for (int i = 0; i < cantidad; i++) {
                    addOrdini(piatti);
                }
            }
        });

        // Seteamos acción del botón de iniciar procedimiento.
        effettuare_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarProcesamiento();
            }
        });
    }

    private void addOrdini(String piatti) {
        IPiatto piatto = null;
        switch (piatti) {
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
            ordiniPanel.add(new JLabel("Pedido " + ordine.getId() + ": " + piatto.getNome()));
            ordiniPanel.add(progressBar);
            piatto.setProgressBar(progressBar);
        }

        revalidate(); // Se usa para actualizar la gui cuando se agregan o modifican los componentes del panel.
        repaint(); // Se usa para actualizar la apariencia visual del componente sin modificar su disposición.
    }

    public void iniciarProcesamiento() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LaCosaNostra.getInstance().roundRobin();
            }
        }).start();
    }

    }
