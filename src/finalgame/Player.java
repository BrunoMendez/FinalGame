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

    private int health;
    private Game game;
    private Animation animationPistol;
    private Animation animationFeet;
    private Animation shootPistol;
    private Animation animationShotgun;
    private Animation shootShotgun;
    private int lastAnimation;
    private int direction;
    private long hitTime;
    private boolean isHit;
    private int weaponType;
    private boolean isIdle;
    private final int PISTOL = 1;
    private final int SHOTGUN = 2;
    private Animation currentAnimation;
    private int degrees;
    
    
    public Player(int x, int y, int width, int height, int health, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.health = health;
        direction = 1;
        
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

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
    public boolean isHit(){
        return isHit;
    }
    
    @Override
    public void tick() {
        weaponType = game.getWeapon().getType();
        if(System.currentTimeMillis() - hitTime > 5000 && health < 100){
            health+=10;
            hitTime = System.currentTimeMillis();
        }
        if(isHit){
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
        drawAnimation(animationFeet, g);
        drawAnimation(currentAnimation, g);
    }
    
    private void drawAnimation(Animation a, Graphics g){
        //drawing rotated image
        ImageIcon icon = new ImageIcon(a.getCurrentFrame());
        BufferedImage blankCanvas = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)blankCanvas.getGraphics();
        
        g2.rotate(Math.toRadians(degrees), icon.getIconWidth()/ 2, icon.getIconHeight()/ 2);
        g2.drawImage(a.getCurrentFrame(), 0, 0, null);
        g.drawImage(blankCanvas, getX(), getY(), 
                getWidth(), getHeight(), null);
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