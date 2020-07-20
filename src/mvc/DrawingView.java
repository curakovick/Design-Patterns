package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import shapes.Shape;


public class DrawingView extends JPanel {


	/**
	 * Create the panel.
	 */
	private static final long serialVersionUID = 1L;
	private DrawingModel model;
	
	public DrawingView() {

	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			Iterator<Shape> it = model.getShapes().iterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
		}

	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	

}
