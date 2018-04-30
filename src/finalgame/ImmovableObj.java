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
public class ImmovableObj extends Item{

    private Animation rock;
    private Game game;
    
    public ImmovableObj(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.game = game;
        rock = new Animation(Assets.rock, 2000);
    }
   
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(rock.getCurrentFrame(), getX(), getY(), 50, 50, null);
    }
    
}
