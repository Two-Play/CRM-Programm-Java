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
import java.awt.event.WindowFocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

public class Termine extends JDialog {
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public Termine(DBManager dbm) {
		
		setTitle("Termine");
		setModal(true);
		setBounds(100, 100, 690, 353);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AnmeldungGui.class.getResource("/resources/icon.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"TerminNr", "Kunde", "Uhrzeit", "Datum", "Bemerkung"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
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
	               new TerminBearbeiten(dbm, tableRow).setVisible(true);
	               
	            }
	         }
	      });
		
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				dbm.startConnect("crm");
				try {
					DefaultTableModel tbm = (DefaultTableModel) table.getModel();    
					tbm.setRowCount(0);
					ResultSet rs = dbm.getStatement().executeQuery("SELECT termine.*, kunden.name, vorname FROM crm.termine, crm.kunden where termine.kundenNr = kunden.kundenNr;");
					while(rs.next()){
						String kunde = rs.getString(7)+ ", " +rs.getString(6);
				        String data[] = {rs.getString(1),kunde,rs.getString(3), rs.getString(2),rs.getString(4)};
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
