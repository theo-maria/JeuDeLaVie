/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.controller;

import java.util.Observable;
import jeuDeLaVie.model.EtatCellule;
import jeuDeLaVie.model.JeuDeLaVie;

/**
 *
 * @author tmaria
 */
public class JeuDeLaVieController extends Observable{
    private JeuDeLaVie jeu;
    private Boolean playing;
    private JeuDeLaViePlayer player;

    public JeuDeLaVieController(JeuDeLaVie jeu) {
        this.jeu = jeu;
        playing = false;
        player = new JeuDeLaViePlayer(jeu.plateau, this);
        player.start();
    }
    
    public void switchEtatCase(float tailleCellule, int posX, int posY){
        int x = (int)(posX/tailleCellule);
        int y = (int)(posY/tailleCellule);
        
        if (jeu.plateau.getEtatCellule(x, y).equals(EtatCellule.MORTE))
            jeu.plateau.setEtatCellule(EtatCellule.VIVANTE, x, y);
        else
            jeu.plateau.setEtatCellule(EtatCellule.MORTE, x, y);
    }
    
    public void switchPlayPause(){
        if(playing)
            playing = false;
        else
            playing = true;
        this.setChanged();
        this.notifyObservers();
    }
    
    public void chargerTampon(){
        
    }
    
    public void setCelluleTampon(){
        
    }
    
    public void setTaillePlateau(){
        
    }
    
    public void resetPlateau(){
        
    }
    
    public void randomInit(){
        
    }

    public Boolean isPlaying() {
        return playing;
    }
    
    
}
