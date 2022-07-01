package fachklasse;

import javax.swing.JOptionPane;
import java.sql.*;


public class DBManager {
	
	private Connection connection = null;
	private Statement statement;
	
	private String host;
	private String user;
	private String password;

	//Getter Setter
	public Connection getConnection() {
		return connection;
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
	
	public Statement getStatement() {
		return statement;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	//Konstruktor
	public DBManager(String user, String password, String host) {
		super();
		this.host = host;
		this.user = user;
		this.password = password;
	}
	

	public boolean startConnect(String db) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://" + getHost() + "/";
			connection = DriverManager.getConnection(url+db, user, password);
			statement = connection.createStatement();
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
			statement.close();
			System.out.println("Verbindung beended");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e, "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean tableExist(Connection con, String tableName) {
		try {
		DatabaseMetaData dbm = con.getMetaData();
		ResultSet tables = dbm.getTables(null, null, tableName, null);
		if (tables.next()) {
		  //Tabelle exestiert
			return true;
		}
		else {
		  //Tabelle exestiert nicht
			return false;
		}
		}catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	

	public boolean databaseExist(Connection con) {
		String sql = "SHOW DATABASES LIKE 'crm'";
	    try (Statement stmt = con.createStatement()) {
	        ResultSet rs = stmt.executeQuery(sql);
	   
	        while(rs.next()){
	            if ("crm".equals(rs.getString(1))) {
	            	//DB existiert
	     		   return true;
	     	   }else {
	     		//DB existiert nicht
	     		   return false;
	     	   }
	        }
	   
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	   return false;
	}

}
