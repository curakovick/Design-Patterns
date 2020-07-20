package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import javax.swing.DefaultListModel;



import adapter.HexagonAdapter;
import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdRemoveShape;
import command.CmdSelectShape;
import command.CmdToBack;
import command.CmdToFront;
import command.CmdUpdateCircle;
import command.CmdUpdateHexagon;
import command.CmdUpdateLine;
import command.CmdUpdatePoint;
import command.CmdUpdateRectangle;
import command.CmdUpdateSquare;
import command.Command;
import hexagon.Hexagon;
import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import shapes.Circle;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;


public class FileLog implements Strategy {

	private DrawingFrame frame;
	private DrawingModel model;
	private DrawingController controller;
	private BufferedWriter writer;
	private BufferedReader reader;

	public FileLog(DrawingFrame frame, DrawingModel model, DrawingController controller) {
		this.frame = frame;
		this.model = model;
		this.controller = controller;
	}

	@Override
	public void save(File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file + ".log")); 
			DefaultListModel<String> list = frame.getDlmLogList(); 
			for (int i = 0; i < frame.getDlmLogList().size(); i++) { 
				writer.write(list.getElementAt(i));  
				writer.newLine(); 

			}
			writer.close(); 

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	

	@Override
	public void open(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void getLine() {
		try {
			String command = reader.readLine(); 
			Command doCommand = null; 
			int i = 0;
			if (command != null && command.length() != 0) {
				String[] commandOperation = command.split("_");  
				switch (commandOperation[0]) {
				case "ADD":
					Shape shape = parse(commandOperation[2].split(":")[0], commandOperation[2].split(":")[1]);
					doCommand = new CmdAddShape(shape, model);
				
					if (commandOperation[1].equals("EXECUTE")) {
						controller.performCommand(doCommand); 
						controller.getLog().addElement(((CmdAddShape) doCommand).getCommandLog());

					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand(); 

					}
					frame.getView().repaint();
					break;

				case "DELETE":
					Shape deleteShape = parse(commandOperation[2].split(":")[0], commandOperation[2].split(":")[1]);
					if (commandOperation[1].equals("EXECUTE")) {
						doCommand = new CmdRemoveShape(deleteShape, model);
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdRemoveShape) doCommand).getCommandLog());

					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;
				case "BringToBack":
					Shape bringToBackShape = parse(commandOperation[2].split(":")[0],
							commandOperation[2].split(":")[1]);
					doCommand = new CmdBringToBack(model, bringToBackShape);
					if (commandOperation[1].equals("EXECUTE")) {
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdBringToBack) doCommand).getCommandLog());
					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;
				case "BringToFront":
					Shape bringToFrontShape = parse(commandOperation[2].split(":")[0],
							commandOperation[2].split(":")[1]);
					i = model.getIndexOf(bringToFrontShape);
					doCommand = new CmdBringToFront(model, model.getShape(i), model.getShapes().size() - 1);
					if (commandOperation[1].equals("EXECUTE")) {
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdBringToFront) doCommand).getCommandLog());
					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;
				case "ToBack":
					Shape toBackShape = parse(commandOperation[2].split(":")[0], commandOperation[2].split(":")[1]);
					i = model.getIndexOf(toBackShape);
					doCommand = new CmdToBack(model, model.getShape(i));
					if (commandOperation[1].equals("EXECUTE")) {
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdToBack) doCommand).getCommandLog());
					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;
				case "ToFront":
					Shape toFrontShape = parse(commandOperation[2].split(":")[0], commandOperation[2].split(":")[1]);
					i = model.getIndexOf(toFrontShape);
					doCommand = new CmdToFront(model.getShape(i), model);
					if (commandOperation[1].equals("EXECUTE")) {
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdToFront) doCommand).getCommandLog());
					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;

				case "UPDATEPOINT":
					Shape oldShape = parse(commandOperation[2].split("->")[0].split(":")[0],
							commandOperation[2].split("->")[0].split(":")[1]);
					Shape newShape = parse(commandOperation[2].split("->")[0].split(":")[0],
							commandOperation[2].split("->")[1].split(":")[1]);

					if (commandOperation[1].equals("EXECUTE")) {
						doCommand = new CmdUpdatePoint((Point) oldShape, (Point) newShape);
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdUpdatePoint) doCommand).getCommandLog());
					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;

				case "UPDATELINE":
					String oldLineString = commandOperation[2].split(";")[0];
					String updatedLineString = commandOperation[2].split(";")[1];
						
					Shape oldLine = parse(oldLineString.split(":")[0],
							oldLineString.split(":")[1]);
					Shape newLine = parse(updatedLineString.split(":")[0],
							updatedLineString.split(":")[1]);

					if (commandOperation[1].equals("EXECUTE")) {
						i = model.getIndexOf(oldLine);
						doCommand = new CmdUpdateLine((Line) model.getShape(i), (Line) newLine);
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdUpdateLine) doCommand).getCommandLog());
					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;

				case "UPDATEREC":
					Shape oldRec = parse(commandOperation[2].split("->")[0].split(":")[0],
							commandOperation[2].split("->")[0].split(":")[1]);
					Shape newRec = parse(commandOperation[2].split("->")[0].split(":")[0],
							commandOperation[2].split("->")[1].split(":")[1]);

					if (commandOperation[1].equals("EXECUTE")) {
						i = model.getIndexOf(oldRec);
						doCommand = new CmdUpdateRectangle((Rectangle) model.getShape(i), (Rectangle) newRec);
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdUpdateRectangle) doCommand).getCommandLog());
					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;
				case "UPDATESQUARE":
					Shape oldSquare = parse(commandOperation[2].split("->")[0].split(":")[0],
							commandOperation[2].split("->")[0].split(":")[1]);
					Shape newSquare = parse(commandOperation[2].split("->")[0].split(":")[0],
							commandOperation[2].split("->")[1].split(":")[1]);

					if (commandOperation[1].equals("EXECUTE")) {
						i = model.getIndexOf(oldSquare);
						doCommand = new CmdUpdateSquare((Square) model.getShape(i), (Square) newSquare);
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdUpdateSquare) doCommand).getCommandLog());
					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;
				case "UPDATECIRCLE":
					Shape oldCircle = parse(commandOperation[2].split("->")[0].split(":")[0],
							commandOperation[2].split("->")[0].split(":")[1]);
					Shape newCircle = parse(commandOperation[2].split("->")[0].split(":")[0],
							commandOperation[2].split("->")[1].split(":")[1]);

					if (commandOperation[1].equals("EXECUTE")) {
						i = model.getIndexOf(oldCircle);
						doCommand = new CmdUpdateCircle((Circle) model.getShape(i), (Circle) newCircle);
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdUpdateCircle) doCommand).getCommandLog());
					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;
				case "UPDATEHEX":
					Shape oldHex = parse(commandOperation[2].split("->")[0].split(":")[0],
							commandOperation[2].split("->")[0].split(":")[1]);
					Shape newHex = parse(commandOperation[2].split("->")[0].split(":")[0],
							commandOperation[2].split("->")[1].split(":")[1]);

					if (commandOperation[1].equals("EXECUTE")) {
						i = model.getIndexOf(oldHex);
						doCommand = new CmdUpdateHexagon((HexagonAdapter) model.getShape(i), (HexagonAdapter) newHex);
						controller.performCommand(doCommand);
						controller.getLog().addElement(((CmdUpdateHexagon) doCommand).getCommandLog());
					} else if (commandOperation[1].equals("UNEXECUTE")) {
						controller.undoCommand();
					}
					frame.getView().repaint();
					break;

				default:
					break;
				}
			} else {
				frame.getMntmGetLog().setEnabled(false);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Shape parse(String shape, String value) {
		switch (shape) {
		case "Point":
			String[] values = value.split("-");
			String[] values2 = values[1].split(" ");
			return new Point(Integer.parseInt(values[0].substring(1)),
					Integer.parseInt(values2[0].substring(0, values2[0].length() - 1)), parseColor(values2[1]));
		case "Line":
			String[] data = value.split("->");
			String[] point1 = data[0].split("-");
			String[] point2 = data[1].split("-");
			String col = data[2];
			return new Line(
					new Point(Integer.parseInt(point1[0].substring(1)),
							Integer.parseInt(point1[1].substring(0, point1[1].length() - 1))),
					new Point(Integer.parseInt(point2[0].substring(1)),
							Integer.parseInt(point2[1].substring(0, point2[1].length() - 1))),
					parseColor(col));
		case "Rectangle":
			String[] e = value.split(" ");
			String[] upLeft = e[0].split(",");
			int x1 = Integer.parseInt(upLeft[0].substring(7));
			int y1 = Integer.parseInt(upLeft[1].substring(0, upLeft[1].length() - 1));
			int side = Integer.parseInt(e[1].substring(5, e[1].length() - 1));
			int width = Integer.parseInt(e[2].substring(6, e[2].length() - 1));
			Color colRect = parseColor(e[3]);
			Color colRectArea = parseColor(e[4]);
			return new Rectangle(new Point(x1, y1), side, width, colRect, colRectArea);
		case "Square":
			String[] t = value.split(" ");
			String[] upLeftSquare = t[0].split(",");
			int x2 = Integer.parseInt(upLeftSquare[0].substring(10));
			int y2 = Integer.parseInt(upLeftSquare[1].substring(0, upLeftSquare[1].length() - 1));
			int sideSquare = Integer.parseInt(t[1].substring(5, t[1].length() - 1));
			Color colSq = parseColor(t[2]);
			Color colSqArea = parseColor(t[3]);
			return new Square(new Point(x2, y2), sideSquare, colSq, colSqArea);
		case "Circle":
			String[] a = value.split(" ");
			String[] center = a[0].split(",");
			int x = Integer.parseInt(center[0].substring(7));
			int y = Integer.parseInt(center[1].substring(0, center[1].length() - 1));
			int radius = Integer.parseInt(a[1].substring(7, a[1].length() - 1));
			Color col1 = parseColor(a[2]);
			Color col2 = parseColor(a[3]);
			return new Circle(new Point(x, y), radius, col1, col2);
		case "Hexagon":
			String[] hex = value.split(" ");
			String[] centerHex = hex[0].split(",");
			int x3 = Integer.parseInt(centerHex[0].substring(7));
			int y3 = Integer.parseInt(centerHex[1].substring(0, centerHex[1].length() - 1));
			int radiusHex = Integer.parseInt(hex[1].substring(7, hex[1].length() - 1));
			Color colHex = parseColor(hex[2]);
			Color colHexArea = parseColor(hex[3]);
			Hexagon hexagon=new Hexagon(x3, y3, radiusHex);
			hexagon.setAreaColor(colHexArea);
			hexagon.setBorderColor(colHex);		
			HexagonAdapter h = new HexagonAdapter(hexagon);
			return h;
		default:
			return null;
		}
	}

	public Color parseColor(String col) {
		
			String colors = col.substring(15, col.length() - 1);
			String[] rgb = colors.split(",");

			int r = Integer.parseInt(rgb[0].substring(2, rgb[0].length()));
			int g = Integer.parseInt(rgb[1].substring(2, rgb[1].length()));
			int b = Integer.parseInt(rgb[2].substring(2, rgb[2].length()));
			return new Color(r, g, b);
	
	}
}
