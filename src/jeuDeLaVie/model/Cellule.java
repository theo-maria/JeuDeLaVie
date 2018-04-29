package jeuDeLaVie.model;

/**
 * Une cellule dont est composé une zone de cellules
 */
public class Cellule {
    
    /**
     * état de la cellule
     */
    protected EtatCellule etat;
    
    /**
     * Permet d'initier une instance de Cellule
     */
    protected Cellule()
    {
        this.etat = EtatCellule.MORTE;
    }
    
    /**
     * Permet d'initier une instance de cellule à partir de son état
     * @param etat état de le cellule
     */
    protected Cellule(EtatCellule etat)
    {
        this.etat = etat;
    }
    
    /**
     * Permet de définir l'état d'une cellule
     * @param etat état
     */
    protected void setEtat(EtatCellule etat)
    {
        this.etat = etat;
    }
    
    /**
     * Permet d'obtenir l'état d'une cellule
     * @return état
     */
    protected EtatCellule getEtat()
    {
        return this.etat;
    }
}
