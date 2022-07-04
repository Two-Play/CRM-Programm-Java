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

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TerminBearbeiten extends JDialog {
	private JTextField textFieldDatum;
	private JTextField textFieldUhrzeit;

	public TerminBearbeiten(DBManager dbm, String terminNr) {
		setTitle("Termin bearbeiten");
		setBounds(100, 100, 216, 357);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/resources/icon.png")));
		
		JLabel lblNewLabel = new JLabel("Datum");
		
		textFieldDatum = new JTextField();
		textFieldDatum.setColumns(10);
		
		JLabel lblUhrzeit = new JLabel("Uhrzeit");
		
		textFieldUhrzeit = new JTextField();
		textFieldUhrzeit.setColumns(10);
		
		JLabel lblBemerkung = new JLabel("Bemerkung");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblKunde = new JLabel("Kunde");
		
		JComboBox comboBox = new JComboBox();
		
		JButton btnErstellen = new JButton("Termin aktualisieren");
		
		JButton btnLoeschen = new JButton("Termin Löschen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Sind Sie sicher, dass Sie diesen Termin Löschen möchten?", "Warnung" , dialogButton, JOptionPane.WARNING_MESSAGE);
				if(dialogResult == JOptionPane.YES_OPTION){
				dbm.startConnect("crm");
				try {
					dbm.getStatement().executeUpdate("delete FROM crm.termine where termineNr = "+terminNr+";" );

					JOptionPane.showMessageDialog(null, "Löschung war erfolgreich", "Info", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				     
				}
				dbm.closeConnection();
			}
		});
		btnLoeschen.setBackground(new Color(204, 51, 51));
		btnLoeschen.setForeground(new Color(204, 51, 51));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
						.addComponent(textFieldDatum, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addComponent(lblUhrzeit)
						.addComponent(textFieldUhrzeit, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
						.addComponent(lblBemerkung)
						.addComponent(lblKunde)
						.addComponent(comboBox, 0, 180, Short.MAX_VALUE)
						.addComponent(btnErstellen, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
						.addComponent(btnLoeschen, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUhrzeit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldUhrzeit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBemerkung)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblKunde)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnErstellen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLoeschen)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		getContentPane().setLayout(groupLayout);
		
		
		ArrayList<String> kundenNr = new ArrayList<>();
		dbm.startConnect("crm");
		try {
			ResultSet rs = dbm.getStatement().executeQuery("SELECT kundenNr, vorname, name FROM crm.kunden;");
	        while (rs.next()) {
	            String val = rs.getString(2)+ " " +rs.getString(3);
	            kundenNr.add(rs.getString(1));
	            comboBox.addItem(val);
	        }

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbm.closeConnection();
		
		btnErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.startConnect("crm");
				try {
					String kundenNrIndex = kundenNr.get(comboBox.getSelectedIndex()).toString();
					String datum = textFieldDatum.getText().isEmpty()? "0000-00-00":textFieldDatum.getText();
					String uhrzeit = textFieldUhrzeit.getText().isEmpty()? "00:00":textFieldUhrzeit.getText();

					dbm.getStatement().executeUpdate("update crm.termine set datum ='"+datum+"', uhrzeit ='"+uhrzeit+"', bemerkung ='"+textArea.getText()+"', kundenNr ="+kundenNrIndex+" where termineNr = "+terminNr+";");
					JOptionPane.showMessageDialog(null, "Termin erfolgreich geändert!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				dbm.closeConnection();
			}
		});
		
		
		dbm.startConnect("crm");
		try {
			ResultSet rs = dbm.getStatement().executeQuery("SELECT termine.*, kunden.vorname, kunden.name FROM crm.termine, crm.kunden where termine.kundenNr = kunden.kundenNr and termineNr = '"+terminNr+"';");
			while(rs.next()){
			textFieldDatum.setText(rs.getString(2));
			textFieldUhrzeit.setText(rs.getString(3));
			textArea.setText(rs.getString(4));
			comboBox.setSelectedItem(rs.getString(6) + " " + rs.getString(7));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		dbm.closeConnection();
	}
}
