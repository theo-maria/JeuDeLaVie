package jeuDeLaVie.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Le plateau du jeu de la vie
 */
public class Plateau extends ZoneCellule {
    
    /**
     * Permet d'initier un plateau à partir de sa taille
     * @param xN taille horizontale
     * @param yN taille verticale
     */
    public Plateau(int xN, int yN)
    {
        super(xN,yN);
    }
    
    /**
     * Permet d'initier un plateau à partir de sa taille et un tableau de booleens
     * @param xN taille horizontale
     * @param yN taille verticale
     * @param tableau tableau de booleens
     */
    public Plateau(int xN, int yN, Boolean[][] tableau)
    {
        super(xN,yN, tableau);
    }
    
    /**
     * Permet d'initier un plateau à partir de sa taille et une probabilité
     * @param xN taille horizontale
     * @param yN taille verticale
     * @param p probabilité d'avoir une cellule
     */
    public Plateau(int xN, int yN, float p)
    {
        super(xN,yN);
        Random r = new Random();
        for(int i=0;i<xN;i++){
            for(int j=0;j<yN;j++){
                if(r.nextFloat() < p)
                    setEtatCellule(EtatCellule.VIVANTE, i, j);
            }
        }
    }
    
    /**
     * Permet d'obtenir les celulles voisines à une cellule du plateau
     * @param x position sur l'axe horizontal de la cellule
     * @param y position sur l'axe vertical de la cellule
     * @return les cellules voisines
     */
    private List<Cellule> getCellulesVoisines(int x, int y){
        List<Cellule> liste = new ArrayList<>();
        for(int i=-1;i < 2;i++){
            for(int j=-1;j < 2;j++){
                if(!(i == 0 && j == 0)){
                    try{
                        if(tableauCellule.get(x+i).get(y+j).getEtat() == EtatCellule.VIVANTE)
                            liste.add(tableauCellule.get(x+i).get(y+j));
                    }catch(Exception e){};
                }
            }
        }
        return liste;
    }
    
    /**
     * Permet d'obtenir le nombre de voisins d'une celulle du plateau
     * @param x position sur l'axe horizontal de la cellule
     * @param y position sur l'axe vertical de la cellule
     * @return le nombre de voisins
     */
    private int getNbCellulesVoisines(int x, int y){
        return getCellulesVoisines(x, y).size();
    }
    
    /**
     * Permet au plateau de passer à son état suivant (n+1)
     */
    public void gotoEtatSuivant(){
        ArrayList<ArrayList<Integer>> matriceNbCellulesVivantes = new ArrayList<>();
        for(int i=0;i<xN;i++){
            matriceNbCellulesVivantes.add(new ArrayList<>());
            for(int j=0;j<yN;j++){
                matriceNbCellulesVivantes.get(i).add(getNbCellulesVoisines(i, j));
            }
        }
        
        for(int i=0;i<xN;i++){
            for(int j=0;j<yN;j++){
                int nb = matriceNbCellulesVivantes.get(i).get(j);
                if(nb >= JeuDeLaVie.mortAsphyxie ||
                   nb <= JeuDeLaVie.mortSolitude)
                    setEtatCellule(EtatCellule.MORTE, i, j);
                if(nb >= JeuDeLaVie.vieMin &&
                   nb <= JeuDeLaVie.vieMax)
                    setEtatCellule(EtatCellule.VIVANTE, i, j);
            }
        }
        updateObservers();
    }
    
    /**
     * Permet de vider le plateau de ses cellules
     */
    @Override
    public void reinitialiser(){
        super.reinitialiser();
    }
}
