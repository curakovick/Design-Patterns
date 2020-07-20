package command;

import adapter.HexagonAdapter;



public class CmdUpdateHexagon implements Command {
	
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalState;
	private String commandLog;
	

	public CmdUpdateHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
		super();
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		originalState = oldState.clone();

		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setR(newState.getR());
		oldState.setColorOfEdge(newState.getColorOfEdge());
		oldState.setInsideColor(newState.getInsideColor());
		
		commandLog = "UPDATEHEX" + "_EXECUTE_" + originalState + "->" + oldState;
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		oldState.setX(originalState.getX());
		oldState.setY(originalState.getY());
		oldState.setR(originalState.getR());
		oldState.setColorOfEdge(originalState.getColorOfEdge());
		oldState.setInsideColor(originalState.getInsideColor());
		
		commandLog = "UPDATEHEX" + "_UNEXECUTE_" + oldState + "->" + originalState;
		
	}

	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}
	
	
	
	
	
	
	
	

}
