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
    private Weapon weapon;
    private Box box;                // to create a box
    private KeyManager keyManager;  // to manage the keyboard
    private ArrayList<Enemy> enemies; // my enemies
    private ArrayList<Enemy2> enemies2;
    private ArrayList<Bullet> bullets;          // player bullets
    private ArrayList<EnemyBullet> enemyBullets; //enemy bullets
    private boolean gameOver;            // To stop the game
    private boolean started;             // to start the game
    private long lastTime;
    private int score;                 	 //score
    private boolean win;
    private long bulletTimer;

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
        score = 0;
        //tweet = new SoundClip("/sounds/twitter_ios.wav", 0, false);
        //hit = new SoundClip("/sounds/hit.wav", 0, false);
        win = false;
        bulletTimer = System.currentTimeMillis();
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
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        box = new Box(ThreadLocalRandom.current().nextInt(0, getWidth() + 1), ThreadLocalRandom.current().nextInt(0, getHeight() + 1), 30, 30, this);
        player = new Player((getWidth()/2)-75, (getHeight()/2)-75, 150, 150, 3,  this);
        weapon = new Weapon(this);
        bullets = new ArrayList<Bullet>();
        display.getJframe().addKeyListener(keyManager);
        enemies = new ArrayList<Enemy>();
        int randX = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getWidth()) 
                : 3 * -(int)(Math.random() * getWidth());
        int randY = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getHeight())
                : 3 * -(int)(Math.random() * getHeight());
        Enemy enemy = new Enemy(randX, randY, 150, 150, 0, 0, 100, 0, this);
        enemies.add(enemy);
        enemies2 = new ArrayList<Enemy2>();
        int randX2 = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getWidth()) 
                : 3 * -(int)(Math.random() * getWidth());
        int randY2 = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getHeight())
                : 3 * -(int)(Math.random() * getHeight());
        Enemy2 enemy2 = new Enemy2(randX2, randY2, 150, 150, 0, 0, 100, this);
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
    
    public ArrayList<Enemy2> getEnemies2() {
        return enemies2;
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
        PlayerOverBox();
        box.tick();
        weapon.tick();
        player.tick();
        shootPlayer();
        BulletTick();
        
        
        // getting every enemy by using iterator
        Iterator itr = enemies.iterator();
        while(itr.hasNext()){
            // getting specific enemy
            Enemy enemy = (Enemy) itr.next();
            enemy.setSpeedX((enemy.getX() > player.getX()) ? -2 : 2);
            enemy.setSpeedY((enemy.getY() > player.getY()) ? -2 : 2);
            //  moving the enemy
            enemy.tick();
        }
        // getting every enemy by using iterator
        Iterator itr2 = enemies2.iterator();
        while(itr2.hasNext()){
            // getting specific enemy
            Enemy2 enemy2 = (Enemy2) itr.next();
            enemy2.setSpeedX((enemy2.getX() > player.getX()) ? -2 : 2);
            enemy2.setSpeedY((enemy2.getY() > player.getY()) ? -2 : 2);
            //  moving the enemy
            enemy2.tick();
        }
    }
    
    /**
     * Player over the box
     */
    public void PlayerOverBox(){
        if(getPlayer().intersects(getBox())){
            box.boxBroken();
        }
    } 
    
    public void shootEnemy() {
        Iterator itr = enemies2.iterator();
        while (itr.hasNext()) {
            Enemy2 enemy2 = (Enemy2) itr.next();
            if (System.currentTimeMillis() - enemy2.getLastTime() >= 500) {
                switch (enemy2.getDirection()) {
                case 1: 
                    enemyBullets.add(new EnemyBullet(enemy2.getX() + 
                            enemy2.getWidth()/2, enemy2.getY(),
                        5, 20, 10, enemy2.getDirection(), this));
                    break;
                case 2: 
                    enemyBullets.add(new EnemyBullet(enemy2.getX() + 
                            enemy2.getWidth()/2, enemy2.getY() + enemy2.getHeight(),
                        5, 20, 10, enemy2.getDirection(), this));
                    break;
                case 3:
                    enemyBullets.add(new EnemyBullet(enemy2.getX() + 
                            enemy2.getWidth(), enemy2.getY() + enemy2.getHeight()/2 
                                    + 10, 20, 5, 10, enemy2.getDirection(), this));
                    break;
                case 4:
                    enemyBullets.add(new EnemyBullet(enemy2.getX(), enemy2.getY()
                            + enemy2.getHeight()/2,
                        20, 5, 10, enemy2.getDirection(), this));
                    break;
                }
                enemy2.setLastTime(System.currentTimeMillis());
            }
            enemy2.tick();
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
                        // reset enemy
                        enemy.setY(-(int)(Math.random()*2*getHeight()));
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
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            box.render(g);
            for (Enemy enemy : enemies) {
                enemy.render(g);
            }
            player.render(g);
            Iterator itr = bullets.iterator();
            while(itr.hasNext()){
                Bullet bullet = (Bullet) itr.next();
                bullet.render(g);
            }
            if(weapon.getType() == 1){
                g.drawString("PISTOL", player.getX()+player.width/3, player.getY());
                g.drawString("" + weapon.getAmmoPISTOL(), 10, height);
            }
            if(weapon.getType() == 2){
                g.drawString("SHOTGUN", player.getX()+player.width/4, player.getY());
                g.drawString("" + weapon.getAmmoSHOTGUN(), 10, height);
            }
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
