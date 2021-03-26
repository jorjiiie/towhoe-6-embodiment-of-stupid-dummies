import java.util.ArrayList;
import java.awt.*;

public class Player extends PhysicalObject implements Ship {
	public static final int SPEED = 5; // pixels/frame or pixels/sec if its too fast but i think it's ok
	private int lives = 5;
	private int pLevel = 0;
	private int xp=0;
	private ArrayList<Bullet> bullets;
	public Player(int x, int y) {
		// initial velocity is [0,0]
		// initial position is middle of panel so we pass that in
		super(x,y,0,0,15); // radius for now? i have no idea
	}
	public ArrayList<Bullet> shoot() {
		// we'll implement a global list of bullets so it's simpler to check yadayada
		return bullets;
	}
	public void levelUp() {

	}
	public void addXp() {

	}
	public void goUp() {
		super.setYVelocity(1); 
	}
	public void goDown() {
		super.setYVelocity(-1);
	}
	public void goRight() {
		super.setXVelocity(1);
	}
	public void goLeft() {
		super.setXVelocity(-1);
	}
	public boolean loseLife() {
		lives--;
		return lives==0;
	}


	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(super.getX(),super.getY(),15,15);
	}
}