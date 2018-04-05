/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tmaria
 */
public class Plateau extends ZoneCellule {
    
    
    
    public Plateau(int xN, int yN)
    {
        super(xN,yN);
    }
    
    public Plateau(int xN, int yN, List<List<Boolean>> tableau)
    {
        super(xN,yN);
    }
    
    public Plateau(int xN, int yN, float p)
    {
        super(xN,yN);
    }
    
    private List<Cellule> getCellulesVoisines(int x, int y){
        List<Cellule> liste = new ArrayList<>();
        for(int i=-1;i < 2;i++){
            for(int j=-1;j < 2;j++){
                if(!(i == 0 && j == 0)){
                    try{
                        liste.add(tableauCellule.get(x+i).get(y+j));
                    }catch(ArrayIndexOutOfBoundsException e){};
                }
            }
        }
        return liste;
    }
    
    private int getNbCellulesVoisinesVivantes(int x, int y){
        return getCellulesVoisines(x, y).size();
    }
    
    public void gotoEtatSuivant(){
        for(int i=0;i<xN-1;i++){
            for(int j=0;j<yN;j++){
                int nb = getNbCellulesVoisinesVivantes(i, j);
                if(nb >= JeuDeLaVie.mortAsphyxie ||
                   nb <= JeuDeLaVie.mortSolitude)
                    setEtatCellule(EtatCellule.MORTE, i, j);
                if(nb >= JeuDeLaVie.vieMin &&
                   nb <= JeuDeLaVie.vieMax)
                    setEtatCellule(EtatCellule.VIVANTE, i, j);
            }
        }
    }
    
    public void redimensionner(int xN, int yN){
        
    }
}
