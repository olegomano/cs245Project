/***************************************************************
*
file: Cs245Project.java
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

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author momo-chan
 */
public class Cs245Project {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Setting the size and make sure it does not inclucde the 
        //border size
        final JFrame jFrame = new JFrame();
        jFrame.getContentPane().setMinimumSize(new Dimension(600,400));
        jFrame.getContentPane().add(new RootJPanel());
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.pack();
        jFrame.setLocation(dimension.width/2-300, dimension.height/2-200);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jFrame.setVisible(true);
            }
        });
    }
    
}
