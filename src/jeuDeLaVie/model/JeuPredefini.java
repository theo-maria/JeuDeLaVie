package jeuDeLaVie.model;

/**
 * Un jeu prédéfini pour la zone tampon
 */
public class JeuPredefini {
    /**
     * Nom du preset
     */
    private String nom;
    /**
     * Tableau de booleens correspondant
     */
    private Boolean[][] tableau;

    /**
     * Permet d'initier une instance de JeuPredefini
     * @param nom le nom du preset
     * @param tableau le tableau de booleens correspondant
     */
    public JeuPredefini(String nom, Boolean[][] tableau) {
        this.nom = nom;
        this.tableau = tableau;
    }
    
    /**
     * Permet de redéfinir l'affichage du jeu prédéfinis
     * @return le nom du jeu
     */
    @Override
    public String toString(){
        return nom;
    }

    /**
     * Permet d'obtenir le tableau de booleens du jeu prédéfini
     * @return le tableau
     */
    public Boolean[][] getTableau() {
        return tableau;
    }
}
