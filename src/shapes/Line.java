package shapes;

import java.awt.Color;
import java.awt.Graphics;



public class Line extends Shape{

	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, Color colorOfEdge) {
		this(startPoint, endPoint);
		setColor(colorOfEdge);
	}
	
	public void moveFor(int poX, int poY) {
		startPoint.moveFor(poX, poY);
		endPoint.moveFor(poX, poY);
	}
	public void moveOn(int x, int y) {
		startPoint.moveOn(x, y);
		endPoint.moveOn(x, y);
	}
	
	public double length() {
		return startPoint.distance(endPoint);
	}
	
	public Point middleOfLine(){
		int xMiddle = (startPoint.getX() + endPoint.getX())/2;
		int yMiddle = (startPoint.getY() + endPoint.getY())/2;
		Point middleOfLine = new Point(xMiddle, yMiddle);
		return middleOfLine;
	}
	
	
	public boolean equals(Object obj) {
		if(obj instanceof Line) {
			Line forwarded = (Line) obj;
			if(startPoint.equals(forwarded.startPoint) && endPoint.equals(forwarded.endPoint))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if(isSelected())
			selected(g);
	}
	
	
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		startPoint.selected(g);
		endPoint.selected(g);
		middleOfLine().selected(g);
	}
	
	
	public int compareTo(Object o) {
		if(o instanceof Line){
			return (int) (this.length()-((Line) o).length());
		}
		else 
			return 0;
	}
	
	
	public boolean contains(int x, int y){
		Point help = new Point(x, y);
		if((startPoint.distance(help)+endPoint.distance(help))-length()<=1)
			return true;
		else
			return false;
	}

	
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return "Line:("+startPoint.getX()+"-"+startPoint.getY() + ")->(" + endPoint.getX()+"-"+endPoint.getY()+")->"+getColor();
	}
	
	

	@Override
	public Shape clone() { 
		// TODO Auto-generated method stub
		Line line = new Line(new Point(this.getStartPoint().getX(), this.getStartPoint().getY()), 
				new Point(this.getEndPoint().getX(), this.getEndPoint().getY()), this.getColor());
		return line;
	}

}
