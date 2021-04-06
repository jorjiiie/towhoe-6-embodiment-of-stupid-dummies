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
	public static final int FRAMERATE = 60;
	private Player player; // player
	private int score, coins; // score, 1 coin = 1 life

	private static int DEBUG_MODE = 1;

	private javax.swing.Timer timer; // draw rate
	// private ArrayList<Enemy> enemies; // enemies as arraylist

	private Image background_image;
	private int img1Y,img2Y;
	private Set<Bullet> player_bullets, enemy_bullets; // separated cause player bullets only check for enemy collision and enemy bullets only check for player collision

	private Set<Enemy> enemies; // I WANT EFFICIENT SEARCH/REMOVE! thanks bye
	// for debugging purposes
	// why doesn't java just have a queue
	private Queue<Long> prev_frameTimes = new LinkedList<>();
	private long time_sum = 0;
	// stack up 10 frame buffer for times and average it out
	private int frame_count = 10,current_frames; 

	private EnemySpawner spawner;

	private GameStats gStats;

	private int current_points,high_score;

	private boolean running = false,done=false;

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

		timer = new javax.swing.Timer(1000/FRAMERATE, this); // 16 ms per frame frame rate = ~60, this will stutter a lot noticeably but who cares

		timer.start();
		addKeyListener(this); // used for key controls

		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setBackground(background);

		setPreferredSize(new Dimension(width, height));
		player_bullets = new HashSet<Bullet>();
		enemy_bullets = new HashSet<Bullet>();
		enemies = new HashSet<Enemy>();
		spawner = new EnemySpawner();
	}

	public void addPlayerBullets(Bullet b) {
		player_bullets.add(b);
	}

	public void actionPerformed(ActionEvent e) { // draw those mf frames
		// checkStats();
		// battery draine
		if (!running) return; 
		long startTime = System.nanoTime();

		ArrayList<Bullet> player_shoot = player.shoot(); // having this as what is basically a persist script is bad for performance but the amount of variable juggling we'd do anyways... its worth it
		if (player_shoot!=null) {
			for (Bullet b : player_shoot) {
				player_bullets.add(b);
			}
		}

		for (Enemy en : enemies) {
			// probably causes high overhead but |enemies| should be < 30 so this isn't actually that bad...
			ArrayList<Bullet> enemy_shot = en.shoot(); 


			if (enemy_shot!=null) {
				for (Bullet b : enemy_shot) {
					enemy_bullets.add(b);
				}
			}

		}

		// spawn enemies
		int to_spawn = spawner.spawn();
		for (int i=0;i<to_spawn;i++) 
			spawnEnemy(1); // probably can do randomizer here bc i don't want to implement waves or something
		if (to_spawn>0) {
			System.out.println(enemies.size());
		}
		// test intersections
		for (Bullet b : player_bullets) {
			if (b.isActive()) {
				// if not removed yet for whatever reason
				for (Enemy k : enemies) {
					if (!k.isActive()) continue;

					if (k.intersect(b)) {
						if (b.hasHit(k)) {
							continue;
						}
						b.hit(k);
						k.hit(b.getDmg()); // same for if we want to implement health (hit())
						if (k.isActive()==false) {
							current_points+=(int) ((spawner.getVal()) * 100) * 100;
							gStats.updatePoints();
						}
						break;
					}

				}
			}
		}

		for (Bullet b : enemy_bullets) {
			if (b.intersect(player)) {
				// probably an invulnerable period
				int player_state = player.hit();
				if (player_state==-1) {
					// game over
					stopGame();
					// DO SOMETHING
				}
				if (player_state==1) {
					// returns true if it is past 1s, which is where player is invulnerable
					b.phit();
					gStats.updateLives(player.getLives());
				}
			}
		}


		repaint();
		// better perf unfocused
		Toolkit.getDefaultToolkit().sync();

		if (DEBUG_MODE>=3) {
			long endTime = System.nanoTime();
			long nanoseconds = ((endTime-startTime));
			prev_frameTimes.add(nanoseconds);
			time_sum+=nanoseconds;
			if (current_frames<frame_count) {
				current_frames++;
			} else {
				// remove the oldest frame 
				long front = prev_frameTimes.remove();
				time_sum-=front;
			}
			System.out.println("PREVIOUS FRAME TOOK " + nanoseconds + " ns, AVERAGE FOR **TEN** FRAMES IS " + getMsPerFrame() + " ns");
		}
	}
	public void spawnEnemy(int type) {
		switch(type) {
			default:
				// spawn a default enemy with random x at top of screen
				enemies.add(new Enemy((int)(Math.random()*500), 0, 0, 2, 7, 4, 1));
		}
		
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
		case KeyEvent.VK_U:
			if (DEBUG_MODE>0) spawnEnemy(1);
		default: // TODO is this needed
		}
	}

	public void draw_background(Graphics g) {
		// draws background
		g.drawImage(background_image, -(background_image.getWidth(null)-Towhoe.window.getBorderWidth())/2,img1Y++,null); // centered image on X axis, scrolling Y axis
		g.drawImage(background_image, -(background_image.getWidth(null)-Towhoe.window.getWidth())/2,img2Y++,null);

		if (img1Y>=background_image.getHeight(null)) img1Y = - background_image.getHeight(null);
		if (img2Y>=background_image.getHeight(null)) img2Y = - background_image.getHeight(null);
	}

	// Stuff we have to do every frame + repaint
	public void paintComponent(Graphics g) {

		super.paintComponent(g); // the important one keep this first

		// anti aliasing so circles are actually circles
		// yes taken from stackoverflow :)
		Graphics2D g2d = (Graphics2D) g.create();
        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);

		if (background_image!=null) draw_background(g2d);
		
		// TODO ryan explain this to me pls + maybe find a better way
		// theo this is the best way, its (expected) constant access + remove + add

		// drawing bullets, if something is inactive, then it will get removed and not be drawn

		// essentially a for each loop but has the iterator go back if something is removed
		for (Iterator<Bullet> i = player_bullets.iterator(); i.hasNext();) {
			Bullet b = i.next();
			if (b.isActive()) {
				// drawing before move means that on the next frame, the calculations will match the drawn (x,y) of the thing
				b.draw(g2d);
				b.move();
			}
			else i.remove();
		}
		for (Iterator<Bullet> i = enemy_bullets.iterator(); i.hasNext();) {
			Bullet b = i.next();
			if (b.isActive()) {
				b.draw(g2d);
				b.move();
			}
			else i.remove();
		}
		for (Iterator<Enemy> i = enemies.iterator(); i.hasNext();) {
			Enemy current = i.next();
			if (current.isActive()) {
				current.draw(g2d);
				current.move();
			}
			else i.remove();
		}

		player.draw(g2d);
		player.move();
		g2d.dispose();
	}

	public long getMsPerFrame() {
		return (time_sum/current_frames);
	}
	public int getPlayerX() {
		return player.getX();
	}
	public int getPlayerY() {
		return player.getY();
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
		return running;
	}

	/*
	 * This method shorunninguld start your game, it should also set a global boolean value
	 * so that your  method can return the appropriate value
	 */

	public void startGame() {
		// start = true; // i hav eno clue
		if (done) return; // cannot start again! 
		running = true;
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
		if (done) return;
		running = false;

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
		return (""+high_score);
	}

	/*
	 * This method should stop the timers, reset the score, and set a running
	 * boolean value to false
	 */
	public void stopGame() {
		if (current_points>high_score) {
			// idk if there's congrats but
			high_score = current_points;
		}
		running = false;
		done = true;
		gStats.updateHighScore();
		return;
	}

	/* This method shoud return the current players number of points */

	public int getPoints() {
		return current_points;
	}

	/*
	 * This method provides access to GameStats display for UserPanel to pass
	 * information to update score GameStats is created in Arcade, a reference
	 * should be passed to UserPanel (main panel) to update poionts
	 */
	public void setDisplay(GameStats d) {
		gStats = d;
		return;
	}
	public int getPlayerLives() {
		return player.getLives();
	}

}
