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
    private int direction;      // Direction the player is facing
    private Rectangle hitBox;   //hit box
    
    /**
     * <code>Bullet</code> Constructor 
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of the bullet
     * @param height height of the bullet
     * @param game Game instance
     */
    public Bullet(int x, int y, int width, int height, int speed, int direction, int type, Game game) {
        super(x, y, width, height);
        this.type = type;
        this.game = game;
        this.speed = speed;
        this.direction = direction;
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

    public int getType() {
        return type;
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
        /*
        Player direction 
        1 = up
        2 = down
        3 = right
        4 = left
        */
        //  PISTOL shot
        if(type == 1){
            if(direction == 1){
                setY(getY() - speed);
            }
            else if(direction == 2){
                // tilt bulle
                setY(getY() + speed);
            }
            else if(direction == 3){;
                setX(getX() + speed);
            }
            else if(direction == 4){
                setX(getX() - speed);
            }
        }
        //  SHOTGUN 1st shot
        if(type == 2){
            if(direction == 1){
                setY(getY() - speed);
                setX(getX() - speed/10);
            }
            else if(direction == 2){
                // tilt bulle
                setY(getY() + speed);
                setX(getX() - speed/10);
            }
            else if(direction == 3){;
                setX(getX() + speed);
                setY(getY() - speed/10);
            }
            else if(direction == 4){
                setX(getX() - speed);
                setY(getY() + speed/10);
            }
        }
        //  SHOTGUN 2nd shot
        if(type == 22){
            if(direction == 1){
                setY(getY() - speed);
                setX(getX() + speed/10);
            }
            else if(direction == 2){
                // tilt bulle
                setY(getY() + speed);
                setX(getX() + speed/10);
            }
            else if(direction == 3){;
                setX(getX() + speed);
                setY(getY() + speed/10);
            }
            else if(direction == 4){
                setX(getX() - speed);
                setY(getY() - speed/10);
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
