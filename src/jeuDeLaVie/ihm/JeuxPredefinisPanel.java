/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jeuDeLaVie.controller.JeuDeLaVieController;
import jeuDeLaVie.model.JeuPredefini;

/**
 *
 * @author Theo
 */
public class JeuxPredefinisPanel extends JPanel {
    private JComboBox jeux;
    private JeuDeLaVieController controller;
    private JButton chargeButton;

    public JeuxPredefinisPanel(JeuDeLaVieController controller) {
        this.controller = controller;
        
        jeux = new JComboBox();
        jeux.insertItemAt("Sélectionnez une forme prédéfinie", 0);
        for(JeuPredefini jeu : controller.jeu.getJeuxPredefinis()){
            jeux.addItem(jeu);
        }
        
        jeux.setSelectedIndex(0);
        
        chargeButton = new JButton("Charger");
        
        Box vBox = Box.createVerticalBox();
        vBox.add(new JLabel("Jeux prédéfinis:"));
        vBox.add(jeux);
        vBox.add(chargeButton);
        
        add(vBox);
        
        chargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jeux.getSelectedIndex() == 0 )
                    controller.jeu.zoneTampon.reinitialiser();
                else
                    controller.jeu.chargerJeuPredefini((JeuPredefini)jeux.getSelectedItem());
            }
        });
    }
    
    
}
