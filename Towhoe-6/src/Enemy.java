/*
all enemies will be some descendant of the enemy object so we can ensure the player interacts properly with them
author: all of us
date: today
*/
import java.awt.*;
import java.util.ArrayList;
public class Enemy extends PhysicalObject implements Ship  { // THERES SOME POLYMORPHISM THERE 

	private double shots_per_second;
	private int FRAMES_PER_SHOT;
	private int frames_until_next;
	private int lives;
	// 'soak' up bullets
	private int dmg = 2;
	public Enemy() {
		super(0,0,0,0,7);
		lives=1;
	}

	public Enemy(int x, int y, int xVel, int yVel) {
		super(x,y,xVel,yVel,7);
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
			// generate random number 0-4
			// if number == 4 then influence the bullet towards player 
			if ((int) (Math.random()*4)==3 && ((UserPanel) Towhoe.window.getGame()).getPlayerY() > super.getY()){
				int dx = super.getX()-((UserPanel) Towhoe.window.getGame()).getPlayerX();
				int dy = super.getY()-((UserPanel) Towhoe.window.getGame()).getPlayerY();
				// scale down [dx,dy] vector magnitude 5 (roughly)
				double len = Math.sqrt(dx*dx+dy*dy);
				double scale_factor = 5/len;
				dx = (int) (scale_factor*dx+.5);
				dy = (int) (scale_factor*dy+.5);

				bullets.add(new Bullet(super.getX()+super.getRadius()-2,super.getY()+super.getRadius()-2,-dx,-dy,2));
			}
			else bullets.add(new Bullet(super.getX()+super.getRadius()-2, super.getY()+super.getRadius()-2, (int)(Math.random()*3-1), 3, 2));
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
		g.fillOval(super.getX(),super.getY(),super.getRadius()*2,super.getRadius()*2);
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
