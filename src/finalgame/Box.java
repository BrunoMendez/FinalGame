/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.Graphics;

/**
 *
 * @author bruno
 */
public class Box extends Item{

    private Game game;
    private int x;                                       // x position of the box
    private int y;                                       // y position of the box
    private final int ItemCount = 4;                     // number of items in the box   
    private int item;                                    // item in the box
    private int counter;                                 // time that takes the box to appear
    private String[] names = new String[ItemCount];      // names of all the weapons in the box
    public int[] items = new int[ItemCount];             // the number of the item in the box
    
    /**
     * <code>Box</code> Constructor
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of the bullet
     * @param height height of the bullet
     * @param game Game instance
     */
    public Box(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }
    
    /**
     * Time that takes the box to appear in the map
     */
    public void countDown(){
        counter--;
    }
    
    /**
     * Generate a random item in the box
     * @return Index of the weapon
     */
    public int generateItem(){
        return 0;
    }
    
    /**
     * <code>counter</code> Getter
     * @return counter
     */
    public int getCounter() {
        return counter;
    }
    
    /**
     * <code>Counter</code> Setter
     * @param counter 
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }
}
