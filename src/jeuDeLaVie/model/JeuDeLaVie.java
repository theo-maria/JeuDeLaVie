/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.model;

import java.util.Observer;



/**
 *
 * @author tmaria
 */
public class JeuDeLaVie{
    
    private static final int X_PLATEAU_DEFAULT = 40;
    private static final int Y_PLATEAU_DEFAULT = 40;
    private static final int X_PLATEAU_MIN = 40;
    private static final int Y_PLATEAU_MIN = 40;
    private static final int X_TAMPON_DEFAULT = 10;
    private static final int Y_TAMPON_DEFAULT = 10;
    
    private static final int MORT_SOLITUDE_DEFAULT = 1;
    private static final int MORT_ASPHYXIE_DEFAULT = 4;
    private static final int VIE_MIN_DEFAULT = 3;
    private static final int VIE_MAX_DEFAULT = 3;
    
    public static int mortSolitude;
    public static int mortAsphyxie;
    public static int vieMin;
    public static int vieMax;
    
    public Plateau plateau;
    public ZoneCellule zoneTampon;

    public JeuDeLaVie(){
        mortSolitude = MORT_SOLITUDE_DEFAULT;
        mortAsphyxie = MORT_ASPHYXIE_DEFAULT;
        vieMin = VIE_MIN_DEFAULT;
        vieMax = VIE_MAX_DEFAULT;
        
        plateau = new Plateau(X_PLATEAU_DEFAULT, Y_PLATEAU_DEFAULT);
        zoneTampon = new ZoneCellule(X_TAMPON_DEFAULT, Y_TAMPON_DEFAULT);
    }
    
    public void setObservers(Observer plateauObserver, Observer tamponObserver){
        plateau.addObserver(plateauObserver);
        zoneTampon.addObserver(tamponObserver);
    }
}
