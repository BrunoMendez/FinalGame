package finalgame;


import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


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
     * Initializes the values for the application game
     * @param title Window title
     * @param width Window's height dimension
     * @param height Window's width dimension
     */
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }
    
    /**
     * Creates window and adds canvas to the window
     */
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
    
    /**
     * <code>jFrame</code> Getter
     * @return jFrame
     */
    public JFrame getJframe() {
        return jframe;
    }
    
    /**
     * <code>canvas</code> Getter
     * @return canvas;
     */
    public Canvas getCanvas(){
        return canvas;
    }
}
