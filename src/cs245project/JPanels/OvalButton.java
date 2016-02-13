/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs245project.JPanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author ningli
 */
public class OvalButton extends JButton implements MouseListener {
    public interface OvalButtonListener{
        public void onOvalButtonPressed(Color c);
    }
    
    private Color mColor = Color.BLUE;
    private boolean isBorder = false;
    private OvalButtonListener l;
    public OvalButton(){
        super();
        setBorder(BorderFactory.createEmptyBorder());
        this.addMouseListener(this);
    }
    
    public void setOvalButtonListener(OvalButtonListener listener){
        l = listener;
    }
    
    public void setColor(Color c){
        mColor = c;
    }
    
    public void getColor(){
    
    }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        if(isBorder){
            g.setColor(Color.black);
            g.fillOval(0, 0, getWidth(), getHeight());
        }
        g.setColor(mColor);
        g.fillOval(7, 7, getWidth()- 14 , getHeight() - 14);
        
    }

  
    @Override
    public void mouseClicked(MouseEvent e) {
        if(l!=null){
            l.onOvalButtonPressed(mColor);
            
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
            
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isBorder = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isBorder = false;
    }
    
    
    
}
