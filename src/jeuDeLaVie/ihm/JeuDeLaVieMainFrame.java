/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
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
public class JeuDeLaVieMainFrame extends JFrame implements Observer{
    private JeuDeLaVie jeu;
    private JeuDeLaVieController controller;
    
    private EditionPanel editPanel;
    private ParamsPanel paramPanel;
    private JButton pauseStartButton;
    private PlateauPanel plateauPanel;
    private TamponPanel tamponPanel;
    private JeuxPredefinisView jeuxPredefinisPanel;
    private JButton chargeButton;
    private JButton quitButton;
    
    public JeuDeLaVieMainFrame(JeuDeLaVie jeu, JeuDeLaVieController controller) {
        setLocation (100, 100);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        this.jeu = jeu;
        this.controller = controller;

        editPanel = new EditionPanel();
        paramPanel = new ParamsPanel();
        pauseStartButton = new JButton();
        plateauPanel = new PlateauPanel(jeu.plateau, controller);
        tamponPanel = new TamponPanel();
        jeuxPredefinisPanel = new JeuxPredefinisView();
        chargeButton = new JButton("Charger");
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new QuitButtonListener());
        pauseStartButton.addActionListener(new PauseStartListener());
        pauseStartButton.setText("|>");
        
        Box leftBox = Box.createVerticalBox();
        Box rightBox = Box.createVerticalBox();
        
        leftBox.add(editPanel);
        leftBox.add(paramPanel);
        leftBox.add(pauseStartButton);
        
        rightBox.add(tamponPanel);
        rightBox.add(jeuxPredefinisPanel);
        rightBox.add(chargeButton);
        rightBox.add(Box.createGlue());
        rightBox.add(quitButton);
        
        add(leftBox, BorderLayout.WEST);
        add(plateauPanel, BorderLayout.CENTER);
        add(rightBox, BorderLayout.EAST);
        
        setMinimumSize(new Dimension(800,600));
        
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(controller.isPlaying())
            pauseStartButton.setText("||");
        else
            pauseStartButton.setText("|>");
    }
    
    private class QuitButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    private class PauseStartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.switchPlayPause();
        }
    }
    
    public static void main(String [ ] args){
        //testConsole();
        JeuDeLaVie jeu = new JeuDeLaVie();
        JeuDeLaVieController controller = new JeuDeLaVieController(jeu);
        JeuDeLaVieMainFrame jeuView = new JeuDeLaVieMainFrame(jeu, controller);
        jeu.setObservers(jeuView.getPlateauPanel(), jeuView.getTamponPanel());
        controller.addObserver(jeuView);
        jeuView.pack();
    }

    public PlateauPanel getPlateauPanel() {
        return plateauPanel;
    }

    public TamponPanel getTamponPanel() {
        return tamponPanel;
    }

    public JeuDeLaVieController getController() {
        return controller;
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
}
