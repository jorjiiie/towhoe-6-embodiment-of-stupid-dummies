import javax.swing.*;
import java.awt.*; 	
import java.awt.event.*;
import java.util.ArrayList;

public class UserPanel extends JPanel implements KeyListener, ActionListener {
	
	private Player player; // player
	private int score, coins; // score, 1 coin = 1 life
	
	private javax.swing.Timer timer; // draw rate 
	private ArrayList<Enemy>; // enemies as arraylist

	// THE BIG OL CONSTRUCTOR
	public UserPanel(int width, int height) {
   
		Color background = Color.black; // fallback, image bg pls
		score = 0;
		
		player = new Player(); // TODO  add args pls
		
		timer = new javax.swing.Timer(16, this); // 16 ms per frame frame rate = ~60, this will stutter a lot noticeably but who cares
		
		addKeyListener(this);//used for key controls

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);      
		setBackground(backColor);
	   
	    setPreferredSize(new Dimension(width, height));
	}
	
	public void actionPerformed (ActionEvent e){ // draw those mf frames
		checkStats();
		repaint();
	}

	// we only care about holding da key , stop actions on release
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) { // the one that matters
		switch(e.getKeyCode()) {
		// TODO add all the keys
			case KeyEvent.VK_ENTER: // template
				// do a thing
				break;
			default:
       }
	}

	// draws stuff
	public void paintComponent(Graphics g){
		// TODO add for loop for all enemies, collision checks, player , etc etc etc
		super.paintComponent(g); // the important one keep this first
	}  

	//TODO FIGURE OUT WTF TO DO WITH THIS
   private void checkStats(){ //called every 5ms, checks status of targets and hero
   
   //Set the width in case the panel gets resized.  All enemies share this value, therefore it is
   //static and its corresponding static method is called using the class name.
   
   	 Enemy.setPanelWidth(getWidth()); 
   	 enemyFast.move();
   	 enemySlow.move();
   }
	   	  
	   	  
	   	 
}
   
 
