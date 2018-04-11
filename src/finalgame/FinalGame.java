/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgame;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author bruno
 */
public class FinalGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // java - get screen size using the Toolkit class
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        //adjust game window to screen size
        Game g = new Game("Juego", 1200, 750);
        g.start();
    }
    
}
