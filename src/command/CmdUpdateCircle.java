package command;

import shapes.Circle;


public class  CmdUpdateCircle implements Command {

	private Circle oldState;
	private Circle newState;
	private Circle originalState = new Circle();
	private String commandLog;
	
	
	public CmdUpdateCircle(Circle oldState, Circle newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalState = (Circle) oldState.clone();
		
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setRadius(newState.getRadius());
		oldState.setColor(newState.getColor());
		oldState.setInsideColor(newState.getInsideColor());
		
		commandLog = "UPDATECIRCLE" + "_EXECUTE_" + originalState + "->" + oldState;

		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getCenter().setX(originalState.getCenter().getX());
		oldState.getCenter().setY(originalState.getCenter().getY());
		oldState.setRadius(originalState.getRadius());
		oldState.setColor(originalState.getColor());
		oldState.setInsideColor(originalState.getInsideColor());
		
		commandLog = "UPDATECIRCLE" + "_UNEXECUTE_" + oldState + "->" + originalState;

		
	}

	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}
	
	
 
}
