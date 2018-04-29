package jeuDeLaVie.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jeuDeLaVie.controller.JeuDeLaVieController;
import jeuDeLaVie.model.JeuPredefini;

/**
 * Permet de gérer les jeux prédéfinis
 */
public class JeuxPredefinisPanel extends JPanel {
    /**
     * Champ de sélection du jeu prédéfini
     */
    private JComboBox jeux;
    /**
     * Controleur du jeu
     */
    private JeuDeLaVieController controller;
    /**
     * Permet de charger le jeu sélectionné dans la zone tampon
     */
    private JButton chargeButton;

    /**
     * Permet d'initier une instance de JeuxPredefinisPanel
     * @param controller le controleur du jeu
     */
    public JeuxPredefinisPanel(JeuDeLaVieController controller) {
        this.controller = controller;
        
        jeux = new JComboBox();
        jeux.insertItemAt("Sélectionnez une forme prédéfinie", 0);
        for(JeuPredefini jeu : controller.jeu.getJeuxPredefinis()){
            jeux.addItem(jeu);
        }
        
        jeux.setSelectedIndex(0);
        
        chargeButton = new JButton("Charger");
        
        Box vBox = Box.createVerticalBox();
        vBox.add(new JLabel("Jeux prédéfinis:"));
        vBox.add(jeux);
        vBox.add(chargeButton);
        
        add(vBox);
        
        chargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jeux.getSelectedIndex() == 0 )
                    controller.jeu.zoneTampon.reinitialiser();
                else
                    controller.jeu.chargerJeuPredefini((JeuPredefini)jeux.getSelectedItem());
            }
        });
    }
    
    
}
