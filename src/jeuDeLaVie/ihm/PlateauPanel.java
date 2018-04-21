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
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import jeuDeLaVie.controller.JeuDeLaVieController;
import jeuDeLaVie.model.EtatCellule;
import jeuDeLaVie.model.Plateau;
import jeuDeLaVie.model.ZoneCellule;

/**
 *
 * @author tmaria
 */
public class PlateauPanel extends JPanel implements Observer, MouseWheelListener, MouseListener {
    private Plateau plateau;
    private float tailleCellule;
    private JeuDeLaVieController controleur;
    
    public PlateauPanel(Plateau plateau, JeuDeLaVieController controleur) {
        setMinimumSize(new Dimension(200,200));
        setSize(500,500);
        this.tailleCellule = 5;
        this.plateau = plateau;
        this.controleur = controleur;
        this.addMouseWheelListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        Line2D line;
        Rectangle2D rectangle;
        
        
        for(int i=0;i<plateau.getxN();i++){
            for(int j=0;j<plateau.getyN();j++){
                if(plateau.getTableauBooleen().get(i).get(j) == true){
                    rectangle = new Rectangle2D.Float(i*tailleCellule, j*tailleCellule, tailleCellule, tailleCellule);
                    g2.setColor(Color.RED);
                    g2.fill(rectangle);
                }
                line = new Line2D.Float(0,i*tailleCellule,plateau.getxN()*tailleCellule,i*tailleCellule);
                g2.setColor(Color.BLACK);
                g2.draw(line);
            }
            line = new Line2D.Float(i*tailleCellule,0,i*tailleCellule,plateau.getyN()*tailleCellule);
            g2.setColor(Color.BLACK);
            g2.draw(line);
        }
        
        line = new Line2D.Float(0,0,plateau.getxN()*tailleCellule,0);
        g2.draw(line);
        line = new Line2D.Float(0,0,0,plateau.getyN()*tailleCellule);
        g2.draw(line);
        line = new Line2D.Float(0,plateau.getyN()*tailleCellule,plateau.getxN()*tailleCellule,plateau.getyN()*tailleCellule);
        g2.draw(line);
        line = new Line2D.Float(plateau.getxN()*tailleCellule,0,plateau.getyN()*tailleCellule,plateau.getyN()*tailleCellule);
        g2.draw(line);
    }
    
    
    public void zoom(){
        tailleCellule+=0.5;
    }
    
    public void dezoom(){
        if(plateau.getxN() * (tailleCellule - 0.5) >= 200 )
            tailleCellule-=0.5;
    } 

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() < 0)
            zoom();
        else
            dezoom();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        Point location = e.getPoint();
        controleur.switchEtatCase(tailleCellule, location.x, location.y);
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
