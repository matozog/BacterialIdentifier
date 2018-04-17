package module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class DatabaseManager {
	private Statement statement = null;
	private ResultSet resultSet;
	private PreparedStatement preparedStatment;
	private ConnectionManager connectionManager;
	private static ArrayList<Bacteria> flagellaTable = new ArrayList<Bacteria>();
	private static ArrayList<Bacteria> toughnessTable = new ArrayList<Bacteria>();
	
	public DatabaseManager()
	{
		connectionManager = new ConnectionManager();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void readFlagellaTable()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			statement = (Statement) connectionManager.getConnection().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM flagella;");
			ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
			flagellaTable.clear();
			while (resultSet.next())
			{
				Bacteria bacteria = new Bacteria();
				bacteria.setAlphaGene(resultSet.getString(2));
				bacteria.setBetaGene(resultSet.getString(3));
				bacteria.setNumber(resultSet.getString(4));
				
				flagellaTable.add(bacteria);
			 }
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | NullPointerException e) {
			JOptionPane.showMessageDialog(null,"Connection error!","Warning", JOptionPane.WARNING_MESSAGE);
		} 
	}
	
	public void readToughnessTable()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			statement = (Statement) connectionManager.getConnection().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM toughness;");
			ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
			toughnessTable.clear();
			while (resultSet.next())
			{
				Bacteria bacteria = new Bacteria();
				bacteria.setBetaGene(resultSet.getString(2));
				bacteria.setGammeGene(resultSet.getString(3));
				bacteria.setRank(resultSet.getString(4));
				
				toughnessTable.add(bacteria);
			 }
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void exitProgram()
	{
		try {
			if(connectionManager.getConnection()!=null) connectionManager.closeConnection();
			if(preparedStatment != null && !preparedStatment.isClosed()) preparedStatment.close();	
			if(statement != null && !statement.isClosed()) statement.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public ArrayList<Bacteria> getFlagellaTable() {
		return flagellaTable;
	}


	public ArrayList<Bacteria> getToughnessTable() {
		return toughnessTable;
	}
	
	public PreparedStatement getPreparedStatment() {
		return preparedStatment;
	}
	
	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public void setPreparedStatment(PreparedStatement preparedStatment) {
		this.preparedStatment = preparedStatment;
	}
}
