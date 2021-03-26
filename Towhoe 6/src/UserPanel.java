import javax.swing.*;
import java.awt.*; 	
import java.awt.event.*;
import java.util.ArrayList;

public class UserPanel extends JPanel implements KeyListener, ActionListener, JavaArcade {
	
	private Player player; // player
	private int score, coins; // score, 1 coin = 1 life
	
	private javax.swing.Timer timer; // draw rate 
	private ArrayList<Enemy> enemies; // enemies as arraylist
	
	// THE BIG OL CONSTRUCTOR
	public UserPanel(int width, int height) {
   
		Color background = Color.black; // fallback, image bg pls
		score = 0;
		
		player = new Player(width, height); // TODO  add/fix args pls
		
		timer = new javax.swing.Timer(16, this); // 16 ms per frame frame rate = ~60, this will stutter a lot noticeably but who cares
		
		addKeyListener(this);//used for key controls

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);      
		setBackground(background);
	   
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
	   
	   	 // Enemy.setPanelWidth(getWidth()); 
	   	 // enemyFast.move();
	   	 // enemySlow.move();
	   }
	  public boolean running() {
	  	return true;
	  }
	  
	  /* This method should start your game, it should also set a global boolean value so that your running method
	   * can return the appropriate value */
	   
	  public void startGame() {
	  	//start = true; // i hav eno clue
	  }
	  
	  /*This method should return the name of your game */
	  public String getGameName() {
	  	return "TOWHOE";
	  }
	  
	  /* This method should stop your timers but save your score, it should set a boolean value to indicate
	   *the game is not running, this value will be returned by running method */
	   
	  public void pauseGame() {
	  	return;
	  }
	  
	  /* This method should return your instructions */
	  public String getInstructions() {
	  	return "ajskld";
	  }
	 
	 /* This method should return the author(s) of the game */
	  
	  public String getCredits() {
	  	return "joe";
	  }
	  
	  /* This method should return the highest score played for this game */
	  public String getHighScore() {
	  	return "69420";
	  }
	  
	  /* This method should stop the timers, reset the score, and set a running boolean value to false */
	  public void stopGame() {
	  	return;
	  }
	  
	  /* This method shoud return the current players number of points */
	  
	  public int getPoints() {
	  	return 69;
	}  
	  /* This method provides access to GameStats display for UserPanel to pass information to update score
	  GameStats is created in Arcade, a reference should be passed to UserPanel (main panel) to update poionts */
	  public void setDisplay(GameStats d) {
	  	return;
	  }
	   	 
}
   
 
