import java.awt.*;
import javax.swing.*;

/**
 * Write a description of class GamePopUp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GamePopUp extends JWindow
{
    /**
     * Constructor for objects of class GamePopUp
     */
    public GamePopUp()
    {
        
    }

    /**
     * Constructor for objects of class GamePopUp
     */
    public void showSplashWindow() {
        //create content pane
        JPanel content = new JPanel(new BorderLayout());
        
        // Set the window's bounds, position the window in the center of the screen
	int width =  334;
	int height = 263;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	int x = (screen.width-width)/2;
	int y = (screen.height-height)/2;
	//set the location and the size of the window
	setBounds(x,y,width,height);
	    
	JLabel splash = new JLabel(new ImageIcon(getClass().getResource("TicTaacToeStartImage.gif"))); // Grab the image to use for the splash screen
	    
	content.add(splash, BorderLayout.CENTER); // Make splash screen take whole space
	    
	setContentPane(content); // Add splash screen to pane
	setVisible(true); // Make splash screen visible
	    
	try {
            Thread.sleep(2000); // Let splash screen be visible for 5000ms 
	}
        catch (InterruptedException e) {
        }
	    //destroy the window and release all resources
	dispose(); // Ends splash screen
    }
}
