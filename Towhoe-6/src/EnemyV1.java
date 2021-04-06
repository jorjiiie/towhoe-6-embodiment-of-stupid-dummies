import java.awt.*;
//is obstacle
public class EnemyV1 extends Enemy{
	public EnemyV1() {
		super((int)(Math.random()*500),0,0,1,35, 200, 0.001);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(super.getX(),super.getY(),super.getRadius()*2,super.getRadius()*2);
	}
}