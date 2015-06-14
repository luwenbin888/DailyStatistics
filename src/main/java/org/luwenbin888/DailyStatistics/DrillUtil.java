package org.luwenbin888.DailyStatistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DrillUtil {
	
	private static Connection drillConn;
	
	static {
		try {
			drillConn = getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(AppConfig.DrillJDBCDriverClass);
		Connection connection = DriverManager
				.getConnection(AppConfig.DrillConnectionStr);
		
		return connection;
	}
	
	public static ResultSet submitQuery(String query) throws SQLException {
		
		
		try {
			if (drillConn == null || drillConn.isClosed()) {
				System.out.println("Drill connection closed, reopen..");
				drillConn = getConnection();
			}
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		if (drillConn == null || drillConn.isClosed()) {
			return null;
		}
		
		Statement statement = drillConn.createStatement();
		
		ResultSet result = statement.executeQuery(query);
		
		return result;
	}
	
	public static void closeConnection() throws SQLException {
		if (drillConn != null && !drillConn.isClosed()) {
			drillConn.close();
		}
	}

}
