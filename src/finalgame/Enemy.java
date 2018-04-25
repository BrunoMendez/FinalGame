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

    private Game game;
    private int speedX;      //enemy speedX
    private int speedY;     //enemy speedY
    private int points;     //points the enemy grants
    private int enemyType;  //0 - normal, 1 - shooter
    private int health;     //enemy health
    private Animation animationRun;

    public Enemy(int x, int y, int width, int height, int speedX, int speedY, int health, int enemyType, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speedX = speedX;
        this.speedY = speedY;
        this.health = health;
        this.enemyType = enemyType;
        this.animationRun = new Animation(Assets.zombieRun, 100);
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
        if(game.getPlayer().getX() + game.getPlayer().getWidth()/2 > getX()){
            setX(getX() +  1);
        }
        else if(game.getPlayer().getX() + game.getPlayer().getWidth()/2 < getX()){
            setX(getX() - 1);
        }
        if(game.getPlayer().getY() + game.getPlayer().getHeight()/2 > getY()){
            setY(getY() + 1);
        }
        else if(game.getPlayer().getY() - game.getPlayer().getHeight()/2 < getY()){
            setY(getY() - 1);
        }
        this.animationRun.tick();
    }

    @Override
    public void render(Graphics g) {
        int distY = game.getPlayer().getY() + game.getPlayer().getHeight()/2 - getY();
        int distX = game.getPlayer().getX() + game.getPlayer().getWidth()/2 - getX();
        //degrees between player and enemy
        double radians;
        radians = Math.atan2(game.getPlayer().getY() - getY(), game.getPlayer().getX() - getX());
        System.out.println(radians);
           
        ImageIcon icon = new ImageIcon(animationRun.getCurrentFrame());
        BufferedImage blankCanvas = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)blankCanvas.getGraphics();
        g2.rotate(radians, icon.getIconWidth() / 2, icon.getIconHeight() / 2);
        g2.drawImage(animationRun.getCurrentFrame(), 0, 0, null);
        g.drawImage(blankCanvas, getX(), getY(), 
                getWidth(), getHeight(), null);

        //g.setColor(Color.black);
        //g.fillRect(getX(), getY(), getWidth(), getHeight());
        /*
        if (enemyType == 0) {
            g.drawImage(img, x, y, observer);
        } else {
            g.drawImage(img, x, y, observer);
        }
        */
        //g.drawImage(Assets.protester, getX(), getY(), getWidth(), getHeight(), null);
    }
    
    public void rotateImage(double degrees){
        ImageIcon icon = new ImageIcon(animationRun.getCurrentFrame());
        BufferedImage blankCanvas = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)blankCanvas.getGraphics();
        g2.rotate(Math.toRadians(degrees), icon.getIconWidth() / 2, icon.getIconHeight() / 2);
        g2.drawImage(animationRun.getCurrentFrame(), 0, 0, null);
    }
}
