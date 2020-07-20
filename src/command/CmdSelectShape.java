package command;

import shapes.Shape;

public class CmdSelectShape implements Command {
	
	private Shape shape;
	private boolean selected;

	private String commandLog;
	
	public CmdSelectShape(Shape shape, boolean selected) {
		this.shape = shape;
		this.selected = selected;
	}

	@Override
	public void execute() {
		this.shape.setSelected(selected);
		commandLog = "SELECT" + "_EXECUTE_" + shape.toString() + " SELECTED " + shape.isSelected();
		
	}

	@Override
	public void unexecute() {
		if (shape.isSelected())
			shape.setSelected(false);
		else 
			shape.setSelected(true);
		
		commandLog = "SELECT" + "_UNEXECUTE_" + shape.toString() + " SELECTED " + shape.isSelected();
		
	}
	
	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}

}
