/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Random;



/**
 *
 * @author tmaria
 */
public class JeuDeLaVie{
    
    private static final int X_PLATEAU_DEFAULT = 100;
    private static final int Y_PLATEAU_DEFAULT = 100;
    private static final int X_PLATEAU_MIN = 40;
    private static final int Y_PLATEAU_MIN = 40;
    private static final int X_TAMPON_DEFAULT = 10;
    private static final int Y_TAMPON_DEFAULT = 10;
    
    private static final int MORT_SOLITUDE_DEFAULT = 1;
    private static final int MORT_ASPHYXIE_DEFAULT = 4;
    private static final int VIE_MIN_DEFAULT = 3;
    private static final int VIE_MAX_DEFAULT = 3;
    
    private List<JeuPredefini> jeuxPredefinis;
    
    public static int mortSolitude;
    public static int mortAsphyxie;
    public static int vieMin;
    public static int vieMax;
    
    public Plateau plateau;
    public ZoneCellule zoneTampon;
    
    private Observer plateauObserver;
    private Observer tamponObserver;

    public JeuDeLaVie(){
        mortSolitude = MORT_SOLITUDE_DEFAULT;
        mortAsphyxie = MORT_ASPHYXIE_DEFAULT;
        vieMin = VIE_MIN_DEFAULT;
        vieMax = VIE_MAX_DEFAULT;
        
        initJeuxPredefinis();
        
        plateau = new Plateau(X_PLATEAU_DEFAULT, Y_PLATEAU_DEFAULT);
        zoneTampon = new ZoneCellule(X_TAMPON_DEFAULT, Y_TAMPON_DEFAULT);
    }
    
    public void setObservers(Observer plateauObserver, Observer tamponObserver){
        this.tamponObserver = tamponObserver;
        this.plateauObserver = plateauObserver;
        plateau.addObserver(plateauObserver);
        zoneTampon.addObserver(tamponObserver);
    }
    
    public int[] redimensionnerPlateau(int xN, int yN){
        if(xN < X_PLATEAU_MIN)
            xN = X_PLATEAU_MIN;
        if(yN < Y_PLATEAU_MIN)
            yN = Y_PLATEAU_MIN;
        
        int diff_xN = (xN - plateau.getxN())/2;
        int diff_yN = (yN - plateau.getyN())/2;
        Plateau newPlateau = new Plateau(xN, yN);
        newPlateau.addObserver(plateauObserver);
        plateau.deleteObserver(plateauObserver);
        if(diff_xN > 0 && diff_yN > 0)
        {
            for(int i=0;i < xN; i++){
                for(int j=0;j < yN;j++){
                    if(i < plateau.getxN() && j < plateau.getyN()){
                    newPlateau.setEtatCellule(plateau.getEtatCellule(i, j), i+diff_xN, j+diff_yN);
                    }else{
                    //newPlateau.setEtatCellule(EtatCellule.MORTE, i, j);
                    }
                }
            }
        }
        else if(diff_xN > 0 && diff_yN < 0)
        {
            for(int i=0;i < xN; i++){
                for(int j=0;j < yN;j++){
                    if(i < plateau.getxN() && j < plateau.getyN()+diff_yN){
                    newPlateau.setEtatCellule(plateau.getEtatCellule(i, j-diff_yN), i+diff_xN, j);
                    }else{
                    //newPlateau.setEtatCellule(EtatCellule.MORTE, i, j);
                    }
                }
            }
        }
        else if(diff_xN < 0 && diff_yN > 0)
        {
            for(int i=0;i < xN; i++){
                for(int j=0;j < yN;j++){
                    if(i < plateau.getxN()+diff_xN && j < plateau.getyN()){
                    newPlateau.setEtatCellule(plateau.getEtatCellule(i-diff_xN, j), i, j+diff_yN);
                    }else{
                    //newPlateau.setEtatCellule(EtatCellule.MORTE, i, j);
                    }
                }
            }
        }
        else if(diff_xN < 0 && diff_yN < 0)
        {
            for(int i=0;i < xN; i++){
                for(int j=0;j < yN;j++){
                    if(i < plateau.getxN()+diff_xN && j < plateau.getyN()+diff_yN){
                    newPlateau.setEtatCellule(plateau.getEtatCellule(i-diff_xN, j-diff_yN), i, j);
                    }else{
                    //newPlateau.setEtatCellule(EtatCellule.MORTE, i, j);
                    }
                }
            }
        }
        
        /*
        for(int i=0;i < xN; i++){
            for(int j=0;j < yN;j++){
                if(i < plateau.getxN() && j < plateau.getyN()){
                    if(!(i+diff_xN < 0) && !(j+diff_yN < 0))
                        newPlateau.setEtatCellule(plateau.getEtatCellule(i, j), i+diff_xN, j+diff_yN);
                    else if(!(i-diff_xN < 0) && !(j-diff_yN < 0))
                        newPlateau.setEtatCellule(plateau.getEtatCellule(i-diff_xN, j-diff_yN), i, j);
                    else if(!(i-diff_xN < 0) && !(j-diff_yN < 0))
                        newPlateau.setEtatCellule(plateau.getEtatCellule(i-diff_xN, j-diff_yN), i, j);
                    else if(!(i-diff_xN < 0) && !(j-diff_yN < 0))
                        newPlateau.setEtatCellule(plateau.getEtatCellule(i-diff_xN, j-diff_yN), i, j);
                    else
                        newPlateau.setEtatCellule(EtatCellule.MORTE, i, j);
                }
            }
        }
*/
        plateau = newPlateau;
        int[] dimensions = {xN, yN};
        return dimensions;
    }
    
