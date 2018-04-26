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
    public JeuDeLaVie jeu;
    private Boolean playing;
    private JeuDeLaViePlayer player;

    public JeuDeLaVieController() {
        jeu = new JeuDeLaVie();
        playing = false;
        player = new JeuDeLaViePlayer(this);
        player.start();
    }
    
    public void switchEtatCasePlateau(int tailleCellule, int posX, int posY){
        int x = posX/tailleCellule;
        int y = posY/tailleCellule;
        
        if(inBoundsPlateau(x, y)){
            if (jeu.plateau.getEtatCellule(x, y).equals(EtatCellule.MORTE))
                jeu.plateau.setEtatCellule(EtatCellule.VIVANTE, x, y);
            else
                jeu.plateau.setEtatCellule(EtatCellule.MORTE, x, y);
        }
    }
    
    public void switchEtatCaseTampon(int tailleCellule, int posX, int posY){
        int x = posX/tailleCellule;
        int y = posY/tailleCellule;
        
        if(inBoundsTampon(x, y)){
            if (jeu.zoneTampon.getEtatCellule(x, y).equals(EtatCellule.MORTE))
                jeu.zoneTampon.setEtatCellule(EtatCellule.VIVANTE, x, y);
            else
                jeu.zoneTampon.setEtatCellule(EtatCellule.MORTE, x, y);
        }
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
        
        if(inBoundsPlateau(x, y)){
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
    }
    
    public void chargerPlateauLocation(int tailleCellule, int startX, int startY){
        jeu.zoneTampon.reinitialiser();
        int x = startX/tailleCellule;
        int y = startY/tailleCellule;
        
        if(inBoundsPlateau(x, y)){
            for(int i=0;i< (jeu.zoneTampon.getxN());i++){
                for(int j=0;j<jeu.zoneTampon.getyN();j++){
                    if((i+x < jeu.plateau.getxN()) && (j+y < jeu.plateau.getyN()) ){
                        if(jeu.plateau.getEtatCellule(i+x, j+y).equals(EtatCellule.VIVANTE))
                            jeu.zoneTampon.setEtatCellule(EtatCellule.VIVANTE, i, j);
                    }

                }
            }
        }
        
    }
    
    private Boolean inBoundsTampon(int x, int y){
        return (x < jeu.zoneTampon.getxN() && y < jeu.zoneTampon.getyN()
                && x >= 0 && y >= 0);
    }
    
    private Boolean inBoundsPlateau(int x, int y){
        return (x < jeu.plateau.getxN() && y < jeu.plateau.getyN()
                && x >= 0 && y >= 0);
    }
    
    
    public int[] setTaillePlateau(String xText, String yText){
        try{
            int x = Integer.parseInt(xText);
            int y = Integer.parseInt(yText);
            return jeu.redimensionnerPlateau(x, y);
        }catch(NumberFormatException e){};
        int[] res = {jeu.plateau.getxN(), jeu.plateau.getyN()};
        
        return res;
    }
    
    public void randomInit(String probaText){
        int proba = Integer.valueOf(probaText);
        if(proba >= 0 && proba <= 100)
            jeu.initialiserPlateauAleatoire(((float)proba)/100);
    }

    public Boolean isPlaying() {
        return playing;
    }
    
    
}
