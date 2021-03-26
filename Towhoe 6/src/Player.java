import java.util.ArrayList;
import java.awt.*;

public class Player extends PhysicalObject implements Ship {
	public static final int SPEED = 3; // pixels/frame or pixels/sec if its too fast but i think it's ok
	private int lives = 5; // TODO turn into coin maybe
	private int pLevel = 0; // TODO add this to how shoot works
	private int xp=0; // TODO add this when item collision or osmething asdlkajsdl
	private ArrayList<Bullet> bullets;
	public Player(int x, int y) {
		// initial velocity is [0,0]
		// initial position is middle of panel so we pass that in
		super(x,y,0,0,15); // radius for now? i have no idea
		bullets = new ArrayList<Bullet>();
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
	}
	public void moveUp() {
		super.setYVelocity(-SPEED); 
	}
	public void moveDown() {
		super.setYVelocity(SPEED);
	}
	public void moveRight() {
		super.setXVelocity(SPEED);
	}
	public void moveLeft() {
		super.setXVelocity(-SPEED);
	}
	// coin system more so than lives since its arcade imo
	public boolean loseLife() {
		lives--;
		return lives==0;
	}
	// bad
	public void stopX() {
		super.setXVelocity(0);
	}
	public void stopY() {
		super.setYVelocity(0);
	}
	public void move() {
		// System.out.println("joe"); // mama
		super.move();
	}
	public void draw(Graphics g){
		g.setColor(Color.RED);
		// System.out.println("FUCK YOU");
		g.fillOval(super.getX(),super.getY(),super.getRadius(),super.getRadius());
	}
}