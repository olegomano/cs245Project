/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245project;

import cs245project.JPanels.DisplayInfoJPanel;
import cs245project.JPanels.MainMenuJPanel;
import cs245project.JPanels.HangManJPanel;
import cs245project.JPanels.HighScoreJPanel;
import cs245project.JPanels.CreditsJPanel;
import cs245project.JPanels.HangManJPanel.HangManStateListener;
import cs245project.JPanels.MainMenuJPanel.OnMainMenuOptionPressed;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author momo-chan
 */
public class RootJPanel extends JPanel implements OnMainMenuOptionPressed, ReturnToMainMenuListener, HangManStateListener {
    private DisplayInfoJPanel displayInfo;
    private MainMenuJPanel mainMenu;
    private HangManJPanel hangMan;
    private HighScoreJPanel highScore;
    private CreditsJPanel credits;
    
    public RootJPanel(){
        super();
        setPreferredSize(new Dimension(600,400));
        displayInfo = new DisplayInfoJPanel();
        mainMenu = new MainMenuJPanel(); 
        hangMan = new HangManJPanel();
        highScore = new HighScoreJPanel();
        credits = new CreditsJPanel();
        try {
            mainMenu.setBackroundImage("symbol.jpg");
        } catch (IOException ex) {
            Logger.getLogger(RootJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainMenu.setOnMainMenuOptionListener(this);
        highScore.setReturnMainMenuSelectedListener(this);
        credits.setReturnMainMenuSelectedListener(this);
        hangMan.setHangmanStateListener(this);
  
        add(mainMenu);
        revalidate();
        repaint();
    }
    
    @Override
    public void onReturnToMainMenuSelected(){
        System.out.println("Returned to main menu");
        this.removeAll();
        add(mainMenu);
        revalidate();
        repaint();
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
        remove(mainMenu);
        add(highScore);
        revalidate();
        repaint();
    }

    @Override
    public void onCreditsSelected() {
        System.out.println("On Credits Selected");
        remove(mainMenu);
        add(credits);
        revalidate();
        repaint();
    }

    @Override
    public void onGameReset() {

    }

    @Override
    public void onGameFinished(int score) {

    }

    @Override
    public void onGameLost() {

    }
    
}
