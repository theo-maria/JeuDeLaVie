package jeuDeLaVie.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import jeuDeLaVie.controller.JeuDeLaVieController;

/**
 * Permet de gérer le plateau du jeu
 */
public class PlateauPanel extends JPanel implements Observer, MouseWheelListener, MouseListener, KeyListener {
    /**
     * Taille d'une cellule (en pixels)
     */
    private int tailleCellule;
    /**
     * Le controleur du jeu
     */
    private JeuDeLaVieController controleur;
    /**
     * Indique si la touche 'shift' est pressée par l'utilisateur
     */
    private Boolean shiftPressed = false;
    
    /**
     * Permet d'initier une instance de PlateauPanel
     * @param controleur le controleur du jeu
     */
    public PlateauPanel(JeuDeLaVieController controleur) {
        setMinimumSize(new Dimension(200,200));
        setSize(500,500);
        this.tailleCellule = 5;
        this.controleur = controleur;
        this.addMouseWheelListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        this.requestFocus();
        
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, InputEvent.SHIFT_DOWN_MASK, false), "shiftPressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, 0, true), "shiftReleased");
        
        am.put("shiftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shiftPressed = true;
            }
        });
        
        am.put("shiftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shiftPressed = false;
            }
        });
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
    
    /**
     * Permet de définir les actions à effectuer au moment de l'affichage du composant
     * @param g l'objet Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        Line2D line;
        Rectangle2D rectangle;
        
        ArrayList<ArrayList<Boolean>> tableau = controleur.jeu.plateau.getTableauBooleen();
        for(int i=0;i<controleur.jeu.plateau.getxN();i++){
            for(int j=0;j<controleur.jeu.plateau.getyN();j++){
                if(tableau.get(i).get(j) == true){
                    rectangle = new Rectangle2D.Float(i*tailleCellule, j*tailleCellule, tailleCellule, tailleCellule);
                    g2.setColor(Color.RED);
                    g2.fill(rectangle);
                }
                line = new Line2D.Float(0,j*tailleCellule,controleur.jeu.plateau.getxN()*tailleCellule,j*tailleCellule);
                g2.setColor(Color.BLACK);
                g2.draw(line);
            }
            line = new Line2D.Float(i*tailleCellule,0,i*tailleCellule,controleur.jeu.plateau.getyN()*tailleCellule);
            g2.setColor(Color.BLACK);
            g2.draw(line);
        }
        
        line = new Line2D.Float(0,controleur.jeu.plateau.getyN()*tailleCellule,controleur.jeu.plateau.getxN()*tailleCellule,controleur.jeu.plateau.getyN()*tailleCellule);
        g2.draw(line);
        line = new Line2D.Float(controleur.jeu.plateau.getxN()*tailleCellule,0,controleur.jeu.plateau.getxN()*tailleCellule,controleur.jeu.plateau.getyN()*tailleCellule);
        g2.draw(line);
    }
    
    /**
     * Permet d'effectuer un zoom (augmenter le nombre de pixels par cellule)
     */
    public void zoom(){
        tailleCellule+=1;
    }
    
    /**
     * Permet d'effectuer un dezoom (diminuer le nombre de pixels par cellule)
     */
    public void dezoom(){
        if(controleur.jeu.plateau.getxN() * (tailleCellule - 1) >= 200 )
            tailleCellule-=1;
    } 

    /**
     * Permet de zoomer ou dezoomer suivant les mouvements de la molette
     * @param e event
     */
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

    /**
     * Permet d'effectuer les actions lors des clics souris
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            Point location = e.getPoint();
            controleur.switchEtatCasePlateau(tailleCellule, location.x, location.y);
            repaint();
        }
        else if(e.getButton() == MouseEvent.BUTTON3 && !controleur.isPlaying()){
            if(shiftPressed){
                Point location = e.getPoint();
                controleur.chargerTamponToPlateau(tailleCellule, location.x, location.y);
                repaint();
            }
            else{
                Point location = e.getPoint();
                controleur.chargerPlateauToTampon(tailleCellule, location.x, location.y);
                repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    /**
     * Permet de détecter les appuis sur la touche shift
     * @param e event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            shiftPressed = true;
    }

    /**
     * Permet de détecter le lâché de la touche shift
     * @param e event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            shiftPressed = false;
    }
}
