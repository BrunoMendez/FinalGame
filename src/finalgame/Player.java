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
    private Animation animation;
    private int lastAnimation;
    private Game game;
    
    public Player(int x, int y, int width, int height, int lives, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.lives = lives;
        this.animation = new Animation(Assets.playerAnim, 100);
        lastAnimation = 3;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    @Override
    public void tick() {
        //speed 6 of the player
        if (game.getKeyManager().left) {
           setX(getX() - 6);
           this.animation.tick();
        }
        if (game.getKeyManager().right) {
           setX(getX() + 6);
           this.animation.tick();
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
        if (game.getKeyManager().left) {
            g.drawImage(animation.getCurrentFrame(), getX(), getY(), 
                getWidth(), getHeight(), null);
            lastAnimation = 1;
        }
        else if(game.getKeyManager().right) {
            g.drawImage(animation.getCurrentFrame(), getX(), getY(), 
                getWidth(), getHeight(), null);
            lastAnimation = 2;
        }
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), 
            getWidth(), getHeight(), null);
       
    }
}
