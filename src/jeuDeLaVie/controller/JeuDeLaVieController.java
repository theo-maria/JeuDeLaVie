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
    
    public void switchEtatCasePlateau(int tailleCellule, int posX, int posY){
        int x = posX/tailleCellule;
        int y = posY/tailleCellule;
        
        if (jeu.plateau.getEtatCellule(x, y).equals(EtatCellule.MORTE))
            jeu.plateau.setEtatCellule(EtatCellule.VIVANTE, x, y);
        else
            jeu.plateau.setEtatCellule(EtatCellule.MORTE, x, y);
    }
    
    public void switchEtatCaseTampon(int tailleCellule, int posX, int posY){
        int x = posX/tailleCellule;
        int y = posY/tailleCellule;
        
        if (jeu.zoneTampon.getEtatCellule(x, y).equals(EtatCellule.MORTE))
            jeu.zoneTampon.setEtatCellule(EtatCellule.VIVANTE, x, y);
        else
            jeu.zoneTampon.setEtatCellule(EtatCellule.MORTE, x, y);
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
        jeu.plateau.reinitialiser();
        for(int i=0;i<jeu.zoneTampon.getxN();i++){
            for(int j=0;j<jeu.zoneTampon.getyN();j++){
                if(jeu.zoneTampon.getEtatCellule(i, j).equals(EtatCellule.VIVANTE))
                    jeu.plateau.setEtatCellule(EtatCellule.VIVANTE, i, j);
            }
        }
    }
    
    public void chargerTamponLocation(int tailleCellule, int startX, int startY){
        int x = startX/tailleCellule;
        int y = startY/tailleCellule;
        for(int i=0;i< (jeu.zoneTampon.getxN());i++){
            for(int j=0;j<jeu.zoneTampon.getyN();j++){
                if((i+x < jeu.plateau.getxN()) && (j+y < jeu.plateau.getyN()) ){
                    if(jeu.zoneTampon.getEtatCellule(i, j).equals(EtatCellule.VIVANTE))
                        jeu.plateau.setEtatCellule(EtatCellule.VIVANTE, i+x, j+y);
                    else
                        jeu.plateau.setEtatCellule(EtatCellule.MORTE, i+x, j+y);
                }
                
            }
        }
    }
    
    public void chargerPlateauLocation(int tailleCellule, int startX, int startY){
        jeu.zoneTampon.reinitialiser();
        /*for(int i=startX/tailleCellule;i<jeu.zoneTampon.getxN();i++){
            for(int j=startY/tailleCellule;j<jeu.zoneTampon.getyN();j++){
                if(jeu.zoneTampon.getEtatCellule(i, j).equals(EtatCellule.VIVANTE))
                    jeu.plateau.setEtatCellule(EtatCellule.VIVANTE, i, j);
            }
        }*/
    }
    
    
    public void setTaillePlateau(){
        
    }
    
    public void randomInit(){
        
    }

    public Boolean isPlaying() {
        return playing;
    }
    
    
}
