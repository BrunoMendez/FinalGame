/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author EnriqueVilla
 */
public class Files {
    
    /**
     * To load the saved data into the game
     * @param game to set the values with the stored data
     */
    public static void loadFile(Game game) {
        BufferedReader br = null;
        FileReader fr = null;
        String line;
        try {
            fr = new FileReader("save.txt");
            br = new BufferedReader(fr);
            //read the first line
            line = br.readLine();
            String[] elements = line.split(",");
            //setting x and y position of the player
            game.getPlayer().setX(Integer.parseInt(elements[0]));
            game.getPlayer().setY(Integer.parseInt(elements[1]));
            line = br.readLine();
            elements = line.split(",");
            line = br.readLine();
            int num = Integer.parseInt(line);
            game.getEnemies().clear();
            //adding every enemy saved
            for (int i = 0; i < num; i++) {
                line = br.readLine();
                elements = line.split(",");
                int x = Integer.parseInt(elements[0]), y = 
                        Integer.parseInt(elements[1]), lives = 
                        Integer.parseInt(elements[2]);
                //Enemy enemy = new Enemy(x, y, game.getWidth() / 10 - 10, 
                        //25, game);
            }
        } catch (IOException ioe) {
            System.out.println("No hay nada grabado " + ioe.toString());
        }
    }
    
    /**
     * To store the values of each item of the game
     * @param game to obtain the values of the items in the game 
     */
    public static void saveFile(Game game) {
        try {
            PrintWriter printWriter = new PrintWriter(new File("save.txt"));
            printWriter.println("" + game.getPlayer().getX() + "," + 
                    game.getPlayer().getY());
            //printWriter.println("" + game.getBullet().getX()+ "," + 
                    //game.getBullet().getY() + ",");
            printWriter.println("" + game.getEnemies().size());
            for (int i = 0; i < game.getEnemies().size(); i++) {
                Enemy enemy = game.getEnemies().get(i);
                printWriter.println("" + enemy.getX() + "," + enemy.getY());
            }
            printWriter.close();
        } catch(IOException ioe) {
            System.out.println("Se lleno el disco duro, no se puede guardar el"
                    + " juego" + ioe.toString());

        }
    }
}
