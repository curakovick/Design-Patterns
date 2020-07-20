package command;

import mvc.DrawingModel;
import shapes.Shape;


public class CmdToBack implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int index;
	private String commandLog;
	
	

	public CmdToBack(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		index =  model.getShapes().indexOf(shape);
		model.removeAt(index);
		model.addAt(shape, index - 1);
		commandLog = "ToBack" + "_EXECUTE_" + shape;
			
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.removeAt(index - 1);
		model.addAt(shape, index);
		commandLog = "ToBack" + "_UNEXECUTE_" + shape;	

	}

	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}
	
	

}
