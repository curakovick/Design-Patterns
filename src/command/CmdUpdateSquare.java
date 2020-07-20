package command;

import shapes.Square;

public class CmdUpdateSquare implements Command {

	private Square oldState;
	private Square newState;
	private Square originalState = new Square();
	private String commandLog;
	
	
	public CmdUpdateSquare(Square oldState, Square newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalState = (Square) oldState.clone();
	
		oldState.getUpperLeft().setX(newState.getUpperLeft().getX());
		oldState.getUpperLeft().setY(newState.getUpperLeft().getY());
		oldState.setPage(newState.getPage());
		oldState.setColor(newState.getColor());
		oldState.setInsideColor(newState.getInsideColor());
		
		commandLog = "UPDATESQUARE" + "_EXECUTE_" + originalState + "->" + oldState;
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.getUpperLeft().setX(originalState.getUpperLeft().getX());
		oldState.getUpperLeft().setY(originalState.getUpperLeft().getY());
		oldState.setPage(originalState.getPage());
		oldState.setColor(originalState.getColor());
		oldState.setInsideColor(originalState.getInsideColor());
		
		commandLog = "UPDATESQUARE" + "_UNEXECUTE_" + oldState + "->" + originalState;
		
		
	}

	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}
	
	

}
