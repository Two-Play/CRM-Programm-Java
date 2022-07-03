package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fachklasse.DBManager;

public class Termine extends JDialog {
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public Termine(DBManager dbm) {
		setTitle("Termine");
		setModal(true);
		setBounds(100, 100, 517, 353);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/img/icon.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Kunde", "Uhrzeit", "Datum", "Bemerkung"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, String.class, String.class
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
		scrollPane.setViewportView(table);


		dbm.startConnect("crm");
		try {
			DefaultTableModel tbm = (DefaultTableModel) table.getModel();    
			tbm.setRowCount(0);
			ResultSet rs = dbm.getStatement().executeQuery("SELECT termine.*, kunden.name, vorname FROM crm.termine, crm.kunden where termine.kundenNr = kunden.kundenNr;");
			while(rs.next()){
				String kunde = rs.getString(7)+ ", " +rs.getString(6);
		        String data[] = {kunde,rs.getString(3), rs.getString(2),rs.getString(4)};
		        tbm.addRow(data);
		    }

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		dbm.closeConnection();
	}

}
