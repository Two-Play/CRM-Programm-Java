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
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class Kunden extends JDialog {

	private JTextField textFieldName;
	private JTextField textFieldVorname;
	private JTextField textFieldFirma;
	private JTextField textFieldTelefonnummer;
	private JTextField textFieldEmail;
	private JTextField textFieldGeburtsdatum;
	private JTable tableAuftrag;
	private JTextField textFieldStrasse;
	private JTable tableTermin;
	private JTextField textFieldKundenNr;

	/**
	 * Create the dialog.
	 */
	public Kunden(DBManager dbm, String kundenNr) {

		setTitle("Kunde bearbeiten");
		setType(Type.POPUP);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 650);
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
		
		JButton btnAktualisieren = new JButton("Aktualisieren");
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JLabel lblStrasse = new JLabel("Straße mit Hausnummer");
		
		textFieldStrasse = new JTextField();
		textFieldStrasse.setColumns(10);
		
		JLabel lblOrt = new JLabel("Ort");
		
		
		JComboBox comboBox_1 = new JComboBox();
		
		Utility ut = new Utility();
		
		dbm.startConnect("crm");
        try {
				ResultSet rs = dbm.getStatement().executeQuery("SELECT * FROM crm.kunden, crm.ort where kunden.ortNr = ort.ortNr and kundenNr = '"+kundenNr+"';");
				while(rs.next()){
			        
			    }
				dbm.closeConnection();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
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
		
		JButton btnLoeschen = new JButton("Kunde Löschen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Sind Sie sicher, dass Sie "+textFieldVorname.getText() + " "+ textFieldName.getText()+" Löschen möchten?", "Warnung" , dialogButton, JOptionPane.WARNING_MESSAGE);
				if(dialogResult == JOptionPane.YES_OPTION){
				dbm.startConnect("crm");
				try {
					dbm.getStatement().executeUpdate("delete FROM crm.auftraege where kundenNr = "+textFieldKundenNr.getText()+";" );
					dbm.getStatement().executeUpdate("delete FROM crm.termine where kundenNr = "+textFieldKundenNr.getText()+";" );
					dbm.getStatement().executeUpdate("delete FROM crm.kunden where kundenNr = "+textFieldKundenNr.getText()+";" );
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
		btnLoeschen.setBackground(new Color(255, 51, 51));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("KundenNr");
		
		textFieldKundenNr = new JTextField();
		textFieldKundenNr.setEditable(false);
		textFieldKundenNr.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Aufträge");
		
		JLabel lblNewLabel_3 = new JLabel("Termine");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInteressen, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(lblBemerkung, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(lblTelefonnummer, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGeburtsdatum, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textFieldName, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(textFieldKundenNr, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVorname, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldVorname, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFirma, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldFirma, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
						.addComponent(textFieldStrasse, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox_1, 0, 268, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(lblOrt, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(lblStrasse, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAktualisieren, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLoeschen, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
						.addComponent(textFieldGeburtsdatum, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(textFieldTelefonnummer, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblName)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldKundenNr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
							.addGap(9)
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
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblInteressen)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAktualisieren)
								.addComponent(btnLoeschen))
							.addGap(1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(2)
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
					.addGap(7))
		);
		
		tableTermin = new JTable();
		tableTermin.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"TerminNr", "Uhrzeit", "Datum", "Bemerkung"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_3.setViewportView(tableTermin);
		
		tableAuftrag = new JTable();
		tableAuftrag.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"AuftragsNr", "Auftragname", "Status"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(tableAuftrag);
		
		JTextArea textAreaInteresse = new JTextArea();
		scrollPane_1.setViewportView(textAreaInteresse);
		
		JTextArea textAreaBemerkung = new JTextArea();
		scrollPane.setViewportView(textAreaBemerkung);
		getContentPane().setLayout(groupLayout);
		
		
		dbm.startConnect("crm");
		try {
			ResultSet rs = dbm.getStatement().executeQuery("SELECT * FROM crm.kunden, crm.ort where kunden.ortNr = ort.ortNr and kundenNr = '"+kundenNr+"';");
			while (rs.next()) {
	            textFieldVorname.setText(rs.getString(2));
	            textFieldName.setText(rs.getString(3));
	            textFieldEmail.setText(rs.getString(4));
	            textFieldTelefonnummer.setText(rs.getString(5));
	            textFieldFirma.setText(rs.getString(6));
	            textFieldStrasse.setText(rs.getString(8));
	            textAreaBemerkung.setText(rs.getString(9));
	            textAreaInteresse.setText(rs.getString(10));
	            textFieldGeburtsdatum.setText(rs.getString(11));
	            comboBox.setSelectedItem(rs.getString(12));
	            comboBox_1.setSelectedItem(rs.getString(14));
	            textFieldKundenNr.setText(rs.getString(1));
	        }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		dbm.closeConnection();
		
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.startConnect("crm");
				String ortNr = null;
				try {
					ResultSet rs = dbm.getStatement().executeQuery("select ortNr from crm.ort where ortName = '"+comboBox_1.getSelectedItem().toString()+"';");
					while (rs.next()) {
						ortNr = rs.getString(1);
					}
					String gebTag = textFieldGeburtsdatum.getText().isEmpty()? "0000-00-00":textFieldGeburtsdatum.getText();
					dbm.getStatement().executeUpdate("update crm.kunden set name = '"+textFieldName.getText()+"', vorname ='"+textFieldVorname.getText()+
							"', firma ='"+textFieldFirma.getText()+"', email ='"+textFieldEmail.getText()+"', tel ='"+textFieldTelefonnummer.getText()+
							"', strasse ='"+textFieldStrasse.getText()+"', bemerkungen ='"+textAreaBemerkung.getText()+"', interessen ='"+textAreaInteresse.getText()+
							"', geburtsdatum ='"+gebTag+"', geschlecht ='"+comboBox.getSelectedItem()+"',ortNr ='"+
							ortNr+"' where kundenNr = "+textFieldKundenNr.getText()+";");
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);

				}
				dbm.closeConnection();
			}
		});
		
		dbm.startConnect("crm");
		try {
			
			
			
			ResultSet rsA = dbm.getStatement().executeQuery("SELECT auftraege.name, status, auftraegeNr  FROM crm.auftraege where auftraege.kundenNr = '"+textFieldKundenNr.getText()+"';");
			while(rsA.next()){
		        String data[] = {rsA.getString(3), rsA.getString(1), rsA.getString(2)};
		        DefaultTableModel tbmA = (DefaultTableModel) tableAuftrag.getModel();
		        tbmA.addRow(data);
		    }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbm.closeConnection();
		
		tableAuftrag.addMouseListener((MouseListener) new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     // to detect doble click events
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // select a row
	               String tableRow = (String) tableAuftrag.getValueAt(row, 0); // get the value of a row and column.
	               System.out.println(tableRow);
	               new AuftraegeBearbeiten(dbm, tableRow).setVisible(true);
	               
	            }
	         }
	      });
		
		tableTermin.addMouseListener((MouseListener) new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     // to detect doble click events
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // select a row
	               String tableRow = (String) tableTermin.getValueAt(row, 0); // get the value of a row and column.
	               System.out.println(tableRow);
	               new TerminBearbeiten(dbm, tableRow).setVisible(true);
	               
	            }
	         }
	      });

		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				DefaultTableModel tbm = (DefaultTableModel) tableAuftrag.getModel();    
				tbm.setRowCount(0);
				dbm.startConnect("crm");
				DefaultTableModel tbmT = (DefaultTableModel) tableTermin.getModel();    
				tbmT.setRowCount(0);
				
				try {
					ResultSet rsT = dbm.getStatement().executeQuery("SELECT * FROM crm.termine where termine.kundenNr = "+kundenNr+";");
					while(rsT.next()){
				        String data[] = {rsT.getString(1), rsT.getString(3), rsT.getString(2),rsT.getString(4)};
				        tbmT.addRow(data);
				    }
					ResultSet rs = dbm.getStatement().executeQuery("SELECT auftraege.name, status, auftraegeNr  FROM crm.auftraege where auftraege.kundenNr = '"+textFieldKundenNr.getText()+"';");
					while(rs.next()){
				        String data[] = {rs.getString(3), rs.getString(1), rs.getString(2)};
				        tbm.addRow(data);
				    }

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				dbm.closeConnection();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
	}
}
