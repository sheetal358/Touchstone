package com.swinfosoft.touchstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.swinfosoft.touchstone.models.Group;
import com.swinfosoft.touchstone.models.Message;
import com.swinfosoft.touchstone.models.Notification;
import com.swinfosoft.touchstone.models.User;

public class MessageDao extends AbstractDao {
	Logger logger=Logger.getRootLogger();
	public void saveNotification(Notification note) throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		PreparedStatement stmt=con.prepareStatement(
			"insert into Notification values (?,?,?,?)");
		int id=1;
		ResultSet rset=stmt.executeQuery(
				"select max(id) from Notification");
		if(rset.next())
		{
			int i=rset.getInt(1);
			i++;
			id=i;
		}
		logger.info("Notification Id is: "+id);
		stmt.setInt(1, id);
		stmt.setString(2, note.getNotification());
		stmt.setString(3, note.getUserName());
		stmt.setString(4, note.getCreatedOn());
		stmt.executeUpdate();
		logger.info("Notification saved.");
		con.close();
	}
	
	public List<Notification> fetchNotifications() throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		PreparedStatement stmt=con.prepareStatement("select * from Notification");
		ResultSet rSet=stmt.executeQuery();
		List<Notification> list=new ArrayList<Notification>();
		while(rSet.next())
		{
			Notification note=new Notification();
			//note=new Notification();
			note.setId(rSet.getInt(1));
			note.setNotification(rSet.getString(2));
			note.setUserName(rSet.getString(3));
			note.setCreatedOn(rSet.getString(4));
			list.add(note);
		}
		return list;
	}
	
	public void deleteNotificationById(int id) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("delete from Notification where id=?");
		stmt.setInt(1, id);
		stmt.executeUpdate();
		con.close();
	}
	
	public void saveMessage(Message msg,String[] str) throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		for(String gid:str )            //str is group ids
		{
			PreparedStatement stmt1=con.prepareStatement("select name from GroupMaster where groupId="+gid);
			ResultSet rset1=stmt1.executeQuery();
			while(rset1.next())
			{
				PreparedStatement stmt=con.prepareStatement("insert into Message values (?,?,?,?,?)");
					int id=1;
					ResultSet rset=stmt.executeQuery(
							"select max(id) from Message");
					if(rset.next())
					{
						int i=rset.getInt(1);
						i++;
						id=i;
					}
					logger.info("Message Id is: "+id);
					stmt.setInt(1, id);
					stmt.setString(2, msg.getMessage());
					stmt.setString(3, msg.getUserName());
					stmt.setString(4, msg.getCreatedOn());
					stmt.setString(5, rset1.getString(1));
					stmt.executeUpdate();
			}			
		}
		logger.info("Message saved.");
		con.close();
	}
	
	public List<Message> fetchMessages(List<Group> groups,User user) throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");		
		List<Message> list=new ArrayList<Message>();
		Iterator<Group> itr=groups.listIterator();
		while(itr.hasNext())
		{
			Group group=itr.next();
			PreparedStatement stmt1=con.prepareStatement("select * from Message");
			ResultSet rset=stmt1.executeQuery();
			while(rset.next())
			{
				String gName=rset.getString(5);
				if(group.getName().equals(gName))
				{
					Message msg=new Message();
					msg.setId(rset.getInt(1));
					msg.setMessage(rset.getString(2));
					msg.setUserName(rset.getString(3));
					msg.setCreatedOn(rset.getString(4));
					msg.setGroupName(rset.getString(5));
					list.add(msg);
				}
			}
		}
		con.close();
		return list;
	}
	
	public void deleteMessageById(int id) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("delete from Message where id=?");
		stmt.setInt(1, id);
		stmt.executeUpdate();
		con.close();
	}
}
