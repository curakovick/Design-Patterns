package shapes;

import java.awt.Color;
import java.awt.Graphics;
 

public class Square extends AreaShape {
	protected Point upperLeft;
	protected int page;
	private static final long serialVersionUID = 1L;
	
	
	public Square() {
		
	}

	
	public Square (Point upperLeft,int page) {
		this. upperLeft =  upperLeft;
		this.page = page;
		
	}
	public Square (Point upperLeft,int page, Color colorOfEdge) {
		this(upperLeft, page);
		setColor(colorOfEdge);
		
	}
	
	public Square (Point upperLeft,int page, Color colorOfEdge, Color insideColor) {
		this(upperLeft, page);
		setColor(colorOfEdge);
		setInsideColor(insideColor);
		
	}
	
	public void moveOn(int x, int y) {
		upperLeft.moveOn(x, y);
	}
	
	public void moveFor(int poX, int poY) {
		upperLeft.moveFor(poX, poY);
		
	}
	
	public double surfaceArea() {
		return page*page;
	}
	
	
	public double perimeter() {
		return 4 * page;
	}
	
	public String toString() {
		return "Square:UpperLeft(" + upperLeft.getX()+","+upperLeft.getY() + ") Page(" + page + ") "+getColor()+" "+getInsideColor();
				
	}
	
	public Line diagonal() {
		Point downRight = new Point(upperLeft.getX()+page, upperLeft.getY()+page);
		return new Line(upperLeft, downRight);
	}
	
	public Point center() {
		return diagonal().middleOfLine();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Square) {
			Square forwarded = (Square) obj;
			if(upperLeft.equals(forwarded.upperLeft) && page == forwarded.page)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), page, page);
		fill(g);
		if (isSelected())
			selected(g);
	}
	
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX()+page, getUpperLeft().getY())).selected(g);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX(), getUpperLeft().getY()+page)).selected(g);
		new Line(new Point(getUpperLeft().getX()+page, getUpperLeft().getY()), diagonal().getEndPoint()).selected(g);
		new Line(new Point(getUpperLeft().getX(), getUpperLeft().getY()+page), diagonal().getEndPoint()).selected(g);
	}
	
	public void fill(Graphics g) {
		g.setColor(getInsideColor());
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, page-1, page-1);
	}
	
	public int compareTo(Object o) {
		if(o instanceof Square){
			return (int) (this.surfaceArea()-((Square) o).surfaceArea());
		}
		else 
			return 0;
	}
	
	public boolean contains(int x, int y) {
		if(this.getUpperLeft().getX()<=x 
				&& x<=(this.getUpperLeft().getX() + page)
				&& this.getUpperLeft().getY()<=y 
				&& y<=(this.getUpperLeft().getY() + page))
			return true;
		else 
			return false;
	}
	
	public Point getUpperLeft() {
		return upperLeft;
	}
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		Square s = new Square(new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY()), 
				this.getPage(), this.getColor(), this.getInsideColor());
		return s;
	}
	
	
	
	
}
