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

import extern.ButtonColumn;
import fachklasse.DBManager;

public class Auftraege extends JDialog {
	private JTable table;

	public Auftraege(DBManager dbm) {
		setTitle("Aufträge");
		setBounds(100, 100, 699, 320);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/img/icon.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Auftragsname", "Kunde", "Start", "ende", "Status", "Notizen"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
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
		scrollPane.setViewportView(table);
		
		dbm.startConnect("crm");
		try {
			ResultSet rs = dbm.getStatement().executeQuery("SELECT auftraege.*, kunden.name, vorname FROM crm.auftraege, crm.kunden where auftraege.kundenNr = kunden.kundenNr;");
			while(rs.next()){
				String kunde = rs.getString(8)+ "; " +rs.getString(9);
		        String data[] = {rs.getString(2),kunde, rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(5)};
		        DefaultTableModel tbm = (DefaultTableModel) table.getModel();
		        tbm.addRow(data);
		    }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbm.closeConnection();
	}

}
