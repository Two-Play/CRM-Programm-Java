package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fachklasse.DBManager;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import extern.ButtonColumn;

import java.awt.event.WindowFocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.WindowEvent;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTextField txtbenutzer;
	private JTable table;
	
	public MainView(DBManager dbm) {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 486);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/img/icon.png")));
		setTitle("CRM Programm");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Angemeldet als:");
		
		txtbenutzer = new JTextField();
		txtbenutzer.setText("[Benutzer]");
		txtbenutzer.setEditable(false);
		txtbenutzer.setColumns(10);
		txtbenutzer.setText(dbm.getUser());
		JButton btnBenutzerverwaltung = new JButton("Benutzerverwaltung");
		btnBenutzerverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Öffnet Benutzervewaltung
				new Benutzervewaltung(dbm).setVisible(true);
				}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAbmelden = new JButton("Abmelden");
		btnAbmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Abmeldung durchführen
				if (dbm.getConnection() != null) {
					dbm.closeConnection();
				}
				dispose();
				new AnmeldungGui().setVisible(true);
			}
		});
		
		JButton btnNeuerKunde = new JButton("Neuer Kunde anlegen");
		btnNeuerKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Neuer Kunde Fenster öffnen
				new NeuerKunde(dbm).setVisible(true);
			}
		});
		
		JButton btnTerminErstellen = new JButton("Neuer Termin erstellen");
		
		JButton btnTermineAnz = new JButton("Termine anzeigen");
		btnTermineAnz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnAuftrag = new JButton("Auftäge anzeigen");
		
		JButton btnNewButton = new JButton("Neuer Aufttrag erstellen");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtbenutzer))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAbmelden, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBenutzerverwaltung, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(65)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAuftrag, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnTermineAnz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNeuerKunde, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnTerminErstellen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(189))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(txtbenutzer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAbmelden)
								.addComponent(btnBenutzerverwaltung)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAuftrag)
								.addComponent(btnNeuerKunde)
								.addComponent(btnNewButton))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTerminErstellen)
								.addComponent(btnTermineAnz))))
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		//Tabelle
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Kundennummer", "Name", "Vorname", "Firma", "E-Mail", "Ort"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
	    table.addMouseListener((MouseListener) new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     // to detect doble click events
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // select a row
	               String tableRow = (String) table.getValueAt(row, 0); // get the value of a row and column.
	               System.out.println(tableRow);
	               new Kunden(dbm, tableRow).setVisible(true);
	               
	            }
	         }
	      });
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				DefaultTableModel tbm = (DefaultTableModel) table.getModel();    
				tbm.setRowCount(0);
				dbm.startConnect("crm");
				try {
					ResultSet rs = dbm.getStatement().executeQuery("SELECT kundenNr, name, vorname, firma, email, ortName FROM crm.kunden, crm.ort where kunden.ortNr = ort.ortNr;");
					while(rs.next()){
				        String data[] = {rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
				        
				        tbm.addRow(data);
				    }
					dbm.closeConnection();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});	
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