    public void initialiserPlateauAleatoire(float proba){
        Plateau newPlateau = new Plateau(plateau.getxN(), plateau.getyN(), proba);
        newPlateau.addObserver(plateauObserver);
        plateau.deleteObserver(plateauObserver);
        plateau = newPlateau;
        plateau.updateObservers();
    }
    
    public void initJeuxPredefinis(){
        jeuxPredefinis = new ArrayList<>();
        
        Boolean[][] jeu1 = {{false, true, false, false, false, false, false, false, false, false},
                            {true, true, true, false, false, false, false, false, false, false},
                            {false, false, false, false, false, false, false, false, false, false},
                            {false, false, false, false, false, false, false, false, false, false},
                            {false, false, false, false, false, false, false, false, false, false},
                            {false, false, false, false, false, false, false, false, false, false},
                            {false, false, false, false, false, false, false, false, false, false},
                            {false, false, false, false, false, false, false, false, false, false},
                            {false, false, false, false, false, false, false, false, true, false},
                            {false, false, false, false, false, false, false, true, true, true}};
        
        Boolean[][] jeu2 = {{true, true, false, true, true, false, true, true, false, false},
                            {true, true, false, true, true, false, true, true, false, false},
                            {false, false, false, false, false, false, false, false, false, false},
                            {true, true, false, true, true, false, true, true, false, false},
                            {true, true, false, true, true, false, true, true, false, false},
                            {false, false, false, false, false, false, false, false, false, false},
                            {true, true, false, true, true, false, true, true, false, false},
                            {true, true, false, true, true, false, true, true, false, false},
                            {false, false, false, false, false, false, false, false, false, false},
                            {false, false, false, false, false, false, false, false, false, false}};
        
        jeuxPredefinis.add(new JeuPredefini("Formes évolutives", jeu1));
        jeuxPredefinis.add(new JeuPredefini("Formes fixes", jeu2));
    }
    
    public void chargerJeuPredefini(JeuPredefini jeuPredefini){
        ZoneCellule newZoneTampon = new ZoneCellule(X_TAMPON_DEFAULT, Y_TAMPON_DEFAULT, jeuPredefini.getTableau());
        zoneTampon.deleteObserver(tamponObserver);
        newZoneTampon.addObserver(tamponObserver);
        zoneTampon = newZoneTampon;
        zoneTampon.updateObservers();
    }

    public List<JeuPredefini> getJeuxPredefinis() {
        return jeuxPredefinis;
    }
    
    
}
