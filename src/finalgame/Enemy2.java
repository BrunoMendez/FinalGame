/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author bruno
 */
public class Enemy2 extends Item {
    private Game game;
    private int speedX;      //enemy speedX
    private int speedY;     //enemy speedY
    private int points;     //points the enemy grants
    private int health;     //enemy health
    private int direction; 
    private long lastTime;

    public Enemy2(int x, int y, int width, int height, int speedX, int speedY, int health, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speedX = speedX;
        this.speedY = speedY;
        this.health = health;
    }

    /**
     * To get the speedX of the enemy
     * @return an <code>int</code> of the speedX of the enemy
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * To set the speed of the enemy
     * @param speed To set the speedX of the enemy
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    /**
     * To get the speedY of the enemy
     * @return an <code>int</code> of the speedY of the enemy
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * To set the speedY of the enemy
     * @param speedY to get the speedY of the enemy
     */
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
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
    
    /*
    Directions:
    1 = up
    2 = down
    3 = right
    4 = left
    */
    /**
     * To get the enemy direction
     * @return an <code>int</code> of the enemy direction
     */
    public int getDirection() {
        return direction;
    }
    
    public void setLastTime(long time) {
        lastTime = time;
    }
    
    public long getLastTime() {
        return lastTime;
    }

    @Override
    public void tick() {
        setX(getX() + speedX);
        setY(getY() + speedY);
        if (speedX == 0) {
            if (speedY < 0) {
                direction = 1;
            } else if (speedY > 0) {
                direction = 2;
            }
        } else if (speedY == 0) {
            if (speedX > 0) {
                direction = 3;
            } else if (speedX < 0) {
                direction = 4;
            }
        } else {
            direction = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        /*
        if (enemyType == 0) {
            g.drawImage(img, x, y, observer);
        } else {
            g.drawImage(img, x, y, observer);
        }
        */
        //g.drawImage(Assets.protester, getX(), getY(), getWidth(), getHeight(), null);
    }
}
