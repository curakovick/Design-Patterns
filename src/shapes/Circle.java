package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends AreaShape   {

	private static final long serialVersionUID = 1L;
	private Point center;
	private int radius;
	
	
	

	public Circle() {
		super();
	}
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	public Circle(Point center, int radius, Color colorOfEdge) {
		this(center, radius);
		setColor(colorOfEdge);

	}

	public Circle(Point center, int radius, Color colorOfEdge, Color insideColor) {
		this(center, radius);
		setColor(colorOfEdge);
		setInsideColor(insideColor);
		
	}
	
	

	public void moveOn(int x, int y) {
		center.moveOn(x, y);
	}

	public void moveFor(int poX, int poY) {
		center.moveFor(poX, poY);
	}

	public double surfaceArea() {
		return radius*radius*Math.PI;
	}

	public double perimeter() {
		return 2*radius*Math.PI;
	}
	public boolean equals(Object obj) {
		if(obj instanceof Circle) {
			Circle forwarded = (Circle) obj;
			if(center.equals(forwarded.center) && radius == forwarded.radius)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX()-radius, center.getY()-radius, 2*radius, 2*radius);
		fill(g);
		if(isSelected())
			selected(g);
	}
	
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		new Line(new Point(center.getX(), center.getY()-radius), 
				new Point(center.getX(), center.getY() + radius)).selected(g);
		new Line(new Point(center.getX()-radius, center.getY()), 
				new Point(center.getX()+radius, center.getY())).selected(g);
	}
	
	public void fill(Graphics g) {
		g.setColor(getInsideColor());
		g.fillOval(center.getX()-radius+1, center.getY()-radius+1, 2*radius-2, 2*radius-2);
	}
	
	public int compareTo(Object o) {
		if(o instanceof Circle){
			return (int) (this.radius-((Circle) o).radius);
		}
		else 
			return 0;
	}
	
	public boolean contains(int x, int y) {
		if(new Point(x, y).distance(getCenter()) <= radius)
			return true;
		else
			return false;
	}
	
	public String toString() {
		return "Circle:Center(" + center.getX()+","+center.getY() + ") Radius(" + radius + ") "+getColor()+" "+getInsideColor();
				
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		Circle c = new Circle(new Point(this.getCenter().getX(), this.getCenter().getY()), 
				this.getRadius(), this.getColor(), this.getInsideColor());
		return c;
	}

	
}
