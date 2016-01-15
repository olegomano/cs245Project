/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245project;

import cs245project.JPanels.MainMenuJPanel;
import cs245project.JPanels.HangManJPanel;
import cs245project.JPanels.MainMenuJPanel.OnMainMenuOptionPressed;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author momo-chan
 */
public class RootJPanel extends JPanel implements OnMainMenuOptionPressed, ReturnToMainMenuListener {
    private MainMenuJPanel mainMenu;
    private HangManJPanel hangMan;
    public RootJPanel(){
        super();
        setPreferredSize(new Dimension(600,400));
        mainMenu = new MainMenuJPanel(); 
        hangMan = new HangManJPanel();
        try {
            mainMenu.setBackroundImage("symbol.jpg");
        } catch (IOException ex) {
            Logger.getLogger(RootJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainMenu.setOnMainMenuOptionListener(this);
        add(mainMenu);
        revalidate();
        repaint();
    }
    
    @Override
    public void onReturnToMainMenuSelected(){
        this.removeAll();
        add(mainMenu);
    }

    @Override
    public void onNewGameSelected() {
        System.out.println("New Game Selected");
        remove(mainMenu);  
        add(hangMan);
        revalidate();
        repaint();
    }

    @Override
    public void onHighScoreSelected() {
        System.out.println("On HighScoreSelected");
    }

    @Override
    public void onCreditsSelected() {
        System.out.println("On Credits Selected");
    }
    
}
