/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author tmaria
 */
public class ZoneCellule {
    
    protected int xN;
    protected int yN;
    protected ArrayList<ArrayList<Cellule>> tableauCellule;
    
    protected ZoneCellule(int xN, int yN)
    {
        this.xN = xN;
        this.yN = yN;
        
        this.tableauCellule = new ArrayList<ArrayList<Cellule>>();
        
    }
    
    protected void setEtatCellule(EtatCellule etat, int x, int y)
    {
        this.tableauCellule.get(x).get(y).setEtat(etat);
    }
    
    protected ArrayList<ArrayList<Boolean>> getTableauBooleen()
    {
        ArrayList<ArrayList<Boolean>> tabBooleen = new ArrayList<ArrayList<Boolean>>();
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
}
