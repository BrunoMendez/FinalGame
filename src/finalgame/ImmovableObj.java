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

    private Animation rock;     //animation for rock  
    private Animation tree;     //animation for tree
    private Game game;          //game attribute   
    private int type;           //type of object
    
    public ImmovableObj(int x, int y, int width, int height, int type) {
        super(x, y, width, height);
        this.game = game;
        this.type = type;
        rock = new Animation(Assets.rock, 2000);
        tree = new Animation(Assets.tree, 2000);
    }
   
    /**
     * To get the type of immovableobj
     * @return an <code>int</code> of the type of immovableobj
     */
    public int getType() {
        return type;
    }
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        //to render rock or tree based on the type
        if(getType() == 1){
            g.drawImage(rock.getCurrentFrame(), getX(), getY(), 50, 50, null);
        }
        if(getType() == 2){
            g.drawImage(tree.getCurrentFrame(), getX(), getY(), 50, 50, null);
        }
    }
    
}
