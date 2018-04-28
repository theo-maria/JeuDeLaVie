/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.model;

/**
 *
 * @author Theo
 */
public class JeuPredefini {
    private String nom;
    private Boolean[][] tableau;

    public JeuPredefini(String nom, Boolean[][] tableau) {
        this.nom = nom;
        this.tableau = tableau;
    }
    
    @Override
    public String toString(){
        return nom;
    }

    public Boolean[][] getTableau() {
        return tableau;
    }
    
    
}
