package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import extern.ButtonColumn;
import fachklasse.DBManager;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class Benutzervewaltung extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private JTextField textFieldBenutzername;
	private JTextField textFieldPasswort;
	private JTextField textField;

	private static DBManager dbm = null;

	/**
	 * Create the dialog.
	 */
	public Benutzervewaltung(String benutzer, String passwort, String host) {
		setTitle("Benutzerverwaltung");
		dbm = new DBManager(benutzer, passwort, host);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/img/icon.png")));
		setBounds(100, 100, 691, 435);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblBenutzerErstellen = new JLabel("Benutzer erstellen");
		lblBenutzerErstellen.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenutzerErstellen.setFont(new Font("Tahoma", Font.BOLD, 29));
		
		JLabel lblBenutzername = new JLabel("Benutzername");
		
		textFieldBenutzername = new JTextField();
		textFieldBenutzername.setColumns(10);
		
		JLabel lblPasswort = new JLabel("Passwort");
		
		textFieldPasswort = new JTextField();
		textFieldPasswort.setColumns(10);
		
		JLabel lblHost = new JLabel("Host");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnErstellen = new JButton("Erstellen");
		
		JButton btnSchliessen = new JButton("Schließen");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblPasswort, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
							.addComponent(lblBenutzername, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
							.addComponent(btnErstellen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
							.addComponent(btnSchliessen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
							.addComponent(textFieldBenutzername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
							.addComponent(lblBenutzerErstellen, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
							.addComponent(textFieldPasswort, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
							.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
						.addComponent(lblHost, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
					.addGap(29))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblBenutzerErstellen)
							.addGap(18)
							.addComponent(lblBenutzername)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldBenutzername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPasswort)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldPasswort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblHost)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
							.addComponent(btnErstellen)
							.addGap(18)
							.addComponent(btnSchliessen))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Benutzer", "Host", "L\u00F6schen"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class
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
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		
		//Button action event Löschen
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	//Tabelle suche (quelle + reihe)
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        
		        //Get benutzer und host von der Quelle
		        String benutzer = (String) table.getModel().getValueAt(modelRow, 0);
		        String host = (String) table.getModel().getValueAt(modelRow, 1);
		        
		        try {
		        	//Löscht benutzer
		        	dbm.startConnect("");
					dbm.getStatement().executeUpdate("DROP USER '"+benutzer+"'@'"+host+"';");
					((DefaultTableModel)table.getModel()).removeRow(modelRow);
					dbm.closeConnection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
				}
		    }
		};
		try {
			//Listet alle relevante Benutzer auf (Tabelle)
			dbm.startConnect("");
			ResultSet rs = dbm.getStatement().executeQuery("select User, Host from mysql.user where not User=\"root\" and not User=\"mysql.infoschema\" and not User=\"mysql.session\" and not User=\"mysql.sys\" and not User='"+benutzer+"';");
			while(rs.next()){
				new ButtonColumn(table, delete, 2);
		        String data[] = {rs.getString(1),rs.getString(2),"Löschen"};
		        DefaultTableModel tbm = (DefaultTableModel) table.getModel();
		        
		        tbm.addRow(data);
		    }
			dbm.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
