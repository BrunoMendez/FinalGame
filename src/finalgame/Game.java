/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {
    private boolean startGame;
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private Weapon weapon;          //type of weapon
    private Box box;                // to create a box
    private ImmovableObj rock;
    private KeyManager keyManager;  // to manage the keyboard
    private MouseManager mouseManager; // to manage the mouse actions
    private ArrayList<Enemy> enemies; // my enemies
    private ArrayList<Box>  boxes;      // loot boxes
    private ArrayList<Bullet> bullets;          // player bullets
    private ArrayList<EnemyBullet> enemyBullets; //enemy bullets
    private ArrayList<ImmovableObj> rocks;
    private ArrayList<ImmovableObj> trees;
    private boolean gameOver;            // To stop the game
    private boolean started;             // to start the game
    private boolean paused;                 // to pause the game
    private long lastTime;      //to keep track of time
    private int score;                 	 //score
    private boolean win;
    private long bulletTimer;
    private long lastTimeTickBox;
    private long lastTimeTickEnemy;
    private long waveTextTimer;
    private int MAX_ENEMIES;
    private boolean startOfWave;
    private int waveCounter;
    private long lastTimeTick;      //timer for tick
    public MenuItem quitButton;     //Quit Menu Button
    public MenuItem startButton;    //Start menu Button
    public MenuItem resumeButton;    // Resume pause menu button
    public MenuItem exitButton;   //Exit pause menu button
    public boolean frameExists;   //Check whether a Frame has been created
    private SoundClip backgroundMenuMusic; // to Store background music on the Menu
    private SoundClip backgroundGameMusic; // to Store background music on Game
    private boolean musicSelect;    // to know when music should change
    
    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        startGame = false;
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        mouseManager = new MouseManager(this);
        quitButton = new MenuItem(width/2 - (width/4/2), height/5+((height/7*2+40)), width/4,height/7-40,2, this);
        startButton = new MenuItem(width/2 - (width/4/2), height/5+((height/7+50)), width/4,height/7-40,1, this);
        resumeButton = new MenuItem(width/2 - (width/4/2), height/5+((height/7+100)), width/4,height/7-40,3, this);
        exitButton = new MenuItem(width/2 - (width/4/2), height/5+((height/7*2+90)), width/4,height/7-40,4, this);
        backgroundMenuMusic = new SoundClip("/sounds/MenuSound.wav");
        backgroundGameMusic = new SoundClip("/sounds/BackgroundInGame.wav");
        
        frameExists = false;
        gameOver = false;
        started = false;
        lastTime = System.currentTimeMillis();
        MAX_ENEMIES = 7;
        startOfWave = false;
        score = 0;
        win = false;
        waveCounter = 1;
        bulletTimer = System.currentTimeMillis();
        lastTimeTickBox = System.currentTimeMillis();
        lastTimeTickEnemy = System.currentTimeMillis();
        waveTextTimer = System.currentTimeMillis();
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    /**
     * To check if the game has started
     * @return 
     */
    public boolean isStarted() {
        return started;
    }
    /**
     * check started status
    */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * initializing the display window of the game
     */
    public void init() {
        //Display
        if(frameExists == true) {
            display.getJframe().dispose();
        }
        display = new Display(title, getWidth(), getHeight());
        frameExists = true;
        Assets.init();
        backgroundGameMusic.setLooping(true);
        backgroundGameMusic.setVolume(0.4);
        backgroundGameMusic.stop();
        backgroundMenuMusic.setLooping(true);
        backgroundMenuMusic.setVolume(0.4);
        backgroundMenuMusic.play();
        //Mouse
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        //Boxes
        boxes = new ArrayList<Box>();
        for(int i = 0; i < 10; i++){
            box = new Box(ThreadLocalRandom.current().nextInt(0, getWidth() + 1), ThreadLocalRandom.current().nextInt(0, getHeight() + 1), 15, 15, this);
            boxes.add(box);
        }
        
        //Player
        player = new Player((getWidth()/2)-47, (getHeight()/2)-40, 47, 40, 100,  this);
        
        //Weapon
        weapon = new Weapon(this);
        
        //Bullets
        bullets = new ArrayList<Bullet>();
        
        //keyManager
        display.getJframe().addKeyListener(keyManager);
        
        //Mouse
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        //rocks
        rocks = new ArrayList<ImmovableObj>();
        for(int i = 0; i < 10; i++){
            int randX = ThreadLocalRandom.current().nextInt(0, getWidth() + 1);
            int randY = ThreadLocalRandom.current().nextInt(0, getHeight() + 1);
            ImmovableObj rock = new ImmovableObj(randX, randY, 50, 50, 2);
            rocks.add(rock); 
        }
        
        //trees
        trees = new ArrayList<ImmovableObj>();
        for(int i = 0; i < 10; i++){
            int randX = ThreadLocalRandom.current().nextInt(0, getWidth() + 1);
            int randY = ThreadLocalRandom.current().nextInt(0, getHeight() + 1);
            ImmovableObj tree = new ImmovableObj(randX, randY, 50, 50, 1);
            trees.add(tree);
        }
        
        //enemies
        enemies = new ArrayList<Enemy>();
        for(int i = 0; i < 5; i++){
            int randX = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getWidth()) 
                    : 3 * -(int)(Math.random() * getWidth());
            int randY = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getHeight())
                    : 3 * -(int)(Math.random() * getHeight());
            Enemy enemy = new Enemy(randX, randY, 30, 30, 2, 0, 100, 0, this);
            enemies.add(enemy); 
        }
        display.getJframe().addKeyListener(keyManager);
    }

    @Override
    public void run() {
        
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            // if delta is positive we tick the game
            if (delta >= 1) {
                
                if (musicSelect) {
                    if (startGame) {
                        backgroundMenuMusic.stop();
                        backgroundGameMusic.play();
                    }
                    else {
                        backgroundGameMusic.stop();
                        backgroundMenuMusic.play();  
                    }
                    musicSelect = false;
                }
                
                tick();
                render();
                delta--;
            }
        }
        stop();
    }
    /**
     * 
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }
    /**
     * 
     * @return mouseManager
     */
    public MouseManager getMouseManager() {
        return mouseManager;
    }
    
    /**
     * 
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * <code>Box</code> Getter
     * @return box
     */
    public Box getBox() {
        return box;
    }
    /**
     * 
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<EnemyBullet> getEnemyBullets() {
        return enemyBullets;
    }

    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

        
    public Weapon getWeapon() {
        return weapon;
    }
    
    public boolean getStartGame() {
        return startGame;
    }
    public void setStartGame(boolean s) {
        startGame = s;
    }
    public boolean getPaused() {
        return paused;
    }
    public void setPaused(boolean p) {
        paused = p;
    }
    /**
     * main tick function
     */
    private void tick() {
        keyManager.tick();
        //check if game is started
        if(startGame){
            paused = keyManager.getP();
        }
        else{
            paused = false;
        }
        // checks if game over
        if(player.getHealth() <= 0){
            gameOver = true;
        }
        //checks if we are in the main menu
        if (!startGame && ! paused && !gameOver) {
            tickMainMenu();
        }
        //checks if game is startes and unpaused
        if (startGame && !paused && !gameOver) {
            tickGame();
        }
        //checks if game is paused
        if (paused && !gameOver) {
            tickPauseMenu();
        }
        if (gameOver) {
            tickGameOverMenu();
        }
    }
    
    private void tickGame() {
        //checks if player is over box
        PlayerOverBox();
        //spawns random boxes
        box.tick();
        //weapon type
        weapon.tick();
        //player movement
        player.tick();
        //shoot tick
        shootPlayer();
        BulletTick();
        //randomly create new boxes
        createNewBox();
        enemySearchPlayer();
        wavesControl(); 
        PlayerCollisionRocks();
        EnemyCollisonRocks();
        EnemyCollisonTrees();
        PlayerCollisionTrees();
        
    }
    
    private void tickMainMenu() {
        quitButton.tick(g);
        startButton.tick(g);
    }
    
    private void tickPauseMenu() {
        resumeButton.tick(g);
        exitButton.tick(g);
    }
    
    private void tickGameOverMenu() {
        exitButton.tick(g);
    }
    
    /**
     *  Player collision with rocks
     */
    public void PlayerCollisionRocks(){
        Iterator itr = rocks.iterator();
        while(itr.hasNext()){
            ImmovableObj rock = (ImmovableObj) itr.next();
            rock.tick();
            if(player.intersects(rock)){
                if(player.getDirection() == 4){
                    player.setX(player.getX()+4);
                }
                else if(player.getDirection() == 3){
                    player.setX(player.getX()-4);
                }
                else if(player.getDirection() == 1){
                    player.setY(player.getY()+4);
                }
                else if(player.getDirection() == 2){
                    player.setY(player.getY()-4);
                }
                if(player.getDirection() == 5){
                    player.setY(player.getY()+2);
                    player.setX(player.getX()-3);
                }
                else if(player.getDirection() == 6){
                    player.setY(player.getY()+2);
                    player.setX(player.getX()+3);
                }
                else if(player.getDirection() == 7){
                    player.setY(player.getY()-2);
                    player.setX(player.getX()-3);
                }
                else if(player.getDirection() == 8){
                    player.setY(player.getY()-2);
                    player.setX(player.getX()+3);
                }
            }
        }
    }
    
    /**
     *  Player collision with trees
     */
    public void PlayerCollisionTrees(){
        Iterator itr = trees.iterator();
        while(itr.hasNext()){
            ImmovableObj tree = (ImmovableObj) itr.next();
            tree.tick();
            if(player.intersects(tree)){
                if(player.getDirection() == 4){
                    player.setX(player.getX()+4);
                }
                else if(player.getDirection() == 3){
                    player.setX(player.getX()-4);
                }
                else if(player.getDirection() == 1){
                    player.setY(player.getY()+4);
                }
                else if(player.getDirection() == 2){
                    player.setY(player.getY()-4);
                }
                if(player.getDirection() == 5){
                    player.setY(player.getY()+2);
                    player.setX(player.getX()-3);
                }
                else if(player.getDirection() == 6){
                    player.setY(player.getY()+2);
                    player.setX(player.getX()+3);
                }
                else if(player.getDirection() == 7){
                    player.setY(player.getY()-2);
                    player.setX(player.getX()-3);
                }
                else if(player.getDirection() == 8){
                    player.setY(player.getY()-2);
                    player.setX(player.getX()+3);
                }
            }
        }
    }
    
    /**
     * Enemy collision with rocks
     */
    public void EnemyCollisonRocks(){
        Iterator itr = rocks.iterator();
        while(itr.hasNext()){
            ImmovableObj rock = (ImmovableObj) itr.next();
            Iterator itr2 = enemies.iterator();
            while(itr2.hasNext()){
                Enemy enemy = (Enemy) itr2.next();
                if(enemy.intersects(rock)){
                    itr2 = enemies.iterator();
                    if(enemy.getX() <= rock.getX() + rock.width && enemy.getDirection() == 4){
                        enemy.setX(enemy.getX()+2);
                        enemy.setY(enemy.getY()+5);
                    }
                    if(enemy.getX() + enemy.width >= rock.getX() && enemy.getDirection() == 2){
                        enemy.setX(enemy.getX()-2);
                        enemy.setY(enemy.getY()-5);
                    }
                    if(enemy.getY() <= rock.getY() + rock.height && enemy.getDirection() == 1){
                        enemy.setY(enemy.getY()+5);
                        enemy.setX(enemy.getX()-2);
                    }
                    if(enemy.getY() + enemy.height >= rock.getY() && enemy.getDirection() == 2){
                        enemy.setY(enemy.getY()-5);
                        enemy.setX(enemy.getX()+2);
                    }
                }
            } 
        }
    }
    
        /**
     * Enemy collision with tree
     */
    public void EnemyCollisonTrees(){
        Iterator itr = trees.iterator();
        while(itr.hasNext()){
            ImmovableObj tree = (ImmovableObj) itr.next();
            Iterator itr2 = enemies.iterator();
            while(itr2.hasNext()){
                Enemy enemy = (Enemy) itr2.next();
                if(enemy.intersects(tree)){
                    itr2 = enemies.iterator();
                    if(enemy.getX() <= tree.getX() + tree.width && enemy.getDirection() == 4){
                        enemy.setX(enemy.getX()+2);
                        enemy.setY(enemy.getY()+5);
                    }
                    if(enemy.getX() + enemy.width >= tree.getX() && enemy.getDirection() == 2){
                        enemy.setX(enemy.getX()-2);
                        enemy.setY(enemy.getY()-5);
                    }
                    if(enemy.getY() <= tree.getY() + tree.height && enemy.getDirection() == 1){
                        enemy.setY(enemy.getY()+5);
                        enemy.setX(enemy.getX()-2);
                    }
                    if(enemy.getY() + enemy.height >= tree.getY() && enemy.getDirection() == 2){
                        enemy.setY(enemy.getY()-5);
                        enemy.setX(enemy.getX()+2);
                    }
                }
            } 
        }
    }
    /**
     *  Waves control, start a new wave
     */
    public void wavesControl(){
        if(enemies.size() <= 0){
            waveCounter++;
            MAX_ENEMIES = MAX_ENEMIES + 3;
            for(int i = 0; i < MAX_ENEMIES; i++){
                int randX = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getWidth()) 
                        : 3 * -(int)(Math.random() * getWidth());
                int randY = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getHeight())
                        : 3 * -(int)(Math.random() * getHeight());
                Enemy enemy = new Enemy(randX, randY, 56, 56, 2, 0, 100, 0, this);
                enemies.add(enemy); 
            }
        }
    }
    
    public void enemySearchPlayer(){
        // getting every enemy by using iterator
        Iterator itr = enemies.iterator();
        while(itr.hasNext()){
            Enemy enemy = (Enemy) itr.next();
            //  moving the enemy
            enemy.tick();
        }
    }
    
    /**
     * Check if player is over the box
     */
    public void PlayerOverBox(){
        Iterator itr = boxes.iterator();
        while(itr.hasNext()){
            Box box = (Box) itr.next();
            box.tick();
            if(getPlayer().intersects(box)){
                //  Guarda la weapon a la que se le va a agregar la municion
                int randomWeapon = 1;
                //  Remover la box cuando la "pisa"
                boxes.remove(box);
                itr = boxes.iterator();
                //  Random weapon
                if(waveCounter >= 3){
                    randomWeapon = ThreadLocalRandom.current().nextInt(1, 2 + 1);   
                }
                if(waveCounter >= 4){
                    randomWeapon = ThreadLocalRandom.current().nextInt(1, 3 + 1);   
                }
                int randomAmmo = ThreadLocalRandom.current().nextInt(5, 10 + 1);
                if(waveCounter >= 8){
                    randomAmmo = ThreadLocalRandom.current().nextInt(5, 10 + waveCounter/2 + 1);
                }
                //System.out.println("" + randomWeapon);
                if(randomWeapon == 1){
                weapon.setAmmoPISTOL(weapon.getAmmoPISTOL()+randomAmmo);
                }
                if(randomWeapon == 2){
                weapon.setAmmoSHOTGUN(weapon.getAmmoSHOTGUN()+randomAmmo);
                }
                if(randomWeapon == 3 && weapon.getAmmoLASER() <= 100){
                    if(weapon.getAmmoLASER() + randomAmmo > 100){
                        weapon.setAmmoLASER(100);
                    }
                    else {
                        weapon.setAmmoLASER(weapon.getAmmoLASER()+randomAmmo);
                    }
                }
            }
        }
    } 
    
    /**
     * Creates a new box every 10seconds
     */
    public void createNewBox(){
        if(System.currentTimeMillis() - lastTimeTickBox > 10000){
            lastTimeTickBox = System.currentTimeMillis();
            boxes.add(new Box(ThreadLocalRandom.current().nextInt(0, getWidth() + 1), ThreadLocalRandom.current().nextInt(0, getHeight() + 1), 15, 15, this));
        }
    }
    
    public void shootEnemy() {
        Iterator itr = enemies.iterator();
        while (itr.hasNext()) {
            Enemy enemy = (Enemy) itr.next();
            if (System.currentTimeMillis() - enemy.getLastTime() >= 500) {
                switch (enemy.getDirection()) {
                case 1: 
                    enemyBullets.add(new EnemyBullet(enemy.getX() + 
                            enemy.getWidth()/2, enemy.getY(),
                        5, 20, 10, enemy.getDirection(), this));
                    break;
                case 2: 
                    enemyBullets.add(new EnemyBullet(enemy.getX() + 
                            enemy.getWidth()/2, enemy.getY() + enemy.getHeight(),
                        5, 20, 10, enemy.getDirection(), this));
                    break;
                case 3:
                    enemyBullets.add(new EnemyBullet(enemy.getX() + 
                            enemy.getWidth(), enemy.getY() + enemy.getHeight()/2 
                                    + 10, 20, 5, 10, enemy.getDirection(), this));
                    break;
                case 4:
                    enemyBullets.add(new EnemyBullet(enemy.getX(), enemy.getY()
                            + enemy.getHeight()/2,
                        20, 5, 10, enemy.getDirection(), this));
                    break;
                }
                enemy.setLastTime(System.currentTimeMillis());
            }
            enemy.tick();
        }
    }
    
    public void shootPlayer(){
        //  PISTOL
        if(this.getKeyManager().space && System.currentTimeMillis() - bulletTimer >= 500 && weapon.getType() == 1 && weapon.getAmmoPISTOL() > 0){
            weapon.setAmmoPISTOL(weapon.getAmmoPISTOL()-1);
            if(player.getDirection() == 4){
               bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY(),
                        5, 20, 10, player.getDirection(), 1, this)); 
            }
            if(player.getDirection() == 3){
                bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2,
                        5, 20, 10, player.getDirection(), 1, this));
            }
            if(player.getDirection() == 2){
                bullets.add(new Bullet(player.getX() + player.getWidth()/4, player.getY() + player.getHeight()/2,
                        5, 20, 10, player.getDirection(), 1, this));
            }
            if(player.getDirection() == 1){
                bullets.add(new Bullet(player.getX() + player.getWidth() - player.getWidth()/4, player.getY(),
                        5, 20, 10, player.getDirection(), 1, this));
            }
            bulletTimer = System.currentTimeMillis();
            Assets.pistolShotSound.play();
        }
        //  SHOTGUN
        if(this.getKeyManager().space && System.currentTimeMillis() - bulletTimer >= 500 && weapon.getType() == 2 && weapon.getAmmoSHOTGUN() > 0){
            weapon.setAmmoSHOTGUN(weapon.getAmmoSHOTGUN()-1);
            bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2,
                        5, 20, 10, player.getDirection(), 1, this));
            bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2,
                        5, 20, 10, player.getDirection(), 2, this));
            bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2,
                        5, 20, 10, player.getDirection(), 22, this));
            bulletTimer = System.currentTimeMillis();
             Assets.shotgunShotSound.play();
        }
        //  LASER
        if(this.getKeyManager().space && weapon.getType() == 3 && weapon.getAmmoLASER() > 0){
            weapon.setAmmoLASER(weapon.getAmmoLASER()-1);
            bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2,
                        5, 20, 10, player.getDirection(), 1, this));   
        }
    }
    
    
    /**
     * Shoot of the enemy (Not implemented yet)
     */
    public void enemyBulletTick() {
        Iterator itr = enemyBullets.iterator();
        while (itr.hasNext()) {
            EnemyBullet enemyBullet = (EnemyBullet) itr.next();
            enemyBullet.tick();
            if (enemyBullet.getY() <= 0) {
                enemyBullets.remove(enemyBullet);
                itr = enemyBullets.iterator();
            } else {
                boolean crashed = false;
                //if enemybullet crashes player
                while (!crashed){
                    if (player.intersects(enemyBullet)){
                        //player.setHealth(player.getHealth() - cantidad);
                        enemyBullets.remove(enemyBullet);
                        itr = enemyBullets.iterator();
                        crashed = true;
                    }
                } 
            }
        }
    }
    
    /**
     * Bullet tick function
     */
    public void BulletTick(){
               // getting every bullet by using iterator
        Iterator itr = bullets.iterator();
        while(itr.hasNext()){
            // getting specific bullets
            Bullet bullet = (Bullet) itr.next();
            //  moving the enemy
            bullet.tick();
            if(bullet.getY() >= height){
               bullets.remove(bullet);
               itr = bullets.iterator(); 
            }
            if(bullet.getX() >= width){
               bullets.remove(bullet);
               itr = bullets.iterator(); 
            }
            if(bullet.getX() <= 0){
               bullets.remove(bullet);
               itr = bullets.iterator(); 
            }
            //  if bullet is out of the screen
            if (bullet.getY() <= 0){
                //  re set y position
                bullets.remove(bullet);
                itr = bullets.iterator();
                System.out.println(bullets.size());
            } else {
                //  create a boolean to check removing bullet
                boolean crashed = false;
                // if bullet crashes an enemy 
                Iterator itr2 = enemies.iterator();
                while(itr2.hasNext() && !crashed){
                    //  get specific enemy 
                    Enemy enemy = (Enemy) itr2.next();
                    if (enemy.intersects(bullet)){
                        //  Daño pistol
                        if(weapon.getType() == 1){
                            enemy.setHealth(enemy.getHealth()-30);
                        }
                        //  Daño shotgun
                        if(weapon.getType() == 2){
                            enemy.setHealth(enemy.getHealth()-30);
                        }
                        //  Daño laser
                        if(weapon.getType() == 3){
                            enemy.setHealth(enemy.getHealth()-40);
                        }
                        // remove enemy
                        itr2 = enemies.iterator();
                        if(enemy.getHealth() <= 0){
                            enemy.setHealth(100);
                            enemies.remove(enemy);
                        }
                        //  delete bullet
                        bullets.remove(bullet);
                        itr = bullets.iterator();
                        //  set the crashon
                        crashed = true;
                    }
                } 
                //  Bullets se destruyen al chocar con una roca o arbol
                Iterator itr3 = rocks.iterator();
                while(itr3.hasNext()){
                    ImmovableObj rock = (ImmovableObj) itr3.next();
                    if(rock.intersects(bullet)){
                        bullets.remove(bullet);
                        itr = bullets.iterator();
                    }
                }
                Iterator itr4 = trees.iterator();
                while(itr4.hasNext()){
                    ImmovableObj tree = (ImmovableObj) itr4.next();
                    if(tree.intersects(bullet)){
                        bullets.remove(bullet);
                        itr = bullets.iterator();
                    }
                }
            }
        }
    }
    
    /**
     * Decide which Block of assets to render: Main, Game, Pause, or GameOver
     */
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            //Get graphics
            g = bs.getDrawGraphics();
            
            if (!startGame && !paused && !gameOver){
                renderMenuScreen(g);
            }
            if (startGame && !paused && !gameOver) {
                renderGame(g);
            }
            if (paused && !gameOver) {
                renderPauseMenu(g);
                
            }
            if (gameOver) {
                renderGameOverMenu(g);
            }
            
        }

    }
    /**
     * Render Main Menu screen
     * @param g 
     */
    public void renderMenuScreen(Graphics g) {
        //render Background
         g.drawImage(Assets.menu1, 0, 0, width, height, null);
         startButton.render(g);
         quitButton.render(g);
         bs.show();
         g.dispose();
    }
    /**
     * Render Game
     * @param g 
     */
    private void renderGame(Graphics g) {
         // get the buffer strategy from the display
            //draw background
            g.drawImage(Assets.background, 0, 0, width, height, null);
            //render rocks
            Iterator itrRock = rocks.iterator();
            while(itrRock.hasNext()){
                ImmovableObj rock = (ImmovableObj) itrRock.next();
                rock.render(g);
            }
            //render trees
            Iterator itrTree = trees.iterator();
            while(itrTree.hasNext()){
                ImmovableObj tree = (ImmovableObj) itrTree.next();
                tree.render(g);
            }
            //render boxes
            Iterator itrB = boxes.iterator();
            while(itrB.hasNext()){
                Box box = (Box) itrB.next();
                box.render(g);
            }
            //render enemies
            for (Enemy enemy : enemies) {
                enemy.render(g);
            }
            //render player
            player.render(g);
            //render bullets
            Iterator itr = bullets.iterator();
            while(itr.hasNext()){
                Bullet bullet = (Bullet) itr.next();
                bullet.render(g);
            }
            //string showing what weapon player is using.
            if(weapon.getType() == 1){
                g.drawString("PISTOL", player.getX()+player.width/3, player.getY());
                g.drawString("Ammo: " + weapon.getAmmoPISTOL(), 10, height);
            }
            if(weapon.getType() == 2){
                g.drawString("SHOTGUN", player.getX()+player.width/4, player.getY());
                g.drawString("Ammo: " + weapon.getAmmoSHOTGUN(), 10, height);
            }
            if(weapon.getType() == 3){
                g.drawString("LASER", player.getX()+player.width/4, player.getY());
                g.drawString("Ammo: " + weapon.getAmmoLASER(), 10, height);
            }
            //  Display the number of the wave
            g.drawString("Wave: " + waveCounter, 10, height-30);
            //  Display the number of enemies in the map
            g.drawString("Enemies: " + enemies.size(), 10, height - 15);
            bs.show();
            g.dispose();
    }
    /**
     * Render game Paused Menu with two buttons
     * Press Resume to continue
     * Press Exit to restart and go back to Main Menu
     * @param g 
     */
    private void renderPauseMenu(Graphics g) {
         g.drawImage(Assets.menu2, width/2 - 250, height/2 - 250, 500, 500, null);
         resumeButton.render(g);
         exitButton.render(g);
         bs.show();
         g.dispose();
    }
    
    /**
     * render game over menu 
     */
    private void renderGameOverMenu(Graphics g) {
        g.drawImage(Assets.menu3, width/2 - 300, height/2 - 300, 600, 600, null);
        exitButton.render(g);
        bs.show();
        g.dispose();
    }
    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
                
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    void setGameOver(boolean b) {
       gameOver = b;
    }
    
    public boolean getGameOver(){
        return gameOver;
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isStartGame() {
        return startGame;
    }
    
    public boolean isMusicSelect() {
        return musicSelect;
    }
    
    public void setMusicSelect(boolean m) {
        musicSelect = m;
    }
   
}