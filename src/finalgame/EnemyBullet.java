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

public class EnemyBullet extends Item{

    private int width;
    private int height;
    private Game game;
    private int speed;          // speed of the bullet
    private int damage;         // damage of the bullet
    private int type;           // type of the bullet
    private int direction;      // Direction the player is facing
    private Rectangle hitBox; 
    
    public EnemyBullet(int x, int y, int width, int height, int speed, int direction, Game game) {
        super(x, y, width, height);
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

    @Override
    public void render(Graphics g) {
        //draw the enemy bullet
        g.setColor(Color.blue);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
