package finalgame;


import java.awt.image.BufferedImage;

/**
 *
 * @author bruno
 */
public class Assets {
    public static BufferedImage background;     // to strore background image
    public static BufferedImage pause;          // to store the pause image
    public static BufferedImage win;            // to store the win image
    public static BufferedImage loose;          // to store the loose image
    public static BufferedImage menu1;          // to store the menu1 image
    public static BufferedImage menu2;          // to store the menu2 image
    public static BufferedImage box[];          // to store the box image
    public static BufferedImage rock[];
    public static BufferedImage playerUp[];      //to store player up animation
    public static BufferedImage playerDown[];      //to store player down animation
    public static BufferedImage playerRight[];     //to store player right animation
    public static BufferedImage playerLeft[];     //to store player left animation
    public static BufferedImage zombieRun[];    //zombie run animation
    public static BufferedImage zombieAttack[]; //zombie attack animation
    public static BufferedImage playerFeet[];
    public static BufferedImage playerPistol[];
    public static BufferedImage playerShotgun[];
    public static BufferedImage pistolShoot[];
    public static BufferedImage shotgunShoot[];
    
    /**
     * To initialize assets
     */
    public static void init(){
        background = ImageLoader.loadImage("/images/background-exam.jpg");
        //pause = ImageLoader.loadImage("/images/pause.jpg");
        //win = ImageLoader.loadImage("/images/win.jpg");
        //loose = ImageLoader.loadImage("/images/loose.jpg");
        SpriteSheet ssRock = new SpriteSheet(ImageLoader.loadImage("/images/rock.png"));
        rock = new BufferedImage[1];
        rock[0] = ssRock.crop(0, 0, 256, 256);
        
        //  To store the animation of the box
        SpriteSheet ss = new SpriteSheet(ImageLoader.loadImage("/images/box1.png"));
        SpriteSheet ss2 = new SpriteSheet(ImageLoader.loadImage("/images/box2.png"));
	box = new BufferedImage[1];
	box[0] = ss.crop(0, 0, 180, 150);
        
        //To store the player animation       
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
        
        //To store zombie running animation
        zombieRun = new BufferedImage[32];
        for(int i = 0; i<32; i++){
            if(i>=10){
                zombieRun[i] = ImageLoader.loadImage("/images/zombie_01/run/run00"+ i + ".png");
                zombieRun[i] = zombieRun[i].getSubimage(80, 80, 112, 112);
            }
            else{
                zombieRun[i] = ImageLoader.loadImage("/images/zombie_01/run/run000"+ i + ".png");
                zombieRun[i] = zombieRun[i].getSubimage(80, 80, 112, 112);
            }
        }
        zombieAttack = new BufferedImage[19];
        for(int i = 0; i<19; i++){
            if(i>=10){
                zombieAttack[i] = ImageLoader.loadImage("/images/zombie_01/attack03/attack03_00"+ i + ".png");
                zombieAttack[i] = zombieAttack[i].getSubimage(80, 80, 112, 112);
            }
            else{
                zombieAttack[i] = ImageLoader.loadImage("/images/zombie_01/attack03/attack03_000"+ i + ".png");
                zombieAttack[i] = zombieAttack[i].getSubimage(80, 80, 112, 112);
            }
        }
        //Players feet
        playerFeet = new BufferedImage[20];
        for(int i = 0; i<20; i++){
            playerFeet[i] = ImageLoader.loadImage("/images/player/feet/run/survivor-run_"+ i + ".png");
        }
        
        //Player with pistol
        playerPistol = new BufferedImage[20];
        for(int i = 0; i<20; i++){
            playerPistol[i] = ImageLoader.loadImage("/images/player/handgun/move/survivor-move_handgun_"+ i + ".png");
        }
        
        //Player with shotgun
        playerShotgun = new BufferedImage[20];
        for(int i = 0; i<20; i++){
            playerShotgun[i] = ImageLoader.loadImage("/images/player/shotgun/move/survivor-move_shotgun_"+ i + ".png");
        }
        //shoot with pistol
        pistolShoot = new BufferedImage[3];
        for(int i = 0; i<3; i++){
            pistolShoot[i] = ImageLoader.loadImage("/images/player/handgun/shoot/survivor-shoot_handgun_"+ i + ".png");
        }
        //shoot with shotgun
        shotgunShoot = new BufferedImage[3];
        for(int i = 0; i<3; i++){
            shotgunShoot[i] = ImageLoader.loadImage("/images/player/shotgun/shoot/survivor-shoot_shotgun_"+ i + ".png");
        }
        
    }
}
