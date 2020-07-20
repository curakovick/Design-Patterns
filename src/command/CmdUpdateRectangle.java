package command;

import shapes.Rectangle;


public class CmdUpdateRectangle implements Command{
	

	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalState = new Rectangle();
	private String commandLog;
	
	
	

	public CmdUpdateRectangle(Rectangle oldState, Rectangle newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalState = oldState.clone();
		
		oldState.getUpperLeft().setX(newState.getUpperLeft().getX());
		oldState.getUpperLeft().setY(newState.getUpperLeft().getY());
		oldState.setPage(newState.getPage());
		oldState.setWidth(newState.getWidth());
		oldState.setColor(newState.getColor());
		oldState.setInsideColor(newState.getInsideColor());
		
		commandLog = "UPDATEREC" + "_EXECUTE_" + originalState + "->" + oldState;
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
		oldState.getUpperLeft().setX(originalState.getUpperLeft().getX());
		oldState.getUpperLeft().setY(originalState.getUpperLeft().getY());
		oldState.setPage(originalState.getPage());
		oldState.setWidth(originalState.getWidth());
		oldState.setColor(originalState.getColor());
		oldState.setInsideColor(originalState.getInsideColor());
		
		commandLog = "UPDATEREC" + "_UNEXECUTE_" + oldState + "->" + originalState;
		
		
		
		
	}

	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}
	
	

}
