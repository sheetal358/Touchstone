package com.swinfosoft.touchstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.swinfosoft.touchstone.models.Group;
import com.swinfosoft.touchstone.models.Quiz;
import com.swinfosoft.touchstone.models.User;

public class QuizDao extends AbstractDao{

	Logger logger=Logger.getRootLogger();
	public void save(Quiz quiz) throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		PreparedStatement stmt=con.prepareStatement(
			"insert into QuizMaster values (?,?,?,?,?,?)");
		int id=1;
		ResultSet rset=stmt.executeQuery(
				"select max(id) from QuizMaster");
		if(rset.next())
		{
			int i=rset.getInt(1);
			i++;
			id=i;
		}
		quiz.setId(id);
		logger.info("Quiz Id is: "+quiz.getId());
		stmt.setInt(1, quiz.getId());
		stmt.setString(2,quiz.getName());
		stmt.setInt(3,quiz.getStatus());
		stmt.setInt(4,quiz.getDuration());
		stmt.setString(5,quiz.getCreatedOn());
		stmt.setInt(6,quiz.getOwnerId());
		stmt.executeUpdate();
		logger.info("Quiz saved.");
		con.close();
	}
	
	public List<Quiz> getAllQuiz(int ownerId) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=
				con.prepareStatement(
			"select * from QuizMaster where ownerId=?");
		stmt.setInt(1, ownerId);
		ResultSet rset=stmt.executeQuery();
		ArrayList<Quiz> list=new ArrayList<Quiz>();
		while(rset.next())
		{
			Quiz quiz=new Quiz();
			quiz.setId(rset.getInt(1));
			quiz.setName(rset.getString(2));
			quiz.setStatus(rset.getInt(3));
			quiz.setDuration(rset.getInt(4));
			quiz.setCreatedOn(rset.getString(5));
			quiz.setOwnerId(rset.getInt(6));
			
			list.add(quiz);
		}
		con.close();
		return list;
	}
	
	
	public List<Quiz> getAllAssignQuiz(User user) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("select * from GroupMaster");
		ResultSet rset=stmt.executeQuery();
		List<Group> list=new ArrayList<Group>();
		while(rset.next())
		{
			Group group=new Group();
			
			GroupDao dao=new GroupDao();
			boolean flag=dao.matchUser(user,rset.getString(4));
			if(flag)
			{
				group.setGroupId(rset.getInt(1));
				group.setName(rset.getString(2));
				group.setDescription(rset.getString(3));
				group.setMembers(rset.getString(4));
				group.setCode(rset.getString(6));
				group.setStatus(rset.getInt(7));
				list.add(group);
			}
		}
		List<Quiz> list1=new ArrayList<Quiz>();
		Iterator<Group> itr=list.iterator();
		while(itr.hasNext())
		{
			 Group group=itr.next();
			 
		PreparedStatement stmt1=con.prepareStatement("select * from QuizMaster where id in (select quizId from QuizGroup where groupId="+group.getGroupId()+")");
		ResultSet rset1=stmt1.executeQuery();
			while(rset1.next())
			{
				PreparedStatement stmt2=con.prepareStatement("select count(*) from ResultMaster where quizId="+rset1.getInt(1)+" and userId="+user.getId());
				
				ResultSet rset2=stmt2.executeQuery();
				int count=0;
				if(rset2.next())
					count=rset2.getInt(1);
				Quiz quiz=new Quiz();
				quiz.setId(rset1.getInt(1));
				quiz.setName(rset1.getString(2));
				quiz.setStatus(rset1.getInt(3));
				quiz.setDuration(rset1.getInt(4));
				quiz.setCreatedOn(rset1.getString(5));
				quiz.setOwnerId(rset1.getInt(6));
				quiz.setAttempted(count);
				list1.add(quiz);
			}
		}
		con.close();
		return list1;
	}
	
	
	
	public Quiz loadById(int id) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=
				con.prepareStatement(
			"select * from QuizMaster where id=?");
		stmt.setInt(1, id);
		ResultSet rset=stmt.executeQuery();
		Quiz quiz=null;
		if(rset.next())
		{
			quiz=new Quiz();
			quiz.setId(rset.getInt(1));
			quiz.setName(rset.getString(2));
			quiz.setStatus(rset.getInt(3));
			quiz.setDuration(rset.getInt(4));
			quiz.setCreatedOn(rset.getString(5));
			quiz.setOwnerId(rset.getInt(6));
			
			
		}
		con.close();
		return quiz;
	}
	
	public void deleteQuizById(int id) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("delete from QuestionMaster where quizId=?");
		stmt.setInt(1, id);
		stmt.executeUpdate();
		PreparedStatement stmt1=con.prepareStatement("delete from QuizMaster where id=?");
		stmt1.setInt(1, id);
		stmt1.executeUpdate();
		con.close();
	}
	
	public void update(Quiz q)throws Exception {
		logger.info("updating...");
		Connection con=getConnection();
		PreparedStatement stmt=
			con.prepareStatement("update QuizMaster set name=?,duration=? where id=?");
		stmt.setString(1,q.getName());
		stmt.setInt(2, q.getDuration());
		stmt.setInt(3, q.getId()); 
		stmt.executeUpdate();
		logger.info("updated.");
		con.close();
	}
	
	public List<Quiz> getQuizByGroupId(int gid)throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("select QuizId from QuizGroup where GroupId=?");
		stmt.setInt(1, gid);
		ResultSet rset=stmt.executeQuery();
		List<Quiz> list=new ArrayList<Quiz>();
		while(rset.next())
		{System.out.println(rset.getInt(1));
			PreparedStatement stmt1=con.prepareStatement("select * from QuizMaster where id=?");
			stmt1.setInt(1, rset.getInt(1));
			ResultSet rset1=stmt1.executeQuery();
			Quiz quiz=null;
			while(rset1.next())
			{System.out.println(rset1.getInt(1));
				quiz=new Quiz();
				quiz.setId(rset1.getInt(1));
				quiz.setName(rset1.getString(2));
				quiz.setStatus(rset1.getInt(3));
				quiz.setDuration(rset1.getInt(4));
				quiz.setCreatedOn(rset1.getString(5));
				quiz.setOwnerId(rset1.getInt(6));
				list.add(quiz);
			}
		}
		return list;
	}
	
	public boolean assignQuizToGroup(int gId,int qId) throws Exception
	{
		boolean flag=false;
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("select * from QuizGroup where GroupId=? and QuizId=?");
		stmt.setInt(1, gId);
		stmt.setInt(2, qId);
		ResultSet rset=stmt.executeQuery();
		if(!rset.next())
		{
			flag=true;
			PreparedStatement stmt1=con.prepareStatement("insert into QuizGroup values(?,?)");
			stmt1.setInt(1, gId);
			stmt1.setInt(2, qId);
			stmt1.executeUpdate();
			PreparedStatement stmt2=con.prepareStatement("update QuizMaster set status=? where id=?");
			stmt2.setInt(1, Quiz.ASSIGNED);
			stmt2.setInt(2, qId);
			stmt2.executeUpdate();
		}
		con.close();
		return flag;
	}
	
	public boolean assignQuizToMember(int mId,int qId) throws Exception
	{
		boolean flag=false;
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("select * from QuizMember where UserId=? and QuizId=?");
		stmt.setInt(1, mId);
		stmt.setInt(2, qId);
		ResultSet rset=stmt.executeQuery();
		if(!rset.next())
		{
			flag=true;
			PreparedStatement stmt1=con.prepareStatement("insert into QuizMember values(?,?)");
			stmt1.setInt(1, mId);
			stmt1.setInt(2, qId);
			stmt1.executeUpdate();
			PreparedStatement stmt2=con.prepareStatement("update QuizMaster set status=? where id=?");
			stmt2.setInt(1, Quiz.ASSIGNED);
			stmt2.setInt(2, qId);
			stmt2.executeUpdate();
		}
		con.close();
		return flag;
	}
	
	public boolean findByName(String name) throws Exception
	{
		boolean flag=false;
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("select * from QuizMaster where name=?");
		stmt.setString(1, name);
		ResultSet rset=stmt.executeQuery();
		if(rset.next())
		{
			flag=true;
		}
		con.close();
		return flag;
	}
	
	public void deleteQuizFromQuizGroup(int id) throws Exception
	{
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("update QuizMaster set status=? where id=?");
		stmt.setInt(1, Quiz.UNASSIGNED);
		stmt.setInt(2, id);
		stmt.executeUpdate();
		PreparedStatement stmt1=con.prepareStatement("delete from QuizGroup where quizId=?");
		stmt1.setInt(1, id);
		stmt1.executeUpdate();
		con.close();
	}
	
	public void deleteQuizByGroupId(int gId,String[] str) throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		for(String qid:str )
		{
			PreparedStatement stmt=con.prepareStatement("delete from QuizGroup where groupId=? and quizId="+qid);
			stmt.setInt(1, gId);
			stmt.executeUpdate();
		}
		logger.info("Quiz unassigned.");
		con.close();
	}
	
}
