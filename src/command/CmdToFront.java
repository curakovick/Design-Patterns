package command;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdToFront implements Command {
	
	private Shape shape;
	private DrawingModel model;
	private int index;
	private String commandLog;
	
	

	public CmdToFront(Shape shape, DrawingModel model) {
		super();
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		index = model.getIndexOf(shape);
		model.removeAt(index);
		model.addAt(shape, index + 1);
		commandLog = "ToFront" + "_EXECUTE_" + shape;
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.removeAt(index + 1);
		model.addAt(shape, index);
		commandLog = "ToFront" + "_UNEXECUTE_" + shape;
	}

	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}
	
	

}
