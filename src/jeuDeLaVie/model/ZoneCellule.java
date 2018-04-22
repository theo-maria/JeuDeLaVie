/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author tmaria
 */
public class ZoneCellule extends Observable {
    
    protected int xN;
    protected int yN;
    protected ArrayList<ArrayList<Cellule>> tableauCellule;
    
    public ZoneCellule(int xN, int yN)
    {
        this.xN = xN;
        this.yN = yN;
        
        creerCellules(xN, yN);
    }
    
    protected void creerCellules(int x, int y){
        tableauCellule = new ArrayList<>();
        for(int i=0 ; i<x ; i++){
            tableauCellule.add(new ArrayList<>());
            for(int j=0 ; j<y ; j++){
                tableauCellule.get(i).add(new Cellule());
            }
        }
        this.setChanged();
        this.notifyObservers();
    }
    
    public EtatCellule getEtatCellule(int x, int y){
        return tableauCellule.get(x).get(y).getEtat();
    }
    
    public void setEtatCellule(EtatCellule etat, int x, int y)
    {
        this.tableauCellule.get(x).get(y).setEtat(etat);
        this.setChanged();
        this.notifyObservers();
    }
    
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

    public int getxN() {
        return xN;
    }

    public int getyN() {
        return yN;
    }
    
    public void reinitialiser(){
        for(ArrayList<Cellule> liste : tableauCellule){
            for(Cellule c : liste)
                c.setEtat(EtatCellule.MORTE);
        }
        this.setChanged();
        this.notifyObservers();
    }
}
