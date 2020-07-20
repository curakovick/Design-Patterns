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


public class DlgModifyPoint extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private Color color;
	private JTextField txtPointX;
	private JTextField txtPointY;
	private JButton btnColor;
	private boolean canceled=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgModifyPoint dialog = new DlgModifyPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgModifyPoint() {
		setTitle("Modify point:");
		setModal(true); 
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.NORTH);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{139, 64, 86, 0};
		gbl_pnlCenter.rowHeights = new int[]{20, 0, 0, 0, 0};
		gbl_pnlCenter.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		
		{
			JLabel lblPointX = new JLabel("X coordinate of point:");
			lblPointX.setHorizontalAlignment(SwingConstants.LEFT);
			lblPointX.setVerticalAlignment(SwingConstants.TOP);
			GridBagConstraints gbc_lblPointX = new GridBagConstraints();
			gbc_lblPointX.insets = new Insets(0, 0, 5, 5);
			gbc_lblPointX.gridx = 0;
			gbc_lblPointX.gridy = 0;
			pnlCenter.add(lblPointX, gbc_lblPointX);
		}
		{
			txtPointX = new JTextField();
			GridBagConstraints gbc_txtPointX = new GridBagConstraints();
			gbc_txtPointX.insets = new Insets(0, 0, 5, 5);
			gbc_txtPointX.gridx = 1;
			gbc_txtPointX.gridy = 0;
			pnlCenter.add(txtPointX, gbc_txtPointX);
			txtPointX.setColumns(10);
		}
		{
			JLabel lblPointY = new JLabel("Y coordinate of point:");
			lblPointY.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_lblPointY = new GridBagConstraints();
			gbc_lblPointY.insets = new Insets(0, 0, 5, 5);
			gbc_lblPointY.gridx = 0;
			gbc_lblPointY.gridy = 1;
			pnlCenter.add(lblPointY, gbc_lblPointY);
		}
		{
			txtPointY = new JTextField();
			GridBagConstraints gbc_txtPointY = new GridBagConstraints();
			gbc_txtPointY.insets = new Insets(0, 0, 5, 5);
			gbc_txtPointY.gridx = 1;
			gbc_txtPointY.gridy = 1;
			pnlCenter.add(txtPointY, gbc_txtPointY);
			txtPointY.setColumns(10);
		}
		{
			btnColor = new JButton("Color");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					 Color  colorOfPoint = JColorChooser.showDialog(null,"Choose color: ", color);
			    	 if(colorOfPoint != null)
			    			color = colorOfPoint;
			    	 btnColor.setBackground(color);	
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor.gridx = 0;
			gbc_btnColor.gridy = 2;
			pnlCenter.add(btnColor, gbc_btnColor);
		}
		
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
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
	

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public JTextField getTxtPointX() {
		return txtPointX;
	}

	public void setTxtPointX(JTextField txtPointX) {
		this.txtPointX = txtPointX;
	}

	public JTextField getTxtPointY() {
		return txtPointY;
	}

	public void setTxtPointY(JTextField txtPointY) {
		this.txtPointY = txtPointY;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}

	public JPanel getPnlCenter() {
		return pnlCenter;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	
	
	

}
