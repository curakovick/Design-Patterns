package observer;


import java.util.Iterator;

import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import shapes.Shape;


public class UpdateButtonsAvailability implements Observer {
	private DrawingController controller;

	@Override
	public void update(DrawingFrame frame, DrawingModel model, int numberOfUndoCmd, int numberOfRedoCmd) {
		// TODO Auto-generated method stub
		controller = new DrawingController(model, frame);
		
		

		int numberOfSelected = 0;
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			Shape temp = it.next();
			if(temp.isSelected()) {
				numberOfSelected++;
			}
		}
		
		frame.getBtnDelete().setEnabled(numberOfSelected > 0);
		frame.getBtnModify().setEnabled(numberOfSelected == 1);
		frame.getMntmUndo().setEnabled(numberOfUndoCmd>0);
		frame.getMntmRedo().setEnabled(numberOfRedoCmd>0);

		
		int numOfShapes = model.getShapes().size();
		
		frame.getMntmBringToFront().setEnabled(numberOfSelected==1 && numOfShapes>1);
		frame.getMntmBringToBack().setEnabled(numberOfSelected == 1 && numOfShapes>1);
		frame.getMntmToFront().setEnabled(numberOfSelected == 1 && numOfShapes>1);
		frame.getMntmToBack().setEnabled(numberOfSelected == 1 && numOfShapes>1);
		
		if (numberOfUndoCmd==0) {
			frame.getMntmUndo().setEnabled(false);
			
		}
		else if (numberOfRedoCmd==0) {
			frame.getMntmRedo().setEnabled(false);
		}
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
	

}
