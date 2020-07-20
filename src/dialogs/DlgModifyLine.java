package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class DlgModifyLine extends JDialog {
	
	
	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JTextField txtStartPointX;
	private JTextField txtStartPointY;
	private JTextField txtEndPointX;
	private JTextField txtEndPointY;
	private Color colorOfEdge;
	private JButton btnColorOdEdge;
	private boolean canceled=false;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgModifyLine dialog = new DlgModifyLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgModifyLine() {
		setTitle("Modify line:");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{0, 0, 0};
		gbl_pnlCenter.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCenter.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		{
			JLabel lblStartPointX = new JLabel("x coordinate of start point:");
			lblStartPointX.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblStartPointX = new GridBagConstraints();
			gbc_lblStartPointX.anchor = GridBagConstraints.EAST;
			gbc_lblStartPointX.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartPointX.gridx = 0;
			gbc_lblStartPointX.gridy = 0;
			pnlCenter.add(lblStartPointX, gbc_lblStartPointX);
		}
		{
			txtStartPointX = new JTextField();
			GridBagConstraints gbc_txtStartPointX = new GridBagConstraints();
			gbc_txtStartPointX.insets = new Insets(0, 0, 5, 0);
			gbc_txtStartPointX.gridx = 1;
			gbc_txtStartPointX.gridy = 0;
			pnlCenter.add(txtStartPointX, gbc_txtStartPointX);
			txtStartPointX.setColumns(10);
		}
		{
			JLabel lblStartPointY = new JLabel("Y coordinate of start point:\r\n");
			GridBagConstraints gbc_lblStartPointY = new GridBagConstraints();
			gbc_lblStartPointY.anchor = GridBagConstraints.EAST;
			gbc_lblStartPointY.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartPointY.gridx = 0;
			gbc_lblStartPointY.gridy = 1;
			pnlCenter.add(lblStartPointY, gbc_lblStartPointY);
		}
		{
			txtStartPointY = new JTextField();
			GridBagConstraints gbc_txtStartPointY = new GridBagConstraints();
			gbc_txtStartPointY.insets = new Insets(0, 0, 5, 0);
			gbc_txtStartPointY.gridx = 1;
			gbc_txtStartPointY.gridy = 1;
			pnlCenter.add(txtStartPointY, gbc_txtStartPointY);
			txtStartPointY.setColumns(10);
		}
		{
			JLabel lblEndPointX = new JLabel("x coordinate of end point:");
			lblEndPointX.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblEndPointX = new GridBagConstraints();
			gbc_lblEndPointX.anchor = GridBagConstraints.EAST;
			gbc_lblEndPointX.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndPointX.gridx = 0;
			gbc_lblEndPointX.gridy = 2;
			pnlCenter.add(lblEndPointX, gbc_lblEndPointX);
		}
		{
			txtEndPointX = new JTextField();
			GridBagConstraints gbc_txtEndPointX= new GridBagConstraints();
			gbc_txtEndPointX.insets = new Insets(0, 0, 5, 0);
			gbc_txtEndPointX.gridx = 1;
			gbc_txtEndPointX.gridy = 2;
			pnlCenter.add(txtEndPointX, gbc_txtEndPointX);
			txtEndPointX.setColumns(10);
		}
		{
			JLabel lblEndPointY = new JLabel("y coordinate of end point:");
			lblEndPointY.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblEndPointY = new GridBagConstraints();
			gbc_lblEndPointY.anchor = GridBagConstraints.EAST;
			gbc_lblEndPointY.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndPointY.gridx = 0;
			gbc_lblEndPointY.gridy = 3;
			pnlCenter.add(lblEndPointY, gbc_lblEndPointY);
		}
		{
			txtEndPointY = new JTextField();
			GridBagConstraints gbc_txtEndPointY = new GridBagConstraints();
			gbc_txtEndPointY.insets = new Insets(0, 0, 5, 0);
			gbc_txtEndPointY.gridx = 1;
			gbc_txtEndPointY.gridy = 3;
			pnlCenter.add(txtEndPointY, gbc_txtEndPointY);
			txtEndPointY.setColumns(10);
		}
		{
		    btnColorOdEdge = new JButton("Color of edge");
		    btnColorOdEdge.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 Color  colorOfLine = JColorChooser.showDialog(null,"Choose color: ", colorOfEdge);
			    	 if(colorOfLine != null)
			    		 colorOfEdge = colorOfLine;
			    	 btnColorOdEdge.setBackground(colorOfEdge);
					
				}
			});
			GridBagConstraints gbc_btnColorOfEdge = new GridBagConstraints();
			gbc_btnColorOfEdge.anchor = GridBagConstraints.BELOW_BASELINE;
			gbc_btnColorOfEdge.insets = new Insets(0, 0, 5, 5);
			gbc_btnColorOfEdge.gridx = 0;
			gbc_btnColorOfEdge.gridy = 4;
			pnlCenter.add(btnColorOdEdge, gbc_btnColorOfEdge);
		}		
		
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton btnConfirm  = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
			
					public void actionPerformed(ActionEvent arg0) {						
							
							dispose();					
					}
				});
				btnConfirm.setActionCommand("OK");
				pnlButtons.add(btnConfirm);
				getRootPane().setDefaultButton(btnConfirm);
			}
			{
				JButton btnCancel = new JButton("Odustani");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						canceled=true;
						dispose();
						
					}
				});
				btnCancel.setActionCommand("Cancel");
				pnlButtons.add(btnCancel);
			}
    	}

}

	public JTextField getTxtStartPointX() {
		return txtStartPointX;
	}

	public void setTxtStartPointX(JTextField txtStartPointX) {
		this.txtStartPointX = txtStartPointX;
	}

	public JTextField getTxtStartPointY() {
		return txtStartPointY;
	}

	public void setTxtStartPointY(JTextField txtStartPointY) {
		this.txtStartPointY = txtStartPointY;
	}

	public JTextField getTxtEndPointX() {
		return txtEndPointX;
	}

	public void setTxtEndPointX(JTextField txtEndPointX) {
		this.txtEndPointX = txtEndPointX;
	}

	public JTextField getTxtEndPointY() {
		return txtEndPointY;
	}

	public void setTxtEndPointY(JTextField txtEndPointY) {
		this.txtEndPointY = txtEndPointY;
	}

	public Color getColorOfEdge() {
		return colorOfEdge;
	}

	public void setColorOfEdge(Color colorOfEdge) {
		this.colorOfEdge = colorOfEdge;
	}

	public JButton getBtnColorOdEdge() {
		return btnColorOdEdge;
	}

	public void setBtnColorOdEdge(JButton btnColorOdEdge) {
		this.btnColorOdEdge = btnColorOdEdge;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}


	
	
	
}
