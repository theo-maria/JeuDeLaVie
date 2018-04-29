package jeuDeLaVie.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;



/**
 * Classe principale du modèle du jeu de la vie
 */
public class JeuDeLaVie{
    
    /**
     * Taille horizontale par défaut du plateau
     */
    private static final int X_PLATEAU_DEFAULT = 100;
    /**
     * Taille verticale par défaut du plateau
     */
    private static final int Y_PLATEAU_DEFAULT = 100;
    /**
     * Taille horizontale minimale par défaut du plateau
     */
    private static final int X_PLATEAU_MIN = 40;
    /**
     * Taille verticale minimale par défaut du plateau
     */
    private static final int Y_PLATEAU_MIN = 40;
    /**
     * Taille horizontale par défaut de la zone tampon
     */
    private static final int X_TAMPON_DEFAULT = 10;
    /**
     * Taille verticale par défaut de la zone tampon
     */
    private static final int Y_TAMPON_DEFAULT = 10;
    /**
     * Paramètres par défaut de mort solitude
     */
    private static final int MORT_SOLITUDE_DEFAULT = 1;
    /**
     * Paramètre par défaut de mort asphyxie
     */
    private static final int MORT_ASPHYXIE_DEFAULT = 4;
    /**
     * Paramètre par défaut de vue min
     */
    private static final int VIE_MIN_DEFAULT = 3;
    /**
     * Paramètre par défaut de vie max
     */
    private static final int VIE_MAX_DEFAULT = 3;
    
    /**
     * Jeux prédéfinis
     */
    private List<JeuPredefini> jeuxPredefinis;
    
    /**
     * Paramètre de mort solitude
     */
    public static int mortSolitude;
    /**
     * Paramètre de mort asphyxie
     */
    public static int mortAsphyxie;
    /**
     * Paramètre de vie min
     */
    public static int vieMin;
    /**
     * Paramètre de vie max
     */
    public static int vieMax;
    
    /**
     * Plateau de jeu
     */
    public Plateau plateau;
    /**
     * Zone tampon
     */
    public ZoneCellule zoneTampon;
    
    /**
     * Observateur du plateau
     */
    private Observer plateauObserver;
    /**
     * Observateur de la zone tampon
     */
    private Observer tamponObserver;

    /**
     * Permet d'initier une instance de JeuDeLaVie
     */
    public JeuDeLaVie(){
        mortSolitude = MORT_SOLITUDE_DEFAULT;
        mortAsphyxie = MORT_ASPHYXIE_DEFAULT;
        vieMin = VIE_MIN_DEFAULT;
        vieMax = VIE_MAX_DEFAULT;
        
        initJeuxPredefinis();
        
        plateau = new Plateau(X_PLATEAU_DEFAULT, Y_PLATEAU_DEFAULT);
        zoneTampon = new ZoneCellule(X_TAMPON_DEFAULT, Y_TAMPON_DEFAULT);
    }
    
    /**
     * Permet d'associer les classes observatrices au plateau et à la zone tampon
     * @param plateauObserver l'observateur du plateau
     * @param tamponObserver l'observateur de la zone tampon
     */
    public void setObservers(Observer plateauObserver, Observer tamponObserver){
        this.tamponObserver = tamponObserver;
        this.plateauObserver = plateauObserver;
        plateau.addObserver(plateauObserver);
        zoneTampon.addObserver(tamponObserver);
    }
    
    /**
     * Permet de redimensionner le plateau
     * @param xN taille horizontale
     * @param yN taille verticale
     * @return la taille effective après redimensionnement
     */
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
        
        for(int i=0 ; i < xN ; i++){
            for(int j=0 ; j < yN ; j++){
                if( i - diff_xN >= 0
                        && j - diff_yN >= 0
                        && i < plateau.getxN() + diff_xN
                        && j < plateau.getyN() + diff_yN
                        && plateau.getEtatCellule(i - diff_xN, j - diff_yN) != null)
                    newPlateau.setEtatCellule(plateau.getEtatCellule(i - diff_xN, j - diff_yN), i, j);
                else
                    newPlateau.setEtatCellule(EtatCellule.MORTE, i, j);
            }
        }
        
        plateau = newPlateau;
        plateau.updateObservers();
        int[] dimensions = {xN, yN};
        return dimensions;
    }
    
    /**
     * Permet d'initialiser un plateau aléatoire
     * @param proba probabilité d'avoir une cellule
     */
    public void initialiserPlateauAleatoire(float proba){
        Plateau newPlateau = new Plateau(plateau.getxN(), plateau.getyN(), proba);
        newPlateau.addObserver(plateauObserver);
        plateau.deleteObserver(plateauObserver);
        plateau = newPlateau;
        plateau.updateObservers();
    }
    
    /**
     * Permet de créer les jeux prédéfinis pour la zone tampon
     */
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
    
    /**
     * Permet de charger un jeu prédéfini dans la zone tampon
     * @param jeuPredefini le jeu prédéfini
     */
    public void chargerJeuPredefini(JeuPredefini jeuPredefini){
        ZoneCellule newZoneTampon = new ZoneCellule(X_TAMPON_DEFAULT, Y_TAMPON_DEFAULT, jeuPredefini.getTableau());
        zoneTampon.deleteObserver(tamponObserver);
        newZoneTampon.addObserver(tamponObserver);
        zoneTampon = newZoneTampon;
        zoneTampon.updateObservers();
    }

    /**
     * Permet d'obtenir les jeux prédéfinis
     * @return les jeux prédéfinis
     */
    public List<JeuPredefini> getJeuxPredefinis() {
        return jeuxPredefinis;
    }
    
    
}
