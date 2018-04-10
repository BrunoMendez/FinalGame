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

public class EnemyBullet extends Item{

    private int width;
    private int height;
    private Game game;
    
    public EnemyBullet(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    /**
     * To set the width of the enemy bullet
     * @param width to set the width of the enemy bullet
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * To set the height of the enemy bullet
     * @param height to set the height of the enemy bullet
     */
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick() {
        setY(getY() + 7);
    }

    @Override
    public void render(Graphics g) {
        //draw the enemy bullet
        g.setColor(Color.blue);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
