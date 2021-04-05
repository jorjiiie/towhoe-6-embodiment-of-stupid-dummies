/*
all enemies will be some descendant of the enemy object so we can ensure the player interacts properly with them
author: all of us
date: today
*/
// TODO THIS CLASS IS SO USEFUL RIGHT NOW
import java.awt.*;
import java.util.ArrayList;
public class Enemy extends PhysicalObject implements Ship  {

	private double shots_per_second;
	private int FRAMES_PER_SHOT;
	private int frames_until_next;
	private int lives;
	// 'soak' up bullets
	private int dmg = 2;
	public Enemy() {
		super(0,0,0,0,15);
		lives=1;
	}

	public Enemy(int x, int y, int xVel, int yVel) {
		super(x,y,xVel,yVel,15);
		lives=4;
	}

	public Enemy(int x, int y, int xVel, int yVel, int hitboxRadius, int lives, double shots) {
		super(x,y,xVel,yVel,hitboxRadius);
		this.lives=lives;
		shots_per_second=shots;
		FRAMES_PER_SHOT = (int) (UserPanel.FRAMERATE / shots);
		frames_until_next = FRAMES_PER_SHOT;
	}

	public ArrayList<Bullet> shoot() {
		frames_until_next--;
		if (frames_until_next<=0) {
			frames_until_next = FRAMES_PER_SHOT;
			ArrayList<Bullet> bullets = new ArrayList<Bullet>();
			bullets.add(new Bullet(super.getX()+super.getRadius()/2-2, super.getY()+super.getRadius()/2, 0, 5, 5));
			return bullets;
		}
		return null;
	}
	public void hit(int dmg) {
		
		lives-=dmg;
		if (lives<=0) super.setActive(false);
	}
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(super.getX(),super.getY(),super.getRadius(),super.getRadius());
	}

	public int getDmg() {
		return dmg;
	}
	public void move() {
		if (isOffscreen()) setActive(false);
		super.move();
	}
	private boolean isOffscreen() {
		return super.getY() < 0 || super.getY() > Towhoe.window.getBorderHeight() || super.getX() < 0 || super.getX() > Towhoe.window.getBorderWidth(); 
	}
}
