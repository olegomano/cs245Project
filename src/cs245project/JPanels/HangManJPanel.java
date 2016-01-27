/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245project.JPanels;

import cs245project.JPanels.KeyBoardPanel.OnKeyPressedListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author root
 */
public class HangManJPanel extends javax.swing.JPanel implements OnKeyPressedListener, ActionListener{

   
    public interface HangMangStateListener{
        public void onGameReset();
        public void onGameFinished(int score);
        public void onGameLost();
    }

    /**
     * Creates new form HangManJPanel
     */
    public HangManJPanel() {
        super();
        initComponents();
        mInit();
        startGame();
    }
    
    private void mInit(){
        keyBoardPanel1.setKeyListener(this);
        dateTimer.start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenejTextField1rated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        keyBoardPanel1 = new cs245project.JPanels.KeyBoardPanel();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        hangManGraphicsJpanel1 = new cs245project.JPanels.HangManGraphicsJpanel();

        javax.swing.GroupLayout keyBoardPanel1Layout = new javax.swing.GroupLayout(keyBoardPanel1);
        keyBoardPanel1.setLayout(keyBoardPanel1Layout);
        keyBoardPanel1Layout.setHorizontalGroup(
            keyBoardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        keyBoardPanel1Layout.setVerticalGroup(
            keyBoardPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );

        jButton2.setText("Skip");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setText("score");

        javax.swing.GroupLayout hangManGraphicsJpanel1Layout = new javax.swing.GroupLayout(hangManGraphicsJpanel1);
        hangManGraphicsJpanel1.setLayout(hangManGraphicsJpanel1Layout);
        hangManGraphicsJpanel1Layout.setHorizontalGroup(
            hangManGraphicsJpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        hangManGraphicsJpanel1Layout.setVerticalGroup(
            hangManGraphicsJpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keyBoardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hangManGraphicsJpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(27, 27, 27)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hangManGraphicsJpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(keyBoardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        startGame();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    
     @Override
    public void actionPerformed(ActionEvent ae) {
        jTextField2.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private cs245project.JPanels.HangManGraphicsJpanel hangManGraphicsJpanel1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jTextField2;
    private cs245project.JPanels.KeyBoardPanel keyBoardPanel1;
    // End of variables declaration//GEN-END:variables
    private Timer dateTimer = new Timer(1000,this);
    private final static int MAX_MISTAKES = 6;
    private HangMangStateListener listener;
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
    @Override
    public void onKeyPressed(char key) {
        //jTextField1.setText(jTextField1.getText() + key );
        System.out.println("Pressed: " + key);
        char[] asChar = WORD_BANK[currentWord].toCharArray();
        boolean validKey = false;
        for(int i = 0; i < asChar.length; i++){
            if(asChar[i] == key){
                userInput[i] = key;
                hangManGraphicsJpanel1.setText(userInput);
                hangManGraphicsJpanel1.repaint();
                validKey = true;
                collectedLetters++;
            }
        }
        if(!validKey){
            score-=10;
            ++mistakes;
            System.out.println("Missed Letter");
            if(mistakes >= MAX_MISTAKES){
                System.out.println("You Lost Game");
                if(listener!=null){
                    listener.onGameFinished(score);
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
        //jTextField1.setText(new String(userInput));
        
        if(userInput.length == WORD_BANK[currentWord].length()){
            System.out.println("Game Lost");
            if(listener!=null){
                listener.onGameLost();
            }
            return;
        }
    }
    
    public void startGame(){
        mistakes = 0;
        keyBoardPanel1.reset();
        collectedLetters = 0;
        currentWord = Math.abs( new Random().nextInt() )% WORD_BANK.length;
        System.out.println("Word: " + WORD_BANK[currentWord]);
        score = 100;
        jTextField2.setText(score+"");
        userInput = new char[WORD_BANK[currentWord].length()];
        for(int i = 0 ; i < userInput.length; i++){
            userInput[i] = ' ';
        }
        hangManGraphicsJpanel1.setText(userInput);
        hangManGraphicsJpanel1.repaint();
    }
    
    
}
