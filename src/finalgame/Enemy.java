/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author bruno
 */
public class Enemy extends Item {

    private Game game;
    private int direction;
    private int speed;
    private boolean lowest;
    private int points;

    public Enemy(int x, int y, int width, int height, int direction, int speed, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.direction = direction;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isLowest() {
        return lowest;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setLowest(boolean lowest) {
        this.lowest = lowest;
    }

    @Override
    public void tick() {
        setX(getX() + (speed * direction));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.protester, getX(), getY(), getWidth(), getHeight(), null);
    }
}
