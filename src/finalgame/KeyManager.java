/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author bruno
 */
public class KeyManager implements KeyListener {
    public boolean up;      //move up
    public boolean down;    //move down
    public boolean left;    //move left
    public boolean right;   //move right
    public boolean space;   //shoot
    public boolean uno;       //change to prev weapon
    public boolean dos;       //change to next weapon
    public boolean p;       //pause game
    public boolean s;       //save game
    
    private boolean keys[];
    
    public KeyManager() {
        keys = new boolean[256];
        p = false;
    }
    
    public boolean isUno() {
	return keys[KeyEvent.VK_1];
    }
    
    public boolean isDos() {
	return keys[KeyEvent.VK_2];
    }
    
     @Override
     public void keyTyped(KeyEvent e) {
     }
     
     @Override
     public void keyPressed(KeyEvent e) {
         keys[e.getKeyCode()] = true;
     }
     
     @Override
     public void keyReleased(KeyEvent e) {
         if (keys[KeyEvent.VK_P]){
             
         }
         else {
         keys[e.getKeyCode()] = false;
         }
     }
     
     /**
      * to enable or disable moves on every tick
      */
     public void tick() {
         up = keys[KeyEvent.VK_UP];
         down = keys[KeyEvent.VK_DOWN];
         left = keys[KeyEvent.VK_LEFT];
         right = keys[KeyEvent.VK_RIGHT];
         space = keys[KeyEvent.VK_SPACE];
         uno = keys[KeyEvent.VK_1];
         dos = keys[KeyEvent.VK_2];
         p = keys[KeyEvent.VK_P];
         s = keys[KeyEvent.VK_S];
     }
     public boolean getP(){
         return p;
     }
     public void setP(boolean pause){
         keys[KeyEvent.VK_P] = pause;
     }
}