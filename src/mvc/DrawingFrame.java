package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import mvc.DrawingController;
import mvc.DrawingView;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileNameExtensionFilter;



import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class DrawingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private DrawingView drawingView = new DrawingView();
	private DrawingController controller;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JMenuItem mntmUndo;
	private JMenuItem mntmRedo;
	private JMenuItem mntmToFront;
	private JMenuItem mntmToBack;
	private JMenuItem mntmBringToFront;
	private JMenuItem mntmBringToBack;
	private JMenuItem mntmGetLog;
	
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnSquare;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnHexagon;
	private JToggleButton tglbtnSelect;
	
	private JButton btnColorOfEdge;
	private JButton btnInsideColor;
	private JButton btnModify;
	private JButton btnDelete;
	
	private JFileChooser jFileChooser;
	private JFileChooser openChooser;
	

	private JList<String> list;
	private DefaultListModel<String> dlmLogList;
	private Panel OptionsAndSelect;
	private final Action action = new SwingAction();

	public DrawingView getView() {
		return drawingView;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DrawingFrame() {
		setFont(new Font("Segoe UI", Font.PLAIN, 25));

		setTitle("Curakovic Katarina IT22/2016");
		drawingView.setBackground(Color.WHITE);
		getContentPane().add(drawingView, BorderLayout.CENTER);

		JPanel logView = new JPanel();
		getContentPane().add(logView, BorderLayout.SOUTH);
		
		JPanel optionsView =  new JPanel();
		getContentPane().add(optionsView, BorderLayout.WEST);
	
		GridBagLayout gbl_optionsView = new GridBagLayout();
		gbl_optionsView.columnWidths = new int[]{121, 0};
		gbl_optionsView.rowHeights = new int[]{0, 23, 23, 23, 23, 23, 23, 0, 0, 0, 125, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 23, 23, 125, 23, 23, 0};
		gbl_optionsView.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_optionsView.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		optionsView.setLayout(gbl_optionsView);
		
		tglbtnPoint = new JToggleButton("");
		tglbtnPoint.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tglbtnPoint.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/point.png")));
		buttonGroup.add(tglbtnPoint);
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.anchor = GridBagConstraints.SOUTH;
		gbc_tglbtnPoint.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnPoint.gridx = 0;
		gbc_tglbtnPoint.gridy = 11;
		optionsView.add(tglbtnPoint, gbc_tglbtnPoint);
		
		tglbtnLine = new JToggleButton("");
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnLine.gridx = 0;
		gbc_tglbtnLine.gridy = 12;
		optionsView.add(tglbtnLine, gbc_tglbtnLine);
		tglbtnLine.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tglbtnLine.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/line.png")));
		buttonGroup.add(tglbtnLine);
		
		tglbtnSquare = new JToggleButton("");
		tglbtnSquare.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tglbtnSquare.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/square.png")));
		buttonGroup.add(tglbtnSquare);
		GridBagConstraints gbc_tglbtnSquare = new GridBagConstraints();
		gbc_tglbtnSquare.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnSquare.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSquare.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSquare.gridx = 0;
		gbc_tglbtnSquare.gridy = 13;
		optionsView.add(tglbtnSquare, gbc_tglbtnSquare);
		
		tglbtnRectangle = new JToggleButton("");
		tglbtnRectangle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tglbtnRectangle.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/rectangle.png")));
		buttonGroup.add(tglbtnRectangle);
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnRectangle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnRectangle.gridx = 0;
		gbc_tglbtnRectangle.gridy = 14;
		optionsView.add(tglbtnRectangle, gbc_tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("");
		tglbtnCircle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tglbtnCircle.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/circle.png")));
		buttonGroup.add(tglbtnCircle);
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnCircle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnCircle.gridx = 0;
		gbc_tglbtnCircle.gridy = 15;
		optionsView.add(tglbtnCircle, gbc_tglbtnCircle);
		
		tglbtnHexagon = new JToggleButton("");
		tglbtnHexagon.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tglbtnHexagon.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/Hexagon.png")));
		buttonGroup.add(tglbtnHexagon);
		GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
		gbc_tglbtnHexagon.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnHexagon.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnHexagon.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnHexagon.gridx = 0;
		gbc_tglbtnHexagon.gridy = 16;
		optionsView.add(tglbtnHexagon, gbc_tglbtnHexagon);

		JScrollPane scrollPane = new JScrollPane();
		logView.add(scrollPane);
		
		dlmLogList = new DefaultListModel<String>();

		list = new JList();
		list.setVisibleRowCount(4); 
		list.setFixedCellWidth(600); 
		scrollPane.setViewportView(list); 
		list.setModel(dlmLogList); 
		
		OptionsAndSelect = new Panel();
		getContentPane().add(OptionsAndSelect, BorderLayout.EAST);
		GridBagLayout gbl_OptionsAndSelect = new GridBagLayout();
		gbl_OptionsAndSelect.columnWidths = new int[]{163, 0};
		gbl_OptionsAndSelect.rowHeights = new int[]{39, 0, 0, 0, 0, 0, 0};
		gbl_OptionsAndSelect.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_OptionsAndSelect.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		OptionsAndSelect.setLayout(gbl_OptionsAndSelect);
		
		tglbtnSelect = new JToggleButton("Select");
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSelect.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSelect.gridx = 0;
		gbc_tglbtnSelect.gridy = 0;
		OptionsAndSelect.add(tglbtnSelect, gbc_tglbtnSelect);
		tglbtnSelect.setFont(new Font("Tahoma", Font.PLAIN, 25));
		buttonGroup.add(tglbtnSelect);
		
		btnModify = new JButton("Edit selected");
		GridBagConstraints gbc_btnModify = new GridBagConstraints();
		gbc_btnModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnModify.insets = new Insets(0, 0, 5, 0);
		gbc_btnModify.gridx = 0;
		gbc_btnModify.gridy = 1;
		OptionsAndSelect.add(btnModify, gbc_btnModify);
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btnModifyClicked(btnModify.getActionCommand());
			}
		});
		btnModify.setEnabled(false);
		
		
		
		btnDelete = new JButton("Delete selected");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 2;
		OptionsAndSelect.add(btnDelete, gbc_btnDelete);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btnDeleteClicked(btnDelete.getActionCommand());
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setEnabled(false);
		
		btnColorOfEdge = new JButton("Color of edge");
		GridBagConstraints gbc_btnColorOfEdge = new GridBagConstraints();
		gbc_btnColorOfEdge.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnColorOfEdge.insets = new Insets(0, 0, 5, 0);
		gbc_btnColorOfEdge.gridx = 0;
		gbc_btnColorOfEdge.gridy = 3;
		OptionsAndSelect.add(btnColorOfEdge, gbc_btnColorOfEdge);
		btnColorOfEdge.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnColorOfEdge.setBackground(Color.BLACK);
		btnColorOfEdge.setForeground(Color.WHITE);
		btnColorOfEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnColorOfEdge.setBackground(controller.pickColorOfEdge(btnColorOfEdge.getBackground()));
				btnColorOfEdge.setForeground(Color.BLACK);
			}
		});
		
		
		btnInsideColor = new JButton("Inside color");
		GridBagConstraints gbc_btnInsideColor = new GridBagConstraints();
		gbc_btnInsideColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnInsideColor.fill = GridBagConstraints.BOTH;
		gbc_btnInsideColor.gridx = 0;
		gbc_btnInsideColor.gridy = 4;
		OptionsAndSelect.add(btnInsideColor, gbc_btnInsideColor);
		btnInsideColor.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnInsideColor.setBackground(Color.WHITE);
		btnInsideColor.setForeground(Color.BLACK);
		btnInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnInsideColor.setBackground(controller.pickInsideColor(btnInsideColor.getBackground()));
				btnInsideColor.setForeground(Color.BLACK);
			}
		});

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.menu);
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File"); 
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mnFile.setArmed(true); 
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.newDraw();
			}
		});
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openChooser = new JFileChooser();
				openChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
				openChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG); 
				openChooser.setMultiSelectionEnabled(false); 
				openChooser.setFileHidingEnabled(false); 
				openChooser.setEnabled(true);
				openChooser.setAcceptAllFileFilterUsed(false);
				
				openChooser.setFileFilter(new FileNameExtensionFilter("SerializeDraw", "ser"));
				openChooser.setFileFilter(new FileNameExtensionFilter("FileLog", "log"));
				controller.openFile();		
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG); 
				jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
				jFileChooser.setMultiSelectionEnabled(false);
				jFileChooser.setFileHidingEnabled(false);
				jFileChooser.setEnabled(true);
				jFileChooser.setDialogTitle("Save");
				jFileChooser.setAcceptAllFileFilterUsed(false);

				controller.save();

			}
		});
		mnFile.add(mntmSave);

		mnFile.add(mntmClose);

		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		menuBar.add(mnEdit);

		mntmUndo = new JMenuItem("Undo");
		mntmUndo.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undoCommand();
				
			}
		});
		mnEdit.add(mntmUndo);
		mntmUndo.setEnabled(false);

		mntmRedo = new JMenuItem("Redo");
		mntmRedo.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmRedo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redoCommand();
				
			}
		});
		mnEdit.add(mntmRedo);
		mntmRedo.setEnabled(false);
		
		mntmGetLog = new JMenuItem("Get Log");
		mntmGetLog.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmGetLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.readCommand();
				
			}
		});
		mnEdit.add(mntmGetLog);
		mntmGetLog.setEnabled(false);


		JMenu mnMove = new JMenu("Move");
		mnMove.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		menuBar.add(mnMove);
		
		mntmToFront = new JMenuItem("To Front");
		mntmToFront.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveByZAxis(mntmToFront.getActionCommand());
				
			}
		});
		mnMove.add(mntmToFront);
		mntmToFront.setEnabled(false);

		mntmToBack = new JMenuItem("To Back");
		mntmToBack.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmToBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveByZAxis(mntmToBack.getActionCommand());
				
			}
		});
		mnMove.add(mntmToBack);
		mntmToBack.setEnabled(false);

		mntmBringToFront = new JMenuItem("Bring To Front");
		mntmBringToFront.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmBringToFront.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveByZAxis(mntmBringToFront.getActionCommand());
				
			}
		});
		mnMove.add(mntmBringToFront);
		mntmBringToFront.setEnabled(false);

		mntmBringToBack = new JMenuItem("Bring To Back");
		mntmBringToBack.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		mntmBringToBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.moveByZAxis(mntmBringToBack.getActionCommand());
				
			}
		});
		
		mnMove.add(mntmBringToBack);
		mntmBringToBack.setEnabled(false);
		
		
		


	drawingView.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent click) {
			if (tglbtnPoint.isSelected()) controller.btnAddPointClicked(click);
			else if (tglbtnLine.isSelected()) controller.btnAddLineClicked(click);
			else if (tglbtnRectangle.isSelected()) controller.btnAddRectangleClicked(click);
			else if (tglbtnSquare.isSelected()) controller.btnAddSquareClicked(click);		
			else if (tglbtnCircle.isSelected()) controller.btnAddCircleClicked(click);
			else if (tglbtnHexagon.isSelected()) controller.btnAddHexagonClicked(click);
			else if(tglbtnSelect.isSelected()) controller.btnSelectClicked(click);
		}
	});
	}

	public DrawingView getDrawingView() {
		return drawingView;
	}

	public void setDrawingView(DrawingView drawingView) {
		this.drawingView = drawingView;
	}


	public JMenuItem getMntmUndo() {
		return mntmUndo;
	}

	public void setMntmUndo(JMenuItem mntmUndo) {
		this.mntmUndo = mntmUndo;
	}

	public JMenuItem getMntmRedo() {
		return mntmRedo;
	}

	public void setMntmRedo(JMenuItem mntmRedo) {
		this.mntmRedo = mntmRedo;
	}

	public JMenuItem getMntmToFront() {
		return mntmToFront;
	}

	public void setMntmToFront(JMenuItem mntmToFront) {
		this.mntmToFront = mntmToFront;
	}

	public JMenuItem getMntmToBack() {
		return mntmToBack;
	}

	
	public JFileChooser getjFileChooser() {
		return jFileChooser;
	}

	public void setjFileChooser(JFileChooser jFileChooser) {
		this.jFileChooser = jFileChooser;
	}

	public JFileChooser getOpenChooser() {
		return openChooser;
	}

	public void setOpenChooser(JFileChooser openChooser) {
		this.openChooser = openChooser;
	}

	public void setMntmToBack(JMenuItem mntmToBack) {
		this.mntmToBack = mntmToBack;
	}

	public JMenuItem getMntmBringToFront() {
		return mntmBringToFront;
	}

	public void setMntmBringToFront(JMenuItem mntmBringToFront) {
		this.mntmBringToFront = mntmBringToFront;
	}

	public JMenuItem getMntmBringToBack() {
		return mntmBringToBack;
	}

	public void setMntmBringToBack(JMenuItem mntmBringToBack) {
		this.mntmBringToBack = mntmBringToBack;
	}

	public JMenuItem getMntmGetLog() {
		return mntmGetLog;
	}

	public void setMntmGetLog(JMenuItem mntmGetLog) {
		this.mntmGetLog = mntmGetLog;
	}

	
	public JList<String> getList() {
		return list;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}

	public DefaultListModel<String> getDlmLogList() {
		return dlmLogList;
	}

	public void setDlmLogList(DefaultListModel<String> dlmLogList) {
		this.dlmLogList = dlmLogList;
	}

	public DrawingController getController() {
		return controller;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public JButton getBtnColorOfEdge() {
		return btnColorOfEdge;
	}

	public void setBtnColorOfEdge(JButton btnColorOfEdge) {
		this.btnColorOfEdge = btnColorOfEdge;
	}

	public JButton getBtnInsideColor() {
		return btnInsideColor;
	}

	public void setBtnInsideColor(JButton btnInsideColor) {
		this.btnInsideColor = btnInsideColor;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}
	

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}