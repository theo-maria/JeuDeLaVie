package jeuDeLaVie.model;

/**
 * Etat d'une cellule
 */
public enum EtatCellule {
    
    VIVANTE(true),
    MORTE(false);
    
    /**
     * Boolean indiquant si la cellule est vivante
     */
    private Boolean etat;
    
    /**
     * Permet d'initier une instance de EtatCellule
     * @param etat boolean correspondant
     */
    EtatCellule(Boolean etat)
    {
        this.etat = etat;
    }
    
    /**
     * Permet d'obtenir l'état de la cellule en boolean
     * @return état en boolean
     */
    protected Boolean getEtatBoolean()
    {
        return this.etat;
    }
}
