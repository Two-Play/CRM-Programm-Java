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
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTextField txtbenutzer;

	private JTable table;
	
	private DBManager dbm = null;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView(String benutzer, String passwort, String host) {	
		dbm = new DBManager(benutzer, passwort, host);
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
		txtbenutzer.setText(benutzer);
		JButton btnBenutzerverwaltung = new JButton("Benutzerverwaltung");
		btnBenutzerverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Benutzervewaltung(dbm.getUser(), dbm.getPassword(), dbm.getHost()).setVisible(true);
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
				new NeuerKunde(dbm.getUser(), dbm.getPassword(), dbm.getHost()).setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtbenutzer, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnBenutzerverwaltung)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAbmelden)
							.addPreferredGap(ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
							.addComponent(btnNeuerKunde)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtbenutzer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBenutzerverwaltung)
						.addComponent(btnAbmelden)
						.addComponent(btnNeuerKunde))
					.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		//Tabelle
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Vorname", "Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}

	//parameter empfang
	public MainView() {
		System.exit(0);
	}
}
