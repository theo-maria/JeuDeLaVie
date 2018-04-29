package jeuDeLaVie.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jeuDeLaVie.controller.JeuDeLaVieController;
import jeuDeLaVie.model.JeuDeLaVie;

/**
 * Permet de gérer les paramètres de jeu
 */
public class ParamsPanel extends JPanel {

    /**
     * Le controleur du jeu
     */
    private JeuDeLaVieController controller;
    /**
     * Le champ du paramètre "mort solitude"
     */
    private JTextField solitudeField;
    /**
     * Le champ du paramètre "mort asphyxie"
     */
    private JTextField asphyxieField;
    /**
     * Le champ du paramètre "vie min"
     */
    private JTextField vieMinField;
    /**
     * Le champ du paramètre "vie max"
     */
    private JTextField vieMaxField;
    
    /**
     * Permet d'initier une instance de ParamsPanel
     * @param controller le controleur du jeu
     */
    public ParamsPanel(JeuDeLaVieController controller) {
        this.controller = controller;
        
        Box vBox = Box.createVerticalBox();
        vBox.add(new JLabel("Paramètres du jeu"));
        vBox.add(new JLabel("Mort solitude:"));
        solitudeField = new JTextField(String.valueOf(JeuDeLaVie.mortSolitude));
        vBox.add(solitudeField);
        vBox.add(new JLabel("Mort asphyxie:"));
        asphyxieField = new JTextField(String.valueOf(JeuDeLaVie.mortAsphyxie));
        vBox.add(asphyxieField);
        vBox.add(new JLabel("Vie min:"));
        vieMinField = new JTextField(String.valueOf(JeuDeLaVie.vieMin));
        vBox.add(vieMinField);
        vBox.add(new JLabel("Vie max:"));
        vieMaxField = new JTextField(String.valueOf(JeuDeLaVie.vieMax));
        vBox.add(vieMaxField);
        add(vBox);
        
        ActionListener paramListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment valider les paramètres ?");
                if(confirmation == JOptionPane.YES_OPTION){
                    String[] params = controller.changeParams(solitudeField.getText(), asphyxieField.getText(), vieMinField.getText(), vieMaxField.getText());
                    solitudeField.setText(params[0]);
                    asphyxieField.setText(params[1]);
                    vieMinField.setText(params[2]);
                    vieMaxField.setText(params[3]);
                }
            }
        };
        
        solitudeField.addActionListener(paramListener);
        asphyxieField.addActionListener(paramListener);
        vieMinField.addActionListener(paramListener);
        vieMaxField.addActionListener(paramListener);
    }
    
}
