package mvc;

import java.util.ArrayList;

import shapes.Shape;


public class DrawingModel {

private ArrayList<Shape> listOfShapes = new ArrayList<Shape>();
	
	public void addShape(Shape s) {
		listOfShapes.add(s);
	}
	
	public Shape getShape(int i) {
		return listOfShapes.get(i);
	}

	
	public ArrayList<Shape> getShapes() {
		return listOfShapes;
	}
	
	public void removeShape(Shape s) {
		listOfShapes.remove(s);
	}
	
	public void removeAll() {
		listOfShapes.removeAll(listOfShapes);
	}
	
	public void addAll(ArrayList<Shape> shapes) {
		listOfShapes.addAll(shapes);
	}
	
	public void addAt(Shape s, int i) {
		listOfShapes.add(i,s);
	}
	
	public void removeMultiple(ArrayList<Shape> list) {
		listOfShapes.removeAll(list);
	}
	
	public void removeAt(int index) {
		listOfShapes.remove(index);
	}
	
	public int getIndexOf(Shape s) {
		return listOfShapes.indexOf(s);
	}

}
