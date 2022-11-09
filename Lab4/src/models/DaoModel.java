package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import records.BankRecords;

public class DaoModel {

	DBConnect conn = null;

	Statement stmt = null;

	// constructor
	public DaoModel() { // create db object instance
		conn = new DBConnect();
	}


	public void createTable() {
		try {

			// Open a connection
			System.out.println("Connecting to database to create Table...");
			System.out.println("Connected database successfully...");

			// Execute create query
			System.out.println("Creating table in given database...");

			stmt = conn.connect().createStatement();

			String sql = "CREATE TABLE v_katta_tab " + "(pid INTEGER not NULL AUTO_INCREMENT, " + "id VARCHAR(10), "
					+ "income numeric(8,2), " + "pep VARCHAR(3), " + " PRIMARY KEY ( pid ))";
			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			conn.connect().close(); // close db connection
		} catch (SQLException se) { // Handle errors for JDBC
			se.printStackTrace();
		}
	}

	public void insertRecords(BankRecords[] robjs) {
		try {
			int count = 1;
		    // Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.connect().createStatement();
			String sql = null;
            
			//sql = "Insert Into v_katta_tab(pid, id, income, pep)" + " values(" + count + ", " + "'" + robjs[i].getId() + "'" 
			//		+ ", " + robjs[i].getIncome() + "," + "'" + robjs[i].getPep() + "'"+ ")";
		
			// Include all object data to the database table
			  for (int i = 0; i < robjs.length; ++i) {
				  			
					sql = "Insert Into v_katta_tab(pid, id, income, pep)" + " values(?,?,?,?)";
					System.out.println(sql);
					
					PreparedStatement preparedStatement = conn.connect().prepareStatement(sql);
					
					preparedStatement.setLong(1, count);
					preparedStatement.setString(2, "'" + robjs[i].getId() + "'" );
					preparedStatement.setDouble(3, robjs[i].getIncome());
					preparedStatement.setString(4, "'" + robjs[i].getId() + "'");
					count = count +1;
					preparedStatement.executeUpdate();

			  }
			conn.connect().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public ResultSet retrieveRecords() throws SQLException {

		ResultSet rs = null;
		stmt = conn.connect().createStatement();
		String sql = "select pid, id,income, pep from v_katta_tab order by pep desc";
		rs = stmt.executeQuery(sql);
		conn.connect().close();
		return rs;
	}
}
