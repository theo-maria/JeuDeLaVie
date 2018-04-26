/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jeuDeLaVie.controller.JeuDeLaVieController;
import jeuDeLaVie.model.JeuDeLaVie;

/**
 *
 * @author tmaria
 */
public class ParamsPanel extends JPanel {

    private JeuDeLaVieController controller;
    private JTextField solitudeField;
    private JTextField asphyxieField;
    private JTextField vieMinField;
    private JTextField vieMaxField;
    
    public ParamsPanel(JeuDeLaVieController controller) {
        this.controller = controller;
        
        Box vBox = Box.createVerticalBox();
        vBox.add(new JLabel("Paramètres du jeu"));
        vBox.add(new JLabel("Mort solitude:"));
        solitudeField = new JTextField(String.valueOf(JeuDeLaVie.mortSolitude));
        vBox.add(solitudeField);
        vBox.add(new JLabel("Mort asphyxie:"));
        asphyxieField = new JTextField(String.valueOf(JeuDeLaVie.mortAsphyxie));
        vBox.add(asphyxieField);
        vBox.add(new JLabel("Vie min:"));
        vieMinField = new JTextField(String.valueOf(JeuDeLaVie.vieMin));
        vBox.add(vieMinField);
        vBox.add(new JLabel("Vie max:"));
        vieMaxField = new JTextField(String.valueOf(JeuDeLaVie.vieMax));
        vBox.add(vieMaxField);
        add(vBox);
        
        ActionListener paramListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment valider les paramètres ?");
                if(confirmation == JOptionPane.YES_OPTION)
                    controller.changeParams(solitudeField.getText(), asphyxieField.getText(), vieMinField.getText(), vieMaxField.getText());
            }
        };
        
        solitudeField.addActionListener(paramListener);
        asphyxieField.addActionListener(paramListener);
        vieMinField.addActionListener(paramListener);
        vieMaxField.addActionListener(paramListener);
    }
    
}
