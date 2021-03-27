/*
 Everything that is a physical object (capable of interaction) will implement this class

*/
import java.awt.*;


import java.awt.Image;

public abstract class PhysicalObject {
	int xPos, yPos, xVel, yVel; 
	//(x,y) position with [x,y] vector
	int hitboxRadius;
	// all hitboxes are circles for simplicity 
	// AND ITS MORE FUN THAT WAY !!!! -theo

	Image sprite; // not sure how this is going to work for nwo
	public PhysicalObject() {
		// no params spawn in at 0,0 with [0,0]
		// TODO spawn at a good spot
		xPos=yPos=xVel=yVel=hitboxRadius=0; // fun
	}
	public PhysicalObject(int xPos, int yPos, int xVel, int yVel, int hitboxRadius) {
		// (xPos,yPos) [xVel,yVel]
		// fun stuff
		this.xPos=xPos;
		this.yPos=yPos;
		this.xVel=xVel;
		this.yVel=yVel;
		this.hitboxRadius=hitboxRadius;
	}
	public boolean intersect(PhysicalObject o) {
		// if distance < sum of radii, then intersect
		return distance(o) < this.hitboxRadius + o.hitboxRadius;	
	}
	public double distance(PhysicalObject o) { // as of now doesnt need to be a separate method but it could be cool to use it later i guess
		// distance between two objects
		return Math.sqrt((this.xPos-o.xPos)*(this.xPos-o.xPos)+(this.yPos-o.yPos)*(this.yPos-o.yPos)); // fun
	}
	public void move() {
		// System.out.println(xPos + " " + yPos + " " + xVel + " " + yVel); // DEBUG
		xPos+=xVel;
		yPos+=yVel;
	}
	// not used here but could be useful so ima just leave it here
	public int clamp(int x, int min, int max) { // for the border clipping
		return Math.min(Math.max(x, min), max); // TODO is there a fringe case for this where all 3 value are equal and it returns a 0 idfk
	}
	// mutators & accessors
	public void updYVelocity(int vel) { // upd = update (im an idiot)
		yVel+=vel;
	}
	public void updXVelocity(int vel) {
		xVel+=vel;
	}
	public void setYVelocity(int vel) {
		yVel = vel;
	}
	public void setXVelocity(int vel) {
		xVel = vel;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public int getYVelocity() {
		return yVel;
	}
	public int getXVelocity() {
		return xVel;
	}
	public int getRadius() {
		return hitboxRadius;
	}
	// is this really needed (probably)
	public abstract void draw(Graphics g);
}
