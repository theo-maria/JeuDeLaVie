/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.ihm;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import jeuDeLaVie.controller.JeuDeLaVieController;
import jeuDeLaVie.model.JeuDeLaVie;

/**
 *
 * @author tmaria
 */
public class JeuDeLaVieView extends JFrame implements Observer {
    private JeuDeLaVie jeu;
    private JeuDeLaVieController controller;
    
    public JeuDeLaVieView(JeuDeLaVie jeu, JeuDeLaVieController controller) {
        setLocation (100, 100);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        this.jeu = jeu;
        this.controller = controller;
        
        pack();
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
}
