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
    protected int x;        // to store x position
    protected int y;        // to store y position
    protected int width;    // to store item width
    protected int height;   // to store item height
    
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
    
    /**
     * To get the width of the item
     * @return an <code>int</code> of the width of the player
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the item
     * @return an <code>int</code> of the height of the player
     */
    public int getHeight() {
        return height;
    }

    /**
     * To set the width of the item
     * @param width to set the width of the item
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set the height of the item
     * @param height to set the height of the item
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * To check intersection of this with obj
     * @param obj to check intersection with this
     * @return a <code>boolean</code> of this intersecting with obj
     */
    public boolean intersects(Object obj){
        return (obj instanceof Item && 
                this.getBounds().intersects(((Item) obj).getBounds()));
    }
    
    /**
     * To get the bounds of the item
     * @return a <code>Rectangle</code> of the bounds of the item
     */
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
