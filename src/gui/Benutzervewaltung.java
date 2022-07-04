package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;

public class Benutzervewaltung extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private JTextField textFieldBenutzername;
	private JTextField textFieldPasswort;

	/**
	 * Create the dialog.
	 */
	public Benutzervewaltung(DBManager dbm) {
		setTitle("Benutzerverwaltung");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/resources/icon.png")));
		setBounds(100, 100, 673, 353);
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
		
		JButton btnErstellen = new JButton("Erstellen");
		
		
		JButton btnSchliessen = new JButton("Schließen");
		btnSchliessen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"%", "localhost"}));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
					.addGap(13)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textFieldPasswort, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textFieldBenutzername, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblBenutzerErstellen, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
							.addGap(29))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblBenutzername, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblPasswort, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblHost, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSchliessen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(btnErstellen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(comboBox, 0, 291, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblBenutzerErstellen)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblBenutzername)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldBenutzername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPasswort)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldPasswort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHost)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addComponent(btnErstellen)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSchliessen)
							.addContainerGap())))
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
		Action deleteAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e)
		    {
		    	//Tabelle suche (quelle + reihe)
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        
		        //Get benutzer und host von der Quelle
		        String benutzer = (String) table.getModel().getValueAt(modelRow, 0);
		        String host = (String) table.getModel().getValueAt(modelRow, 1);
		        System.out.println("gadfjk");
		        try {
		        	//Löscht benutzer
		        	dbm.startConnect("");
					dbm.getStatement().executeUpdate("DROP USER '"+benutzer+"'@'"+host+"';");
					System.out.println("Benutzer gelöscht!");
					((DefaultTableModel)table.getModel()).removeRow(modelRow);
					dbm.closeConnection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
				}
		    }
		};
	
		getData(dbm);
		table.addMouseListener((MouseListener) new MouseAdapter() {
	         public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	            	int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Sind Sie sicher, dass Sie diesen Benutzer Löschen möchten?", "Warnung" , dialogButton, JOptionPane.WARNING_MESSAGE);
					if(dialogResult == JOptionPane.YES_OPTION){
	            	//Tabelle suche (quelle + reihe)
	            	JTable target = (JTable)e.getSource();
		               int row = target.getSelectedRow(); // select a row
			        
			        //Get benutzer und host von der Quelle
			        String benutzer = (String) table.getModel().getValueAt(row, 0);
			        String host = (String) table.getModel().getValueAt(row, 1);
			        try {
			        	//Löscht benutzer
			        	dbm.startConnect("");
						dbm.getStatement().executeUpdate("DROP USER '"+benutzer+"'@'"+host+"';");
						System.out.println("Benutzer gelöscht!");
						((DefaultTableModel)table.getModel()).removeRow(row);
						dbm.closeConnection();
						getData(dbm);
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
					}
					}  
	            }
	         }
	      });
	
		
		btnErstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.startConnect("");
				try {
					dbm.getStatement().executeUpdate("CREATE USER '"+textFieldBenutzername.getText()+"'@'"+comboBox.getSelectedItem().toString()+"' IDENTIFIED BY '"+textFieldPasswort.getText()+"';");
					dbm.getStatement().executeUpdate("GRANT ALL PRIVILEGES ON crm . * TO '"+textFieldBenutzername.getText()+"'@'"+comboBox.getSelectedItem().toString()+"'");
					JOptionPane.showMessageDialog(null, "Benutzer erfolgreich erstellt", "Info", JOptionPane.INFORMATION_MESSAGE);
					getData(dbm);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
				}
				dbm.closeConnection();
			}
		});
		
		
	
	}
	
	private void getData(DBManager dbm) {
		try {
			//Listet alle relevante Benutzer auf (Tabelle)
			dbm.startConnect("");
	        DefaultTableModel tbm = (DefaultTableModel) table.getModel();
	        tbm.setRowCount(0);
			ResultSet rs = dbm.getStatement().executeQuery("select User, Host from mysql.user where not User=\"root\" and not User=\"mysql.infoschema\" and not User=\"mysql.session\" and not User=\"mysql.sys\" and not User='"+dbm.getUser()+"';");
			while(rs.next()){
				//new ButtonColumn(table, deleteAction, 2);
		        String data[] = {rs.getString(1),rs.getString(2),"Löschen"};
		        tbm.addRow(data);
		    }
			dbm.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
