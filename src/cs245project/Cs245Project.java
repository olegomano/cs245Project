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
package cs245project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 *
 * @author momo-chan
 */
public class Cs245Project {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final JFrame jFrame = new JFrame();
        jFrame.getContentPane().add(new RootJPanel());
        jFrame.getContentPane().setSize(400,600);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation(dimension.width/2-300, dimension.height/2-200);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jFrame.setVisible(true);
            }
        });
    }
    
}
