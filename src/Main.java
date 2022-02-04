
import javax.swing.JFrame;

import csc3a.miniproject.gui.UIFrame;

/**
 * 
 */

/**
 * @author HERVE NG
 *
 */

public class Main {

	/**
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
    	
    	//Creating the Graph UI
    	UIFrame frame = new UIFrame("UN Sustainable Development Goal 3");
    	frame.setSize(620, 560);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
    	frame.setVisible(true);
    	frame.setLocationRelativeTo(null);
    }
}