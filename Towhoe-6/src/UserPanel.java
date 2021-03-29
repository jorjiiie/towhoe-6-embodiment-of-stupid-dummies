/*
the actual game
author: all of us
date: today
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;


public class UserPanel extends JPanel implements KeyListener, ActionListener, JavaArcade {

	public static final String filePath = System.getProperty("java.class.path");
	private Player player; // player
	private int score, coins; // score, 1 coin = 1 life

	private javax.swing.Timer timer; // draw rate
	private ArrayList<Enemy> enemies; // enemies as arraylist

	private Image background_image;
	private int img1Y,img2Y;
	private Set<Bullet> player_bullets, enemy_bullets; // separated cause player bullets only check for enemy collision and enemy bullets only check for player collision

	// for debugging purposes
	// why doesn't java just have a queue
	private Queue<Integer> prev_frameTimes = new LinkedList<>();
	private long time_sum = 0;
	// stack up 10 frame buffer for times and average it out
	private int frame_count = 10,current_frames; 

	// THE BIG OL CONSTRUCTOR
	public UserPanel(int width, int height) {

		try {
			background_image = ImageIO.read(new File(filePath+"/img/texture3.jpg"));
			img1Y=0;
			img2Y=-background_image.getHeight(null);
		}
		catch (IOException e) {
			System.out.println("failed to load background");
			e.printStackTrace();
		}

		Color background = Color.black; // fallback, image bg pls
		score = 0;

		player = new Player(width / 2, height / 2); // TODO add/fix args pls

		timer = new javax.swing.Timer(16, this); // 16 ms per frame frame rate = ~60, this will stutter a lot noticeably but who cares

		timer.start();
		addKeyListener(this); // used for key controls

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setBackground(background);

		setPreferredSize(new Dimension(width, height));
		player_bullets = new HashSet<Bullet>();
	}

	public void addPlayerBullets(Bullet b) {
		player_bullets.add(b);
	}

	public void actionPerformed(ActionEvent e) { // draw those mf frames
		// checkStats();
		player.shoot(); // having this as what is basically a persist script is bad for performance but the amount of variable juggling we'd do anyways... its worth it
		repaint();
	}

	// KEY HANDLER
	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (player.getXVelocity() < 0)
				player.stopX();
			break;
		case KeyEvent.VK_RIGHT:
			if (player.getXVelocity() > 0)
				player.stopX();
			break;
		case KeyEvent.VK_UP:
			if (player.getYVelocity() < 0)
				player.stopY();
			break;
		case KeyEvent.VK_DOWN:
			if (player.getYVelocity() > 0)
				player.stopY();
			break;
		case KeyEvent.VK_SHIFT: // unfocus
			player.unfocus();
			break;
		case KeyEvent.VK_Z:
			player.stopShoot();
			break;
		}
	}

	public void keyPressed(KeyEvent e) { // the one that matters
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			player.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			player.moveRight();
			break;
		case KeyEvent.VK_UP:
			player.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			player.moveDown();
			break;
		case KeyEvent.VK_Z:
			player.startShoot();
			break;
		/* TODO Implement bombs 
		case KeyEvent.VK_X:
			player.bomb();
			break;
		*/
		case KeyEvent.VK_SHIFT: // focus mode
			player.focus();
			break;

		case KeyEvent.VK_ENTER:
			// TODO start the game i guess
			break;
		default: // TODO is this needed
		}
	}

	public void draw_background(Graphics g) {
		// draws background
		g.drawImage(background_image, -(background_image.getWidth(null)-Towhoe.GAME_WIDTH)/2,img1Y++,null); // centered image on X axis, scrolling Y axis
		g.drawImage(background_image, -(background_image.getWidth(null)-Towhoe.GAME_WIDTH)/2,img2Y++,null);

		if (img1Y>=background_image.getHeight(null)) img1Y = - background_image.getHeight(null);
		if (img2Y>=background_image.getHeight(null)) img2Y = - background_image.getHeight(null);
	}

	// Stuff we have to do every frame + repaint
	public void paintComponent(Graphics g) {
		long startTime = System.nanoTime();
		// TODO add for loop for all enemies, collision checks, player , etc etc etc
		super.paintComponent(g); // the important one keep this first
		
		if (background_image!=null) draw_background(g);
		player.move();
		player.draw(g);

		// TODO ryan explain this to me pls + maybe find a better way
		for (Iterator<Bullet> i = player_bullets.iterator(); i.hasNext();) {
			Bullet b = i.next();
			if (b.getActive()) {
				b.move();
				// System.out.println(" BULLET AT " + b.getX() + " " + b.getY()); // DEBUG
				b.draw(g);
			}
			else i.remove();
		}
		long endTime = System.nanoTime();
	}

	// TODO FIGURE OUT WTF TO DO WITH THIS
	private void checkStats() { // called every 5ms, checks status of targets and hero

		// Set the width in case the panel gets resized. All enemies share this value,
		// therefore it is
		// static and its corresponding static method is called using the class name.

		// Enemy.setPanelWidth(getWidth());
		// enemyFast.move();
		// enemySlow.move();
	}

	// TODO wtf is this
	public boolean running() {
		return true;
	}

	/*
	 * This method should start your game, it should also set a global boolean value
	 * so that your running method can return the appropriate value
	 */

	public void startGame() {
		// start = true; // i hav eno clue
	}

	// Towhoe so pog
	public String getGameName() {
		return "Towhoe 6";
	}

	/*
	 * This method should stop your timers but save your score, it should set a
	 * boolean value to indicate the game is not running, this value will be
	 * returned by running method
	 */
	public void pauseGame() {
		return;
	}

	/* This method should return your instructions */
	public String getInstructions() {
		return "press the buttons in the right order to win";
	}

	/* This method should return the author(s) of the game */

	public String getCredits() {
		return "Ryan - carrying the group\nTheo - doing something I dunno\nEric - hopefully actually in the group";
	}

	/* This method should return the highest score played for this game */
	public String getHighScore() {
		return "69420";
	}

	/*
	 * This method should stop the timers, reset the score, and set a running
	 * boolean value to false
	 */
	public void stopGame() {
		return;
	}

	/* This method shoud return the current players number of points */

	public int getPoints() {
		return 69;
	}

	/*
	 * This method provides access to GameStats display for UserPanel to pass
	 * information to update score GameStats is created in Arcade, a reference
	 * should be passed to UserPanel (main panel) to update poionts
	 */
	public void setDisplay(GameStats d) {
		return;
	}

}
