package com.example;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class PiattiButton_GUI extends JButton {

    private String relativePath;

    public PiattiButton_GUI(String relativePath, JFrame frame) {
        JButton imageButton =new JButton(new ImageIcon(relativePath));
        this.relativePath = relativePath;

        imageButton.setBounds(100,100,100, 40);
        frame.add(imageButton);
        frame.setSize(300,400);
        //frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public String getPiatti() {
        String[] relative = this.relativePath.split(".");
        String piatti = relative[0];
        return piatti;
    }


    /*public void addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int cantidad = Integer.parseInt(quantitaDaPreparare_text.getText());
            String piatti = (String) menu_opBox.getSelectedItem();
            for (int i = 0; i < cantidad; i++) {
                addOrdini(piatti);
            }
        }
    });*/
}
