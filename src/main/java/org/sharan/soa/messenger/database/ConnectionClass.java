package org.sharan.soa.messenger.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

	public static Connection getConnection() {
		Connection connect = null;
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			String url = "jdbc:mysql://localhost:3306/A20363568_Itmd566";
			String username = "root";
			String loginPassword = "babu";
			connect = DriverManager.getConnection(url, username, loginPassword);
		} catch (Exception e) {
			System.out.println("Connection Error");
			e.printStackTrace();
		}
		return connect;
	}
}
