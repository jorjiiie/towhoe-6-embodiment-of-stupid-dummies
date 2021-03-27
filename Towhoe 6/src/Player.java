import java.util.ArrayList;
import java.awt.*;

public class Player extends PhysicalObject implements Ship {
	// TODO find comfortable values for these speeds (rip them from touhou ,, use marisa stats probably)
	public static final int PLAYER_SPEED = 5; // pixels/frame or pixels/sec if its too fast but i think it's ok
	public static final int PLAYER_FOCUS_SPEED = 1; // same as speed but separate speed for focus mode

	private int lives = 5; // TODO turn into coin maybe
	private int pLevel = 0; // TODO add this to how shoot works
	private int xp = 0; // TODO add this when item collision or osmething asdlkajsdl
	private boolean focus, shooting;

	public Player(int x, int y) {
		// initial velocity is [0,0]
		// initial position is middle of panel so we pass that in
		super(x, y, 0, 0, 15); // radius for now? i have no idea
		focus = false;
		shooting = false;
		initBullets();
	}

	// THE SHOOTING SECTION
	public void shoot() {
		if (shooting)
			Towhoe.window.getGame().addPlayerBullets(new Bullet(super.getX(), super.getY(), 0, -10, 5));
		// we'll implement a global list of bullets so it's simpler to check yadayada
	}

	public void startShoot() {
		shooting = true;
	}

	public void stopShoot() {
		shooting = false;
	}

	public void initBullets() { // TODO i dont think this function is useful or used at all
		// for now LOL
		// very useful function
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
		xPos = super.clamp(xPos + xVel, 0, Towhoe.GAME_WIDTH); // TODO fix game borders
		yPos = super.clamp(yPos + yVel, 0, Towhoe.GAME_HEIGHT);
	}

	public void focus() {
		// immediately clamp speeds to focus speeds
		super.setYVelocity(super.clamp(yVel, -PLAYER_FOCUS_SPEED, PLAYER_FOCUS_SPEED));
		super.setXVelocity(super.clamp(xVel, -PLAYER_FOCUS_SPEED, PLAYER_FOCUS_SPEED));
		focus = true;
	}

	public void unfocus() {
		// TODO figure out a better way to make speed normal again or just fix my shitty parenthesis
		super.setYVelocity((int) (yVel * (PLAYER_SPEED / PLAYER_FOCUS_SPEED * 1.0)));
		super.setXVelocity((int) (xVel * (PLAYER_SPEED / PLAYER_FOCUS_SPEED * 1.0)));
		focus = false;
	}

	public void draw(Graphics g) {
		g.setColor(focus ? Color.BLUE : Color.RED);
		// System.out.println("profanities lol"); // DEBUG
		g.fillOval(super.getX(), super.getY(), super.getRadius(), super.getRadius());
	}
}