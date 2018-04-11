/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import jeuDeLaVie.model.Plateau;

/**
 *
 * @author tmaria
 */
public class PlateauPanel extends JPanel implements Observer {
    private Plateau plateau;
    
    public PlateauPanel(Plateau plateau) {
        setMinimumSize(new Dimension(200,200));
        setSize(200,200);
        this.plateau = plateau;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        Line2D line;
        
        for(int i=0;i<plateau.getxN();i++){
            line = new Line2D.Float(i*5,0,i*5,plateau.getyN()*5);
            g2.draw(line);
        }
        
        for(int i=0;i<plateau.getyN();i++){
            line = new Line2D.Float(0,i*5,plateau.getxN()*5,i*5);
            g2.draw(line);
        }
        
        line = new Line2D.Float(0,0,plateau.getxN()*5,0);
        g2.draw(line);
        line = new Line2D.Float(0,0,0,plateau.getyN()*5);
        g2.draw(line);
        line = new Line2D.Float(0,plateau.getyN()*5,plateau.getxN()*5,plateau.getyN()*5);
        g2.draw(line);
        line = new Line2D.Float(plateau.getxN()*5,0,plateau.getyN()*5,plateau.getyN()*5);
        g2.draw(line);
    }
}
