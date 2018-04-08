package finalgame;


import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bruno
 */
public class Assets {
    public static BufferedImage background; // to strore background image
    public static BufferedImage player; // to store the player image
    public static BufferedImage enemy;
    public static BufferedImage pause;
    public static BufferedImage win;
    public static BufferedImage loose;
    public static BufferedImage sprites;
    public static BufferedImage playerE[];
    public static BufferedImage playerD[];
    public static BufferedImage playerA[];
    public static BufferedImage playerQ[];
    
    public static void init(){
        background = ImageLoader.loadImage("/images/background-exam.jpg");
        sprites = ImageLoader.loadImage("/images/spritesheet2.png");
        enemy = ImageLoader.loadImage("/images/ghost.png");
        pause = ImageLoader.loadImage("/images/pause.jpg");
        win = ImageLoader.loadImage("/images/win.jpg");
        loose = ImageLoader.loadImage("/images/loose.jpg");
        
        SpriteSheet spritesheet = new SpriteSheet(sprites);
        playerE = new BufferedImage[6];
        playerD = new BufferedImage[6];
        playerA = new BufferedImage[6];
        playerQ = new BufferedImage[6];
       
        
        
        for(int i = 0; i<6; i++) {
            playerD[i] = spritesheet.crop((i+6)*32, 0, 32, 32);
            playerA[i] = spritesheet.crop((i+6)*32, 32, 32, 32);
            playerQ[i] = spritesheet.crop((i+6)*32, 64, 32, 32);
            playerE[i] = spritesheet.crop((i+6)*32, 96, 32, 32);
        }
        
    }
}
