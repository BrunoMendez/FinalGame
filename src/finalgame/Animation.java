/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.image.BufferedImage;

/**
 *
 * @author bruno
 */
public class Animation {
    private int speed;              // for the speed of every frame
    private int index;              // to get the index of the next frame to paint
    private long lastTime;          // last registered time
    private long timer;             // time passed
    private BufferedImage[] frames; // to store every image
    private boolean loopCompleted;
    
    /**
     * <b>Animation</b> constructor with specified frames and speed
     * @param frames is the array of sprites
     * @param speed is the speed the animation will run as
     */
    public Animation(BufferedImage[] frames, int speed) {
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis(); // getting initial time
        this.loopCompleted = false;
    }
    
    /**
     * <code>speed</code> Setter
     * @param speed to modify
     */
    public void setSpeed(int speed){
        this.speed = speed;
    }
    
    /**
     * <code>speed</code> Getter
     * @return speed
     */
    public int getSpeed(){
        return speed;
    }
    
    /**
     * Gets current frame of the animation
     * @return frame in grames[index]
     */
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

    public boolean isLoopCompleted() {
        return loopCompleted;
    }
    
    /**
     * Tick update
     */
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        loopCompleted = false;
        
        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= frames.length) {
                index = 0;
                loopCompleted = true;
            }
        }
    }
    
}