package command;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdRemoveShape implements Command {
	
	private Shape shape;
	private DrawingModel model;
	private String commandLog;
	private int oldPosition;
	
	
	public CmdRemoveShape(Shape shape, DrawingModel model) {
		super();
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		oldPosition =  model.getShapes().indexOf(shape);
		model.removeShape(shape);
		commandLog = "DELETE" + "_EXECUTE_" + shape;
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShapes().add(oldPosition, shape);
		commandLog = "DELETE" + "_UNEXECUTE_" + shape;
	}

	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	
	
	
	

}
