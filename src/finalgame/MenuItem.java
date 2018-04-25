/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
/**
 *
 * @author santiago
 */
public class MenuItem extends Item{
    private int itemType;
    private Game game;
    public boolean clicked;
    
    public MenuItem(int x, int y, int width, int height, int itemType, Game game) {
        super(x, y, width, height);
        this.itemType = itemType;
        this.game = game;
        clicked = false;
    }

    @Override
    public void tick() {
        
        if (clicked) {
            
            if (game.getMouseManager().getX() > getX() && game.getMouseManager().getX() < getX()+width) {
                if (game.getMouseManager().getY() > getY() && game.getMouseManager().getY() < getY()+height) {
                    System.out.println("Clicked on a Button");
                    buttonAction();
                    clicked = false;
                }
            }
        }
        clicked = false;
    }

    @Override
    public void render(Graphics g) {
        
        switch (itemType) {
            case 1:  
                g.drawImage(Assets.startButton[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 2:  
                g.drawImage(Assets.quitButton[0], getX(), getY(), getWidth(), getHeight(), null);
                break;
            
            default: g.drawImage(Assets.startButton[0], getX(), getY(), getWidth(), getHeight(), null);
                     break;
        }
        
        
    }
    public void buttonAction(){
        if (itemType == 1) {
            
            game.setStartGame(true);
        }
        else if(itemType == 2) {
            System.exit(0);
        }
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
    public boolean isClicked() {
        return clicked;
    }
    public void setClicked(boolean click){
        clicked = click;
    }
    
    
}