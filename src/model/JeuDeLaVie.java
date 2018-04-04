/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author tmaria
 */
public class JeuDeLaVie {
    
    private static final int X_PLATEAU_DEFAULT = 100;
    private static final int Y_PLATEAU_DEFAULT = 100;
    private static final int X_PLATEAU_MIN = 50;
    private static final int Y_PLATEAU_MIN = 50;
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
        
        plateau = new Plateau(100, 100);
        zoneTampon = new ZoneCellule(10, 10);
    }
    
    
    
}
