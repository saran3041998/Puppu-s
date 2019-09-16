package com.cognizant.puppus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/puppus", "root", "root");
		} catch (SQLException | ClassNotFoundException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}
}
