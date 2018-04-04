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
    private int speed; // for the speed of every frame
    private int index;  // to get the index of the next frame to paint
    private long lastTime;
    private long timer;
    private BufferedImage[] frames; // to store every image
    
    public Animation(BufferedImage[] frames, int speed) {
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis(); // getting initial time
    }
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
    
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if (timer > speed) {
            index++;
            timer = 0;
            
            if (index >= frames.length) {
                index = 0;
            }
        }
    }
    
}