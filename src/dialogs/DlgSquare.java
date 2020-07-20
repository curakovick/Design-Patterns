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


public class DlgSquare extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel pnlCenter = new JPanel();
	private JTextField txtPage;
	private boolean canceled=false;
	private int page;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgSquare dialog = new DlgSquare();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSquare() {
		setModal(true);
		setTitle("Square");
		setBounds(100, 100, 262, 210);
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
			JLabel lblPage = new JLabel("Page");
			GridBagConstraints gbc_lblPage = new GridBagConstraints();
			gbc_lblPage.anchor = GridBagConstraints.WEST;
			gbc_lblPage.insets = new Insets(0, 0, 5, 5);
			gbc_lblPage.gridx = 0;
			gbc_lblPage.gridy = 0;
			pnlCenter.add(lblPage, gbc_lblPage);
		}
		{
			txtPage = new JTextField();
			GridBagConstraints gbc_txtPage = new GridBagConstraints();
			gbc_txtPage.insets = new Insets(0, 0, 5, 0);
			gbc_txtPage.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPage.gridx = 1;
			gbc_txtPage.gridy = 0;
			pnlCenter.add(txtPage, gbc_txtPage);
			txtPage.setColumns(10);
		}
	
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			{
				JButton btnConfirm = new JButton("Confirm");
				btnConfirm.addActionListener(new ActionListener() {
					

					public void actionPerformed(ActionEvent arg0) {
						if (txtPage.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Values must not be empty!", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							try {
								page = Integer.parseInt(txtPage.getText());
								setVisible(false);
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Enter integer value", "Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							if (page <= 0) {
								JOptionPane.showMessageDialog(null, "Page must be greater than 0",
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public JTextField getTxtPage() {
		return txtPage;
	}

	public void setTxtPage(JTextField txtPage) {
		this.txtPage = txtPage;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	

	

	

}
