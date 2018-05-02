/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author bruno
 */
public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean izquierdo;  //left click
    private boolean derecho;    //right click
    private int x;              //x position
    private int y;              //y position
    private Game game;          //game attribute
    
    public MouseManager(Game g) {
        this.game = g;
    }
    
    /**
     * To get the x position of the mouse
     * @return an <code>int</code> of the x position of the mouse
     */
    public int getX() {
        return x;
    }
    
    /**
     * To get the y position of the mouse
     * @return an <code>int</code> of the y position of the mouse
     */
    public int getY() {
        return y;
    }
    
    /**
     * To get the left click of the mouse
     * @return an <code>boolean</code> if the left click is used
     */
    public boolean isIzquierdo() {
        return izquierdo;
    }
    
    /**
     * To get the right click of the mouse
     * @return a <code>boolean</code> if the right click is used
     */
    public boolean isDerecho() {
        return derecho;
    }
    
    /**
     * To set the left click of the mouse
     * @param izquierdo to set the left click of the mouse
     */
    public void setIzquierdo(boolean izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    /**
     * To set the right click of the mouse
     * @param derecho to set the right click of the mouse
     */
    public void setDerecho(boolean derecho) {
        this.derecho = derecho;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //to check if mouse buttons are pressed
       if(!game.isStartGame() && !game.isPaused()) {
          game.startButton.setClicked(true);
          game.quitButton.setClicked(true); 
       }
       else if(game.isStartGame() && game.isPaused()) {
          game.resumeButton.setClicked(true);
          game.exitButton.setClicked(true); 
       } 
       else if (game.getGameOver()) {
           game.exitButton.setClicked(true);
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            izquierdo = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            izquierdo = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
            izquierdo = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}