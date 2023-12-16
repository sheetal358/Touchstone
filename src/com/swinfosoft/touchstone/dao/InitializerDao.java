package com.swinfosoft.touchstone.dao;

import java.sql.*;
import java.util.StringTokenizer;

public class InitializerDao extends AbstractDao {

	public void initializeDb(String queries,String sep) 
			throws Exception
	{
		Connection con=getConnection();
		Statement stmt=con.createStatement();
		StringTokenizer str=new StringTokenizer(queries,sep);
		while(str.hasMoreTokens())
		{
			String q=str.nextToken();
			stmt.execute(q);
		}
		con.close();
	}
}
