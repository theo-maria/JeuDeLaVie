/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.ihm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jeuDeLaVie.controller.JeuDeLaVieController;

/**
 *
 * @author tmaria
 */
public class EditionPanel extends JPanel {

    private JeuDeLaVieController controller;
    
    private JTextField xNField;
    private JTextField yNField;
    
    private JButton resetButton;
    
    private JButton initProba;
    
    public EditionPanel(JeuDeLaVieController controller) {
        this.controller = controller;
        
        Box vBox = Box.createVerticalBox();
        
        vBox.add(new JLabel("Edition du plateau"));
        vBox.add(new JLabel("x:"));
        xNField = new JTextField(String.valueOf(controller.jeu.plateau.getxN()));
        vBox.add(xNField);
        vBox.add(new JLabel("y:"));
        yNField = new JTextField(String.valueOf(controller.jeu.plateau.getyN()));
        vBox.add(yNField);
        
        add(vBox);
        
        xNField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validerDimensions();
            }
        });
        yNField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validerDimensions();
            }
        });
    }
    
    public void validerDimensions(){
        int[] dim = controller.setTaillePlateau(xNField.getText(), yNField.getText());
        xNField.setText(String.valueOf(dim[0]));
        yNField.setText(String.valueOf(dim[1]));
    }
    
}
