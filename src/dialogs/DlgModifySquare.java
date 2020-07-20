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



public class DlgModifySquare extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JTextField txtUpperLeftX;
	private JTextField txtUpperLeftY;
	private JTextField txtPage;
	private Color colorOfEdge;
	private Color insideColor;
	private JButton btnInsideColor;
	private JButton btnColorOfEdge;
	private boolean canceled=false;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgModifySquare dialog = new DlgModifySquare(); 
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgModifySquare() {
		setTitle("Modify square:");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		pnlCenter.setBackground(Color.WHITE);
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{0, 0, 0, 0};
		gbl_pnlCenter.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCenter.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		{
			JLabel lblUpperLeftX = new JLabel("x coordinate of upperLeft:");
			GridBagConstraints gbc_lblUpperLeftX = new GridBagConstraints();
			gbc_lblUpperLeftX.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperLeftX.gridx = 0;
			gbc_lblUpperLeftX.gridy = 0;
			pnlCenter.add(lblUpperLeftX, gbc_lblUpperLeftX);
		}
		{
			txtUpperLeftX = new JTextField(); 
			GridBagConstraints gbc_txtUpperLeftX = new GridBagConstraints();
			gbc_txtUpperLeftX.insets = new Insets(0, 0, 5, 0);
			gbc_txtUpperLeftX.gridx = 2;
			gbc_txtUpperLeftX.gridy = 0;
			pnlCenter.add(txtUpperLeftX, gbc_txtUpperLeftX);
			txtUpperLeftX.setColumns(10);
		}
		{
			JLabel lblYCoordinateUpperLeft = new JLabel("y coordinate upperLeft:");
			GridBagConstraints gbc_lblYCoordinateUpperLeft = new GridBagConstraints();
			gbc_lblYCoordinateUpperLeft.insets = new Insets(0, 0, 5, 5);
			gbc_lblYCoordinateUpperLeft.gridx = 0;
			gbc_lblYCoordinateUpperLeft.gridy = 1;
			pnlCenter.add(lblYCoordinateUpperLeft, gbc_lblYCoordinateUpperLeft);
		}
		{
			txtUpperLeftY = new JTextField();
			GridBagConstraints gbc_txtUpperLeftY = new GridBagConstraints();
			gbc_txtUpperLeftY.insets = new Insets(0, 0, 5, 0);
			gbc_txtUpperLeftY.gridx = 2;
			gbc_txtUpperLeftY.gridy = 1;
			pnlCenter.add(txtUpperLeftY, gbc_txtUpperLeftY);
			txtUpperLeftY.setColumns(10);
		}

		{
			JLabel lblPage = new JLabel("Page:");
			GridBagConstraints gbc_lblPage = new GridBagConstraints();
			gbc_lblPage.insets = new Insets(0, 0, 5, 5);
			gbc_lblPage.gridx = 0;
			gbc_lblPage.gridy = 3;
			pnlCenter.add(lblPage, gbc_lblPage);
		}
		{
			txtPage = new JTextField(); 
			GridBagConstraints gbc_txtPage = new GridBagConstraints();
			gbc_txtPage.insets = new Insets(0, 0, 5, 0);
			gbc_txtPage.gridx = 2;
			gbc_txtPage.gridy = 3;
			pnlCenter.add(txtPage, gbc_txtPage);
			txtPage.setColumns(10);
		}
		{
			btnColorOfEdge = new JButton("Color Of Edge");
			btnColorOfEdge.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 Color  chosedColorOfEdge= JColorChooser.showDialog(null,"Choose color: ", colorOfEdge);
			    	 if(chosedColorOfEdge != null)
			    		 colorOfEdge = chosedColorOfEdge;
			    	 btnColorOfEdge.setBackground(colorOfEdge);	
				}
			});
			GridBagConstraints gbc_btnColorOfEdge = new GridBagConstraints();
			gbc_btnColorOfEdge.anchor = GridBagConstraints.NORTHEAST;
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
			gbc_btnInsideColor.anchor = GridBagConstraints.WEST;
			gbc_btnInsideColor.gridx = 1;
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

	public JTextField getTxtUpperLeftX() {
		return txtUpperLeftX;
	}

	public void setTxtUpperLeftX(JTextField txtUpperLeftX) {
		this.txtUpperLeftX = txtUpperLeftX;
	}

	public JTextField getTxtUpperLeftY() {
		return txtUpperLeftY;
	}

	public void setTxtUpperLeftY(JTextField txtUpperLeftY) {
		this.txtUpperLeftY = txtUpperLeftY;
	}

	public JTextField getTxtPage() {
		return txtPage;
	}

	public void setTxtPage(JTextField txtPage) {
		this.txtPage = txtPage;
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

	public JButton getBtnInsideColor() {
		return btnInsideColor;
	}

	public void setBtnInsideColor(JButton btnInsideColor) {
		this.btnInsideColor = btnInsideColor;
	}

	public JButton getBtnColorOfEdge() {
		return btnColorOfEdge;
	}

	public void setBtnColorOfEdge(JButton btnColorOfEdge) {
		this.btnColorOfEdge = btnColorOfEdge;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	

    

}
