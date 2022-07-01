package fachklasse;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

public class Utility {

	public void updateOrt(JComboBox cb, DBManager dbm) {
		dbm.startConnect("crm");
		try {
			ResultSet rs = dbm.getStatement().executeQuery("SELECT ortName FROM crm.ort;");
				while(rs.next()){
				    cb.addItem(rs.getString(1));
				}
		dbm.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
