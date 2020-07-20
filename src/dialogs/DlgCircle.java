package dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class DlgCircle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final  JPanel pnlCenter = new JPanel();
	private JTextField txtRadius;
	private int radius;
	private boolean canceled = false;

	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setModal(true);
		setTitle("Circle");
		setBounds(100, 100, 363, 296);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{0, 0, 0};
		gbl_pnlCenter.rowHeights = new int[]{0, 0, 0, 0};
		gbl_pnlCenter.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.WEST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 0;
			pnlCenter.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();     
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 1;
			gbc_txtRadius.gridy = 0;
			pnlCenter.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}

		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtRadius.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Values must not be empty!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							try {
								radius = Integer.parseInt(txtRadius.getText());
								setVisible(false);
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null,"Enter integer values", "Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							if (radius <= 0 ) {
								JOptionPane.showMessageDialog(null, "Radius must be greater than 0",
										"Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							dispose();
						}
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}
	
	





	


}
