package command;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdBringToBack implements Command {
	
	private DrawingModel model;
	private Shape shape;
	private int index;
	private String commandLog;
	
	

	public CmdBringToBack(DrawingModel model, Shape shape) {
		super();
		this.model = model;
		this.shape = shape;
	
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		index = model.getIndexOf(shape);  
		model.removeAt(index);            
		model.addAt(shape, 0);            
		commandLog = "BringToBack"+"_EXECUTE_"+shape;
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.removeAt(0); 
		model.addAt(shape, index);  
		commandLog = "BringToBack"+"_UNEXECUTE_"+shape;
		
	}

	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}

	
	
	
	

}
