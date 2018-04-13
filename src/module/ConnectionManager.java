package module;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class ConnectionManager {

	private String url = "jdbc:mysql://localhost:3306/bacterial", username,password;
	private static Connection connection = null;
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(char[] password)
	{
		this.password = String.copyValueOf(password);
		System.out.print(this.password);
	}
	
	public void setURL(String url)
	{
		this.url = url;
	}
	
	public String getURL()
	{
		return url;
	}
	
	public void createConnection() 
	{
		try {
			connection = (Connection) DriverManager.getConnection(url, username,password);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Connection error!","Warning", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public void closeConnection() throws SQLException
	{
		connection.close();
	}
	
}
