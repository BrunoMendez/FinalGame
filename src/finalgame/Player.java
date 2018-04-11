/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author antoniomejorado
 */
public class Player extends Item{

    private int lives;
    private Game game;
    private Animation animationUp;
    private Animation animationDown;
    private Animation animationLeft;
    private Animation animationRight;
    private int lastAnimation;
    
    public Player(int x, int y, int width, int height, int lives, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.lives = lives;
       
        this.animationUp = new Animation(Assets.playerUp, 100);
        this.animationDown = new Animation(Assets.playerDown, 100);
        this.animationLeft = new Animation(Assets.playerLeft, 100);
        this.animationRight = new Animation(Assets.playerRight, 100);
        lastAnimation = 1;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    @Override
    public void tick() {
        if(game.getKeyManager().up){
            setY(getY()-3);
            this.animationUp.tick();
        }
        if(game.getKeyManager().down){
            setY(getY()+3);
            this.animationDown.tick();
        }
        if(game.getKeyManager().right){
            setX(getX()+3);
            this.animationRight.tick();
        }
        if(game.getKeyManager().left){
            setX(getX()-3);
            this.animationLeft.tick();
        }
        
        // reset x position and y position if colision
        if (getX() + 100 >= game.getWidth()) {
            setX(game.getWidth() - 100);
        }
        else if (getX() <= 0) {
            setX(0);
        }
    }

    @Override
    public void render(Graphics g) {
      if (game.getKeyManager().up) {
            g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), 
                getWidth(), getHeight(), null);
            lastAnimation = 1;
        }
        else if(game.getKeyManager().down) {
            g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), 
                getWidth(), getHeight(), null);
            lastAnimation = 2;
        }
        else if (game.getKeyManager().right){
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), 
                getWidth(), getHeight(), null);
            lastAnimation = 3;
        }
        else if (game.getKeyManager().left){
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), 
                getWidth(), getHeight(), null);
            lastAnimation = 4;
        }
        
        if(lastAnimation == 1){
            g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), 
                getWidth(), getHeight(), null);
        }
        else if (lastAnimation == 2) {
            g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), 
                getWidth(), getHeight(), null);
        }
        else if (lastAnimation == 3) {
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), 
                getWidth(), getHeight(), null);
        }
        else {
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), 
                getWidth(), getHeight(), null);
        }
    }
}
