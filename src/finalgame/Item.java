/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 *
 * @author bruno
 */
public abstract class Item {
    protected int x;    // to store x position
    protected int y;    // to store y position
    protected int width;
    protected int height;
    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Set y value
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set x value
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * get y value
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * get x value
     * @return x
     */
    public int getX() {
        return x;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    public boolean intersects(Object obj){
        return (obj instanceof Item && 
                this.getBounds().intersects(((Item) obj).getBounds()));
    }
    
    private Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    /**
     * to update positions of the item for every tick
     */
    public abstract void tick();
    
    /**
     * To paint the item
     * @param g <b>Graphics</b> object to paint the item.
     */
    public abstract void render(Graphics g);
}
