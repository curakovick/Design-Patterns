package command;

import shapes.Point;

public class CmdUpdatePoint implements Command {
	
	private Point oldState;
	private Point newState;
	private Point originalState = new Point();
	private String commandLog;
	
	

	public CmdUpdatePoint(Point oldState, Point newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}
	
	

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	    originalState = (Point) oldState.clone();

		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setColor(newState.getColor());
		
		commandLog = "UPDATEPOINT" + "_EXECUTE_" + originalState + "->" + oldState;
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setX(originalState.getX());
		oldState.setY(originalState.getY());
		oldState.setColor(originalState.getColor());
		
		commandLog = "UPDATEPOINT" + "_UNEXECUTE_" + oldState + "->" + originalState;
		
	}



	public String getCommandLog() {
		return commandLog;
	}



	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}
	
	

}
