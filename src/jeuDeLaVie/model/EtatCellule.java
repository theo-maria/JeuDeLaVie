/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.model;

/**
 *
 * @author ngueho
 */
public enum EtatCellule {
    
    VIVANTE(true),
    MORTE(false);
    
    private Boolean etat;
    
    EtatCellule(Boolean etat)
    {
        this.etat = etat;
    }
    
    protected Boolean getEtatBoolean()
    {
        return this.etat;
    }
}
