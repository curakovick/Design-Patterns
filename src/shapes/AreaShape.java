package shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class AreaShape extends Shape{
	private static final long serialVersionUID = 1L;
	private Color insideColor;
	
	public abstract double surfaceArea();
	public abstract double perimeter();
	public abstract void fill(Graphics g);
	
	public Color getInsideColor() {
		return insideColor;
	}
	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
	}


}
