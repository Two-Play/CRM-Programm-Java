package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import fachklasse.DBManager;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AuftraegeBearbeiten extends JDialog {
	private JTextField textFieldStartDatum;
	private JTextField textFieldEndDatum;
	private JTextField textFieldAuftragsname;
	private JScrollPane scrollPane;
	private JLabel lblNotiz;
	private JTextArea textAreaNotiz;
	private JTextField textFieldAuftragsnummer;

	public AuftraegeBearbeiten(DBManager dbm, String auftragsNr) {
		setTitle("Auftrag bearbeiten");
		setBounds(100, 100, 317, 423);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/img/icon.png")));
		
		JLabel lblStartDatum = new JLabel("Start Datum (jjjj-mm-tt)");
		
		textFieldStartDatum = new JTextField();
		textFieldStartDatum.setColumns(10);
		
		JLabel lblUhrzeit = new JLabel("End Datum (jjjj-mm-tt)");
		
		textFieldEndDatum = new JTextField();
		textFieldEndDatum.setColumns(10);
		
		JLabel lblAuftragsname = new JLabel("Auftragsname");
		
		textFieldAuftragsname = new JTextField();
		textFieldAuftragsname.setColumns(10);
		
		scrollPane = new JScrollPane();
		
		lblNotiz = new JLabel("Notiz");
		
		JLabel lblStatus = new JLabel("Status");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"In Bearbeitung", "Fertig", "Sonstiges"}));
		
		JLabel lblKunde = new JLabel("Kunde");
		
		JComboBox comboBox_1 = new JComboBox();
		ArrayList<String> kundenNr = new ArrayList<>();
		dbm.startConnect("crm");
		try {
			ResultSet rs = dbm.getStatement().executeQuery("SELECT kundenNr, vorname, name FROM crm.kunden;");
	        while (rs.next()) {
	            String val = rs.getString(2)+ " " +rs.getString(3);
	            kundenNr.add(rs.getString(1));
	            comboBox_1.addItem(val);
	        }

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbm.closeConnection();
		
		JButton btnErstellen = new JButton("Auftrag aktualisieren");
		btnErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.startConnect("crm");
				try {
					String kundenNrIndex = kundenNr.get(comboBox_1.getSelectedIndex()).toString();
					String startDatum = textFieldStartDatum.getText().isEmpty()? "0000-00-00":textFieldStartDatum.getText();
					String endDatum = textFieldEndDatum.getText().isEmpty()? "0000-00-00":textFieldEndDatum.getText();
					dbm.getStatement().executeUpdate("UPDATE crm.auftraege SET name = '"+
					textFieldAuftragsname.getText()+"', start = '"+startDatum+"', ende = '"+endDatum+"', notiz = '"+textAreaNotiz.getText()+"', status = '"+
					comboBox.getSelectedItem().toString()+"', kundenNr = "+kundenNrIndex+" where auftraegeNr = '"+auftragsNr+"';");
					JOptionPane.showMessageDialog(null, "Termin erfolgreich erstellt!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblAuftragsnummer = new JLabel("Auftragsnummer");
		
		textFieldAuftragsnummer = new JTextField();
		textFieldAuftragsnummer.setEditable(false);
		textFieldAuftragsnummer.setColumns(10);
		
		JButton btnLoeschen = new JButton("Termin Löschen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Sind Sie sicher, dass Sie diesen Auftrag Löschen möchten?", "Warnung" , dialogButton, JOptionPane.WARNING_MESSAGE);
				if(dialogResult == JOptionPane.YES_OPTION){
				dbm.startConnect("crm");
				try {
					dbm.getStatement().executeUpdate("delete FROM crm.auftraege where auftraegeNr = "+auftragsNr+";" );

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
		btnLoeschen.setForeground(new Color(255, 51, 51));
		btnLoeschen.setBackground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAuftragsname)
						.addComponent(textFieldAuftragsname, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(lblUhrzeit, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(textFieldEndDatum, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(lblNotiz)
						.addComponent(lblStatus)
						.addComponent(comboBox, 0, 281, Short.MAX_VALUE)
						.addComponent(lblKunde)
						.addComponent(comboBox_1, 0, 281, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblStartDatum, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textFieldStartDatum, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAuftragsnummer)
									.addGap(6))
								.addComponent(textFieldAuftragsnummer, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnErstellen, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoeschen, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAuftragsname)
					.addGap(3)
					.addComponent(textFieldAuftragsname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartDatum)
						.addComponent(lblAuftragsnummer))
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldStartDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldAuftragsnummer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUhrzeit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldEndDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNotiz)
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblStatus)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblKunde)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnErstellen)
						.addComponent(btnLoeschen))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		
		textAreaNotiz = new JTextArea();
		scrollPane.setViewportView(textAreaNotiz);
		getContentPane().setLayout(groupLayout);
		
		dbm.startConnect("crm");
		try {
			ResultSet rs = dbm.getStatement().executeQuery("SELECT auftraege.*, kunden.vorname, kunden.name FROM crm.auftraege, crm.kunden where auftraege.kundenNr = kunden.kundenNr and auftraegeNr = '"+auftragsNr+"';");
			while(rs.next()){
			textFieldAuftragsname.setText(rs.getString(2));
			textFieldStartDatum.setText(rs.getString(3));
			textFieldEndDatum.setText(rs.getString(4));
			textAreaNotiz.setText(rs.getString(5));
			comboBox.setSelectedItem(rs.getString(6));
			comboBox_1.setSelectedItem(rs.getString(8)+ " "+ rs.getString(9));
			textFieldAuftragsnummer.setText(auftragsNr);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		dbm.closeConnection();
	}
}
