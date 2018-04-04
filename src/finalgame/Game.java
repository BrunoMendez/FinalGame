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
    private KeyManager keyManager;  // to manage the keyboard
    private ArrayList<Enemy> enemies; // my bricks
    private ArrayList<Bullet> bullets;          // player bullet
    private ArrayList<EnemyBullet> enemyBullets; //enemy bullets
    private boolean gameOver; // To stop the game
    private boolean started;          // to start the game
    private long lastTime;
    private int score; 	//score
    private SoundClip tweet;
    private SoundClip hit;
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
        player = new Player(getWidth() / 2 - 50, getHeight() - 100, 100, 100, 3, this);
        bullets = new ArrayList<Bullet>();
        enemyBullets = new ArrayList<EnemyBullet>();
        enemies = new ArrayList<Enemy>();
        //creating enemies
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                int width_brick = getWidth() / 15;
                Enemy enemy = new Enemy(i * width_brick + 2, 50 * j + 10,
                        50, 50, 1, 1, this);
                enemies.add(enemy);
                int points = (j == 0) ? 30 : (j < 3 && j > 0) ? 20 : 10;
                enemy.setPoints(points);
                if (j == 3) {
                    enemy.setLowest(true);
                }
            }
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
    
    

    private void tick() {
            
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
            player.render(g);
            for (Enemy enemy : enemies) {
                enemy.render(g);
            }
            for (Bullet bullet : bullets) {
                bullet.render(g);
            }
            for(EnemyBullet eBullet : enemyBullets) {
                eBullet.render(g);
            }
            
            g.setColor(Color.white);
            g.drawString("Score: " + String.valueOf(score), 0, height);
            g.drawString("Lives: " + player.getLives() , 0, height - 15);
            if (keyManager.P) {
                g.setColor(Color.white);
                g.drawString("Presiona: P para despausar", width / 2 - 100, height / 2 + (height / 4));
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
