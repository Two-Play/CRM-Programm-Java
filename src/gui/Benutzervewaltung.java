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

public class Benutzervewaltung extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private static DBManager dbm = null;

	
	public static void main(String[] args) {
		try {
			Benutzervewaltung dialog = new Benutzervewaltung();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the dialog.
	 */
	public Benutzervewaltung(String benutzer, String passwort, String host) {
		dbm = new DBManager(benutzer, passwort, host);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/img/icon.png")));
		setBounds(100, 100, 637, 435);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(111)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
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
		});
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        
		        String benutzer = (String) table.getModel().getValueAt(modelRow, 0);
		        String host = (String) table.getModel().getValueAt(modelRow, 1);
		        dbm.startConnect("");
		        try {
					dbm.getStatement().executeUpdate("DROP USER '"+benutzer+"'@'"+host+"';");
					((DefaultTableModel)table.getModel()).removeRow(modelRow);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1, "Fehler", JOptionPane.ERROR_MESSAGE);
				}
		    }
		};
		try {
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
	
	public Benutzervewaltung() {
		System.exit(0);
	}

}
