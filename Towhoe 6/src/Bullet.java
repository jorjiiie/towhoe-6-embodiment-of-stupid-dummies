/*
the bullet is the thing that shoots other things
author: all of us
date: today
*/

import java.awt.*;

// TODO make this into an abstract maybe or add a field for image somewher efor other bullet types idfk
public class Bullet extends PhysicalObject {
	private boolean active;

	public Bullet(int x, int y, int xVel, int yVel, int r) {
		super(x, y, xVel, yVel, r);
		active = true;
	}

	public Bullet(Bullet o) { // what okay
		super(o.getX(), o.getY(), o.getXVelocity(), o.getYVelocity(), o.getRadius());
		active = true;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(super.getX(), super.getY(), super.getRadius(), super.getRadius());
	}

	public void move() {
		// if it's too far then we destroy!
		super.move();
		active = !isOffscreen(); // I did testing and removing it from the list is good enough for garbage collector to get to it
	}

	public boolean getActive() {
		return active;
	}

	private boolean isOffscreen() {
		return super.getY() < 0 || super.getY() > Towhoe.GAME_HEIGHT || super.getX() < 0 || super.getX() > Towhoe.GAME_WIDTH; // i hate how boolean doesnt actually equal 1 is so annoying
		/*
		map of return super.getY()<0 || super.getY()>Towhoe.GAME_HEIGHT || super.getX()<0 || super.getX()>Towhoe.GAME_WIDTH; 

		if(super.getY()<0)
			return true;
		else if(super.getY()>Towhoe.GAME_HEIGHT)
			return true;
		else if(super.getX()<0)
			return true;
		else if(super.getX()>Towhoe.GAME_WIDTH)
			return true;
		else return false;

		cpu cannot preload good, slow

		verus ( if java had booleans actually equal 1 like in c or python or whatever )

		return (super.getY()<0 + super.getY()>Towhoe.GAME_HEIGHT + super.getX()<0 + super.getX()>Towhoe.GAME_WIDTH) > 0;
		cpu can preload all this code and do it in advance before you actually come to it and is faster despite more calculations per run
		TOO BAD THIS DOESNT WORK CAUSE JAVA IS STUPID AND BOOLEANS DO NOT EQUAL ONE AS WELL
		this approach is a lot of times good for much larger branches of code and i really want to find somewhere i can implement this
		*/
	}
}