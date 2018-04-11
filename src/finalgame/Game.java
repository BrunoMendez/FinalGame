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
    private Box box;                // to create a box
    private KeyManager keyManager;  // to manage the keyboard
    private ArrayList<Enemy> enemies; // my enemies
    private Bullet bullets;          // player bullets
    private ArrayList<EnemyBullet> enemyBullets; //enemy bullets
    private boolean gameOver;            // To stop the game
    private boolean started;             // to start the game
    private long lastTime;
    private int score;                 	 //score
    private boolean win;

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
        player = new Player((getWidth()/2)-75, (getHeight()/2)-75, 150, 150, 3, this);
        bullets = new Bullet((getWidth()/2)-75, (getHeight()/2)-75, 150, 150, this);
        display.getJframe().addKeyListener(keyManager);
        enemies = new ArrayList<Enemy>();
        int randX = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getWidth()) 
                : 3 * -(int)(Math.random() * getWidth());
        int randY = (Math.random() > 0.5) ? 3 * (int)(Math.random() * getHeight())
                : 3 * -(int)(Math.random() * getHeight());
        Enemy enemy = new Enemy(randX, randY, 150, 150, 0, 0, 100, 0, this);
        enemies.add(enemy);
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

    public Bullet getBullets() {
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

    
    private void tick() {
        keyManager.tick();
        PlayerOverBox();
        
        if(keyManager.space){
            bullets = new Bullet(player.getX(), player.getY(), 40, 40, this);
        }
        
        box.tick();
        player.tick();
        for (Enemy enemy : enemies) {
            if(getBullets().intersects(enemy)){
                 enemies.remove(enemy);
            }
            enemy.setSpeedX((enemy.getX() > player.getX()) ? -2 : 2);
            enemy.setSpeedY((enemy.getY() > player.getY()) ? -2 : 2);
            enemy.tick();
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
            bullets.render(g);
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
