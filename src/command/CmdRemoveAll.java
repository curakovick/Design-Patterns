package command;

import java.util.ArrayList;

import mvc.DrawingModel;
import shapes.Shape;

public class CmdRemoveAll implements Command{
	
	private DrawingModel model;
	private ArrayList<Shape> listOfShapes;
	private String commandLog;
	
	


	public CmdRemoveAll(DrawingModel model, ArrayList<Shape> listOfShapes) {
		super();
		this.model = model;
		this.listOfShapes = listOfShapes;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		model.removeMultiple(listOfShapes);
		commandLog = "DELETEshapes"+"_EXECUTE_"+listOfShapes;
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		model.addAll(listOfShapes);
		commandLog = "DELETEshapes"+"_UNEXECUTE_"+listOfShapes;
	}

	public ArrayList<Shape> getListOfShapes() {
		return listOfShapes;
	}

	public void setListOfShapes(ArrayList<Shape> listOfShapes) {
		this.listOfShapes = listOfShapes;
	}

	public String getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(String commandLog) {
		this.commandLog = commandLog;
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	
	
	
	

}
