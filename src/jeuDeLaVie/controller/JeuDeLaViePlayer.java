/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import jeuDeLaVie.model.Plateau;

/**
 *
 * @author Theo
 */
public class JeuDeLaViePlayer extends Thread{

    private Plateau plateau;
    private JeuDeLaVieController controller;
    
    public JeuDeLaViePlayer(Plateau plateau, JeuDeLaVieController controller) {
        this.plateau = plateau;
        this.controller = controller;
    }

    
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            if(controller.isPlaying()){
                plateau.gotoEtatSuivant();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JeuDeLaViePlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
