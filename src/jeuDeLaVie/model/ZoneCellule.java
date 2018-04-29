package jeuDeLaVie.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Une zone contenant un certain nombre de cellules
 */
public class ZoneCellule extends Observable {
    
    /**
     * La taille horizontale
     */
    protected int xN;
    /**
     * La taille verticale
     */
    protected int yN;
    
    /**
     * Les cellules de la zone
     */
    protected ArrayList<ArrayList<Cellule>> tableauCellule;
    
    /**
     * Permet d'initier une ZoneCellule à partir de sa taille
     * @param xN taille horizontale
     * @param yN taille verticale
     */
    public ZoneCellule(int xN, int yN)
    {
        this.xN = xN;
        this.yN = yN;
        
        creerCellules(xN, yN);
    }
    
    /**
     * Permet d'initier une ZoneCellule à partir de sa taille et un tableau de booleens
     * @param xN taille horizontale
     * @param yN taille verticale
     * @param tableau le tableau de booleens
     */
    public ZoneCellule(int xN, int yN, Boolean[][] tableau)
    {
        this(xN,yN);
        for(int i=0;i<xN;i++){
            for(int j=0;j<yN;j++){
                if(tableau[i] != null){
                    if(tableau[j][i] != null){
                        if(tableau[j][i])
                            setEtatCellule(EtatCellule.VIVANTE, i, j);
                    }
                }
            }
        }
        updateObservers();
    }
    
    /**
     * Permet de créer les cellules de la zone
     * @param x taille horizontale
     * @param y taille verticale
     */
    protected void creerCellules(int x, int y){
        tableauCellule = new ArrayList<>();
        for(int i=0 ; i<x ; i++){
            tableauCellule.add(new ArrayList<>());
            for(int j=0 ; j<y ; j++){
                tableauCellule.get(i).add(new Cellule());
            }
        }
        updateObservers();
    }
    
    /**
     * Permet d'obtenir l'état d'une cellule à un emplacement de la zone
     * @param x position horizontale
     * @param y position verticale
     * @return l'état de la cellule
     */
    public EtatCellule getEtatCellule(int x, int y){
        return tableauCellule.get(x).get(y).getEtat();
    }
    
    /**
     * Permet de définir l'état d'une cellule à un emplacement de la zone
     * @param etat état de la cellule
     * @param x position horizontale
     * @param y position verticale
     */
    public void setEtatCellule(EtatCellule etat, int x, int y)
    {
        this.tableauCellule.get(x).get(y).setEtat(etat);
        updateObservers();
    }
    
    /**
     * Permet d'obtenir un tableau de booleens correspondant à la zone
     * @return tableau de booleens
     */
    public ArrayList<ArrayList<Boolean>> getTableauBooleen()
    {
        ArrayList<ArrayList<Boolean>> tabBooleen = new ArrayList<>();
        int x = 0;
        int y = 0;
        for(ArrayList<Cellule> ligneCellule : this.tableauCellule)
        {
            ArrayList<Boolean> ligneBooleen = new ArrayList<>();
            for(Cellule cellule : ligneCellule)
            {
                ligneBooleen.add(y, cellule.getEtat().getEtatBoolean());
                y++;
            }
            tabBooleen.add(x, ligneBooleen);
            y = 0;
            x++;
        }
        return tabBooleen;
    }
    
    /**
     * Permet d'afficher le contenu de la zone en console, à des fins de test
     */
    public void afficherZoneConsole(){
        int cptLigne = 0;
        for(ArrayList<Boolean> ligne : getTableauBooleen()){
            System.out.print(cptLigne + "- ");
            cptLigne++;
            for(Boolean b : ligne){
                if(b)
                    System.out.print("o");
                else
                    System.out.print("-");
            }
            System.out.println("");
        }
    }

    /**
     * Permet d'obtenir la taille horizontale de la zone
     * @return taille horizontale
     */
    public int getxN() {
        return xN;
    }

    /**
     * Permet d'obtenir la taille verticale de la zone
     * @return taille verticale
     */
    public int getyN() {
        return yN;
    }
    
    /**
     * Permet de vider la zone de ses cellules
     */
    public void reinitialiser(){
        for(ArrayList<Cellule> liste : tableauCellule){
            for(Cellule c : liste)
                c.setEtat(EtatCellule.MORTE);
        }
        updateObservers();
    }
    
    /**
     * Permet de prévenir les classes observatrices d'un changement
     */
    public void updateObservers(){
        setChanged();
        notifyObservers();
    }
}
