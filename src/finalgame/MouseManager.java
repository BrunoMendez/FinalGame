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
    private boolean izquierdo;
    private boolean derecho;
    private int x;
    private int y;
    private Game game;
    
    public MouseManager(Game g) {
        this.game = g;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean isIzquierdo() {
        return izquierdo;
    }
    
    public boolean isDerecho() {
        return derecho;
    }
    
    public void setIzquierdo(boolean izquierdo) {
        this.izquierdo = izquierdo;
    }
    public void setDerecho(boolean derecho) {
        this.derecho = derecho;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
       game.startButton.setClicked(true);
       game.quitButton.setClicked(true);
       game.resumeButton.setClicked(true);
       game.exitButton.setClicked(true);
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