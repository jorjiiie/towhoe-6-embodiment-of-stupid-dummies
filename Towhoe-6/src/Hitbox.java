/*
a circular hitbox that can be implemented in many different objects. this has no reason to exist outside of PhysicalObject but who cares
this is the core of all objects and is how their positions are determined for drawing
author: all of us
date: today
*/

public class Hitbox {
    private int radius;
    private int xPos, yPos; // make this a pointer to objects y and y pos idfk god i wish java had pointers
    private boolean active = true;

    public Hitbox(int radius, int xPos, int yPos, boolean active) {
        this.radius = radius;
        this.xPos = xPos;
        this.yPos = yPos;
        this.active = active;
    }

    public boolean intersect(Hitbox o) {
		// if distance < sum of radii, then intersect
		return (this.isActive() && o.isActive()) ? distance(o) < this.radius + o.radius : false;
	}

	private double distance(Hitbox o) { // as of now doesnt need to be a separate method but it could be cool to use it later i guess
		// distance between two objects
		return Math.sqrt((this.xPos - o.xPos) * (this.xPos - o.xPos) + (this.yPos - o.yPos) * (this.yPos - o.yPos)); // fun
	}

    // MUTATORS AND ACCESSORS (THE NOT FANCY STUFF)
    public int getRadius() {
        return radius;
    }

    public int getX() {
        return xPos;
    }

    public void setX(int x) {
        xPos = x;
    }

    public int getY() {
        return yPos;
    }

    public void setY(int y) {
        yPos = y;
    }

    public void addX(int x) {
        xPos += x;
    }

    public void addY(int y) {
        yPos += y;
    }
    
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean b) {
        active = b;
    }
}
