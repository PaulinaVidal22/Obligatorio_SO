/**
 * @author Lucía Olviera, Belén Tellechea, Paulina Vidal
 */

package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI1 extends JFrame {
    private JPanel inputPanel;
    private JPanel ordiniPanel;
    private JTextField quantitaDaPreparare_text;
    private JButton addPiatti_Button;
    private JButton effettuare_Button;
    private DefaultListModel<Ordine> ordineListModel;
    private String selectedPiatto;

    public GUI1() throws IOException {
        setSize(900, 600); //Tamaño de la ventana
        setTitle("La Cosa Nostra"); //Titulo de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Operación de cierre de la ventana
        setLayout(new BorderLayout());

        // Definimos input panel.
        this.inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Seleziona il piatto:"), gbc);

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
        inputPanel.add(new JLabel("Quantità da preparare:"), gbc);

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

    /**
     * Método para crear los botones de selección de platos.
     * Se le añade ActionListener para manejar la acción de 
     * selección del plato. 
     * @param name nombre del botón
     * @param imagePath ruta de la imagen para el botón
     * @return botón con la imagen correspondiente y su nombre
     */
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

    /**
     * Método para agregar una orden al presionar el botón "Aggiungi ordine"
     * @param piatti tipo de plato a agregar.
     */
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
            case "Margherita":
                piatto = new Margherita();
                break;
            case "Sorrentinos":
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
            progressPanel.add(new JLabel("Ordine " + ordine.getId() + ": " + piatto.getNome()), BorderLayout.NORTH);
            progressPanel.add(progressBar, BorderLayout.CENTER);
            ordiniPanel.add(progressPanel);
            piatto.setProgressBar(progressBar);
        }

        revalidate();
        repaint();
    }

    /*
     * Método para limpiar todos los componentes de la ventana actual. 
     */
    private void clearFrameAndShowImage(String imagePath) {
        getContentPane().removeAll();
        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
        add(imageLabel);
        revalidate();
        repaint();
    }

    /**
     * Método que inicia un nuevo hilo para ejecutar el procesamiento
     * de pedidos de LaCosaNostra mediante Round Robin. 
     * Luego de completado este procesamiento, se actualiza la interfaz
     * para mostrar una imagen. 
     */
    public void iniciarProcesamiento() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LaCosaNostra.getInstance().roundRobin();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        clearFrameAndShowImage("exito3.gif"); 
                    }
                });
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GUI1();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}