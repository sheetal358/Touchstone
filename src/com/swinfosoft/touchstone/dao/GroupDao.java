package com.swinfosoft.touchstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

//import com.sun.xml.internal.fastinfoset.util.StringArray;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.models.Group;
import com.swinfosoft.touchstone.models.User;

public class GroupDao extends AbstractDao{

	public void save(Group group) throws Exception
	{
		HttpSession session=ActionContext.getSession();
		User user=(User) session.getAttribute("user");
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
			"insert into GroupMaster values (?,?,?,?,?,?,?)");
		int id=1;
		ResultSet rset=stmt.executeQuery(
				"select max(groupId) from GroupMaster");
		if(rset.next())
		{
			int i=rset.getInt(1);
			i++;
			id=i;
		}
		group.setGroupId(id);
		stmt.setInt(1, group.getGroupId());
		stmt.setString(2,group.getName());
		stmt.setString(3,group.getDescription());
		stmt.setString(4,group.getMembers());
		stmt.setInt(5,user.getId());
		stmt.setString(6,group.getCode());
		stmt.setInt(7,group.getStatus());
		stmt.executeUpdate();
		con.close();
	}
	
	
	public List<Group> getAllGroups() throws Exception
	{
		Connection con=getConnection();
		Statement stmt=con.createStatement();
		ResultSet rset=stmt.executeQuery(
				"select * from GroupMaster");
		ArrayList<Group> list=new ArrayList<Group>();
		while(rset.next())
		{
			Group group=new Group();
			group.setGroupId(rset.getInt(1));
			group.setName(rset.getString(2));
			group.setDescription(rset.getString(3));
			group.setMembers(rset.getString(4));
			group.setCode(rset.getString(6));
			group.setStatus(rset.getInt(7));
			
			list.add(group);
		}
		con.close();
		return list;
	}
	
	public List<Group> getAllGroups(int ownerId) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=
				con.prepareStatement(
			"select * from GroupMaster where ownerId=?");
		stmt.setInt(1, ownerId);
		ResultSet rset=stmt.executeQuery();
		ArrayList<Group> list=new ArrayList<Group>();
		while(rset.next())
		{
			Group group=new Group();
			group.setGroupId(rset.getInt(1));
			group.setName(rset.getString(2));
			group.setDescription(rset.getString(3));
			group.setMembers(rset.getString(4));
			group.setCode(rset.getString(6));
			group.setStatus(rset.getInt(7));
			
			list.add(group);
		}
		con.close();
		return list;
	}
	
	
	public List<Group> getAllActiveGroups(User user) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=
				con.prepareStatement(
			"select * from GroupMaster");
		ResultSet rset=stmt.executeQuery();
		ArrayList<Group> list=new ArrayList<Group>();
		Group group;
		while(rset.next())
		{
			boolean str=matchUser(user,rset.getString(4));
			if(str)
			{
				group=new Group();
				group.setGroupId(rset.getInt(1));
				group.setName(rset.getString(2));
				group.setDescription(rset.getString(3));
				group.setMembers(rset.getString(4));
				group.setCode(rset.getString(6));
				group.setStatus(rset.getInt(7));			
				list.add(group);
			}
		}
		con.close();
		return list;
	}
	
	public boolean joinGroup(String code,User user) throws Exception
	{
		boolean flag=false;
		Connection con=getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from GroupMaster where groupCode=?");
		stmt1.setString(1, code);
		ResultSet rset=stmt1.executeQuery();
		if(rset.next())
		{	
			String id="";
			boolean str=matchUser(user,rset.getString(4));
			if(!str)
			{	
				id=rset.getString(4)+","+user.getId();
				PreparedStatement stmt=con.prepareStatement("update GroupMaster set members=? where groupCode=?");
				stmt.setString(1,id);
				stmt.setString(2, code);
				stmt.executeUpdate();
				flag=true;
			}
		}
		
		con.close();
		return flag;
	}
	
	public boolean matchUser(User user,String op)
	{
		if(op==null)
			return false;
		boolean flag=false;
		StringTokenizer str=new StringTokenizer(op,",");
		while(str.hasMoreTokens())
		{
			int id=user.getId();
			if(id==Integer.parseInt(str.nextToken()))
			{
				flag=true;
				break;
			}		
			else
				flag=false;
		}
		return flag;
	}
	
	public void changeCode(int id,String code) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("select * from UserMaster where GroupCode=(select GroupCode from GroupMaster where groupId="+id+")");
		ResultSet rset=stmt.executeQuery();
		if(rset.next())
		{	int id1=rset.getInt(1);
			PreparedStatement stmt1=con.prepareStatement("update UserMaster set GroupCode=? where id=?");			
			stmt1.setString(1,code);
			stmt1.setInt(2, id1);
			stmt1.executeUpdate();
		}
		PreparedStatement stmt2=con.prepareStatement("update GroupMaster set GroupCode=? where GroupId=?");			
		stmt2.setString(1,code);
		stmt2.setInt(2, id);
		stmt2.executeUpdate();
		con.close();
	}
	
	public void changeStatus(int id,int status) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
		"update GroupMaster set status=? where GroupId=?");			
		stmt.setInt(1,status);
		stmt.setInt(2, id);
		stmt.executeUpdate();
		con.close();
	}
	
	public boolean findCode(String code) throws Exception
	{
		boolean flag=false;
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
		"select * from GroupMaster where GroupCode=? and status=1");
		stmt.setString(1, code);
		ResultSet rset=stmt.executeQuery();
		if(rset.next())
		{
			flag=true;
		}
		con.close();
		return flag;
	}
	
	public boolean findName(String name) throws Exception
	{
		boolean flag=false;
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
		"select * from GroupMaster where name=?");
		stmt.setString(1, name);
		ResultSet rset=stmt.executeQuery();
		if(rset.next())
		{
			flag=true;
		}
		con.close();
		return flag;
	}
}
