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
    public boolean uno;     //change to pistol
    public boolean dos;     //change to shotgun
    public boolean tres;    //change to laser
    public boolean p;       //pause game
    public boolean s;       //save game
    
    private boolean keys[];
    
    /**
     * Constructor of KeyManager
     */
    public KeyManager() {
        keys = new boolean[256];
        p = false;
    }
    
    /**
     * Boolean to represent the state of VK_1
     * @return keys[KeyEvent.VK_1]
    */
    public boolean isUno() {
	return keys[KeyEvent.VK_1];
    }
    
    /**
     * Boolean to represent the state of VK_2
     * @return keys[KeyEvent.VK_2]
    */
    public boolean isDos() {
	return keys[KeyEvent.VK_2];
    }
    
    /**
     * Boolean to represent the state of VK_3
     * @return keys[KeyEvent.VK_3]
    */
    public boolean isTres() {
	return keys[KeyEvent.VK_3];
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
         tres = keys[KeyEvent.VK_3];
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