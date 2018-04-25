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

    private int health;
    private Game game;
    private Animation animationUp;
    private Animation animationDown;
    private Animation animationLeft;
    private Animation animationRight;
    private int lastAnimation;
    private int direction;
    
    public Player(int x, int y, int width, int height, int health, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.health = health;
        direction = 1;
        this.animationUp = new Animation(Assets.playerUp, 100);
        this.animationDown = new Animation(Assets.playerDown, 100);
        this.animationLeft = new Animation(Assets.playerLeft, 100);
        this.animationRight = new Animation(Assets.playerRight, 100);
        lastAnimation = 1;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    /*
    Directions:
    1 = up
    2 = down
    3 = right
    4 = left
    */
    public int getDirection(){
        return direction;
    }
    
    
    @Override
    public void tick() {
        // move player
        if(game.getKeyManager().up){
            setY(getY()-2);
            this.animationUp.tick();
            direction = 1;
        }
        else if(game.getKeyManager().down){
            setY(getY()+2);
            this.animationDown.tick();
            direction = 2;
        }
        else if(game.getKeyManager().right){
            setX(getX()+2);
            this.animationRight.tick();
            direction = 3;
        }
        else if(game.getKeyManager().left){
            setX(getX()-2);
            this.animationLeft.tick();
            direction = 4;
        }
        
        // reset x position and y position if colision
        if (getX() + 50 >= game.getWidth()) {
            setX(game.getWidth() - 50);
        }
        else if (getX() <=-25) {
            setX(-25);
        }
        if(getY() + 75 >= game.getHeight()){
            setY(game.getHeight() - 75);
        }
        else if(getY() <= -18){
            setY(-18);
        }
    }

    @Override
    public void render(Graphics g) {
        //render animation
        int whiteSpace = 20;
      if (game.getKeyManager().up) {
            g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), 
                75, 75, null);
            lastAnimation = 1;
        }
        else if(game.getKeyManager().down) {
            g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), 
                75, 75, null);
            lastAnimation = 2;
        }
        else if (game.getKeyManager().right){
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), 
                75, 75, null);
            lastAnimation = 3;
        }
        else if (game.getKeyManager().left){
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), 
                75, 75, null);
            lastAnimation = 4;
        }
        
      //if idle render the last animation
        if(lastAnimation == 1){
            g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), 
                75, 75, null);
        }
        else if (lastAnimation == 2) {
            g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), 
                75, 75, null);
        }
        else if (lastAnimation == 3) {
            g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), 
                75, 75, null);
        }
        else {
            g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), 
                75, 75, null);
        }
    }
}
