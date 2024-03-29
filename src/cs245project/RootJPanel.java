/***************************************************************
*
file: RootJPanel.java
*
author: Oleg Tolstov
* 
author: Ning Li
*
class: CS 245
–
GUI
*
*
assignment: Quarter Project
*
date last modified: 1/31/2015
*
*
purpose: This program plays a game of hangman
****************************************************************/ 

package cs245project;

import cs245project.JPanels.CreditsJPanel;
import cs245project.JPanels.DisplayInfoJPanel;
import cs245project.JPanels.EndScreenJPanel;
import cs245project.JPanels.GameOfColorJPanel;
import cs245project.JPanels.GameOfColorJPanel.ColorGameStateListener;
import cs245project.JPanels.HangManJPanel;
import cs245project.JPanels.HangManJPanel.HangManStateListener;
import cs245project.JPanels.HighScoreEntryJPanel;
import cs245project.JPanels.HighScoreJPanel;
import cs245project.JPanels.MainMenuJPanel;
import cs245project.JPanels.MainMenuJPanel.OnMainMenuOptionPressed;
import cs245project.JPanels.SudokuBoardDisplay;
import cs245project.JPanels.SudokuBoardDisplay.SudokuGameStateListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author momo-chan
 */
public class RootJPanel extends JPanel implements OnMainMenuOptionPressed, ReturnToMainMenuListener, HangManStateListener, ColorGameStateListener, SudokuGameStateListener, ActionListener {
    private DisplayInfoJPanel displayInfo;
    private MainMenuJPanel mainMenu;
    private HangManJPanel hangMan;
    private HighScoreJPanel highScore;
    private CreditsJPanel credits;
    private EndScreenJPanel endScreen;
    private GameOfColorJPanel gameOfColor;
    private HighScoreEntryJPanel highScoreEntry;
    private SudokuBoardDisplay sudoku;
    private Timer timer;
    private static final String F1_ACTION_INPUT = "F1-ACTION_INPUT";
    public static ScoreManager manager;
    
    //initializing necessary variables and starting a timer
    public RootJPanel(){
        super();
        try {
            manager = new ScoreManager();
        } catch (IOException ex) {
            Logger.getLogger(RootJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //manager.postScore(100000000,"A TEST");
        setPreferredSize(new Dimension(600,400));
        displayInfo = new DisplayInfoJPanel();
        mainMenu = new MainMenuJPanel(); 
        hangMan = new HangManJPanel();
        highScore = new HighScoreJPanel();
        credits = new CreditsJPanel();
        endScreen = new EndScreenJPanel();
        gameOfColor = new GameOfColorJPanel();
        highScoreEntry = new HighScoreEntryJPanel();
        sudoku = new SudokuBoardDisplay();
        try {
            mainMenu.setBackroundImage("symbol.jpg");
        } catch (IOException ex) {
            Logger.getLogger(RootJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainMenu.setOnMainMenuOptionListener(this);
        highScore.setReturnMainMenuSelectedListener(this);
        credits.setReturnMainMenuSelectedListener(this);
        hangMan.setHangmanStateListener(this);
        endScreen.setReturnMainMenuSelectedListener(this);
        gameOfColor.setColorGameStateListener(this);
        highScoreEntry.setReturnToMainMenuListener(this);
        sudoku.setSudokuGameStateListener(this);
        timer = new Timer(3000,this);
        add(displayInfo);
        timer.start();
        revalidate();
        repaint();
        
        Action OpenCredits = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open Credits");
                JOptionPane.showMessageDialog(RootJPanel.this, "  cs245 Quarter Project \n \n  Oleg Tolstov, 009343997 \n Ning Li, 008878792 \n Sprint Quarter 2016");
            }
        };
        
        Action CloseGame = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("CloseGame");
                System.exit(0);
            }
        };
        
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0,true), F1_ACTION_INPUT);
        getActionMap().put(F1_ACTION_INPUT,OpenCredits);
        
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0,true), "close_game");
        getActionMap().put("close_game",CloseGame);
    }
    
    //listeners to "back" and "end" button to return to mainmenu
    @Override
    public void onReturnToMainMenuSelected(){
        System.out.println("Returned to main menu");
        this.removeAll();
        add(mainMenu);
        revalidate();
        repaint();
    }

    //listener to when a new game is needed
    @Override
    public void onNewGameSelected() {
        System.out.println("New Game Selected");
        removeAll();
        add(hangMan);
        hangMan.startGame();
        validate();
        repaint();
    }
    
    //remove everything and
    //displays the highscore screen when user click highScore
    @Override
    public void onHighScoreSelected() {
        System.out.println("On HighScoreSelected");
        remove(mainMenu);
        highScore.showScores(manager);
        add(highScore);
        revalidate();
        repaint();
    }

    //remove everything and
    //displays the credits screen when user click credits
    @Override
    public void onCreditsSelected() {
        System.out.println("On Credits Selected");
        remove(mainMenu);
        add(credits);
        revalidate();
        repaint();
    }

    //when user click the "skip" button, the game finishes with a score
    //of 0, then displays the endscreen
    @Override
    public void onGameReset() {
        System.out.println("Game Reset");
        removeAll();
        add(gameOfColor);
        gameOfColor.setScore(0);
        //add(endScreen);
        //endScreen.setText("Skipped \n score: " + 0);
        revalidate();
        repaint();
    }

    //when user correctly guessed the word, displays it accordingly
    //score and the endscreen
    @Override
    public void onGameFinished(int score) {
        System.out.println("Game Finished");
        removeAll();
        add(gameOfColor);
        gameOfColor.setScore(score);
        revalidate();
        repaint();
    }

    //when user lost the game, display the secore and the endscreen
    @Override
    public void onGameLost() {
        System.out.println("Game Lost");
        removeAll();
        add(gameOfColor);
        gameOfColor.setScore(40);
        revalidate();
        repaint();
    }

    //stop the timer after 3 seconds and then remove the information screen
    // and displays the menu
    @Override
    public void actionPerformed(ActionEvent ae) {
        timer.stop();
        removeAll();
        add(mainMenu);
        revalidate();
        repaint();
    }

    @Override
    public void onColorGameFinished(int score) {
        removeAll();
        sudoku.setScore(score);
        add(sudoku);
        revalidate();
        repaint();
       
    }

    @Override
    public void onSubmitButtonPressed(int score) {
        System.out.println("Sudoku game submitted");
        if(manager.isHighScore(score)){
            removeAll();
            add(highScoreEntry);
            highScoreEntry.setScore(score);
            revalidate();
            repaint();
        }
        else{
            removeAll();
            add(endScreen);
            endScreen.setText("" + score);
            revalidate();
            repaint();
        }
    }

    @Override
    public void onCancelButtonPressed(int score) {
        System.out.println("Sudoku game cancelled");
        if(manager.isHighScore(score)){
            removeAll();
            add(highScoreEntry);
            highScoreEntry.setScore(score);
            revalidate();
            repaint();
        }
        else{
            removeAll();
            add(endScreen);
            endScreen.setText("" + score);
            revalidate();
            repaint();
        }
    }
    
}
