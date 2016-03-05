/***************************************************************
*
file: HangManGraphicsJpanel.java
*
author: Oleg Tolstov
*
class: CS 245
–
GUI
*
*
assignment: Quarter Project
*
date last modified: 1/30/2015
*
*
purpose: This program plays a game of hangman
****************************************************************/ 

package cs245project.JPanels;

import cs245project.JPanels.KeyBoardPanel.OnKeyPressedListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author root
 */
public class HangManJPanel extends javax.swing.JPanel implements OnKeyPressedListener, ActionListener{

   
    public interface HangManStateListener{
        public void onGameReset();
        public void onGameFinished(int score);
        public void onGameLost();
    }
    /**
     * Sets the listener for hangman events
     * @param l 
     */
    public void setHangmanStateListener(HangManStateListener l){
        listener = l;
    }
    
    
    /**
     * Creates new form HangManJPanel
     */
    public HangManJPanel() {
        super();
        initComponents();
        mInit();
        this.setBounds(0,0, 600, 400);
    }
    
    
    
    private void mInit(){
        keyBoardPanel1 = new KeyBoardPanel();
        hangManGraphicsJpanel1 = new HangManGraphicsJpanel();
        jTextField2 = new JLabel();
        hangmanName = new JLabel();
        resetButton = new JButton();
        resetButton.setToolTipText("Skip the game of hangman");
        
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(listener!=null){
                    listener.onGameReset();
                }
            }
        });
        keyBoardPanel1.setKeyListener(this);
        dateTimer.start();
        hangmanName.setText("HangMan");
        hangmanName.setBounds(400, 0, 120, 50);
        hangManGraphicsJpanel1.setBounds(25,0,375,200);
        keyBoardPanel1.setBounds(25, 225, 550, 150);
        jTextField2.setBounds(400,50,150,40);
        resetButton.setBounds(400,100, 150, 40);
        resetButton.setText("skip");
        add(keyBoardPanel1);
        add(hangManGraphicsJpanel1);
        add(jTextField2);
        add(resetButton);
        add(hangmanName);
     
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenejTextField1rated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Callback for the timer that ticks every second to update the date/time
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        jTextField2.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) );
    }
    HangManGraphicsJpanel hangManGraphicsJpanel1;
    JLabel jTextField2;
    KeyBoardPanel keyBoardPanel1;
    JButton resetButton;
    JLabel hangmanName;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private Timer dateTimer = new Timer(1000,this);
    private final static int MAX_MISTAKES = 6;
    private HangManStateListener listener;
    private static String[] WORD_BANK = {
        "abstract", 
        "cemetery", 
        "nurse",
        "pharmacy", 
        "climbing"
    };
    private int mistakes = 0;
    private int score = 100;
    private int currentWord = 0;
    private char[] userInput;
    private int collectedLetters = 0;
    /**
     * Callback every time the keyboard panel has a key pressed
     * @param key ascii code of the key that was pressed
     */
    @Override
    public void onKeyPressed(char key) {
        //jTextField1.setText(jTextField1.getText() + key );
        System.out.println("Pressed: " + key);
        char[] asChar = WORD_BANK[currentWord].toCharArray();
        boolean validKey = false;
        for(int i = 0; i < asChar.length; i++){
            if(asChar[i] == key){
                userInput[i] = key;
                hangManGraphicsJpanel1.repaint();
                validKey = true;
                collectedLetters++;
            }
        }
        if(!validKey){
            score-=10;
            ++mistakes;
            hangManGraphicsJpanel1.setHangmanState(mistakes);
            hangManGraphicsJpanel1.repaint();
            System.out.println("Missed Letter");
            if(mistakes >= MAX_MISTAKES){
                System.out.println("You Lost Game");
                if(listener!=null){
                    listener.onGameLost();
                }
            }
        }
        if(collectedLetters == WORD_BANK[currentWord].length()){
            System.out.println("Game Won: " + score);
            if(listener!=null){
                listener.onGameFinished(score);
                return;
            }
        }
        System.out.println(userInput);
    }
    /**
     * Resets the state of the game, and generates a new word
     */
    public void startGame(){
        mistakes = 0;
        keyBoardPanel1.reset();
        collectedLetters = 0;
        currentWord = Math.abs( new Random().nextInt() )% WORD_BANK.length;
        System.out.println("Word: " + WORD_BANK[currentWord]);
        score = 100;
        //jTextField2.setText(score+"");
        userInput = new char[WORD_BANK[currentWord].length()];
        for(int i = 0 ; i < userInput.length; i++){
            userInput[i] = ' ';
        }
        hangManGraphicsJpanel1.setHangmanState(0);
        hangManGraphicsJpanel1.setText(userInput);
        hangManGraphicsJpanel1.repaint();
        repaint();
        
    }
    
    
}
