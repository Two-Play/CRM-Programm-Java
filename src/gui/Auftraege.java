package gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import extern.ButtonColumn;
import fachklasse.DBManager;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class Auftraege extends JDialog {
	private JTable table;

	public Auftraege(DBManager dbm) {
		
		setTitle("Aufträge");
		setBounds(100, 100, 753, 320);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/resources/icon.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Auftragsnummer", "Auftragsname", "Kunde", "Start", "ende", "Status", "Notizen"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
				
		table.addMouseListener((MouseListener) new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     // to detect doble click events
	               JTable target = (JTable)me.getSource();
	               int row = target.getSelectedRow(); // select a row
	               String tableRow = (String) table.getValueAt(row, 0); // get the value of a row and column.
	               System.out.println(tableRow);
	               new AuftraegeBearbeiten(dbm, tableRow).setVisible(true);
	               
	            }
	         }
	      });
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				DefaultTableModel tbm = (DefaultTableModel) table.getModel();    
				tbm.setRowCount(0);
				dbm.startConnect("crm");
				try {
					ResultSet rs = dbm.getStatement().executeQuery("SELECT auftraege.*, kunden.name, vorname FROM crm.auftraege, crm.kunden where auftraege.kundenNr = kunden.kundenNr;");
					while(rs.next()){
						String kunde = rs.getString(9)+ ", " +rs.getString(8);
				        String data[] = {rs.getString(1),rs.getString(2),kunde, rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(5)};
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
