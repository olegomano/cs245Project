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
    
    public HangManGraphicsJpanel(){
        super();
        hangmanBounds = new Rectangle();
    }
    
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
    
    private void drawLeftArm(Graphics g){
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int)( hangmanBounds.y + hangmanBounds.height*.15f + hangmanBounds.height*.075f);
        int endX = (int)(startX - hangmanBounds.width*.25f);
        int endY = (int)(startY + hangmanBounds.height*.25f);
        g.drawLine(startX, startY, endX, endY);
        
        
    }
    
    private void drawRightArm(Graphics g){
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int)( hangmanBounds.y + hangmanBounds.height*.15f + hangmanBounds.height*.075f);
        int endX = (int)(startX + hangmanBounds.width*.25f);
        int endY = (int)(startY + hangmanBounds.height*.25f);
        g.drawLine(startX, startY, endX, endY);
       
    }
    
    private void drawLeftLeg(Graphics g){
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int) (hangmanBounds.y + hangmanBounds.height*.15f + hangmanBounds.height*.075f + hangmanBounds.height*.15f);
        int endX = (int)(startX - hangmanBounds.width*.15f);
        int endY = (int)(startY + hangmanBounds.height*.55f);
        g.drawLine(startX, startY, endX, endY);
      }
    
    private void drawRightLeg(Graphics g){
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int) (hangmanBounds.y + hangmanBounds.height*.15f + hangmanBounds.height*.075f + hangmanBounds.height*.15f);
        int endX = (int)(startX + hangmanBounds.width*.15f);
        int endY = (int)(startY + hangmanBounds.height*.55f);
        g.drawLine(startX, startY, endX, endY);
    }
    
    private void drawTorso(Graphics g){
        int startX = (int)(hangmanBounds.x + hangmanBounds.width*.5f);
        int startY = (int)( hangmanBounds.y + hangmanBounds.height*.15f + hangmanBounds.height*.075f);
        int endX = startX;
        int endY = (int) (startY + hangmanBounds.height*.15f);
        g.drawLine(startX, startY, endX, endY);
    }
    
    private void drawNoose(Graphics g){
        
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

