package shapes;

import java.awt.Color;
import java.awt.Graphics;


public class Point extends Shape {

	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	public Point() {
      
	}

	public Point(int x, int y) {
		this.x = x;
		setY(y);
	}
	public Point(int x, int y, Color colorOfEdge) {
		this(x, y);
		setColor(colorOfEdge);
	}	

	public void moveOn(int x, int y) {
		this.x = x;
		setY(y);
	}
	public void moveFor(int poX, int poY) {
		this.x += poX;
		this.y = this.y + poY;
	}
	public double distance(Point t) {
		int dX = x - t.x;
		int dY = t.y - y;
		double d = Math.sqrt(dX*dX + dY*dY);
		return d;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y+2, x, y-2);
		
		if(isSelected())
			selected(g);
	}
	
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x-3, y-3, 6, 6);
	}
	
	public boolean contains(int x, int y) {
		if(this.distance(new Point(x, y))<=2)
			return true;
		return false;
	}
	
	public int compareTo(Object o) {
		if(o instanceof Point) {
			Point startPoint = new Point(0, 0);
			return (int) (this.distance(startPoint) - ((Point) o).distance(startPoint));
		}
		else
			return 0;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int novoX) {
		x = novoX;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String toString() {
		return "Point:"+"(" + x + "-" + y + ") "+getColor();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point forwarded = (Point)obj;
			if (x==forwarded.getX() &&
				y==forwarded.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		Point point = new Point(this.getX(), this.getY(), this.getColor());
		return point;
	}

}
