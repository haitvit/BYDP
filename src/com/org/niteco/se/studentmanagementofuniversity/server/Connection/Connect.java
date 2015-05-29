package com.org.niteco.se.studentmanagementofuniversity.server.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	Connection connection;

	public Connection getConn() {

		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String db = "StudentManagementOfUniversity";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String pass = "123456";

		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {

			conn = DriverManager.getConnection(url + db, user, pass);
		} catch (SQLException e) {
			System.err.println("Mysql Connection Error: ");
			e.printStackTrace();
		}
		return conn;
	}
}
