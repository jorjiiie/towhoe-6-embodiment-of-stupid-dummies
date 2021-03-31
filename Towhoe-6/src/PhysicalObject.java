/*
everything capable of interacting with other things will be some descendant of PhysicalObject
author: all of us
date: today
*/
import java.awt.*;
import java.awt.Image;

public abstract class PhysicalObject {
	private int xVel, yVel;
	// positioning is implemented in hitbox
	private Hitbox hitbox;
	Image sprite; // not sure how this is going to work for nwo

	public PhysicalObject() {
		// no params spawn in at 0,0 with [0,0]
		// TODO spawn at a good spot
		xVel = yVel = 0; // fun
		hitbox = new Hitbox(0,0,0,false);
	}

	public PhysicalObject(int xPos, int yPos, int xVel, int yVel, int hitboxRadius) {
		// fun stuff
		this.xVel = xVel;
		this.yVel = yVel;
		hitbox = new Hitbox(hitboxRadius, xPos, yPos, true); // TODO active hitbox
	}

	public void move() {
		// System.out.println(xPos + " " + yPos + " " + xVel + " " + yVel); // DEBUG
		hitbox.addX(xVel);
		hitbox.addY(yVel);
	}

	public boolean intersect(PhysicalObject o) {
		return hitbox.intersect(o.hitbox);
	}
	// CLAMP IS COOL
	protected int clamp(int x, int min, int max) { // for the border clipping
		return Math.min(Math.max(x, min), max);
	}

	// MUTATORS AND ACCESSORS (THE NOT FANCY STUFF)
	public void updYVelocity(int vel) { // upd = update (im an idiot)
		yVel += vel;
	}

	public void updXVelocity(int vel) {
		xVel += vel;
	}

	public void setYVelocity(int vel) {
		yVel = vel;
	}

	public void setXVelocity(int vel) {
		xVel = vel;
	}

	public int getX() {
		return hitbox.getX();
	}

	public int getY() {
		return hitbox.getY();
	}

	public void setX(int x) {
		hitbox.setX(x);
	}
	
	public void setY(int y) {
		hitbox.setY(y);
	}

	public int getYVelocity() {
		return yVel;
	}

	public int getXVelocity() {
		return xVel;
	}

	public int getRadius() {
		return hitbox.getRadius();
	}

	public boolean isActive() {
		return hitbox.isActive();
	}
	public void setActive(boolean b) {
		hitbox.setActive(b);
	}
	// is this really needed (probably)
	public abstract void draw(Graphics g);
}
