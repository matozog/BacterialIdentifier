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
	private String queryInsertToExamined = "INSERT INTO examined (genotype,class) VALUES (?,?);";
	
	public DatabaseManager()
	{
		connectionManager = new ConnectionManager();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public boolean insertNewBacterie(ArrayList<Bacteria> listBacteriesToAdd)
	{
		try {
			preparedStatment = (PreparedStatement) connectionManager.getConnection().prepareStatement(queryInsertToExamined);
			for(int i=0; i< listBacteriesToAdd.size() ;i++)
			{
				preparedStatment.setString(1,listBacteriesToAdd.get(i).getGenotyp());
				preparedStatment.setString(2,listBacteriesToAdd.get(i).getClassification());
				preparedStatment.executeUpdate();
			}
			connectionManager.getConnection().commit();
			listBacteriesToAdd.clear();
			return true;
		} catch (SQLException e) {
			try {
				connectionManager.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return false;
	}
	
	public String calculateClassification(Bacteria bacteria)
	{
		float length = Float.MAX_VALUE;
		for(int i=0; i< flagellaTable.size();i++)
		{
			if(calcLength(bacteria.getAlphaGene(),bacteria.getBetaGene(),flagellaTable.get(i).getAlphaGene(),flagellaTable.get(i).getBetaGene())<length)
			{
				length = calcLength(bacteria.getAlphaGene(),bacteria.getBetaGene(),flagellaTable.get(i).getAlphaGene(),flagellaTable.get(i).getBetaGene());
				bacteria.setNumber(flagellaTable.get(i).getNumber());
			}
		}
		length = Float.MAX_VALUE;
		for(int i=0; i<toughnessTable.size();i++)
		{
			if(calcLength(bacteria.getBetaGene(),bacteria.getGammeGene(),toughnessTable.get(i).getBetaGene(),toughnessTable.get(i).getGammeGene())<length)
			{
				length = calcLength(bacteria.getBetaGene(),bacteria.getGammeGene(),toughnessTable.get(i).getBetaGene(),toughnessTable.get(i).getGammeGene());
				
				bacteria.setRank(toughnessTable.get(i).getRank());
			}
		}
		bacteria.setClassification(bacteria.getNumber() + bacteria.getRank());
		return bacteria.getClassification();
	}
	
	public float calcLength(String examined1, String examined2, String baseData1, String baseData2)
	{
		return (float) Math.sqrt((double)(Math.pow((Integer.parseInt(baseData1) - Integer.parseInt(examined1)),2) + Math.pow((Integer.parseInt(baseData2) - Integer.parseInt(examined2)),2)));
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
