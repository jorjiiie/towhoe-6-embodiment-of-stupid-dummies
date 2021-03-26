import java.awt.*;
public class Bullet extends PhysicalObject {
	boolean active = false;
	public Bullet(int x, int y, int xVel, int yVel, int r) {
		super(x,y,xVel,yVel,r);
		active = true;
	}
	public Bullet(Bullet o) {
		super(o.getX(),o.getY(),o.getXVelocity(),o.getYVelocity(),o.getRadius());
		active = true;
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(super.getX(),super.getY(),super.getRadius(),super.getRadius());
	}
	public void move() {
		// if it's too far then we destroy!
		super.move();
		if (super.getX() < 0 || super.getY() < 0) {
			// destroy?
			active = false;
		}

	}
}