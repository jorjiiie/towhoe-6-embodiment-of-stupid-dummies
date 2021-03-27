import java.util.ArrayList;
import java.awt.*;

public class Player extends PhysicalObject implements Ship {
	public static final int PLAYER_SPEED = 5; // pixels/frame or pixels/sec if its too fast but i think it's ok
	public static final int PLAYER_FOCUS_SPEED = 1; // same as speed but separate speed for focus mode
	private int lives = 5; // TODO turn into coin maybe
	private int pLevel = 0; // TODO add this to how shoot works
	private int xp=0; // TODO add this when item collision or osmething asdlkajsdl
	private boolean focus;
	private ArrayList<Bullet> bullets;
	public Player(int x, int y) {
		// initial velocity is [0,0]
		// initial position is middle of panel so we pass that in
		super(x,y,0,0,15); // radius for now? i have no idea
		bullets = new ArrayList<Bullet>();
		focus = false;
		initBullets();
	}
	public ArrayList<Bullet> shoot() {
		ArrayList<Bullet> j = new ArrayList<Bullet>(); // for now LOL
		j.add(new Bullet(super.getX(),super.getY(),0,-10,5));
		// we'll implement a global list of bullets so it's simpler to check yadayada
		return j;
	}
	public void initBullets() {
		// for now LOL
		// very useful function
	}
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
	// coin system more so than lives since its arcade imo
	public boolean loseLife() {
		lives--;
		return lives==0;
	}
	// bad (now good thanks to theo)
	public void stopX() {
		super.setXVelocity(0);
	}
	public void stopY() {
		super.setYVelocity(0);
	}

	@Override
	public void move() {
		xPos = super.clamp(xPos+xVel, 0, Towhoe.GAME_WIDTH);
		yPos = super.clamp(yPos+yVel, 0, Towhoe.GAME_HEIGHT);
	}
	public void focus() {
		// immediately clamp speeds to focus speeds
		super.setYVelocity(super.clamp(yVel, -PLAYER_FOCUS_SPEED, PLAYER_FOCUS_SPEED));
		super.setXVelocity(super.clamp(xVel, -PLAYER_FOCUS_SPEED, PLAYER_FOCUS_SPEED));
		focus = true;
	}
	public void unfocus() {
		// TODO figure out a better way to make speed normal again or just fix my shitty parenthesis
		super.setYVelocity((int)(yVel * (PLAYER_SPEED/PLAYER_FOCUS_SPEED*1.0)));
		super.setXVelocity((int)(xVel * (PLAYER_SPEED/PLAYER_FOCUS_SPEED*1.0)));
		focus = false;
	}

	public void draw(Graphics g){
		g.setColor(focus ? Color.BLUE : Color.RED);
		// System.out.println("profanities lol"); // DEBUG
		g.fillOval(super.getX(),super.getY(),super.getRadius(),super.getRadius());
	}
}