/*
all enemies will be some descendant of the enemy object so we can ensure the player interacts properly with them
author: all of us
date: today
*/
// TODO THIS CLASS IS SO USEFUL RIGHT NOW
import java.awt.*;
public class Enemy extends PhysicalObject implements Ship  {

	private int lives;
	// 'soak' up bullets
	private int dmg = 1;
	public Enemy() {
		super(0,0,0,0,15);
		lives=1;
	}

	public Enemy(int x, int y, int xVel, int yVel) {
		super(x,y,xVel,yVel,15);
		lives=4;
	}

	public Enemy(int x, int y, int xVel, int yVel, int hitboxRadius, int lives) {
		super(x,y,xVel,yVel,hitboxRadius);
		this.lives=lives;
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
