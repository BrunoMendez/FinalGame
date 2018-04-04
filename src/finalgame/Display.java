package finalgame;


import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bruno
 */
public class Display {
    private JFrame jframe; // This is the app class
    private Canvas canvas; // To display images
    private String title; // Title of the window
    private int width; // Width of the window
    private int height; // Height of the window
    
    
    /**
     * initializes the values for the application game
     * @param title
     * @param width
     * @param height 
     */
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }
    
    public void createDisplay(){
        // Create the app window
        jframe = new JFrame(title);
        
        // set the size of the window
        jframe.setSize(width, height);
        
        // setting not resizeable, visible and possible to close.
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        
        // Creating canvas to paint and setting size
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        
        // adding the canvas to the app window and packing to 
        // get the right dimensions
        jframe.add(canvas);
        jframe.pack();
    }
    
    public JFrame getJframe() {
        return jframe;
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
}
