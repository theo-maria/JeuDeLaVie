/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import jeuDeLaVie.controller.JeuDeLaVieController;
import jeuDeLaVie.model.EtatCellule;
import jeuDeLaVie.model.JeuDeLaVie;
import jeuDeLaVie.model.JeuxPredefinisView;
import jeuDeLaVie.model.ZoneCellule;

/**
 *
 * @author tmaria
 */
public class JeuDeLaVieView extends JFrame implements Observer {
    private JeuDeLaVie jeu;
    private JeuDeLaVieController controller;
    
    private EditionView editPanel;
    private ParamsView paramPanel;
    private JButton pauseStartButton;
    private PlateauView plateauPanel;
    private TamponView tamponPanel;
    private JeuxPredefinisView jeuxPredefinisPanel;
    private JButton chargeButton;
    private JButton quitButton;
    
    public JeuDeLaVieView(JeuDeLaVie jeu, JeuDeLaVieController controller) {
        setLocation (100, 100);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        this.jeu = jeu;
        this.controller = controller;

        editPanel = new EditionView();
        paramPanel = new ParamsView();
        pauseStartButton = new JButton();
        plateauPanel = new PlateauView();
        tamponPanel = new TamponView();
        jeuxPredefinisPanel = new JeuxPredefinisView();
        chargeButton = new JButton();
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new QuitButtonListener());
        
        add(quitButton);
        pack();
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
    private class QuitButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    public static void testConsole(){
        JeuDeLaVie jeu = new JeuDeLaVie();
        ((ZoneCellule)jeu.plateau).setEtatCellule(EtatCellule.VIVANTE, 10, 10);
        ((ZoneCellule)jeu.plateau).setEtatCellule(EtatCellule.VIVANTE, 10, 11);
        ((ZoneCellule)jeu.plateau).setEtatCellule(EtatCellule.VIVANTE, 10, 12);
        ((ZoneCellule)jeu.plateau).setEtatCellule(EtatCellule.VIVANTE, 11, 9);
        ((ZoneCellule)jeu.plateau).setEtatCellule(EtatCellule.VIVANTE, 11, 10);
        ((ZoneCellule)jeu.plateau).setEtatCellule(EtatCellule.VIVANTE, 11, 11);
        
        System.out.println("--- ETAT n°0 ---");
        ((ZoneCellule)jeu.plateau).afficherZoneConsole();
        
        for(int i=0;i<5;i++){
            jeu.plateau.gotoEtatSuivant();
            System.out.println("\n\n\n");
            System.out.println("--- ETAT n°" + (i+1) + "---");
            ((ZoneCellule)jeu.plateau).afficherZoneConsole();
        }
    }
    
    public static void main(String [ ] args){
        testConsole();
    }
}
