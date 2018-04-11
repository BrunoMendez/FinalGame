/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

/**
 *
 * @author EnriqueVilla
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Item{

    private int width;
    private int height;
    private Game game;
    private int speed;          // speed of the bullet
    private int damage;         // damage of the bullet
    private int type;           // type of the bullet
    private Rectangle hitBox; 
    /**
     * <code>Bullet</code> Constructor 
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of the bullet
     * @param height height of the bullet
     * @param game Game instance
     */
    public Bullet(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    
    /**
     * <code>speed</code> Getter
     * @return 
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * <code>hitBoc</code> Getter
     * @return 
     */
    public Rectangle getHitBox() {
        return hitBox;
    }

    /**
     * <code>speed</code> Setter
     * @return speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * <code>hitBox</code> Setter
     * @return hitBox
     */
    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }
    
    /**
     * Tick update
     */
    @Override
    public void tick() {
        
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
