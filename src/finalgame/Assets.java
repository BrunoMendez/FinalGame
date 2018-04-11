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
    public static BufferedImage box[];          // to store the box image
    public static BufferedImage boxDown;
    public static BufferedImage sprites;
    public static BufferedImage playerUp[];      
    public static BufferedImage playerDown[];      
    public static BufferedImage playerRight[];     
    public static BufferedImage playerLeft[];      
    
    /**
     * To initialize assets
     */
    public static void init(){
        background = ImageLoader.loadImage("/images/background-exam.jpg");
        //sprites = ImageLoader.loadImage("/images/spritesheet2.png");
        //enemy = ImageLoader.loadImage("/images/ghost.png");
        //pause = ImageLoader.loadImage("/images/pause.jpg");
        //win = ImageLoader.loadImage("/images/win.jpg");
        //loose = ImageLoader.loadImage("/images/loose.jpg");
        
        //  To store the animation of the box
        SpriteSheet ss = new SpriteSheet(ImageLoader.loadImage("/images/box1.png"));
        SpriteSheet ss2 = new SpriteSheet(ImageLoader.loadImage("/images/box2.png"));
	box = new BufferedImage[1];
	box[0] = ss.crop(0, 0, 180, 150);
        
        //To store the player animation
         //SpriteSheet spritesheet = new SpriteSheet(sprites);
        //playerW = new BufferedImage[6];
        //playerA = new BufferedImage[6];
        //playerS = new BufferedImage[6];
        //playerD = new BufferedImage[6];
       
        SpriteSheet playerSS = new SpriteSheet (ImageLoader.loadImage("/images/player.png"));
        playerUp = new BufferedImage[9];
        playerDown = new BufferedImage[9];
        playerRight = new BufferedImage[9];
        playerLeft = new BufferedImage[9];
        for(int i = 0; i<9; i++){
            playerUp[i] = playerSS.crop(i*64, 0, 64, 64);
            playerLeft[i] = playerSS.crop(i*64, 64, 64, 64);
            playerDown[i] = playerSS.crop(i*64, 128, 64, 64);
            playerRight[i] = playerSS.crop(i*64, 192, 64, 64);
        }
        
    }
}
