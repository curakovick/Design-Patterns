package shapes;

import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends Square {
	private int width;
	private static final long serialVersionUID = 1L;

	public Rectangle() {

	}

	public Rectangle(Point upperLeft, int page, int width) {
		super(upperLeft, page);
		this.width = width;
	}

	public Rectangle(Point upperLeft, int page, int width, Color color) {
		this(upperLeft, page, width);
		setColor(color);
	}

	public Rectangle(Point upperLeft, int page, int width, Color color, Color colorInside) {
		this(upperLeft, page, width, color);
		setInsideColor(colorInside);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double area() {
		return page * width;
	}

	public double perimeter() {
		return 2 * page + 2 * width;
	}

	public String toString() {
		return "Rectangle:UpLeft(" + upperLeft.getX()+","+upperLeft.getY() + ") Page(" + page + ")"+ " Width(" + width + ") "+getColor()+" "+getInsideColor();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			if (upperLeft.equals(r.upperLeft) && page == r.page && width == r.width)
				return true;
			else
				return false;

		}
		return false;
	}

	public void draw(Graphics g) {
		fill(g);
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), width, page);
		if (isSelected())
			selected(g);
	}

	public Line diagonal() {
		Point downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + page);
		return new Line(upperLeft, downRight);
	}

	public void selected(Graphics g) {
		g.setColor(Color.black);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX() + width, getUpperLeft().getY())).selected(g);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX(), getUpperLeft().getY() + page)).selected(g);
		new Line(new Point(getUpperLeft().getX(), getUpperLeft().getY() + page), diagonal().getEndPoint()).selected(g);
		new Line(new Point(getUpperLeft().getX() + width, getUpperLeft().getY()), diagonal().getEndPoint()).selected(g);

	}

	public void fill(Graphics g) {
		g.setColor(getInsideColor());
		g.fillRect(upperLeft.getX() + 1, upperLeft.getY() + 1, width - 1, page - 1);
	}

	public boolean contains(int x, int y) {
		if (this.getUpperLeft().getX() <= x && x <= (this.getUpperLeft().getX() + width)
				&& this.getUpperLeft().getY() <= y && y <= (this.getUpperLeft().getY() + page))
			return true;
		else
			return false;
	}

	public Rectangle clone() {
		Rectangle r = new Rectangle();
		r.setUpperLeft(new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY()));
		r.setPage(this.getPage());
		r.setWidth(this.getWidth());
		r.setColor(this.getColor());
		r.setInsideColor(this.getInsideColor());
		return r;
	}
}