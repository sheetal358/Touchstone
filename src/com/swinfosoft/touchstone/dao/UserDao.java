package com.swinfosoft.touchstone.dao;

import java.sql.*;
import java.util.*;

import com.swinfosoft.touchstone.models.User;

public class UserDao extends AbstractDao{

	public void save(User user) throws Exception
	{
		GroupDao dao=new GroupDao();
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
			"insert into UserMaster values (?,?,?,?,?,?,?,?)");
		int id=1;
		ResultSet rset=stmt.executeQuery(
				"select max(id) from UserMaster");
		if(rset.next())
		{
			int i=rset.getInt(1);
			i++;
			id=i;
		}
		user.setId(id);
		stmt.setInt(1, user.getId());
		stmt.setString(2,user.getName());
		stmt.setString(3,user.getMailId());
		stmt.setString(4,user.getPassword());
		stmt.setString(5,user.getQuestion());
		stmt.setString(6,user.getAns());
		stmt.setInt(7,user.getRole());
		stmt.setString(8,user.getGroupCode());
		dao.joinGroup(user.getGroupCode(), user);
		stmt.executeUpdate();
		con.close();
	}
	
	public void update(User user) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("update UserMaster set name=?,mailId=?,password=? where id=?");
		
		stmt.setString(1,user.getName());
		stmt.setString(2,user.getMailId());
		stmt.setString(3,user.getPassword());
		stmt.setInt(4, user.getId());
		stmt.executeUpdate();
		con.close();
	}
	
	public void changePassword(int id,String pass) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
		"update UserMaster set password=? where id=?");
			
		stmt.setString(1,pass);
		stmt.setInt(2, id);
		stmt.executeUpdate();
		con.close();
	}
	
	
	public void delete(int id) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
				"delete from UserMaster where id=?");
		stmt.setInt(1, id);
		stmt.executeUpdate();
		con.close();
	}
	public User getById(int id) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
				"select * from UserMaster where id=?");
		stmt.setInt(1, id);
		ResultSet rset=stmt.executeQuery();
		User user=null;
		if(rset.next())
		{
			user=new User();
			user.setId(rset.getInt(1));
			user.setName(rset.getString(2));
			user.setMailId(rset.getString(3));
			user.setPassword(rset.getString(4));
			user.setQuestion(rset.getString(5));
			user.setAns(rset.getString(6));
			user.setRole(rset.getInt(7));
			user.setGroupCode(rset.getString(8));
		}
		con.close();
		return user;
	}
	
	public User findByMailId(String mailId) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
				"select * from UserMaster where mailId=?");
		stmt.setString(1, mailId);
		ResultSet rset=stmt.executeQuery();
		User user=null;
		if(rset.next())
		{
			user=new User();
			user.setId(rset.getInt(1));
			user.setName(rset.getString(2));
			user.setMailId(rset.getString(3));
			user.setPassword(rset.getString(4));
			user.setQuestion(rset.getString(5));
			user.setAns(rset.getString(6));
			user.setRole(rset.getInt(7));
			user.setGroupCode(rset.getString(8));
		}
		con.close();
		return user;
	}
	
	
	
	
	public boolean find(User user) throws Exception
	{
		boolean flag=false;
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
		"select * from UserMaster where mailId=? and password=?");
		stmt.setString(1, user.getMailId());
		stmt.setString(2, user.getPassword());
		ResultSet rset=stmt.executeQuery();
		
		if(rset.next())
		{
			flag=true;
			user.setId(rset.getInt(1));
			user.setName(rset.getString(2));
			user.setQuestion(rset.getString(5));
			user.setAns(rset.getString(6));
			user.setRole(rset.getInt(7));
			user.setGroupCode(rset.getString(8));
		}
		con.close();
		return flag;
	}
	
	
	
	
	
	public List<User> getAllUsers() throws Exception
	{
		Connection con=getConnection();
		Statement stmt=con.createStatement();
		ResultSet rset=stmt.executeQuery(
				"select * from UserMaster");
		ArrayList<User> list=new ArrayList<User>();
		while(rset.next())
		{
			User user=new User();
			user.setId(rset.getInt(1));
			user.setName(rset.getString(2));
			user.setMailId(rset.getString(3));
			user.setPassword(rset.getString(4));
			user.setQuestion(rset.getString(5));
			user.setAns(rset.getString(6));
			user.setRole(rset.getInt(7));
			user.setGroupCode(rset.getString(8));
			list.add(user);
		}
		con.close();
		return list;
	}
}