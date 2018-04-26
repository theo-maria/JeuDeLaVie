/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.ihm;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        
        resetButton = new JButton("Reinitialiser");
        vBox.add(Box.createRigidArea(new Dimension(1,20)));
        vBox.add(resetButton);
        
        initProba = new JButton("Initialisation aléatoire");
        vBox.add(Box.createRigidArea(new Dimension(1,20)));
        vBox.add(initProba);
        
        add(vBox);
        
        ActionListener dimensionsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment changer la taille du plateau ?");
                if(confirmation == JOptionPane.YES_OPTION)
                    validerDimensions();
            }
        };
        
        xNField.addActionListener(dimensionsListener);
        yNField.addActionListener(dimensionsListener);
        
        ActionListener resetListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment réinitialiser le plateau ?");
                if(confirmation == JOptionPane.YES_OPTION)
                    controller.jeu.plateau.reinitialiser();
            }
        };
        
        resetButton.addActionListener(resetListener);
        
        ActionListener initProbaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String proba = JOptionPane.showInputDialog("A quelle probabilité voulez vous initialiser le nouveau plateau (en %) ?");
                
                
                int confirmation = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment initialiser aléatoirement un nouveau plateau ?");
                if(confirmation == JOptionPane.YES_OPTION)
                    controller.randomInit(proba);
            }
        };
        
        initProba.addActionListener(initProbaListener);
    }
    
    public void validerDimensions(){
        int[] dim = controller.setTaillePlateau(xNField.getText(), yNField.getText());
        xNField.setText(String.valueOf(dim[0]));
        yNField.setText(String.valueOf(dim[1]));
    }
    
}
