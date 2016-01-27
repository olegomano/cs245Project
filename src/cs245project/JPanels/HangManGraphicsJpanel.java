/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    
    public void setText(char[] text){
        textBounds = new Rectangle[text.length];
        for(int i = 0; i < textBounds.length; i++){
            textBounds[i] = new Rectangle();
        }
        userText = text;
    }
    
    public void setHangmanState(int state){
        hangmanState = state;
    }
    
    private void drawHead(){
    
    }
    
    private void drawLeftArm(){
    
    }
    
    private void drawRightArm(){
    
    }
    
    private void drawLeftLeg(){
    
    }
    
    private void drawRightLeg(){
    
    }
    
    private void drawTorso(){
    
    }
    
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        int panelHeight = getHeight();
        int panelWidth = getWidth();
        int letterWidth = (int) (panelWidth*TEXT_WIDTH/(userText.length+1) );
        int letterHeight = (int) (panelHeight*TEXT_HEIGHT);
        int paddingSpace = (int) ((panelWidth*TEXT_WIDTH - letterWidth*userText.length ) / userText.length);
        hangmanBounds = new Rectangle((int)HANGMAN_START_X*panelWidth,(int)HANGMAN_START_Y*panelHeight,(int)HANGMAN_HEIGHT*panelHeight,(int)HANGMAN_WIDTH*panelWidth);
        switch(hangmanState){
            case 6:
                drawLeftLeg();
            case 5:
                drawRightLeg();
            case 4:
                drawTorso();
            case 3:
                drawLeftArm();
            case 2:
                drawRightArm();
            case 1:
                drawHead();
            case 0:    
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

