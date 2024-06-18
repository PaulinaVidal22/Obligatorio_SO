package com.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {
    private JPanel inputPanel;
    private JPanel ordiniPanel;
    private JTextField quantitaDaPreparare_text;
    private JButton addPiatti_Button;
    private JButton effettuare_Button;
    private DefaultListModel<Ordine> ordineListModel;
    private String selectedPiatto;

    public GUI() throws IOException {
        setSize(900, 600);
        setTitle("La Cosa Nostra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Definimos input panel.
        this.inputPanel = new JPanel();

        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Seleccione el tipo de plato:"), gbc);

        // Panel para los botones de imágenes
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton fettuccineButton = createButton("Fettuccine", "demo\\src\\main\\java\\com\\example\\imagenes\\fettuccine.PNG");
        JButton tiramisuButton = createButton("Tiramisu", "demo\\src\\main\\java\\com\\example\\imagenes\\tiramisu.PNG");
        JButton margheritaButton = createButton("Margherita", "demo\\src\\main\\java\\com\\example\\imagenes\\pizza.PNG");
        JButton sorrentinoButton = createButton("Sorrentinos", "demo\\src\\main\\java\\com\\example\\imagenes\\sorrentinos.PNG");

        buttonPanel.add(fettuccineButton);
        buttonPanel.add(tiramisuButton);
        buttonPanel.add(margheritaButton);
        buttonPanel.add(sorrentinoButton);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(buttonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Cantidad a preparar:"), gbc);

        this.quantitaDaPreparare_text = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(this.quantitaDaPreparare_text, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.addPiatti_Button = new JButton("Aggiungi ordine");
        inputPanel.add(addPiatti_Button, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        this.effettuare_Button = new JButton("Avviare l'elaborazione");
        inputPanel.add(effettuare_Button, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Definimos ordini panel.
        this.ordiniPanel = new JPanel();
        ordiniPanel.setLayout(new BoxLayout(ordiniPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(ordiniPanel), BorderLayout.CENTER);

        // Lista de pedidos (ahora no se muestra).
        this.ordineListModel = new DefaultListModel<>();

        // Especificamos acción del botón para agragar ordini (pedidos).
        addPiatti_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = Integer.parseInt(quantitaDaPreparare_text.getText());
                for (int i = 0; i < cantidad; i++) {
                    addOrdini(selectedPiatto);
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

        setVisible(true);
    }

    private JButton createButton(String name, String imagePath) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(name, scaledIcon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPiatto = name;
            }
        });
        return button;
    }

    private void addOrdini(String piatti) {
        if (piatti == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de plato antes de agregar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
            JPanel progressPanel = new JPanel(new BorderLayout());
            progressPanel.add(new JLabel("Pedido " + ordine.getId() + ": " + piatto.getNome()), BorderLayout.NORTH);
            progressPanel.add(progressBar, BorderLayout.CENTER);
            ordiniPanel.add(progressPanel);
            piatto.setProgressBar(progressBar);
        }

        revalidate();
        repaint();
    }

    private void clearFrameAndShowImage(String imagePath) {
        getContentPane().removeAll();
        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
        add(imageLabel);
        revalidate();
        repaint();
    }

    public void iniciarProcesamiento() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LaCosaNostra.getInstance().roundRobin();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        clearFrameAndShowImage("demo\\src\\main\\java\\com\\example\\imagenes\\exito3.gif"); // Cambia "finalImage.jpg" con la ruta de tu imagen final
                    }
                });
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
