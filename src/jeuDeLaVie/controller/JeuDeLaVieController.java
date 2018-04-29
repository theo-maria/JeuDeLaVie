package jeuDeLaVie.controller;

import java.util.Observable;
import jeuDeLaVie.model.EtatCellule;
import jeuDeLaVie.model.JeuDeLaVie;

/**
 * Permet de gérer le lien entre la vue et le modèle
 */
public class JeuDeLaVieController extends Observable{
    /**
     * L'instance du jeu
     */
    public JeuDeLaVie jeu;
    
    /**
     * Indique si le jeu est en lecture automatique
     */
    private Boolean playing;
    
    /**
     * Permet de gérer la lecture automatique
     */
    private JeuDeLaViePlayer player;

    /**
     * Permet de créer une instance de JeuDeLaVieController
     */
    public JeuDeLaVieController() {
        jeu = new JeuDeLaVie();
        playing = false;
        player = new JeuDeLaViePlayer(this);
        player.start();
    }
    
    /**
     * Permet de changer l'état de la cellule du plateau pointée sur la vue
     * @param tailleCellule le nombre de pixels occupés par une cellule
     * @param posX la position sur l'axe horizontal (en pixels)
     * @param posY la position sur l'axe vertical (en pixels)
     */
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
    
    /**
     * Permet de changer l'état de la cellule de la zone tampon pointée sur la vue
     * @param tailleCellule le nombre de pixels occupés par une cellule
     * @param posX la position sur l'axe horizontal (en pixels)
     * @param posY la position sur l'axe vertical (en pixels)
     */
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
    
    /**
     * Permet démarrer / stopper l'évolution automatique du plateau
     */
    public void switchPlayPause(){
        if(playing)
            playing = false;
        else
            playing = true;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Permet de charger le contenu de la zone tampon dans le plateau
     * @param tailleCellule le nombre de pixels occupés par une cellule
     * @param startX la postion de départ sur l'axe horizontal (en pixels)
     * @param startY la postion de départ sur l'axe vertical (en pixels)
     */
    public void chargerTamponToPlateau(int tailleCellule, int startX, int startY){
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
    
    /**
     * 
     * @param tailleCellule le nombre de pixels occupés par une cellule
     * @param startX la postion de départ sur l'axe horizontal (en pixels)
     * @param startY la postion de départ sur l'axe vertical (en pixels)
     */
    public void chargerPlateauToTampon(int tailleCellule, int startX, int startY){
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
    
    /**
     * Permet de savoir si une position correspond à la taille de la zone tampon
     * @param x position sur l'axe horizontal
     * @param y position sur l'axe vertical
     * @return true si la position correspond, false si elle ne correspond pas
     */
    private Boolean inBoundsTampon(int x, int y){
        return (x < jeu.zoneTampon.getxN() && y < jeu.zoneTampon.getyN()
                && x >= 0 && y >= 0);
    }
    
    /**
     * Permet de savoir si une position correspond à la taille du plateau
     * @param x position sur l'axe horizontal
     * @param y position sur l'axe vertical
     * @return true si la position correspond, false si elle ne correspond pas
     */
    private Boolean inBoundsPlateau(int x, int y){
        return (x < jeu.plateau.getxN() && y < jeu.plateau.getyN()
                && x >= 0 && y >= 0);
    }
    
    /**
     * Permet de redimensionner le plateau
     * @param xText la chaine de caractères correspondant à la position horizontale
     * @param yText la chaine de caractères correspondant à la position verticale
     * @return les dimensions du plateau après redimensionnement
     */
    public int[] setTaillePlateau(String xText, String yText){
        try{
            int x = Integer.parseInt(xText);
            int y = Integer.parseInt(yText);
            return jeu.redimensionnerPlateau(x, y);
        }catch(NumberFormatException e){};
        int[] res = {jeu.plateau.getxN(), jeu.plateau.getyN()};
        
        return res;
    }
    
    /**
     * Permet d'initialiser aléatoirement un nouveau plateau de jeu
     * @param probaText la chaine de caractères correspondant à la probabilité de cellule vivante (en pourcents)
     */
    public void randomInit(String probaText){
        int proba = Integer.valueOf(probaText);
        if(proba >= 0 && proba <= 100)
            jeu.initialiserPlateauAleatoire(((float)proba)/100);
    }

    /**
     * Permet de savoir si la lecture automatique est démarrée
     * @return true si la lecture est démarrée, false si elle ne l'est pas
     */
    public Boolean isPlaying() {
        return playing;
    }
    
    /**
     * Permet de modifier les paramètres de jeu
     * @param solitudeText la chaine de caractères correspondant au paramètre "mort solitude"
     * @param asphyxieText la chaine de caractères correspondant au paramètre "mort asphyxie"
     * @param vieMinText la chaine de caractères correspondant au paramètre "vie min"
     * @param vieMaxText la chaine de caractères correspondant au paramètre "vie max"
     * @return les paramètres après modification
     */
    public String[] changeParams(String solitudeText, String asphyxieText, String vieMinText, String vieMaxText){
        int solitude = Integer.valueOf(solitudeText);
        int asphyxie = Integer.valueOf(asphyxieText);
        int vieMin = Integer.valueOf(vieMinText);
        int vieMax = Integer.valueOf(vieMaxText);
        
        if(solitude >=0 && solitude <= 8)
            JeuDeLaVie.mortSolitude = solitude;
        else
            solitudeText = String.valueOf(JeuDeLaVie.mortSolitude);
        if(asphyxie >=0 && asphyxie <= 8)
            JeuDeLaVie.mortAsphyxie = asphyxie;
        else
            asphyxieText = String.valueOf(JeuDeLaVie.mortAsphyxie);
        if(vieMin >=0 && vieMin <= 8)
            JeuDeLaVie.vieMin = vieMin;
        else
            vieMinText = String.valueOf(JeuDeLaVie.vieMin);
        if(vieMax >=0 && vieMax <= 8)
            JeuDeLaVie.vieMax = vieMax;
        else
            vieMaxText = String.valueOf(JeuDeLaVie.vieMax);
        
        String res[] = {solitudeText, asphyxieText, vieMinText, vieMaxText};
        return res;
    }
}
