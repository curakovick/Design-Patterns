package command;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdAddShape implements Command {
	
	private Shape shape;
	private DrawingModel model;
	private String commandLog;
	private int position;
	
	
	public CmdAddShape(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.getShapes().add(shape);
		position=model.getIndexOf(shape);
		commandLog = "ADD" + "_EXECUTE_" + shape;
		System.out.println(shape);
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.getShapes().remove(position);
		//model.removeShape(shape);
		commandLog = "ADD" + "_UNEXECUTE_" + shape;
		
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

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	
	
	
	
	

}
