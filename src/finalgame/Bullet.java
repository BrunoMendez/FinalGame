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
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Bullet extends Item{

    private int width;
    private int height;
    private Game game;
    private int speed;          // speed of the bullet
    private int damage;         // damage of the bullet
    private int type;           // type of the bullet
    private int direction;      // Direction the player is facing
    private Rectangle hitBox;   //hit box
    private int degrees;
    
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
        degrees = 0;
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
        5 = up-right
        6 = up-left
        7 = down-right
        8 = down-left
        */
        //  PISTOL shot
        if(type == 1){
            switch(direction){
                case 1:
                    setY(getY() - speed);
                    degrees = 0;
                    break;
                case 2:
                    setY(getY() + speed);
                    degrees = 0;
                    break;
                case 3:
                    setX(getX() + speed);
                    degrees = 90;
                    break;
                case 4:
                    setX(getX() - speed);
                    degrees = 90;
                    break;
                case 5:
                    setY(getY() - speed);
                    setX(getX() + speed);
                    degrees = 45;
                    break;
                case 6:
                    setY(getY() - speed);
                    setX(getX() - speed);
                    degrees = 135;
                    break;
                case 7:
                    setY(getY() + speed);
                    setX(getX() + speed);
                    degrees = 135;
                    break;
                case 8:
                    setY(getY() + speed);
                    setX(getX() - speed);
                    degrees = 45;
                    break;              
            }
        }
        //  SHOTGUN 1st shot
        if(type == 2){
            switch(direction){
                case 1:
                    setY(getY() - speed);
                    setX(getX() - speed/10);
                    degrees = 0;
                    break;
                case 2:
                    setY(getY() + speed);
                    setX(getX() - speed/10);
                    degrees = 0;
                    break;
                case 3:
                    setX(getX() + speed);
                    setY(getY() - speed/10);
                    degrees = 90;
                    break;
                case 4:
                    setX(getX() - speed);
                    setY(getY() - speed/10);
                    degrees = 90;
                    break;
                case 5:
                    setY(getY() - speed);
                    setX(getX() + speed);
                    setX(getX() - speed/10);
                    degrees = 45;
                    break;
                case 6:
                    setY(getY() - speed);
                    setX(getX() - speed);
                    setX(getX() - speed/10);
                    degrees = 135;
                    break;
                case 7:
                    setY(getY() + speed);
                    setX(getX() + speed);
                    setY(getY() - speed/10);
                    degrees = 134;
                    break;
                case 8:
                    setY(getY() + speed);
                    setX(getX() - speed);
                    setY(getY() - speed/10);
                    degrees = 45;
                    break;              
            }
        }
        //  SHOTGUN 2nd shot
        if(type == 22){
             switch(direction){
                case 1:
                    setY(getY() - speed);
                    setX(getX() + speed/10);
                    degrees = 0;
                    break;
                case 2:
                    setY(getY() + speed);
                    setX(getX() + speed/10);
                    degrees = 0;
                    break;
                case 3:
                    setX(getX() + speed);
                    setY(getY() + speed/10);
                    degrees = 90;
                    break;
                case 4:
                    setX(getX() - speed);
                    setY(getY() + speed/10);
                    degrees = 90;
                    break;
                case 5:
                    setY(getY() - speed);
                    setX(getX() + speed);
                    setX(getX() + speed/10);
                    degrees = 45;
                    break;
                case 6:
                    setY(getY() - speed);
                    setX(getX() - speed);
                    setX(getX() + speed/10);
                    degrees = 135;
                    break;
                case 7:
                    setY(getY() + speed);
                    setX(getX() + speed);
                    setY(getY() + speed/10);
                    degrees = 135;
                    break;
                case 8:
                    setY(getY() + speed);
                    setX(getX() - speed);
                    setY(getY() + speed/10);
                    degrees = 45;
                    break;              
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        if(game.getWeapon().getType() == 3){
            g.setColor(Color.cyan);   
        }
        //g.fillRect(getX(), getY(), getWidth(), getHeight());
        //paintBullet();
        Rectangle rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(degrees),rectangle.getX() + rectangle.width/2, rectangle.getY() + rectangle.height/2);
        Shape transformed = transform.createTransformedShape(rectangle);
        Graphics2D g2 = (Graphics2D)g;
        g2.fill(transformed);
        g.setColor(Color.black);
    }
    
    public void paintBullet(Graphics g, int direction){
        
    }
}