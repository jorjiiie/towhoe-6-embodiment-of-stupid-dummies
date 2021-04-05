/*
all enemies will be some descendant of the enemy object so we can ensure the player interacts properly with them
author: all of us
date: today
*/
// TODO THIS CLASS IS SO USEFUL RIGHT NOW
import java.awt.*;
public class Enemy extends PhysicalObject implements Ship  {

	private int shots_per_second;
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
		FRAMES_PER_SHOT = (int) (Toehow.window.getGame().FRAMERATE / shots);
		frames_until_next = FRAMES_PER_SHOT;
	}

	public void shoot() {
		frames_until_next--;
		if (frames_until_next<=0) {
			frames_until_next = FRAMES_PER_SHOT;
		}
	}
	public void hit(int dmg) {
		
		lives-=dmg;
		if (lives<=0) super.setActive(false);
	}
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(super.getX(),super.getY(),super.getRadius(),super.getRadius());
	}
	public void shoot() {
		return;
	}
	public int getDmg() {
		return dmg;
	}

}
