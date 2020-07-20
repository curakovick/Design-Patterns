package adapter;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;
import shapes.AreaShape;


public class HexagonAdapter extends AreaShape {
	
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	
	
	public HexagonAdapter() {
		super();
	}
	
	
	public HexagonAdapter(Hexagon hexagon) {
		super();
		this.hexagon = hexagon;
	}
	

	@Override
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof HexagonAdapter)
			return hexagon.getR() - ((HexagonAdapter) obj).getR();
		return 0;
	}

	@Override
	public double surfaceArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		hexagon.paint(g);
		hexagon.setSelected(isSelected());
		
	}

	@Override
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		
		
	}
	
	@Override
	public void moveOn(int x, int y) {
		// TODO Auto-generated method stub
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void moveFor(int poX, int poY) {
		// TODO Auto-generated method stub
		hexagon.setX(hexagon.getX() + poX);
		hexagon.setY(hexagon.getY() + poY);
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return hexagon.doesContain(x, y);
	}
	
	public String toString() {
		return "Hexagon:Center(" + hexagon.getX() + ","+hexagon.getY() + ") Radius(" + hexagon.getR() + ") "+hexagon.getBorderColor()+" "+hexagon.getAreaColor();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			Hexagon hex = ((HexagonAdapter) obj).hexagon;
			return hexagon.getX() == hex.getX() && hexagon.getY() == hex.getY() && hexagon.getR() == hex.getR();
		}
		return false;
	}
	
		
	
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}
	
	@Override
	public HexagonAdapter  clone() {
		// TODO Auto-generated method stub
		Hexagon hex = new Hexagon(getX(), getY(), getR());
		hex.setAreaColor(getInsideColor());
		hex.setBorderColor(getColorOfEdge());
		return new HexagonAdapter(hex);
		
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
		super.setColor(color);
	}
	


	public int getR() {
		return hexagon.getR();
	}

	public void setR(int r) {
		hexagon.setR(r);
	}
	
	public int getX() {
		return hexagon.getX();
	}

	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public int getY() {
		return hexagon.getY();
	}

	public void setX(int x) {
		hexagon.setX(x);
	}
	
	public Color getColorOfEdge() {
		return hexagon.getBorderColor();
	}
	public Color getInsideColor() {
		return hexagon.getAreaColor();
	}
	public void setInsideColor(Color ac) {
		hexagon.setAreaColor(ac);
	}
	public void setColorOfEdge(Color bc) {
		hexagon.setBorderColor(bc);
	}

	

	
	
}
