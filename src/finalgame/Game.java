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
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {

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
    private ArrayList<Enemy> enemies; // my enemies
    private ArrayList<Box>  boxes;      // loot boxes
    private ArrayList<Bullet> bullets;          // player bullets
    private ArrayList<EnemyBullet> enemyBullets; //enemy bullets
    private ArrayList<ImmovableObj> rocks;
    private boolean gameOver;            // To stop the game
    private boolean started;             // to start the game
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

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        gameOver = false;
        started = false;
        lastTime = System.currentTimeMillis();
        MAX_ENEMIES = 7;
        startOfWave = false;
        score = 0;
        //tweet = new SoundClip("/sounds/twitter_ios.wav", 0, false);
        //hit = new SoundClip("/sounds/hit.wav", 0, false);
        win = false;
        waveCounter = 0;
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

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        //Display
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        
        //Boxes
        boxes = new ArrayList<Box>();
        for(int i = 0; i < 6; i++){
            box = new Box(ThreadLocalRandom.current().nextInt(0, getWidth() + 1), ThreadLocalRandom.current().nextInt(0, getHeight() + 1), 15, 15, this);
            boxes.add(box);
        }
        
        //Player
        player = new Player((getWidth()/2)-75, (getHeight()/2)-75, 75, 75, 3,  this);
        
        //Weapon
        weapon = new Weapon(this);
        
        //Bullets
        bullets = new ArrayList<Bullet>();
        
        //keyManager
        display.getJframe().addKeyListener(keyManager);
        
        //rocks
        rocks = new ArrayList<ImmovableObj>();
        for(int i = 0; i < 6; i++){
            int randX = ThreadLocalRandom.current().nextInt(0, getWidth() + 1);
            int randY = ThreadLocalRandom.current().nextInt(0, getHeight() + 1);
            ImmovableObj rock = new ImmovableObj(randX, randY, 50, 50);
            rocks.add(rock); 
        }
        
        //enemies
        enemies = new ArrayList<Enemy>();
        for(int i = 0; i < 5; i++){
            int randX = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getWidth()) 
                    : 3 * -(int)(Math.random() * getWidth());
            int randY = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getHeight())
                    : 3 * -(int)(Math.random() * getHeight());
            Enemy enemy = new Enemy(randX, randY, 56, 56, 2, 0, 100, 0, this);
            enemies.add(enemy); 
        }
        int randX2 = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getWidth()) 
                : 3 * -(int)(Math.random() * getWidth());
        int randY2 = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getHeight())
                : 3 * -(int)(Math.random() * getHeight());
        Enemy2 enemy2 = new Enemy2(randX2, randY2, 56, 56, 0, 0, 100, this);
        //creating enemies
        /*
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                int width_brick = getWidth() / 10;
                Brick brick = new Brick(i * width_brick + 2, 30 * j + 10, width_brick - 10, 25, 5 - j, this);
                bricks.add(brick);
            }
        }
        */
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
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

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
        
    private void tick() {
        keyManager.tick();
        //check if player is over the box
        PlayerOverBox();
        box.tick();
        weapon.tick();
        player.tick();
        //shoot tick
        shootPlayer();
        BulletTick();
        //randomly create new boxes
        createNewBox();
        enemySearchPlayer();
        wavesControl(); 
        PlayerCollisionRocks();

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
                if(player.getX() <= rock.getX() + rock.width && player.getDirection() == 4){
                    player.setX(player.getX()+3);
                }
                if(player.getX() + player.width >= rock.getX() && player.getDirection() == 3){
                    player.setX(player.getX()-3);
                }
                if(player.getY() <= rock.getY() + rock.height && player.getDirection() == 1){
                    player.setY(player.getY()+3);
                }
                if(player.getY() + player.height >= rock.getY() && player.getDirection() == 2){
                    player.setY(player.getY()-3);
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
                boxes.remove(box);
                itr = boxes.iterator();
                int randomWeapon = ThreadLocalRandom.current().nextInt(1, 2 + 1);
                int randomAmmo = ThreadLocalRandom.current().nextInt(10, 20 + 1);
                System.out.println("" + randomWeapon);
                if(randomWeapon == 1){
                weapon.setAmmoPISTOL(weapon.getAmmoPISTOL()+randomAmmo);
                }
                if(randomWeapon == 2){
                weapon.setAmmoSHOTGUN(weapon.getAmmoSHOTGUN()+randomAmmo);
                }
            }
        }
    } 
    
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
    
    /**
     * Bullet keyManager, shoot when space
     */
    public void shootPlayer(){
        //  PISTOL
        if(this.getKeyManager().space && System.currentTimeMillis() - bulletTimer >= 500 && weapon.getType() == 1 && weapon.getAmmoPISTOL() > 0){
            weapon.setAmmoPISTOL(weapon.getAmmoPISTOL()-1);
            switch(player.getDirection()){
                case 1: 
                    bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY(),
                        5, 20, 10, player.getDirection(), 1, this));
                    break;
                case 2: 
                    bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight(),
                        5, 20, 10, player.getDirection(), 1, this));
                    break;
                case 3:
                    bullets.add(new Bullet(player.getX() + player.getWidth(), player.getY() + player.getHeight()/2 + 10,
                        20, 5, 10, player.getDirection(), 1, this));
                    break;
                case 4:
                    bullets.add(new Bullet(player.getX(), player.getY() + player.getHeight()/2,
                        20, 5, 10, player.getDirection(), 1, this));
                    break;
            }
            System.out.println(weapon.getAmmoPISTOL());
            bulletTimer = System.currentTimeMillis();
        }
        //  SHOTGUN
        if(this.getKeyManager().space && System.currentTimeMillis() - bulletTimer >= 500 && weapon.getType() == 2 && weapon.getAmmoSHOTGUN() > 0){
            weapon.setAmmoSHOTGUN(weapon.getAmmoSHOTGUN()-1);
            switch(player.getDirection()){
                case 1: 
                    bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY(),
                        5, 20, 10, player.getDirection(), 1, this));
                    bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY(),
                        5, 20, 10, player.getDirection(), 2, this));
                    bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY(),
                        5, 20, 10, player.getDirection(), 22, this));
                    break;
                case 2: 
                    bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight(),
                        5, 20, 10, player.getDirection(), 1, this));
                    bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight(),
                        5, 20, 10, player.getDirection(), 2, this));
                    bullets.add(new Bullet(player.getX() + player.getWidth()/2, player.getY() + player.getHeight(),
                        5, 20, 10, player.getDirection(), 22, this));
                    break;
                // Right
                case 3:
                    bullets.add(new Bullet(player.getX() + player.getWidth(), player.getY() + player.getHeight()/2 + 10,
                        20, 5, 10, player.getDirection(), 1, this));
                    bullets.add(new Bullet(player.getX() + player.getWidth(), player.getY() + player.getHeight()/2 + 10,
                        20, 5, 10, player.getDirection(), 2, this));
                    bullets.add(new Bullet(player.getX() + player.getWidth(), player.getY() + player.getHeight()/2 + 10,
                        20, 5, 10, player.getDirection(), 22, this));
                    break;
                case 4:
                    bullets.add(new Bullet(player.getX(), player.getY() + player.getHeight()/2,
                        20, 5, 10, player.getDirection(), 1, this));
                    bullets.add(new Bullet(player.getX(), player.getY() + player.getHeight()/2,
                        20, 5, 10, player.getDirection(), 2, this));
                    bullets.add(new Bullet(player.getX(), player.getY() + player.getHeight()/2,
                        20, 5, 10, player.getDirection(), 22, this));
                    break;
            }
            bulletTimer = System.currentTimeMillis();
        }
    }
    
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
                        enemy.setHealth(enemy.getHealth()-30);
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
            }
        }
    }
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
            //draw background
            g.drawImage(Assets.background, 0, 0, width, height, null);
            //render rocks
            Iterator itrRock = rocks.iterator();
            while(itrRock.hasNext()){
                ImmovableObj rock = (ImmovableObj) itrRock.next();
                rock.render(g);
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
            g.drawString("Enemies: " + enemies.size(), 10, height - 20);
            bs.show();
            g.dispose();
        }

    }

    /**
     * setting the thead for the game
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
}
