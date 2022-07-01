package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import fachklasse.DBManager;

import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Ort extends JDialog {
	private JTextField textFieldPlz;
	private JTextField textFieldOrt;

	/**
	 * Create the dialog.
	 */
	public Ort(String benutzer, String passwort, String host) {
		setType(Type.POPUP);
		setTitle("Ort Anlegen");
		setBounds(100, 100, 359, 133);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/img/icon.png")));
		setModal(true);
		
		JLabel lblPlz = new JLabel("PLZ");
		
		textFieldPlz = new JTextField();
		textFieldPlz.setColumns(10);
		
		JLabel lblOrt = new JLabel("Ort");
		
		textFieldOrt = new JTextField();
		textFieldOrt.setColumns(10);
		
		JButton btnHinzufuegen = new JButton("Hinzufügen");
		btnHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbm = new DBManager(benutzer, passwort, host);
				dbm.startConnect("crm");
				try {
					dbm.getStatement().executeUpdate("insert into crm.ort(ortName, plz) values ('"+textFieldOrt.getText()+"', '"+textFieldPlz.getText()+"');");
					JOptionPane.showMessageDialog(null, "Ort wurde erstellt", "Erstellt", JOptionPane.INFORMATION_MESSAGE);
					textFieldOrt.setText("");
					textFieldPlz.setText("");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnHinzufuegen, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPlz)
							.addGap(79)
							.addComponent(lblOrt))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textFieldPlz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldOrt, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlz)
						.addComponent(lblOrt))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPlz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldOrt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(btnHinzufuegen)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}

}
