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
public class Cellule {
    
    protected EtatCellule etat;
    
    protected Cellule()
    {
        this.etat = EtatCellule.VIVANTE;
    }
    
    protected Cellule(EtatCellule etat)
    {
        this.etat = etat;
    }
    
    protected void setEtat(EtatCellule etat)
    {
        this.etat = etat;
    }
    
    protected EtatCellule getEtat()
    {
        return this.etat;
    }
}
