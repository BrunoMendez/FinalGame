//Enemy
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
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author bruno
 */
public class Enemy extends Item {

    private Game game;                      // game instance
    private int speed;                      // enemy speedX
    private int direction;                  // enemy speedY
    private int points;                     // points the enemy grants
    private int enemyType;                  // 0 - normal, 1 - shooter
    private int health;                     // enemy health
    private Animation animationRun;         // run animation
    private Animation animationAttack;      // attack animation
    private Animation currentAnimation;     // Current animation state;
    private long lastTime;                  // save time
    private int damage;                     // damage of the enemy

    public Enemy(int x, int y, int width, int height, int speed, int direction, int health, int enemyType, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speed = speed;
        this.direction = direction;
        this.health = health;
        this.enemyType = enemyType;
        this.lastTime = System.currentTimeMillis();
        this.animationRun = new Animation(Assets.zombieRun, 25);
        this.animationAttack = new Animation(Assets.zombieAttack, 20);
        this.currentAnimation = animationRun;
        this.damage = 10;   // initial damage
    }

    /**
     * <code>LastTime</code> setter
     * @param lastTime 
     */
    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * <code>lasTime</code> getter
     * @return 
     */
    public long getLastTime() {
        return lastTime;
    }
    
    /**
     * To get the speedX of the enemy
     * @return an <code>int</code> of the speedX of the enemy
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * To set the speed of the enemy
     * @param speed To set the speedX of the enemy
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * To get the speedY of the enemy
     * @return an <code>int</code> of the speedY of the enemy
     */
    public int getDirection() {
        return direction;
    }

    /**
     * To set the speedY of the enemy
     * @param speedY to get the speedY of the enemy
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    /**
     * To set the points the enemy grants
     * @param points to set the points the enemy grants
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * To get the amount of points the enemy grants
     * @return an <code>int</code> of the amount of points the enemy grants
     */
    public int getPoints() {
        return points;
    }
    
    /**
     * To set the enemyType
     * @param enemyType to set the enemyType
     */
    public void setEnemyType(int enemyType) {
        this.enemyType = enemyType;
    }
    
    /**
     * To get the enemyType
     * @return an <code>int</code> of the enemyType
     */
    public int getEnemyType() {
        return enemyType;
    }
    
    /**
     * To set the enemy health
     * @param health to set the enemy health
     */
    public void setHealth(int health) {
        this.health = health;
    }
    
    /**
     * To get the enemy health
     * @return an <code>int</code> of the enemy health
     */
    public int getHealth() {
        return health;
    }

    @Override
    public void tick() {
        //Moving enemy towards player
        // 1 up
        // 2 down
        // 3 right
        // 4 left
        if(!game.getPlayer().intersects(this)){
            currentAnimation = animationRun;
            if(game.getPlayer().getX() + (game.getPlayer().getWidth()/2) > getX() + (getWidth()/2)){
                setX(getX() +  speed);
                direction = 3;
            }
            else if(game.getPlayer().getX() + (game.getPlayer().getWidth()/2)<= getX() + (getWidth()/2)){
                setX(getX() - speed);
                direction = 4;
            }
            if(game.getPlayer().getY() + (game.getPlayer().getHeight()/2)> getY() + (getHeight()/2)){
                setY(getY() + speed);
                direction = 2;
            }
            else if(game.getPlayer().getY() + (game.getPlayer().getHeight()/2)<= getY()  + (getHeight()/2)){
                setY(getY() - speed);
                direction = 1;
            }
        }
        else{
            currentAnimation = animationAttack;
            if(currentAnimation.isLoopCompleted()){
                game.getPlayer().setHealth(game.getPlayer().getHealth()-damage);
                game.getPlayer().setIsHit(true);
            }
            else{
                game.getPlayer().setIsHit(false);
            }
        }
        this.currentAnimation.tick();
    }

    @Override
    public void render(Graphics g) {
        // Getting degrees between player and enemy
        int distY = game.getPlayer().getY() + (28) - getY() - (28);
        int distX = game.getPlayer().getX() + (28) - getX() - (28);
        double radians;
        radians = Math.atan2(distY, distX);
        
        //drawing rotated image towards enemy
        ImageIcon icon = new ImageIcon(currentAnimation.getCurrentFrame());
        BufferedImage blankCanvas = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)blankCanvas.getGraphics();
        g2.rotate(radians, icon.getIconWidth() / 2, icon.getIconHeight() / 2);
        g2.drawImage(currentAnimation.getCurrentFrame(), 0, 0, null);
        g.drawImage(blankCanvas, getX(), getY(), 
                56, 56, null);

        //drawing health over enemy
        g.drawString("Health: " + health, getX()+58/3, getY());
    }
}
