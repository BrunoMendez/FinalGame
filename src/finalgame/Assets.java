package finalgame;


import java.awt.image.BufferedImage;

/**
 *
 * @author bruno
 */
public class Assets {
    public static BufferedImage background;     // to strore background image
    public static BufferedImage player;         // to store the player image
    public static BufferedImage enemy;          // to store the enemy image
    public static BufferedImage enemy2;         // to store the enemy2 image
    public static BufferedImage pause;          // to store the pause image
    public static BufferedImage win;            // to store the win image
    public static BufferedImage loose;          // to store the loose image
    public static BufferedImage menu1;          // to store the menu1 image
    public static BufferedImage menu2;          // to store the menu2 image
    public static BufferedImage sprites;
    public static BufferedImage playerW[];      // to store the W movement of player
    public static BufferedImage playerA[];      // to store the A movement of player
    public static BufferedImage playerS[];      // to store the S movement of player
    public static BufferedImage playerD[];      // to store the D movement of player
    
    /**
     * To initialize assets
     */
    public static void init(){
        background = ImageLoader.loadImage("/images/background-exam.jpg");
        sprites = ImageLoader.loadImage("/images/spritesheet2.png");
        enemy = ImageLoader.loadImage("/images/ghost.png");
        pause = ImageLoader.loadImage("/images/pause.jpg");
        win = ImageLoader.loadImage("/images/win.jpg");
        loose = ImageLoader.loadImage("/images/loose.jpg");
        
        SpriteSheet spritesheet = new SpriteSheet(sprites);
        playerW = new BufferedImage[6];
        playerA = new BufferedImage[6];
        playerS = new BufferedImage[6];
        playerD = new BufferedImage[6];
       
        
        
        for(int i = 0; i<6; i++) {
            playerW[i] = spritesheet.crop((i+6)*32, 0, 32, 32);
            playerA[i] = spritesheet.crop((i+6)*32, 32, 32, 32);
            playerS[i] = spritesheet.crop((i+6)*32, 64, 32, 32);
            playerD[i] = spritesheet.crop((i+6)*32, 96, 32, 32);
        }
        
    }
}
