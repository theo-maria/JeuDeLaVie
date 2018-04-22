/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import jeuDeLaVie.controller.JeuDeLaVieController;
import jeuDeLaVie.model.ZoneCellule;

/**
 *
 * @author tmaria
 */
public class TamponPanel extends JPanel implements Observer, MouseListener {

    private ZoneCellule tampon;
    private final int TAILLE_CELLULE = 8;
    private JeuDeLaVieController controleur;

    public TamponPanel(ZoneCellule tampon, JeuDeLaVieController controleur) {
        this.tampon = tampon;
        this.setSize(tampon.getxN()*TAILLE_CELLULE, tampon.getyN()*TAILLE_CELLULE);
        this.controleur = controleur;
        this.addMouseListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        Line2D line;
        Rectangle2D rectangle;
        
        for(int i=0;i<tampon.getxN();i++){
            for(int j=0;j<tampon.getyN();j++){
                if(tampon.getTableauBooleen().get(i).get(j) == true){
                    rectangle = new Rectangle2D.Float(i*TAILLE_CELLULE, j*TAILLE_CELLULE, TAILLE_CELLULE, TAILLE_CELLULE);
                    g2.setColor(Color.RED);
                    g2.fill(rectangle);
                }
                line = new Line2D.Float(0,i*TAILLE_CELLULE,tampon.getxN()*TAILLE_CELLULE,i*TAILLE_CELLULE);
                g2.setColor(Color.BLACK);
                g2.draw(line);
            }
            line = new Line2D.Float(i*TAILLE_CELLULE,0,i*TAILLE_CELLULE,tampon.getyN()*TAILLE_CELLULE);
            g2.setColor(Color.BLACK);
            g2.draw(line);
        }
        
        line = new Line2D.Float(0,0,tampon.getxN()*TAILLE_CELLULE,0);
        g2.draw(line);
        line = new Line2D.Float(0,0,0,tampon.getyN()*TAILLE_CELLULE);
        g2.draw(line);
        line = new Line2D.Float(0,tampon.getyN()*TAILLE_CELLULE,tampon.getxN()*TAILLE_CELLULE,tampon.getyN()*TAILLE_CELLULE);
        g2.draw(line);
        line = new Line2D.Float(tampon.getxN()*TAILLE_CELLULE,0,tampon.getyN()*TAILLE_CELLULE,tampon.getyN()*TAILLE_CELLULE);
        g2.draw(line);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        Point location = e.getPoint();
        controleur.switchEtatCaseTampon(TAILLE_CELLULE, location.x, location.y);
        repaint();
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
