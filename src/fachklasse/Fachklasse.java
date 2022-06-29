package fachklasse;

import javax.swing.JOptionPane;
import java.sql.*;


public class Fachklasse {
	
	private Connection connection = null;
	
	private String url = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String password = "";
	
	//Getter Setter
	public Connection getConnection() {
		return connection;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public boolean startConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			//Statement statement = connection.createStatement();
			System.out.println("Anmeldung Erfolgreich");
			return true;
		}catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e, "Fehler", JOptionPane.ERROR_MESSAGE);
			return false;
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
