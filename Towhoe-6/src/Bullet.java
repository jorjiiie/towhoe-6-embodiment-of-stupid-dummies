/*
the bullet is the thing that shoots other things
author: Ryan, Theo
date: today
*/

import java.awt.*;
import java.util.ArrayList;
// TODO make this into an abstract maybe or add a field for image somewher efor other bullet types idfk
public class Bullet extends PhysicalObject {

	private int hits = 4,original_hits=4; // how many hits before death - if this is implemented, we'd have to implement bullet memory though 
	private int dmg = 2;
	// bullet memory is probably better than enemy memory since bullets will hit less, we can just use arraylists and bath in small n :)
	private ArrayList<Enemy> previous_hit;
	private Color col;

	// because why make bullets abstract when you can just have 2 constructors !!! epic time save !!!!!!
	public Bullet(int x, int y, int xVel, int yVel, int r) {
		super(x, y, xVel, yVel, r);
		previous_hit = new ArrayList<Enemy>(hits);
		col = Color.BLUE; // default;
	}
	public Bullet(int x, int y, int xVel, int yVel, int r, int hits, int dmg) {
		super(x,y,xVel,yVel,r);
		original_hits=hits;
		previous_hit = new ArrayList<Enemy>(hits); 
		this.dmg = dmg;
		col = Color.BLUE;
	}
	public Bullet(Bullet o) { // what okay
		super(o.getX(), o.getY(), o.getXVelocity(), o.getYVelocity(), o.getRadius());
		hits = o.getHits();
		dmg = o.getDmg();
		previous_hit = new ArrayList<Enemy>();
		col=o.col;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(super.getX()-1,super.getY()-1,(super.getRadius()+1)*2,(super.getRadius()+1)*2);
		g.setColor(col);
		g.fillOval(super.getX(), super.getY(), (super.getRadius()*2), super.getRadius()*2);
	}
	public boolean hasHit(Enemy e) {
		for (Enemy enemy : previous_hit) {
			if (enemy==e) return true;
		}
		return false;
	}
	public void move() {
		// if it's too far then we destroy!
		super.move();
		if (isOffscreen()) {
			super.setActive(false);
		}
	}
	public int getHits() {
		return original_hits;
	}

	public void phit() {
		// hit player
		super.setActive(false);
	}

	public void hit(Enemy e) {
		hits-=e.getDmg();
		previous_hit.add(e);
		if (hits<=0) super.setActive(false);
	}

	public int getDmg() {
		return dmg;
	}

	private boolean isOffscreen() {
		return super.getY() < 0 || super.getY() > Towhoe.window.getBorderHeight() || super.getX() < 0 || super.getX() > Towhoe.window.getBorderWidth(); // i hate how boolean doesnt actually equal 1 is so annoying
		/*
		BAD RANT PLEASE IGNORE

		map of return super.getY()<0 || super.getY()>Towhoe.window.getBorderHeight() || super.getX()<0 || super.getX()>Towhoe.window.getBorderWidth(); 

		if(super.getY()<0)
			return true;
		else if(super.getY()>Towhoe.window.getBorderHeight())
			return true;
		else if(super.getX()<0)
			return true;
		else if(super.getX()>Towhoe.window.getBorderWidth())
			return true;
		else return false;

		cpu cannot preload good, slow

		verus ( if java had booleans actually equal 1 like in c or python or whatever )

		return (super.getY()<0 + super.getY()>Towhoe.window.getBorderHeight() + super.getX()<0 + super.getX()>Towhoe.window.getBorderWidth()) > 0;
		cpu can preload all this code and do it in advance before you actually come to it and is faster despite more calculations per run
		TOO BAD THIS DOESNT WORK CAUSE JAVA IS STUPID AND BOOLEANS DO NOT EQUAL ONE AS WELL and i dont want to cast them or whatever
		this approach is a lot of times good for much larger branches of code and i really want to find somewhere i can implement this just for fun
		*/
	}
}