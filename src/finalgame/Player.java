/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author antoniomejorado
 */
public class Player extends Item{

    private int health;                     //health of the player
    private Game game;                      //game attribute
    private Animation animationPistol;      //pistol animation
    private Animation animationFeet;        //walking animation
    private Animation shootPistol;          //shooting pistol animation
    private Animation animationShotgun;     //shotgun animation
    private Animation shootShotgun;         //shooting shotgun animation
    private int lastAnimation;              //last animation used
    private int direction;                  //player direction
    private long hitTime;                   //time to hit player
    private boolean isHit;                  //player hit flag
    private int weaponType;                 //player weapons
    private boolean isIdle;                 //player idle flag
    private final int PISTOL = 1;           //pistol int
    private final int SHOTGUN = 2;          //shotgun int
    private Animation currentAnimation;     //animation currently running
    private int degrees;                    //player walk degrees
    
    
    public Player(int x, int y, int width, int height, int health, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.health = health;
        direction = 3;
        
        this.shootPistol = new Animation(Assets.pistolShoot, 50);
        this.animationFeet = new Animation(Assets.playerFeet, 25);
        this.animationPistol = new Animation(Assets.playerPistol, 25);
        this.animationShotgun = new Animation(Assets.playerShotgun, 25);
        this.shootShotgun = new Animation(Assets.shotgunShoot, 50);
        currentAnimation = animationPistol;
        
        weaponType = 1;
        degrees = 0;
        
        lastAnimation = 1;
        hitTime = System.currentTimeMillis();
        isIdle = true;
    }

    /**
     * To get the health of the player
     * @return an <code>int</code> of the health of the player
     */
    public int getHealth() {
        return health;
    }

    /**
     * To set the health of the player
     * @param health to set the health of the player
     */
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

    /**
     * To set if the player is hit
     * @param isHit to set if the player is hit
     */
    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
    
    /**
     * To get if the player is hit
     * @return a <code>boolean</code> if the player is hit
     */
    public boolean isHit(){
        return isHit;
    }
    
    @Override
    public void tick() {
        //player weapons
        weaponType = game.getWeapon().getType();
        //player hit time
        if(isHit){
            hitTime = System.currentTimeMillis();
        }
        if(System.currentTimeMillis() - hitTime > 5000 && health < 100){
            health+=10;
            hitTime = System.currentTimeMillis();
        }
        // move player
        if(game.getKeyManager().up && game.getKeyManager().right){
            setY(getY()-3);
            setX(getX()+3);
            direction = 5;
            isIdle = false;
            degrees = 315;
        }
        else if(game.getKeyManager().up && game.getKeyManager().left){
            setY(getY()-3);
            setX(getX()-3);
            direction = 6;
            isIdle = false;
            degrees = 215;
        }
        else if(game.getKeyManager().down && game.getKeyManager().right){
            setY(getY()+3);
            setX(getX()+3);
            direction = 7;
            isIdle = false;
            degrees = 45;
        }
        else if(game.getKeyManager().down && game.getKeyManager().left){
            setY(getY()+3);
            setX(getX()-3);
            direction = 8;
            isIdle = false;
            degrees = 135;
        }
        else if(game.getKeyManager().up){
            setY(getY()-4);
            direction = 1;
            isIdle = false;
            degrees = 270;
        }
        else if(game.getKeyManager().down){
            setY(getY()+4);
            direction = 2;
            isIdle = false;
            degrees = 90;
        }
        else if(game.getKeyManager().right){
            setX(getX()+4);
            direction = 3;
            isIdle = false;
            degrees = 0;
        }
        else if(game.getKeyManager().left){
            setX(getX()-4);
            direction = 4;
            isIdle = false;
            degrees = 180;
        }
        else{
            isIdle = true;
        }
        //weapon animations
        if(weaponType == PISTOL){
            currentAnimation = animationPistol;
        }
        else if(weaponType == SHOTGUN){
            currentAnimation = animationShotgun;
        }
        //Shoot
        if(game.getKeyManager().space){
            if(weaponType == PISTOL){
                currentAnimation = shootPistol;
            }
            else if(weaponType == SHOTGUN){
                currentAnimation = shootShotgun;
            }
        }
        if(!isIdle){
            animationFeet.tick();
        }
        currentAnimation.tick();
        
        // reset x position and y position if colision
        if (getX() + 47 >= game.getWidth()) {
            setX(game.getWidth() - 47);
        }
        else if (getX() <=0) {
            setX(0);
        }
        if(getY() + 47 >= game.getHeight()){
            setY(game.getHeight() - 47);
        }
        else if(getY() <= 0){
            setY(0);
        }
    }

    @Override
    public void render(Graphics g) {
        drawAnimation(animationFeet, g);
        drawAnimation(currentAnimation, g);
    }
    
    private void drawAnimation(Animation a, Graphics g){
        //drawing rotated image
        Graphics2D g2 = (Graphics2D)g.create();
        double radians = Math.toRadians( degrees );
        g2.translate(getX()+getWidth()/2, getY()+getHeight()/2);
        g2.rotate(radians);
        g2.translate(-getWidth()/2, -getHeight()/2);
        g2.drawImage(a.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);

        g2.dispose();
        if(isHit){
            g.setColor(Color.RED);
            g.drawString("Health: " + health, getX()+getWidth()/4, getY() - 13);
            g.setColor(Color.black);
        }
        else{
            g.drawString("Health: " + health, getX()+getWidth()/4, getY() - 13);
        }
    }
}