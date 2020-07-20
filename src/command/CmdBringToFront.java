package command;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdBringToFront implements Command {

	private DrawingModel model;
	private Shape shape;
	private int index;
	private int size;
	private String commandLog;
	
	
	
	
	public CmdBringToFront(DrawingModel model, Shape shape, int size) {
		super();
		this.model = model;
		this.shape = shape;
		this.size = size;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		index = model.getIndexOf(shape);
		model.removeAt(index);
		model.addAt(shape, size);
		commandLog = "BringToFront"+"_EXECUTE_"+shape;
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.removeAt(size);
		model.addAt(shape, index);
		commandLog = "BringToFront"+"_UNEXECUTE_"+shape;
	}

	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}
	
	

	
}
