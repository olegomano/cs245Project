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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author root
 */
public class HangManGraphicsJpanel extends JPanel{
    private static final float HANGMAN_START_X = .15f;
    private static final float HANGMAN_START_Y = .125f;
   
    private static final float HANGMAN_HEIGHT = .5f;
    private static final float HANGMAN_WIDTH = .75f;
    
    private static final float TEXT_HEIGHT = .3f;
    private static final float TEXT_WIDTH = .6f;
    private static final float TEXT_START_X = .2f;
    private static final float TEXT_START_Y = .8f;    
    
    
    private Rectangle[] textBounds;
    private Rectangle hangmanBounds;
    private char[] userText;
    private int hangmanState;
    
    public HangManGraphicsJpanel(){
        super();
        hangmanBounds = new Rectangle();
    }
    /**
    * Sets the users current input text 
    * @param text 
    */
    public void setText(char[] text){
        textBounds = new Rectangle[text.length];
        for(int i = 0; i < textBounds.length; i++){
            textBounds[i] = new Rectangle();
        }
        userText = text;
    }
    /**
     * Sets how much of the hangman to draw
     * @param state 
     */
    public void setHangmanState(int state){
        hangmanState = state;
    }
    
    /**
     * Draws the hangman's head
     * @param g 
     */
    private void drawHead(Graphics g){
        System.out.println("Drawing Head");
        g.setColor(Color.RED);
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int)( hangmanBounds.y + hangmanBounds.height*.15f);
        int rad = (int)(hangmanBounds.height*.15f);
        System.out.println("Bounds " + hangmanBounds.toString());
        System.out.println("Head (x,y,r): (" + startX + ", " + startY + "," + rad);
        g.fillOval(startX - rad/2, startY - rad/2, rad, rad);
    }
    /**
     * Draws the hangman's left arm
     * @param g 
     */
    private void drawLeftArm(Graphics g){
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int)( hangmanBounds.y + hangmanBounds.height*.15f + hangmanBounds.height*.075f);
        int endX = (int)(startX - hangmanBounds.width*.25f);
        int endY = (int)(startY + hangmanBounds.height*.25f);
        g.drawLine(startX, startY, endX, endY);
        
        
    }
    
    /**
     * Draws the hangman's right arm
     * @param g 
     */
    private void drawRightArm(Graphics g){
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int)( hangmanBounds.y + hangmanBounds.height*.15f + hangmanBounds.height*.075f);
        int endX = (int)(startX + hangmanBounds.width*.25f);
        int endY = (int)(startY + hangmanBounds.height*.25f);
        g.drawLine(startX, startY, endX, endY);
       
    }
    
    /**
     * Draws the hangman's left arm
     * @param g 
     */
    private void drawLeftLeg(Graphics g){
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int) (hangmanBounds.y + hangmanBounds.height*.15f + hangmanBounds.height*.075f + hangmanBounds.height*.15f);
        int endX = (int)(startX - hangmanBounds.width*.15f);
        int endY = (int)(startY + hangmanBounds.height*.55f);
        g.drawLine(startX, startY, endX, endY);
    }
    
    /**
     * Draws the hangman's right leg
     * @param g 
     */
    private void drawRightLeg(Graphics g){
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int) (hangmanBounds.y + hangmanBounds.height*.15f + hangmanBounds.height*.075f + hangmanBounds.height*.15f);
        int endX = (int)(startX + hangmanBounds.width*.15f);
        int endY = (int)(startY + hangmanBounds.height*.55f);
        g.drawLine(startX, startY, endX, endY);
    }
    /**
     * Draws the hangman's left leg
     * @param g 
     */
    private void drawTorso(Graphics g){
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int)( hangmanBounds.y + hangmanBounds.height*.15f + hangmanBounds.height*.075f);
        int endX = startX;
        int endY = (int) (startY + hangmanBounds.height*.15f);
        g.drawLine(startX, startY, endX, endY);
    }
    
    
    private void drawNoose(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect((int)(hangmanBounds.x + hangmanBounds.width*.1f), (int)(hangmanBounds.y + hangmanBounds.height*.90f),(int)(hangmanBounds.width*.7f),(int)(hangmanBounds.height*.07f) );
        g.fillRect((int)(hangmanBounds.x + hangmanBounds.width*.15f), (int)(hangmanBounds.y + hangmanBounds.height*.10f),(int)(hangmanBounds.width*.03f),(int)(hangmanBounds.height*.8f) );
        
        int headX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int headY = (int)( hangmanBounds.y + hangmanBounds.height*.15f);
        int headRad = (int)(hangmanBounds.height*.15f);
        g.fillRect((int)(hangmanBounds.x + hangmanBounds.width*.15f), (int)(hangmanBounds.y + hangmanBounds.height*.10f),headX - (int)(hangmanBounds.x + hangmanBounds.width*.15f), headY - (int)(hangmanBounds.y + hangmanBounds.height*.10f) );
        
    }
    /**
     * Draws the text that the user inputted as well as the hangman
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        int panelHeight = getHeight();
        int panelWidth = getWidth();
        int letterWidth = (int) (panelWidth*TEXT_WIDTH/(userText.length+1) );
        int letterHeight = (int) (panelHeight*TEXT_HEIGHT);
        int paddingSpace = (int) ((panelWidth*TEXT_WIDTH - letterWidth*userText.length ) / userText.length);
        hangmanBounds.setBounds((int)(HANGMAN_START_X*panelWidth),(int)(HANGMAN_START_Y*panelHeight),(int)(HANGMAN_WIDTH*panelWidth),(int)(HANGMAN_HEIGHT*panelHeight));
        System.out.println(hangmanBounds.toString());
        switch(hangmanState){
            case 6:
                drawLeftLeg(g);
            case 5:
                drawRightLeg(g);
            case 4:
                drawTorso(g);
            case 3:
                drawLeftArm(g);
            case 2:
                drawRightArm(g);
            case 1:
                drawHead(g);
            case 0:   
                drawNoose(g);
        }
       
        int letterX = (int) (TEXT_START_X*panelWidth);
        int letterY = (int) ( (TEXT_START_Y*panelHeight));
      
        for(int i = 0; i < textBounds.length; i++){
            textBounds[i].setBounds(letterX,letterY, letterWidth, letterHeight);
            letterX+= (paddingSpace + letterWidth);
            g.setColor(Color.BLACK);
            if(userText[i] == ' '){
                g.fillRect(textBounds[i].x, textBounds[i].y, textBounds[i].width, textBounds[i].height);
            }
            g.setColor(Color.BLUE);
            g.drawChars(userText,i,1 , (int) (textBounds[i].x + letterWidth*.5f), (int) (textBounds[i].y + letterHeight*.5f));
            System.out.println("Drawing character " + userText[i]);
        }
        
        
        
    
    }

}

