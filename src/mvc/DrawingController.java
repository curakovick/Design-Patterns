package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdRemoveAll;
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

import dialogs.DlgCircle;
import dialogs.DlgHexagon;
import dialogs.DlgModifyCircle;
import dialogs.DlgModifyHexagon;
import dialogs.DlgModifyLine;
import dialogs.DlgModifyPoint;
import dialogs.DlgModifyRectangle;
import dialogs.DlgModifySquare;
import dialogs.DlgRectangle;
import dialogs.DlgSquare;

import hexagon.Hexagon;
import observer.Observer;
import observer.Subject;
import shapes.Circle;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;
import strategy.FileManager;
import strategy.FileLog;
import strategy.SerializeDraw;

public class DrawingController implements Subject {
	
	private DrawingModel model;
	private DrawingFrame frame;
	private CmdAddShape shape;  
	private Color insideColorDefault=Color.WHITE;
	private Color colorOfEdgeDefault=Color.BLACK;
	private Point t1=new Point();
	private ArrayList<Observer> observers;
	private Stack<Command> undoCommands;
	private Stack<Command> redoCommands;
	private DefaultListModel<String> log;
	private FileManager context;
	private FileLog fileLog;
	private int clickCount;
	
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		super();
		this.model = model;
		this.frame = frame;
		this.observers = new ArrayList<Observer>();
		this.undoCommands = new Stack<Command>();
		this.redoCommands = new Stack<Command>();
		log = frame.getDlmLogList();
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}
	


	public void performCommand(Command c) {
		// TODO Auto-generated method stub
		c.execute();
		undoCommands.add(c);
		notifyObservers(undoCommands.size(), redoCommands.size());
		frame.getView().repaint();
		
	}



	public Color pickColorOfEdge(Color oldColor) {
		// TODO Auto-generated method stub
		Color  colorOfEdge = JColorChooser.showDialog(null,"Choose color: ", oldColor);
		if(colorOfEdge != null)
			colorOfEdgeDefault=colorOfEdge;
		else
			colorOfEdgeDefault=oldColor;
				
	    return colorOfEdgeDefault;
		
	
	}

	public Color pickInsideColor(Color oldColor) {
		// TODO Auto-generated method stub
		Color  colorOfEdge = JColorChooser.showDialog(null,"Choose color: ", oldColor);
		if(colorOfEdge != null)
			insideColorDefault=colorOfEdge;
		else
			insideColorDefault=oldColor;
				
	    return insideColorDefault;
	
	}

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}

	@Override
	public void notifyObservers(int undo, int redo) {
		Iterator<Observer> it = observers.iterator();
		while (it.hasNext()) {
			it.next().update(frame, model, undo, redo);
		}
	}
	
	public void btnAddPointClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		Point p = new Point(x, y);
		p.setColor(colorOfEdgeDefault);
		shape = new CmdAddShape(p, model);
		performCommand(shape);
		log.addElement(shape.getCommandLog());
		
		
	}

	public void btnAddLineClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(clickCount==0) {
 			t1.setX(e.getX());
 			t1.setY(e.getY());
 			clickCount=1;	
 		} else {
 			Line l1 = new Line(new Point(t1.getX(),t1.getY()),new Point(e.getX(),e.getY()));
 			l1.setColor(colorOfEdgeDefault);
 			shape = new CmdAddShape(l1, model);
 			performCommand(shape);	
			clickCount=0;
 			log.addElement(shape.getCommandLog());
				
 		}
	
	}

	public void btnAddSquareClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		DlgSquare sq = new DlgSquare();
 		sq.setVisible(true);
 		if(sq.isCanceled()) {
 			log.addElement("Adding square canceled");
 				
 		} else {
 			int page = sq.getPage();
 			Square kv1 = new Square(new Point(e.getX(),e.getY()),page);
 			kv1.setColor(colorOfEdgeDefault);
 			kv1.setInsideColor(insideColorDefault);
 			shape = new CmdAddShape(kv1, model);
 			performCommand(shape);
 			log.addElement(shape.getCommandLog());
		
 		}
	}

	public void btnAddRectangleClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		DlgRectangle dlgAddRectangle = new DlgRectangle();
		dlgAddRectangle.setVisible(true);
		if (dlgAddRectangle.isCanceled()) {
			log.addElement("Adding rectangle canceled");
		} else {
			Rectangle rectangle = new Rectangle(new Point(e.getX(), e.getY()),
					dlgAddRectangle.getHeightRec(), dlgAddRectangle.getWidthRec(),
					colorOfEdgeDefault, insideColorDefault);
			if (dlgAddRectangle.getHeightRec() > 0 && dlgAddRectangle.getWidthRec() > 0) {
				shape = new CmdAddShape(rectangle, model);
				performCommand(shape);
				log.addElement(shape.getCommandLog());
			} else if (dlgAddRectangle.getHeightRec() <= 0 || dlgAddRectangle.getWidthRec() <= 0)
				JOptionPane.showMessageDialog(null, "Value can't be negative", "Warning",
						JOptionPane.ERROR_MESSAGE);
		}
 		
 	
	}

	public void btnAddCircleClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		DlgCircle kr = new DlgCircle();
 		kr.setVisible(true);
 		if(!kr.isCanceled()) {
 			int radius = kr.getRadius();
 	 		Circle kr1 = new Circle(new Point(e.getX(),e.getY()),radius);
 	 		kr1.setColor(colorOfEdgeDefault);
 	 		kr1.setInsideColor(insideColorDefault);
 	 		shape = new CmdAddShape(kr1, model);
 	 		performCommand(shape);
 			log.addElement(shape.getCommandLog());
 		} else {
 			
 			log.addElement("Adding circle canceled");
 		}
 		
	}

	public void btnAddHexagonClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		DlgHexagon kr = new DlgHexagon();
 		kr.setVisible(true);
 		if(!kr.isCanceled()) {
 			int radius = kr.getRadius();
 			Hexagon kr1 = new Hexagon(e.getX(),e.getY(),radius);
 			kr1.setBorderColor(colorOfEdgeDefault);
 			kr1.setAreaColor(insideColorDefault);
 			HexagonAdapter ha= new HexagonAdapter(kr1);
 			shape = new CmdAddShape(ha, model);
 			performCommand(shape);
 			log.addElement(shape.getCommandLog());
 		} else {
 			log.addElement("Adding hexagon canceled");
 		} 
	}

	public void btnSelectClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i = this.model.getShapes().size() - 1; i >= 0; i--) {
			if (this.model.getShapes().get(i).contains(e.getX(), e.getY())) {
				if (this.model.getShapes().get(i).isSelected()) {
					Command c = new CmdSelectShape(this.model.getShapes().get(i), false);
					performCommand(c);
					log.addElement(((CmdSelectShape) c).getCommandLog());
					// log.addElement("Deselect_" + model.getShape(i).toString());
					break;
				} else {
					Command c = new CmdSelectShape(this.model.getShapes().get(i), true);
					performCommand(c);
					log.addElement(((CmdSelectShape) c).getCommandLog());
					// log.addElement("Select_" + model.getShape(i).toString());
					break;
				}
			}
		}frame.getView().repaint();
	}



	public void btnDeleteClicked(String actionCommand) {
		// TODO Auto-generated method stub
		Command deleteShape;
		Command deleteAll;
		ArrayList<Shape> listOfSelectedShapes = new ArrayList<Shape>();
		Object[] options = { "Yes", "No" };
		int n = 0;

		
			for (int i = model.getShapes().size() - 1; i >= 0; i--) {
				if (model.getShape(i).isSelected()) {
					listOfSelectedShapes.add(model.getShape(i));
				}
			}
			if (listOfSelectedShapes.size() == 1) {
				n = JOptionPane.showOptionDialog(null, "Are you sure you want to delete selected shape?",
						"Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);

				if (n == 0) {
					deleteShape = new CmdRemoveShape(listOfSelectedShapes.get(0), model);
					performCommand(deleteShape);
					System.out.println(((CmdRemoveShape) deleteShape).getCommandLog());
					log.addElement(((CmdRemoveShape) deleteShape).getCommandLog());
				}

			} else if (listOfSelectedShapes.size() > 1) {
				n = JOptionPane.showOptionDialog(null, "Are you sure you want to delete selected shapes?",
						"Delete confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[0]);
				if (n == 0) {
					deleteAll = new CmdRemoveAll(model, listOfSelectedShapes);
					performCommand(deleteAll);
					log.addElement(((CmdRemoveAll) deleteAll).getCommandLog());
				}
			}

		
	}
	

	public void btnModifyClicked(String actionCommand) {
		// TODO Auto-generated method stub
		
		Shape selected = getSelectedShape();
		if (selected instanceof Line) btnUpdateLineClicked((Line)selected);
		else if (selected instanceof Point) btnUpdatePointClicked((Point) selected);
		else if (selected instanceof Rectangle) btnUpdateRectangleClicked((Rectangle) selected);
		else if (selected instanceof Square) btnUpdateSquareClicked((Square) selected);
		else if (selected instanceof Circle) btnUpdateCircleClicked((Circle) selected);
		else if (selected instanceof HexagonAdapter) btnUpdateHexagonClicked((HexagonAdapter) selected);
			
		
	}

	private void btnUpdateHexagonClicked(HexagonAdapter selected) {
		// TODO Auto-generated method stub
		DlgModifyHexagon c = new DlgModifyHexagon(); 
		try {
			c.getTxtX().setText(Integer.toString(selected.getX()));
			c.getTxtY().setText(Integer.toString(selected.getY()));
			c.getTxtRadius().setText(Integer.toString(selected.getR()));
			c.getBtnColorOfEdge().setBackground(selected.getColor());
			c.getBtnInsideColor().setBackground(selected.getInsideColor());
			c.setModal(true);
			c.setVisible(true);
			if(!c.isCanceled()) {
				Hexagon changedHexagon = new Hexagon(Integer.parseInt(c.getTxtX().getText()),Integer.parseInt(c.getTxtY().getText()), Integer.parseInt(c.getTxtRadius().getText()));
				changedHexagon.setBorderColor(c.getBtnColorOfEdge().getBackground());
				changedHexagon.setAreaColor(c.getBtnInsideColor().getBackground());
				HexagonAdapter hex=new HexagonAdapter(changedHexagon);
				CmdUpdateHexagon newShape = new CmdUpdateHexagon((HexagonAdapter) selected, hex);
				performCommand(newShape);
				log.addElement(((CmdUpdateHexagon) newShape).getCommandLog());
					
			}else {
				log.addElement("Point update canceled");
			}
			
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Enter integer values!",
					"Error", JOptionPane.WARNING_MESSAGE);
		  }
	}

	private void btnUpdateCircleClicked(Circle selected) {
		// TODO Auto-generated method stub
		DlgModifyCircle c = new DlgModifyCircle(); 
		try {
			c.getTxtCenterX().setText(Integer.toString(selected.getCenter().getX()));
			c.getTxtCenterY().setText(Integer.toString(selected.getCenter().getY()));
			c.getTxtRadius().setText(Integer.toString(selected.getRadius()));
			c.getBtnColorOfEdge().setBackground(selected.getColor());
			c.getBtnInsideColor().setBackground(selected.getInsideColor());
			c.setModal(true);
			c.setVisible(true);
			if(!c.isCanceled()) {
				Circle changedCircle = new Circle();
				changedCircle.setCenter(new Point(Integer.parseInt(c.getTxtCenterX().getText()),Integer.parseInt(c.getTxtCenterY().getText())));	
				changedCircle.setRadius(Integer.parseInt(c.getTxtRadius().getText()));
				changedCircle.setColor(c.getBtnColorOfEdge().getBackground());
				changedCircle.setInsideColor(c.getBtnInsideColor().getBackground());
				CmdUpdateCircle newShape = new CmdUpdateCircle((Circle) selected, changedCircle);
				performCommand(newShape);
				log.addElement(((CmdUpdateCircle) newShape).getCommandLog());
					
			}else {
				log.addElement("Circle update canceled");
			}
			
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Enter integer values!",
					"Error", JOptionPane.WARNING_MESSAGE);
		  }
	}

	private void btnUpdateSquareClicked(Square selected) {
		// TODO Auto-generated method stub
		DlgModifySquare kv = new DlgModifySquare(); 
		try {
			kv.getTxtUpperLeftX().setText(Integer.toString(selected.getUpperLeft().getX()));
			kv.getTxtUpperLeftY().setText(Integer.toString(selected.getUpperLeft().getY()));
			kv.getTxtPage().setText(Integer.toString(selected.getPage()));
			kv.getBtnColorOfEdge().setBackground(selected.getColor());
			kv.getBtnInsideColor().setBackground(selected.getInsideColor());
			kv.setModal(true);
			kv.setVisible(true);
			if(!kv.isCanceled()) {
				Square changedSquare = new Square();
				changedSquare.setUpperLeft(new Point(Integer.parseInt(kv.getTxtUpperLeftX().getText()),Integer.parseInt(kv.getTxtUpperLeftY().getText())));	
				changedSquare.setPage(Integer.parseInt(kv.getTxtPage().getText()));
				changedSquare.setColor(kv.getBtnColorOfEdge().getBackground());
				changedSquare.setInsideColor(kv.getBtnInsideColor().getBackground());
				CmdUpdateSquare newShape = new CmdUpdateSquare((Square) selected, changedSquare);
				performCommand(newShape);
				log.addElement(((CmdUpdateSquare) newShape).getCommandLog());
					
			}else {
				log.addElement("Point update canceled");
			}
			
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Enter integer values!",
					"Error", JOptionPane.WARNING_MESSAGE);
		  }
		
	}

	private void btnUpdateRectangleClicked(Rectangle selected) {
		// TODO Auto-generated method stub
		DlgModifyRectangle p = new DlgModifyRectangle();
		try {
			  p.getTxtUpperLeftX().setText(Integer.toString(selected.getUpperLeft().getX()));
			  p.getTxtUpperLeftY().setText(Integer.toString(selected.getUpperLeft().getY()));
			  p.getTxtWidth().setText(Integer.toString(selected.getWidth()));
			  p.getTxtHeight().setText(Integer.toString(selected.getPage()));
			  p.getBtnColorOfEdge().setBackground(selected.getColor());
			  p.getBtnInsideColor().setBackground(selected.getInsideColor());
			  p.setModal(true);
			  p.setVisible(true);
			  if(!p.isCanceled()) {
				  Rectangle changedRectangle=new Rectangle();
				  changedRectangle.setUpperLeft(new Point(Integer.parseInt(p.getTxtUpperLeftX().getText()),Integer.parseInt(p.getTxtUpperLeftY().getText())));
				  changedRectangle.setWidth(Integer.parseInt(p.getTxtWidth().getText()));
				  changedRectangle.setPage(Integer.parseInt(p.getTxtHeight().getText()));
				  changedRectangle.setColor(p.getBtnColorOfEdge().getBackground());
				  changedRectangle.setInsideColor(p.getBtnInsideColor().getBackground());
				  
				  CmdUpdateRectangle newShape = new CmdUpdateRectangle((Rectangle) selected, changedRectangle);
				  performCommand(newShape);
					log.addElement(((CmdUpdateRectangle) newShape).getCommandLog());
			  }else {
					log.addElement("Point update canceled");
				}							
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Enter integer values!",
					"Error", JOptionPane.WARNING_MESSAGE);
			return;
		 }
	}

	private void btnUpdateLineClicked(Line selected) {
		// TODO Auto-generated method stub
		DlgModifyLine l = new DlgModifyLine();
		try {
			l.getTxtStartPointX().setText(Integer.toString(selected.getStartPoint().getX()));
			l.getTxtStartPointY().setText(Integer.toString(selected.getStartPoint().getY()));
			l.getTxtEndPointX().setText(Integer.toString(selected.getEndPoint().getX()));
			l.getTxtEndPointY().setText(Integer.toString(selected.getEndPoint().getY()));
			l.getBtnColorOdEdge().setBackground(selected.getColor());
			l.setModal(true);
			l.setVisible(true);
			if(!l.isCanceled()) { 
				Line changedLine=new Line();
				changedLine.setStartPoint(new Point(Integer.parseInt(l.getTxtStartPointX().getText()),Integer.parseInt(l.getTxtStartPointY().getText())));
		        changedLine.setEndPoint(new Point(Integer.parseInt(l.getTxtEndPointX().getText()),Integer.parseInt(l.getTxtEndPointY().getText())));
		        changedLine.setColor(l.getBtnColorOdEdge().getBackground());
				CmdUpdateLine newShape = new CmdUpdateLine((Line) selected, changedLine);
				performCommand(newShape);
				log.addElement(((CmdUpdateLine) newShape).getCommandLog());
			} else {
				log.addElement("Line update canceled");
			}
			
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Enter integer values!",
					"Error", JOptionPane.WARNING_MESSAGE);
		 }
	}

	private void btnUpdatePointClicked(Point selected) {
		// TODO Auto-generated method stub
		
		DlgModifyPoint t= new DlgModifyPoint();
			try {
				
				t.getTxtPointX().setText(Integer.toString(selected.getX()));
				t.getTxtPointY().setText(Integer.toString(selected.getY()));
				t.getBtnColor().setBackground(selected.getColor());
				t.setModal(true);
				t.setVisible(true);
				if(!t.isCanceled()) {
					Point changedPoint = new Point();
					changedPoint.setX(Integer.parseInt(t.getTxtPointX().getText()));
					changedPoint.setY(Integer.parseInt(t.getTxtPointY().getText()));
					changedPoint.setColor(t.getBtnColor().getBackground());
					CmdUpdatePoint newShape = new CmdUpdatePoint((Point) selected, changedPoint);
					performCommand(newShape);
					log.addElement(((CmdUpdatePoint) newShape).getCommandLog());
				}
				else {
					log.addElement("Point update canceled");
				}
				
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Enter integer values!",
						"Error", JOptionPane.WARNING_MESSAGE);
			  }
	}

   private Shape getSelectedShape() {
		// TODO Auto-generated method stub
		Iterator<Shape> it = model.getShapes().iterator();
		while(it.hasNext()) {
			Shape temp = it.next();
			if(temp.isSelected()) {
				return temp;
				}
		}
		return null;
	
	}



	public void undoCommand() {
		// TODO Auto-generated method stub
		Command command = undoCommands.peek(); 
		notifyObservers(undoCommands.size(), redoCommands.size());
		command.unexecute();


		
		if (command instanceof CmdAddShape) { 
			log.addElement(((CmdAddShape) command).getCommandLog());
		} else if (command instanceof CmdRemoveShape) {
			log.addElement(((CmdRemoveShape) command).getCommandLog());
		} else if (command instanceof CmdToFront) {
			log.addElement(((CmdToFront) command).getCommandLog());
		} else if (command instanceof CmdToBack) {
			log.addElement(((CmdToBack) command).getCommandLog());
		} else if (command instanceof CmdBringToFront) {
			log.addElement(((CmdBringToFront) command).getCommandLog());
		} else if (command instanceof CmdBringToBack) {
			log.addElement(((CmdBringToBack) command).getCommandLog());
		} else if (command instanceof CmdUpdatePoint) {
			log.addElement(((CmdUpdatePoint) command).getCommandLog());
		} else if (command instanceof CmdUpdateLine) {
			log.addElement(((CmdUpdateLine) command).getCommandLog());
		} else if (command instanceof CmdUpdateRectangle) {
			log.addElement(((CmdUpdateRectangle) command).getCommandLog());
		} else if (command instanceof CmdUpdateSquare) {
			log.addElement(((CmdUpdateSquare) command).getCommandLog());
		} else if (command instanceof CmdUpdateCircle) {
			log.addElement(((CmdUpdateCircle) command).getCommandLog());
		} else if (command instanceof CmdUpdateHexagon) {
			log.addElement(((CmdUpdateHexagon) command).getCommandLog());
		} else if (command instanceof CmdSelectShape) {
			log.addElement(((CmdSelectShape) command).getCommandLog());
		}

		
		if (command instanceof CmdRemoveAll) {
			log.addElement(((CmdRemoveAll) command).getCommandLog());
			int n = ((CmdRemoveAll) command).getListOfShapes().size();
		}

		undoCommands.pop();
		notifyObservers(undoCommands.size(), redoCommands.size());
		redoCommands.push(command);
		notifyObservers(undoCommands.size(), redoCommands.size());
		frame.getView().repaint();
	}

	public void redoCommand() {
		// TODO Auto-generated method stub
		Command command = redoCommands.peek();

		notifyObservers(undoCommands.size(), redoCommands.size());
		command.execute();
		
		
		if (command instanceof CmdAddShape) {
			log.addElement(((CmdAddShape) command).getCommandLog());
		} else if (command instanceof CmdRemoveShape) 
		   {if (((CmdRemoveShape) command).getShape().isSelected()==true) {
				log.addElement(((CmdRemoveShape) command).getCommandLog());
		}
		} else if (command instanceof CmdToFront) {
			log.addElement(((CmdToFront) command).getCommandLog());
		} else if (command instanceof CmdToBack) {
			log.addElement(((CmdToBack) command).getCommandLog());
		} else if (command instanceof CmdBringToFront) {
			log.addElement(((CmdBringToFront) command).getCommandLog());
		} else if (command instanceof CmdBringToBack) {
			log.addElement(((CmdBringToBack) command).getCommandLog());
		} else if (command instanceof CmdUpdatePoint) {
			log.addElement(((CmdUpdatePoint) command).getCommandLog());
		} else if (command instanceof CmdUpdateLine) {
			log.addElement(((CmdUpdateLine) command).getCommandLog());
		} else if (command instanceof CmdUpdateRectangle) {
			log.addElement(((CmdUpdateRectangle) command).getCommandLog());
		} else if (command instanceof CmdUpdateSquare) {
			log.addElement(((CmdUpdateSquare) command).getCommandLog());
		} else if (command instanceof CmdUpdateCircle) {
			log.addElement(((CmdUpdateCircle) command).getCommandLog());
		} else if (command instanceof CmdUpdateHexagon) {
			log.addElement(((CmdUpdateHexagon) command).getCommandLog());
		} else if (command instanceof CmdSelectShape) {
			log.addElement(((CmdSelectShape) command).getCommandLog());
		}
		
		
		if (command instanceof CmdRemoveAll) {
			log.addElement(((CmdRemoveAll) command).getCommandLog());
		}
		undoCommands.push(command);
		notifyObservers(undoCommands.size(), redoCommands.size());
		redoCommands.pop();
		notifyObservers(undoCommands.size(), redoCommands.size());
		frame.getView().repaint();
	}

	public void readCommand() {
		// TODO Auto-generated method stub
		fileLog.getLine();
	}

	public void moveByZAxis(String name) {
		// TODO Auto-generated method stub
		for (int i = model.getShapes().size() - 1; i >= 0; i--) {
			if (model.getShape(i).isSelected()==true) {
				if (name.equals("To Front")) {
					if (model.getIndexOf(model.getShape(i)) == model.getShapes().size() - 1) {
						JOptionPane.showMessageDialog(null, "Shape is already on top");
					} else {
						CmdToFront cmdToFront = new CmdToFront(model.getShape(i), model);
						performCommand(cmdToFront);
						log.addElement(cmdToFront.getCommandLog());
					}
				} else if (name.equals("To Back")) {
					if (model.getIndexOf(model.getShape(i)) == 0) {
						JOptionPane.showMessageDialog(null, "Shape is already at the bottom");
					} else {
						CmdToBack cmdToBack = new CmdToBack(model, model.getShape(i));
						performCommand(cmdToBack);
						log.addElement(cmdToBack.getCommandLog());
						break;
					}
				} else if (name.equals("Bring To Front")) {
					if (model.getIndexOf(model.getShape(i)) == model.getShapes().size() - 1) {
						JOptionPane.showMessageDialog(null, "Shape is already on top!");
					} else {
						CmdBringToFront cmdBringToFront = new CmdBringToFront(model, model.getShape(i),
								model.getShapes().size() - 1);
						performCommand(cmdBringToFront);
						log.addElement(cmdBringToFront.getCommandLog());
					}
				} else if (name.equals("Bring To Back")) {
					if (model.getIndexOf(model.getShape(i)) == 0) {
						JOptionPane.showMessageDialog(null, "Shape is already at the bottom");
					} else {
						CmdBringToBack cmdBringToBack = new CmdBringToBack(model, model.getShape(i));
						performCommand(cmdBringToBack);
						log.addElement(cmdBringToBack.getCommandLog());
						break;

					}
				}
			}
		}
		frame.getView().repaint();
	}

	public DefaultListModel<String> getLog() {
		// TODO Auto-generated method stub
		return log;
	}

	public void openFile() {
		// TODO Auto-generated method stub
		if (frame.getOpenChooser().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			model.removeAll();
			log.removeAllElements();
			undoCommands.clear();
			redoCommands.clear();
			notifyObservers(undoCommands.size(), redoCommands.size());

			frame.getView().repaint();

			if (frame.getOpenChooser().getFileFilter().getDescription().equals("SerializeDraw")) {
				context = new FileManager(new SerializeDraw(model));

			} else if (frame.getOpenChooser().getFileFilter().getDescription().equals("FileLog")) {
				fileLog = new FileLog(frame, model, this);
				context = new FileManager(fileLog);
				frame.getMntmGetLog().setEnabled(true);
			}

			context.open(frame.getOpenChooser().getSelectedFile());
			log.addElement("Imported file from " + frame.getOpenChooser().getSelectedFile().toString());
		}
		frame.getOpenChooser().setVisible(false);
	}

	public void save() {
		// TODO Auto-generated method stub
		if (!model.getShapes().isEmpty()) {
			frame.getjFileChooser().setFileFilter(new FileNameExtensionFilter("SerializeDraw", "ser"));
		}
		if (!log.isEmpty()) {
			frame.getjFileChooser().setFileFilter(new FileNameExtensionFilter("FileLog", "log"));
		}
		if (frame.getjFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (frame.getjFileChooser().getFileFilter().getDescription().equals("FileLog"))
				context = new FileManager(new FileLog(frame, model, this));
			else if (frame.getjFileChooser().getFileFilter().getDescription().equals("SerializeDraw"))
				context = new FileManager(new SerializeDraw(model));

			context.save(frame.getjFileChooser().getSelectedFile());
		}
		frame.getjFileChooser().setVisible(false);
	}

	public void newDraw() {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(null, "Are you sure that you want to start new draw?", "Warning!", JOptionPane.YES_NO_OPTION) == 0) {	
			this.model.getShapes().clear();
			this.log.removeAllElements();
			this.undoCommands.clear();
			this.redoCommands.clear();
			this.notifyObservers(undoCommands.size(), redoCommands.size());
			this.frame.getView().repaint();
		}
	}

	
	
	
	
	
}

