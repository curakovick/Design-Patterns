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
import javax.swing.border.EmptyBorder;


public class DlgModifyCircle extends JDialog {


	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private JTextField txtRadius;
	private Color colorOfEdge;
	private Color insideColor;
	private JButton btnColorOfEdge;
	private JButton btnInsideColor;
	private boolean canceled=false;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgModifyCircle dialog = new DlgModifyCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgModifyCircle() {
		setTitle("Modify Circle:");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{217, 1, 0, 0};
		gbl_pnlCenter.rowHeights = new int[]{1, 0, 0, 0, 0, 0, 0};
		gbl_pnlCenter.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		{
			JLabel label = new JLabel("");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.anchor = GridBagConstraints.NORTHWEST;
			gbc_label.gridx = 1;
			gbc_label.gridy = 0;
			pnlCenter.add(label, gbc_label);
		}
		{
			JLabel lblCenterX = new JLabel("x coordinate of center:\r\n\r\n");
			GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
			gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterX.gridx = 0;
			gbc_lblCenterX.gridy = 1;
			pnlCenter.add(lblCenterX, gbc_lblCenterX);
		}
		{
			txtCenterX = new JTextField();
			GridBagConstraints gbc_txtCenterX = new GridBagConstraints();
			gbc_txtCenterX.insets = new Insets(0, 0, 5, 0);
			gbc_txtCenterX.gridx = 2;
			gbc_txtCenterX.gridy = 1;
			pnlCenter.add(txtCenterX, gbc_txtCenterX);
			txtCenterX.setColumns(10);
		}
		{
			JLabel lblCenterY = new JLabel("y coordinate of center:");
			GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
			gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterY.gridx = 0;
			gbc_lblCenterY.gridy = 2;
			pnlCenter.add(lblCenterY, gbc_lblCenterY);
		}
		{
			txtCenterY = new JTextField();
			GridBagConstraints gbc_txtCenterY = new GridBagConstraints();
			gbc_txtCenterY.insets = new Insets(0, 0, 5, 0);
			gbc_txtCenterY.gridx = 2;
			gbc_txtCenterY.gridy = 2;
			pnlCenter.add(txtCenterY, gbc_txtCenterY);
			txtCenterY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 3;
			pnlCenter.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.gridx = 2;
			gbc_txtRadius.gridy = 3;
			pnlCenter.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
		   btnColorOfEdge = new JButton("Color of edge");
		   btnColorOfEdge.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Color  chosedColorOfEdge= JColorChooser.showDialog(null,"Choose color: ", colorOfEdge);
			    	 if(chosedColorOfEdge != null)
			    		 colorOfEdge = chosedColorOfEdge;
			    	 btnColorOfEdge.setBackground(colorOfEdge);	
				}
			});
			GridBagConstraints gbc_btnColorOfEdge = new GridBagConstraints();
			gbc_btnColorOfEdge.insets = new Insets(0, 0, 0, 5);
			gbc_btnColorOfEdge.gridx = 0;
			gbc_btnColorOfEdge.gridy = 5;
			pnlCenter.add(btnColorOfEdge, gbc_btnColorOfEdge);
		}
		{
		    btnInsideColor = new JButton("Inside color");
		    btnInsideColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color  chosedInsideColor= JColorChooser.showDialog(null,"Choose color: ", insideColor);
			    	 if(chosedInsideColor != null)
			    			insideColor = chosedInsideColor;
			    	 btnInsideColor.setBackground(insideColor);	
					
				}
			});
			GridBagConstraints gbc_btnInsideColor = new GridBagConstraints();
			gbc_btnInsideColor.gridx = 2;
			gbc_btnInsideColor.gridy = 5;
			pnlCenter.add(btnInsideColor, gbc_btnInsideColor);
		}
		
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {						
							dispose();				
						
					}
				});
				btnConfirm.setActionCommand("OK");
				pnlButtons.add(btnConfirm);
				getRootPane().setDefaultButton(btnConfirm);
			}
			{
				JButton btnCancel = new JButton("Cancel");
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

	public JTextField getTxtCenterX() {
		return txtCenterX;
	}

	public void setTxtCenterX(JTextField txtCenterX) {
		this.txtCenterX = txtCenterX;
	}

	public JTextField getTxtCenterY() {
		return txtCenterY;
	}

	public void setTxtCenterY(JTextField txtCenterY) {
		this.txtCenterY = txtCenterY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public Color getColorOfEdge() {
		return colorOfEdge;
	}

	public void setColorOfEdge(Color colorOfEdge) {
		this.colorOfEdge = colorOfEdge;
	}

	public Color getInsideColor() {
		return insideColor;
	}

	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
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

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}


	
	

}
