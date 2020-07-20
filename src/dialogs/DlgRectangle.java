package dialogs;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;


public class DlgRectangle extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private int heightRec;
	private int widthRec;
	private boolean canceled;

	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgRectangle() {
		setTitle("Add rectangle");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 300, 200);
		setLocationRelativeTo(null);

		JPanel pnlAddRectangle = new JPanel();
		getContentPane().add(pnlAddRectangle, BorderLayout.CENTER);
		GridBagLayout gbl_pnlAddRectangle = new GridBagLayout();
		gbl_pnlAddRectangle.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_pnlAddRectangle.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_pnlAddRectangle.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlAddRectangle.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlAddRectangle.setLayout(gbl_pnlAddRectangle);

		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(10, 10, 5, 5);
		gbc_lblHeight.anchor = GridBagConstraints.EAST;
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 1;
		pnlAddRectangle.add(lblHeight, gbc_lblHeight);

		txtHeight = new JTextField();
		GridBagConstraints gbc_txtHeight = new GridBagConstraints();
		gbc_txtHeight.insets = new Insets(10, 0, 5, 5);
		gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHeight.gridx = 2;
		gbc_txtHeight.gridy = 1;
		pnlAddRectangle.add(txtHeight, gbc_txtHeight);
		txtHeight.setColumns(10);

		JLabel lblWidth = new JLabel("Width");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.anchor = GridBagConstraints.EAST;
		gbc_lblWidth.insets = new Insets(0, 0, 0, 5);
		gbc_lblWidth.gridx = 1;
		gbc_lblWidth.gridy = 2;
		pnlAddRectangle.add(lblWidth, gbc_lblWidth);

		txtWidth = new JTextField();
		GridBagConstraints gbc_txtWidth = new GridBagConstraints();
		gbc_txtWidth.insets = new Insets(0, 0, 5, 5);
		gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWidth.gridx = 2;
		gbc_txtWidth.gridy = 2;
		pnlAddRectangle.add(txtWidth, gbc_txtWidth);
		txtWidth.setColumns(10);

		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					heightRec = Integer.parseInt(txtHeight.getText());
					widthRec = Integer.parseInt(txtWidth.getText());
					setVisible(false);

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(rootPane, "Insert the right value!");
					e1.printStackTrace();
				}

			}
		});

		pnlButtons.add(btnConfirm);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				canceled = true;
				dispose();

			}
		});
		pnlButtons.add(btnCancel);
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public int getHeightRec() {
		return heightRec;
	}

	public void setHeightRec(int heightRec) {
		this.heightRec = heightRec;
	}

	public int getWidthRec() {
		return widthRec;
	}

	public void setWidthRec(int widthRec) {
		this.widthRec = widthRec;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

}