package finalgame;


import java.awt.image.BufferedImage;

/**
 *
 * @author bruno
 */
public class Assets {
    public static BufferedImage background;         // to strore background image
    public static BufferedImage menu1;              // to store the main menu image
    public static BufferedImage menu2;              // to store the pause menu image
    public static BufferedImage menu3;              // to store the GameOver menu image    
    public static BufferedImage startButton[];      // to store start button images for Menu
    public static BufferedImage quitButton[];       // to store quit button images for Menu
    public static BufferedImage resumeButton[];     // to store quit button images for Menu
    public static BufferedImage exitButton[];       // to store quit button images for Menu
    public static BufferedImage box[];              // to store the box image
    public static BufferedImage rock[];             // to store the rock image
    public static BufferedImage tree[];             // to store the tree image
    public static BufferedImage playerUp[];         // to store player up animation
    public static BufferedImage playerDown[];       // to store player down animation
    public static BufferedImage playerRight[];      // to store player right animation
    public static BufferedImage playerLeft[];       // to store player left animation
    public static BufferedImage zombieRun[];        // zombie run animation
    public static BufferedImage zombieAttack[];     // zombie attack animation
    public static BufferedImage playerFeet[];       // zombie feet animation
    public static BufferedImage playerPistol[];     // pistol player animation
    public static BufferedImage playerShotgun[];    // shotgun player animation
    public static BufferedImage pistolShoot[];      // pistol shoot animation
    public static BufferedImage shotgunShoot[];     // shotgun shoot animation
    public static SoundClip buttonClickedSound;     // to acknowledge when a button is clicked
    public static SoundClip pistolChangeSound;      // to feedback when changing weapon to pistol
    public static SoundClip shotgunChangeSound;     // to feedback when changing weapon to shotgun
    /**
     * To initialize assets
     */
    public static void init(){
        //  to store the background image
        background = ImageLoader.loadImage("/images/background-exam.jpg");
        
        //  store images for the menus
        menu1 = ImageLoader.loadImage("/images/MenuImages/MenuBackground.png");
        menu2 = ImageLoader.loadImage("/images/MenuImages/PauseBackground.png");
        menu3 = ImageLoader.loadImage("/images/MenuImages/GameOverBackground.png");

        //  store image for the button
        startButton = new BufferedImage[2];
        startButton[0] = ImageLoader.loadImage("/images/MenuImages/ButtonStartClear.png");
        
        //  store image for quit button
        quitButton = new BufferedImage[2];
        quitButton[0] = ImageLoader.loadImage("/images/MenuImages/ButtonQuitClear.png");
        
        //  store image for resume button
        resumeButton = new BufferedImage[2];
        resumeButton[0] = ImageLoader.loadImage("/images/MenuImages/ButtonResumeClear.png");
        
        //  store image for exit button
        exitButton = new BufferedImage[2];
        exitButton[0] = ImageLoader.loadImage("/images/MenuImages/ButtonExitClear.png");
        exitButton[1] = ImageLoader.loadImage("/images/MenuImages/ButtonExitClearGO.png");
        
        //  store the rock image
        SpriteSheet ssRock = new SpriteSheet(ImageLoader.loadImage("/images/rock2.png"));
        rock = new BufferedImage[1];
        rock[0] = ssRock.crop(0, 0, 160, 150);
        
        //  trees
        SpriteSheet ssTree = new SpriteSheet(ImageLoader.loadImage("/images/tree.png"));
        tree = new BufferedImage[1];
        tree[0] = ssTree.crop(0, 0, 1184, 1280);
        
        //  To store the animation of the box
        SpriteSheet ss = new SpriteSheet(ImageLoader.loadImage("/images/box3.png"));
        SpriteSheet ss2 = new SpriteSheet(ImageLoader.loadImage("/images/box4.png"));
	box = new BufferedImage[2];
	box[0] = ss.crop(0, 0, 22, 21);
        box[1] = ss2.crop(0, 0, 25, 20);
        
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
        
        // save sounds
        buttonClickedSound = new SoundClip("/sounds/buttonClickGeneral.wav");
        pistolChangeSound = new SoundClip("/sounds/pistolChange.wav");
        shotgunChangeSound = new SoundClip("/sounds/shotgunChange.wav");
        
    }
}
