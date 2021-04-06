/*
the player and its abilities
author: all of us
date: today
*/
import java.util.ArrayList;
import java.awt.*;

public class Player extends PhysicalObject implements Ship {
	// TODO find comfortable values for these speeds
	public static final int PLAYER_SPEED = 5; // pixels/frame or pixels/sec if its too fast but i think it's ok
	public static final int PLAYER_FOCUS_SPEED = 2; // same as speed but separate speed for focus mode

	public static final int PLAYER_RADIUS = 7;
	private int lives = 3;
	private boolean focus, shooting;
	// can the player be hit
	private int invicible_frames;

	private long last_shot, last_hit;
	public Player(int x, int y) {
		// initial velocity is [0,0]
		// initial position is middle of panel so we pass that in
		super(x, y, 0, 0, PLAYER_RADIUS); // radius for now? i have no idea
		focus = false;
		shooting = false;
		last_hit = 0;
	}
	// THE SHOOTING SECTION
	public ArrayList<Bullet> shoot() {
		if (shooting) {
			// 200ms delay

			// TO DO - convert to frames so pausing is only dependent on one thing
			if (System.nanoTime()-last_shot>=200000000){ // NANO TIME !!
				
				ArrayList<Bullet> bullets = new ArrayList<Bullet>();
				// this is fun stuff
				bullets.add(new Bullet(super.getX()+super.getRadius()-4,super.getY()+super.getRadius()-2, 0, -10, 4));
				bullets.add(new Bullet(super.getX()+super.getRadius()-2,super.getY()+super.getRadius()-4, focus ? 2 : 5, -9, 2,6,2));
				bullets.add(new Bullet(super.getX()+super.getRadius()-2,super.getY()+super.getRadius()-4, focus ? -2 : -5, -9, 3,6,2));
				last_shot = System.nanoTime();
				return bullets;

			}
			return null;
		}
		return null;
	}

	public void startShoot() {
		shooting = true;
	}

	public void stopShoot() {
		shooting = false;
	}

	// PLAYER MOVEMENT
	public void moveUp() {
		super.setYVelocity((focus ? PLAYER_FOCUS_SPEED : PLAYER_SPEED) * -1);
	}

	public void moveDown() {
		super.setYVelocity(focus ? PLAYER_FOCUS_SPEED : PLAYER_SPEED);
	}

	public void moveRight() {
		super.setXVelocity(focus ? PLAYER_FOCUS_SPEED : PLAYER_SPEED);
	}

	public void moveLeft() {
		super.setXVelocity((focus ? PLAYER_FOCUS_SPEED : PLAYER_SPEED) * -1);
	}

	// bad (now good thanks to theo)
	public void stopX() {
		super.setXVelocity(0);
	}

	public void stopY() {
		super.setYVelocity(0);
	}

	public void move() {
		super.setX(super.clamp(super.getX() + getXVelocity(), 0, Towhoe.window.getBorderWidth()));
		super.setY(super.clamp(super.getY() + getYVelocity(), 0, Towhoe.window.getBorderHeight()));
	}

	public void focus() {
		// immediately clamp speeds to focus speeds
		super.setXVelocity(super.clamp(getXVelocity(), -PLAYER_FOCUS_SPEED, PLAYER_FOCUS_SPEED));
		super.setYVelocity(super.clamp(getYVelocity(), -PLAYER_FOCUS_SPEED, PLAYER_FOCUS_SPEED));
		focus = true;
	}

	public void unfocus() {
		// TODO figure out a better way to make speed normal again or just fix my shitty parenthesis
		super.setXVelocity((int) (getXVelocity() * (PLAYER_SPEED / PLAYER_FOCUS_SPEED * 1.0)));
		super.setYVelocity((int) (getYVelocity() * (PLAYER_SPEED / PLAYER_FOCUS_SPEED * 1.0)));
		focus = false;
	}
	public void draw(Graphics g) {

		if (invicible_frames<=0) {
			if (focus) g.setColor(Color.BLUE);
			else g.setColor(Color.RED);

		} else {
			// add yellow tint? (idk lol can change later)
			invicible_frames--;
			if (focus) g.setColor(Color.YELLOW);
			else g.setColor(Color.ORANGE);
		}
		// System.out.println("profanities lol"); // DEBUG
		g.fillOval(super.getX(), super.getY(), super.getRadius()*2, super.getRadius()*2);

	}
	public int hit() {
		// hit by enemy bullet
		if (invicible_frames>0) return 0;

		lives--;

		invicible_frames = UserPanel.FRAMERATE;
		if (lives==0) {
			// GAME OVER... how to do this? 
			return -1;
		}
		return 1;
	}
	public int getLives() {
		return lives;
	}
}