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

public class Bullet extends Item{

    private int width;
    private int height;
    private Game game;
    
    public Bullet(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    @Override
    public void tick() {
        setY(getY() - 7);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tweet, getX(), getY(), getWidth(), getHeight(), null);
        //g.setColor(Color.red);
        //g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
