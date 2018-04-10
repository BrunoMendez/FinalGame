/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author bruno
 */
public class Enemy extends Item {

    private Game game;
    private int speed;      //enemy speed
    private int points;     //points the enemy grants
    private int enemyType;  //0 - normal, 1 - shooter
    private int health;     //enemy health

    public Enemy(int x, int y, int width, int height, int speed, int health, int enemyType, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speed = speed;
        this.health = health;
        this.enemyType = enemyType;
    }

    /**
     * To get the speed of the enemy
     * @return 
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * To set the speed of the enemy
     * @param speed 
     */
    public void setSpeed(int speed) {
        this.speed = speed;
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
        setX(getX() + speed);
    }

    @Override
    public void render(Graphics g) {
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
