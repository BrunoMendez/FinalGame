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
    public boolean E;
    public boolean D;
    public boolean A;
    public boolean Q;
    public boolean P;
    public boolean S;
    public boolean L;
    
    private boolean keys[];
    
    public KeyManager() {
        keys = new boolean[256];
        P = false;
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
         keys[e.getKeyCode()] = false;
          if(e.getKeyCode() == KeyEvent.VK_P){
             if(P){
                 P = false;
             }
             else{
                 P = true;
             }
         }
     }
     
     /**
      * to enable or disable moves on every tick
      */
     public void tick() {
         E = keys[KeyEvent.VK_E];
         D = keys[KeyEvent.VK_D];
         A = keys[KeyEvent.VK_A];
         Q = keys[KeyEvent.VK_Q];
         S = keys[KeyEvent.VK_S];
         L = keys[KeyEvent.VK_L];
     }
}
