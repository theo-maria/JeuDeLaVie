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
import jeuDeLaVie.model.ZoneCellule;

/**
 * Vue principale du jeu de la vie
 */
public class JeuDeLaVieMainFrame extends JFrame implements Observer{
    /**
     * Controleur du jeu
     */
    private JeuDeLaVieController controller;
    
    /**
     * Panneau d'édition
     */
    private EditionPanel editPanel;
    /**
     * Panneau de paramétrage
     */
    private ParamsPanel paramPanel;
    /**
     * Bouton de démarrage / stoppage de la lecture automatique
     */
    private JButton pauseStartButton;
    /**
     * Panneau du plateau
     */
    private PlateauPanel plateauPanel;
    /**
     * Panneau de la zone tampon
     */
    private TamponPanel tamponPanel;
    /**
     * Panneau des jeux prédéfinis
     */
    private JeuxPredefinisPanel jeuxPredefinisPanel;
    /**
     * Bouton pour quitter le jeu
     */
    private JButton quitButton;
    
    /**
     * Permet d'initier une instance de JeuDeLaVieMainFrame
     * @param controller le controleur du jeu
     */
    public JeuDeLaVieMainFrame(JeuDeLaVieController controller) {
        setLocation (100, 100);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        this.controller = controller;

        editPanel = new EditionPanel(controller);
        paramPanel = new ParamsPanel(controller);
        pauseStartButton = new JButton();
        plateauPanel = new PlateauPanel(controller);
        tamponPanel = new TamponPanel(controller);
        jeuxPredefinisPanel = new JeuxPredefinisPanel(controller);
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
        rightBox.add(Box.createGlue());
        rightBox.add(Box.createGlue());
        rightBox.add(quitButton);
        
        add(leftBox, BorderLayout.WEST);
        add(plateauPanel, BorderLayout.CENTER);
        add(rightBox, BorderLayout.EAST);
        
        setMinimumSize(new Dimension(1000,600));
        
        setVisible(true);
    }

    /**
     * Permet d'indiquer les actions à effectuer en cas de mise à jour des objets observés
     * @param o objet mis à jour
     * @param arg arguments
     */
    @Override
    public void update(Observable o, Object arg) {
        if(controller.isPlaying()){
            pauseStartButton.setText("||");
        }
        else{
            pauseStartButton.setText("|>");
        }
    }
    
    /**
     * ActionListener permettant de quitter 
     */
    private class QuitButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    /**
     * ActionListener permettant de mettre en pause ou démarrer la lecture automatique
     */
    private class PauseStartListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.switchPlayPause();
        }
    }
    
    /**
     * Méthode principale du programme
     * @param args arguments
     */
    public static void main(String [ ] args){
        //testConsole();
        
        JeuDeLaVieController controller = new JeuDeLaVieController();
        JeuDeLaVieMainFrame jeuView = new JeuDeLaVieMainFrame(controller);
        controller.jeu.setObservers(jeuView.getPlateauPanel(), jeuView.getTamponPanel());
        controller.addObserver(jeuView);
        jeuView.pack();
    }

    /**
     * Permet d'obtenir le panneau du plateau
     * @return panneau du plateau
     */
    public PlateauPanel getPlateauPanel() {
        return plateauPanel;
    }

    /**
     * Permet d'obtenir le panneau de la zone tampon
     * @return panneau de la zone tampon
     */
    public TamponPanel getTamponPanel() {
        return tamponPanel;
    }

    /**
     * Permet d'obtenir le controleur du jeu
     * @return controleur
     */
    public JeuDeLaVieController getController() {
        return controller;
    }
    
    /**
     * Permet d'effectuer un test du jeu en console
     */
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
