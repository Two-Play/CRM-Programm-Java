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

public class NeueAuftraege extends JDialog {
	private JTextField textFieldStartDatum;
	private JTextField textFieldEndDatum;
	private JTextField textFieldAuftragsname;
	private JScrollPane scrollPane;
	private JLabel lblNotiz;
	private JTextArea textAreaNotiz;

	public NeueAuftraege(DBManager dbm) {
		setTitle("Auftragerstellung");
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
		
		JButton btnErstellen = new JButton("Termin erstellen");
		btnErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.startConnect("crm");
				try {
					String kundenNrIndex = kundenNr.get(comboBox_1.getSelectedIndex()).toString();
					String startDatum = textFieldStartDatum.getText().isEmpty()? "0000-00-00":textFieldStartDatum.getText();
					String endDatum = textFieldEndDatum.getText().isEmpty()? "0000-00-00":textFieldEndDatum.getText();
					dbm.getStatement().executeUpdate("insert into crm.auftraege (name, start, ende, notiz, status, kundenNr) values ('"+
					textFieldAuftragsname.getText()+"', '"+startDatum+"', '"+endDatum+"', '"+textAreaNotiz.getText()+"', '"+
					comboBox.getSelectedItem().toString()+"', "+kundenNrIndex+");");
					JOptionPane.showMessageDialog(null, "Termin erfolgreich erstellt!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAuftragsname)
						.addComponent(textFieldAuftragsname, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(lblStartDatum, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(textFieldStartDatum, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(lblUhrzeit, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(textFieldEndDatum, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(lblNotiz)
						.addComponent(lblStatus)
						.addComponent(comboBox, 0, 281, Short.MAX_VALUE)
						.addComponent(lblKunde)
						.addComponent(comboBox_1, 0, 281, Short.MAX_VALUE)
						.addComponent(btnErstellen, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
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
					.addComponent(lblStartDatum)
					.addGap(4)
					.addComponent(textFieldStartDatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(btnErstellen)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		
		textAreaNotiz = new JTextArea();
		scrollPane.setViewportView(textAreaNotiz);
		getContentPane().setLayout(groupLayout);
	}
}
