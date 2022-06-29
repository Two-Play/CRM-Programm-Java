package fachklasse;

import javax.swing.JOptionPane;
import java.sql.*;


public class Fachklasse {
	
	
	Connection connection;
	private String url = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String password = "df";
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			//Statement statement = connection.createStatement();
			System.out.println("Anmeldung Erfolgreich");
			
		}catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Verbindung beended");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e, "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}

}
