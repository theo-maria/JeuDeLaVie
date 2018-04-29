package jeuDeLaVie.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Permet de lancer une lecture automatique du jeu de la vie
 */
public class JeuDeLaViePlayer extends Thread{

    /**
     * Le controleur du jeu
     */
    private JeuDeLaVieController controller;
    
    /**
     * Permet d'initier une instance de JeuDeLaViePlayer
     * @param controller le controleur du jeu
     */
    public JeuDeLaViePlayer(JeuDeLaVieController controller) {
        this.controller = controller;
    }

    
    /**
     * Permet de lancer le thread de la lecture automatique
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            if(controller.isPlaying()){
                controller.jeu.plateau.gotoEtatSuivant();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JeuDeLaViePlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
