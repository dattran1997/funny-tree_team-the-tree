/**
 * Tran Tuan Anh
 * Date: Apr 3, 2018
 * Project: LibraryManagementSystem
 */
package com.thetree.hackathon.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to open or close connection to database.
 * @author gadfl
 *
 */

public abstract class DBUtils {
	
	private static final String DB_URL = "jdbc:sqlite:database/demoDB.db";
	private final String GET_COUNT = "SELECT COUNT(*) AS count FROM ";
	protected static Connection conn = null;
	
	/**
	 * This method is used to make connection with database.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see SQLException
	 * @see ClassNotFoundException
	 */
	public void createConnection() throws SQLException, ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(DB_URL);
	}
	
	/**
	 * This method is used to close connection to the database.
	 * @throws SQLException
	 * @see SQLException
	 */
	public void closeConnection() throws SQLException{
		if(conn != null) {
			conn.close();
		}
	}
	
	/**
	 * This method is used to get the total number of record in the given table;
	 * @param table is the table that you want to count record.
	 * @return numberOfRows is the number of record in the table.
	 * @throws SQLException 
	 */
	public int countRecord(String table) throws SQLException, ClassNotFoundException {
		int numberOfRows = 0;
		createConnection();
		PreparedStatement stmt = conn.prepareStatement(GET_COUNT+table);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			numberOfRows = rs.getInt("count");
		}
		rs.close();
		closeConnection();
		return numberOfRows;
	}
}
