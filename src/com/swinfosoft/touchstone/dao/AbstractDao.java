package com.swinfosoft.touchstone.dao;

import java.sql.*;

public abstract class AbstractDao {

	public Connection getConnection() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/touchstone",
				"root","root");
		return con;
		
	}
}
