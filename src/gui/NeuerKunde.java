package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;

import com.mysql.cj.protocol.Resultset;

import fachklasse.DBManager;
import fachklasse.Utility;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class NeuerKunde extends JDialog {

	private JTextField textFieldName;
	private JTextField textFieldVorname;
	private JTextField textFieldFirma;
	private JTextField textFieldTelefonnummer;
	private JTextField textFieldEmail;
	private JTextField textFieldGeburtsdatum;
	private JTextField textFieldStrasse;

	/**
	 * Create the dialog.
	 */
	public NeuerKunde(DBManager dbm) {
		setTitle("Neuer Kunde anlegen");
		setType(Type.POPUP);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 315, 650);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/img/icon.png")));
		setModal(true);
		
		JLabel lblName = new JLabel("Name");
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		JLabel lblVorname = new JLabel("Vorname");
		
		textFieldVorname = new JTextField();
		textFieldVorname.setColumns(10);
		
		JLabel lblFirma = new JLabel("Firma");
		
		textFieldFirma = new JTextField();
		textFieldFirma.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel = new JLabel("Anrede");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Herr", "Frau", "Divers"}));
		
		JLabel lblTelefonnummer = new JLabel("Telefonnummer");
		
		textFieldTelefonnummer = new JTextField();
		textFieldTelefonnummer.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail adresse");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum (Format: jjjj-mm-tt)");
		
		textFieldGeburtsdatum = new JTextField();
		textFieldGeburtsdatum.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblBemerkung = new JLabel("Bemerkung");
		
		JLabel lblInteressen = new JLabel("Interessen");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnAnlegen = new JButton("Anlegen");
	
		
		JLabel lblStrasse = new JLabel("Straße mit Hausnummer");
		
		textFieldStrasse = new JTextField();
		textFieldStrasse.setColumns(10);
		
		JLabel lblOrt = new JLabel("Ort");
		
		JComboBox comboBox_1 = new JComboBox();
		Utility ut = new Utility();
		ut.updateOrt(comboBox_1, dbm);
		comboBox_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				comboBox_1.removeAllItems();
				ut.updateOrt(comboBox_1, dbm);
			}
		});
	
				
		JButton btnNewButton = new JButton("Ort anlegen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Ort(dbm).setVisible(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStrasse, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(lblOrt, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(textFieldStrasse, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(lblTelefonnummer, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(textFieldTelefonnummer, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(lblGeburtsdatum, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(textFieldGeburtsdatum, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(lblBemerkung, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(lblInteressen, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(btnAnlegen, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
								.addComponent(comboBox, Alignment.LEADING, 0, 141, Short.MAX_VALUE)
								.addComponent(lblVorname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
								.addComponent(textFieldVorname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldFirma, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(textFieldName, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGap(5)
									.addComponent(lblFirma, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox_1, 0, 179, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVorname)
						.addComponent(lblFirma))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldVorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldFirma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStrasse)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldStrasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOrt)
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblGeburtsdatum)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldGeburtsdatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTelefonnummer)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldTelefonnummer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBemerkung)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblInteressen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAnlegen)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		JTextArea textAreaInteresse = new JTextArea();
		scrollPane_1.setViewportView(textAreaInteresse);
		
		JTextArea textAreaBemerkung = new JTextArea();
		scrollPane.setViewportView(textAreaBemerkung);
		getContentPane().setLayout(groupLayout);
		
		btnAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ortNr = null;
				dbm.startConnect("crm");
				try {
					ResultSet rs = dbm.getStatement().executeQuery("select ortNr from crm.ort where ortName = '"+comboBox_1.getSelectedItem().toString()+"';");
					if (!rs.next()) {
						ortNr = rs.getString(1);
					}
					String gebTag = textFieldGeburtsdatum.getText().isEmpty()? "0000-00-00":textFieldGeburtsdatum.getText();
					dbm.getStatement().executeUpdate("insert into crm.kunden(name, vorname, firma, email, tel, strasse, bemerkungen, interessen, geburtsdatum, geschlecht,ortNr) values ('"+textFieldName.getText()+
							"', '"+textFieldVorname.getText()+"', '"+textFieldFirma.getText()+"', '"+textFieldEmail.getText()+"', '"+textFieldTelefonnummer.getText()+
							"', '"+textFieldStrasse.getText()+"', '"+textAreaBemerkung.getText()+"','"+textAreaInteresse.getText()+"','"+gebTag+"','"+comboBox.getSelectedItem()+"', '"+rs.getString(1)+"');");
					rs.close();
					dbm.closeConnection();
					JOptionPane.showMessageDialog(null, "Kunde erfolgreich erstellt!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		});
	}
}
