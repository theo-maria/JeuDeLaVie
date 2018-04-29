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

/**
 * Permet de gérer la zone tampon
 */
public class TamponPanel extends JPanel implements Observer, MouseListener {

    /**
     * Taille d'une cellule en pixels
     */
    private final int TAILLE_CELLULE = 8;
    /**
     * Le controleur du jeu
     */
    private JeuDeLaVieController controleur;

    /**
     * Permet d'initier une instance de TamponPanel
     * @param controleur le controleur du jeu
     */
    public TamponPanel(JeuDeLaVieController controleur) {
        this.setSize(controleur.jeu.zoneTampon.getxN()*TAILLE_CELLULE, controleur.jeu.zoneTampon.getyN()*TAILLE_CELLULE);
        this.controleur = controleur;
        this.addMouseListener(this);
    }
    
    /**
     * Permet de redéfinir l'affichage du composant
     * @param g l'objet Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        Line2D line;
        Rectangle2D rectangle;
        
        for(int i=0;i<controleur.jeu.zoneTampon.getxN();i++){
            for(int j=0;j<controleur.jeu.zoneTampon.getyN();j++){
                if(controleur.jeu.zoneTampon.getTableauBooleen().get(i).get(j) == true){
                    rectangle = new Rectangle2D.Float(i*TAILLE_CELLULE, j*TAILLE_CELLULE, TAILLE_CELLULE, TAILLE_CELLULE);
                    g2.setColor(Color.RED);
                    g2.fill(rectangle);
                }
                line = new Line2D.Float(0,i*TAILLE_CELLULE,controleur.jeu.zoneTampon.getxN()*TAILLE_CELLULE,i*TAILLE_CELLULE);
                g2.setColor(Color.BLACK);
                g2.draw(line);
            }
            line = new Line2D.Float(i*TAILLE_CELLULE,0,i*TAILLE_CELLULE,controleur.jeu.zoneTampon.getyN()*TAILLE_CELLULE);
            g2.setColor(Color.BLACK);
            g2.draw(line);
        }
        
        line = new Line2D.Float(0,0,controleur.jeu.zoneTampon.getxN()*TAILLE_CELLULE,0);
        g2.draw(line);
        line = new Line2D.Float(0,0,0,controleur.jeu.zoneTampon.getyN()*TAILLE_CELLULE);
        g2.draw(line);
        line = new Line2D.Float(0,controleur.jeu.zoneTampon.getyN()*TAILLE_CELLULE,controleur.jeu.zoneTampon.getxN()*TAILLE_CELLULE,controleur.jeu.zoneTampon.getyN()*TAILLE_CELLULE);
        g2.draw(line);
        line = new Line2D.Float(controleur.jeu.zoneTampon.getxN()*TAILLE_CELLULE,0,controleur.jeu.zoneTampon.getyN()*TAILLE_CELLULE,controleur.jeu.zoneTampon.getyN()*TAILLE_CELLULE);
        g2.draw(line);
    }
    
    /**
     * Permet d'indiquer les actions à effectuer en cas de mise à jour des objets observés
     * @param o objet mis à jour
     * @param arg arguments
     */
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * Permet d'agir sur la cellule située à l'endroit où l'utilisateur a cliqué
     * @param e 
     */
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
