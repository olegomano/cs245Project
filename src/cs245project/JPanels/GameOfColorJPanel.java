/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs245project.JPanels;

import cs245project.JPanels.EndScreenJPanel;
import cs245project.JPanels.OvalButton;
import cs245project.JPanels.OvalButton.OvalButtonListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author ningl_000
 */
public class GameOfColorJPanel extends JPanel implements OvalButtonListener, ActionListener{

    private Timer dateTimer = new Timer(1000,this);
    private JLabel jTextField1;
    private JLabel timer;
    private OvalButton[] ovalButton;
    private EndScreenJPanel endScreen;
    private Color[] colors = new Color[] { Color.RED, Color.GREEN, Color.BLUE, new Color(1.0f,1.0f,0.0f,1), new Color(1.0f,0.7f,0.85f,1)};
    Random rd = new Random();
    Color randomColor = colors[Math.abs(rd.nextInt())%colors.length];
    private String[] names = {"Red","Green","Blue","Yellow","Pink"};
    /**
     * Creates new form GameOfColorJPanel
     */
    public GameOfColorJPanel() {
        super();
        endScreen = new EndScreenJPanel();
        initComponents();
        mInit();
        this.setBounds(0,0, 600, 400);

    }

    private void mInit() {
        jTextField1 = new JLabel();
        add(jTextField1);
        jTextField1.setBounds(280, 0, 100, 100);
        jTextField1.setFont(new Font("serif", Font.PLAIN, 30));
        timer = new JLabel();
        dateTimer.start();
        timer.setBounds(475,5,150,40);
        add(timer);
        ovalButton = new OvalButton[5];
        for(int i = 0; i < ovalButton.length; i++){
            ovalButton[i] = new OvalButton();
            add(ovalButton[i]);
        }
        generateButtons();
        onOvalButtonPressed(Color.BLUE);
        
        
      
    }
    
    private void generateButtons(){
      jTextField1.setText(generateName());
      jTextField1.setForeground(generateColor());  
        
      int xPos = 0;
      int yPos = 0;
      int rad = 80;
        
      for(int i = 0; i < ovalButton.length; i++){
            boolean isValid = false;
            while(!isValid){
               double vecX = rd.nextInt() + 1;
               double vecY = rd.nextInt() + 1;
               double mag = (int) Math.sqrt( vecX*vecX + vecY*vecY  );
               vecX = (int) (( vecX / mag) * 8.3f * rad * rd.nextDouble());
               vecY = (int) (( vecY / mag) * 8.3f * rad * rd.nextDouble());
               int newX = (int) (xPos + vecX);
               int newY = (int) (yPos + vecY);
               ovalButton[i].setBounds(newX, newY, rad, rad);
                              
               System.out.println("Position Generated: " + newX + ", " + newY);
               if( xPos + vecX < (600-rad) && xPos + vecX > 0){
                  if(yPos + vecY < (400 - rad) && yPos + vecY > 0){
                      System.out.println("Position In Bounds: " + newX + ", " + newY);
                      if(!overlaps(i)) {
                          isValid = true;
                          System.out.println("Position validated: " + newX + ", " + newY);
                          xPos = newY;
                          yPos = newX;
            
                      }
                  }
               }
            
            }
            ovalButton[i].setColor(colors[i]);
        } 
    }
    
    public boolean overlaps(int index){
        if(collides(ovalButton[index],jTextField1)) return true;
        if(collides(ovalButton[index],timer)) return true;
        for(int i = 0; i < index; i++){
            if(collides(ovalButton[i],ovalButton[index])){
                return true;
            }
        }
        return false;
    }
    
    public boolean collides(JComponent c1, JComponent c2){
        //System.out.println("Comparing:\n " + c1.toString() + " \n " + c2.toString());
        /*
        if(c1.getX() > c2.getX() && c1.getX() + c1.getWidth()*1.8f < c2.getX() + c2.getWidth()*1.8f ){
            if(c1.getY() > c2.getY() && c1.getY() + c1.getHeight()*1.8f < c2.getY() + c2.getHeight()*1.8f){
                return true;
            }
        }
        if(c2.getX() > c1.getX() && c2.getX() + c2.getWidth()*1.8f < c1.getX() + c1.getWidth()*1.8f ){
            if(c2.getY() > c1.getY() && c2.getY() + c2.getHeight()*1.8f < c1.getY() + c1.getHeight()*1.8f){
                return true;
            }
        }
        */
        
        return c1.getBounds().intersects(c2.getBounds());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

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
    private String generateName(){
        return names[rd.nextInt(names.length)];
    }
    private Color generateColor(){
        return colors[rd.nextInt(colors.length)];
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
       timer.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) );
    }

    @Override
    public void onOvalButtonPressed(Color c) {
        System.out.println("button pressed" + c);
        removeAll();
        add(endScreen);
        generateButtons();
        revalidate();
        repaint();
    }
}
