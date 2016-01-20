/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245project.JPanels;

import cs245project.JPanels.KeyBoardPanel.OnKeyPressedListener;
import java.util.Random;

/**
 *
 * @author root
 */
public class HangManJPanel extends javax.swing.JPanel implements OnKeyPressedListener{
    public interface HangMangStateListener{
        public void onGameReset();
        public void onGameFinished(int score);
    }

    /**
     * Creates new form HangManJPanel
     */
    public HangManJPanel() {
        super();
        initComponents();
        mInit();
    }
    
    private void mInit(){
        keyBoardPanel1.setKeyListener(this);
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
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

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

        jTextField1.setText("jTextField1");

        jLabel1.setText("jLabel1");

        jButton1.setText("skip");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onSkipPressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keyBoardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(17, 17, 17)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keyBoardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void onSkipPressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onSkipPressed
       startGame();
    }//GEN-LAST:event_onSkipPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private cs245project.JPanels.KeyBoardPanel keyBoardPanel1;
    // End of variables declaration//GEN-END:variables
    
    private HangMangStateListener listener;
    private static String[] WORD_BANK = {
        "abstract", 
        "cemetery", 
        "nurse",
        "pharmacy", 
        "climbing"
    };
    private int score = 100;
    private int currentWord = 0;
    private String userInput = "";
    
    @Override
    public void onKeyPressed(char key) {
        jTextField1.setText(jTextField1.getText() + key );
        char expectedChar = WORD_BANK[currentWord].charAt(userInput.length());
        if(expectedChar != key){
            score-=10;
            return;
        }
        userInput+=key;
        jLabel1.setText(score+"");
        
        if(userInput.length() == WORD_BANK[currentWord].length()){
            if(listener!=null){
                listener.onGameFinished(score);
            }
            return;
        }
        
        repaint();
    }
    
    public void startGame(){
        keyBoardPanel1.reset();
        currentWord = new Random().nextInt() % WORD_BANK.length;
        score = 100;
        jLabel1.setText(score+"");
        userInput = "";
        repaint();
    }
    
    
}
