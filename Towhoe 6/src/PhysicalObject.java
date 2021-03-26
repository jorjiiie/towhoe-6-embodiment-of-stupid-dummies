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

	Image sprite; // not sure how this is going to work for nwo
	public PhysicalObject() {
		// no params spawn in at 0,0 with [0,0]
		xPos=yPos=xVel=yVel=hitboxRadius=0;
	}
	public PhysicalObject(int xPos, int yPos, int xVel, int yVel, int hitboxRadius) {
		// (xPos,yPos) [xVel,yVel]
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
	public double distance(PhysicalObject o) {
		// distance between two objects
		return Math.sqrt((this.xPos-o.xPos)*(this.xPos-o.xPos)+(this.yPos-o.yPos)*(this.yPos-o.yPos));
	}
	void move() {
		// no clipping on borders (yet)
		xPos+=xVel;
		yPos+=yVel; 
	}
	public void updYVelocity(int x) {
		yVel+=x;
	}
	public void updXVelocity(int x) {
		xVel+=x;
	}
	public void setYVelocity(int x) {
		yVel = x;
	}
	public void setXVelocity(int x) {
		xVel = x;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public abstract void draw(Graphics g);
}
