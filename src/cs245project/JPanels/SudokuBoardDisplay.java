/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs245project.JPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author ningl_000
 */
public class SudokuBoardDisplay extends JPanel implements ActionListener {
 
    public interface SudokuGameStateListener{
        public void onSubmitButtonPressed(int score);
        public void onCancelButtonPressed(int score);
    }
    public void setSudokuGameStateListener(SudokuGameStateListener l){
        sudokuListener = l;
    }
    
    private SudokuGameStateListener sudokuListener;
    SudokuJPanel sudoku;   
    JLabel name;
    JLabel time;
    private Timer dateTimer = new Timer(1000,this);
    JButton submit;
    JButton cancel;
    private int initialScore = 0;
    /**
     * Creates new form SudokuBoardDisplay
     */
    public SudokuBoardDisplay() {
        super();
        //initComponents();
        mInit();
        this.setBounds(0,0,600,400);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void mInit() {
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
        sudoku = new SudokuJPanel();
        name = new JLabel();
        time = new JLabel();
        submit = new JButton();
        cancel = new JButton();
        
        name.setText("Sudoku");
        submit.setText("Submit");
        cancel.setText("Cancel");
        
        name.setBounds(20, 0, 50, 20);
        time.setBounds(470, 5, 150, 20);
        submit.setBounds(200, 340, 100, 30);
        cancel.setBounds(300, 340, 100, 30);
        sudoku.setBounds(100, 30, 400, 300);
        
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(sudokuListener!=null){
                    sudokuListener.onSubmitButtonPressed(initialScore + calculateScore());
                }
            }
        });
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(sudokuListener!=null){
                    sudokuListener.onCancelButtonPressed(initialScore + calculateScore());
                }
            }
        });
        add(name);
        add(submit);
        add(sudoku);
        add(time);
        add(cancel);
        dateTimer.start();
        revalidate();
        repaint();
        
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++) {
                if(sudoku.blank[row][col] == true) {
                    MDocumentListener listener = new MDocumentListener();
                    listener.me = sudoku.cells[row][col];
                    sudoku.cells[row][col].getDocument().addDocumentListener(listener);
                }
            }
        }
    }
    
    private int calculateScore(){
        int resultScore = 540;
        for(int i = 0; i < sudoku.cells.length; i++){
            for(int b = 0;  b < sudoku.cells[0].length; b++){
                int cellNumber = -1;
                try{
                    cellNumber = Integer.parseInt(sudoku.cells[i][b].getText());
                }catch(Exception e){
                    cellNumber = -1;
                }
                if(sudoku.puzzle[i][b] - cellNumber != 0){
                    resultScore-=10;
                }
            }
        }
        System.out.println("Sudoku Score " + resultScore);
        return resultScore;
    }
    

    public void setScore(int score){
        initialScore = score;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        time.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) );
    }
    
     class MDocumentListener implements DocumentListener{
                        JTextField me;
                        @Override
                        public void insertUpdate(DocumentEvent de) {
                            String newText = "";
                            try {
                                newText = de.getDocument().getText(0, de.getDocument().getLength());
                            } catch (BadLocationException ex) {
                                Logger.getLogger(SudokuBoardDisplay.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            int enteredText = 0;
                            try{
                                enteredText = Integer.parseInt(newText);
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(SudokuBoardDisplay.this, "Please enter number 1-9");
                                java.awt.EventQueue.invokeLater(new Runnable() {
                                    public void run() {
                                        me.setText("");
                                    }
                                });
                                return;
                            }
                            if(enteredText < 0 || enteredText > 9){
                                JOptionPane.showMessageDialog(SudokuBoardDisplay.this, "Please enter number 1-9");
                                java.awt.EventQueue.invokeLater(new Runnable() {
                                    public void run() {
                                        me.setText("");
                                    }
                                });
                              
                            }
                        }

                        @Override
                        public void removeUpdate(DocumentEvent de) {
                       
                        }

                        @Override
                        public void changedUpdate(DocumentEvent de) {
                        
                        }
                    
                    };
}


