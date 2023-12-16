package com.swinfosoft.touchstone.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.swinfosoft.touchstone.models.Quiz;
import com.swinfosoft.touchstone.models.QuizResult;
import com.swinfosoft.touchstone.models.Result;
import com.swinfosoft.touchstone.models.User;

public class ResultDao extends AbstractDao{

	Logger logger=Logger.getRootLogger();
	public void save(Result result) throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		PreparedStatement stmt=con.prepareStatement(
			"insert into ResultMaster values (?,?,?,?,?,?)");
		int id=1;
		ResultSet rset=stmt.executeQuery("select max(resultId) from ResultMaster");
		if(rset.next())
		{
			int i=rset.getInt(1);
			i++;
			id=i;
		}
		result.setResultId(id);
		logger.info("Result Id is: "+result.getResultId());
		stmt.setInt(1, result.getResultId());
		stmt.setInt(2,result.getUserId());
		stmt.setInt(3,result.getQuizId());
		stmt.setFloat(4,result.getResult());
		stmt.setString(5,result.getDate());
		stmt.setFloat(6,result.getTimeTaken());
		stmt.executeUpdate();
		logger.info("Quiz saved.");
		con.close();
		Set<QuizResult> quizResults=result.getQuizResults();
		for (QuizResult quizResult : quizResults) {
			quizResult.setResultId(id);
			saveQuizResult(quizResult);
		}
		
	}
	
	public void saveQuizResult(QuizResult result) throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		PreparedStatement stmt=con.prepareStatement(
			"insert into QuizResult values (?,?,?,?,?)");
		int id=1;
		ResultSet rset=stmt.executeQuery(
				"select max(qrId) from QuizResult");
		if(rset.next())
		{
			int i=rset.getInt(1);
			i++;
			id=i;
		}
		result.setQrId(id);
		logger.info("Result Id is: "+result.getQrId());
		stmt.setInt(1, result.getQrId());
		stmt.setInt(2,result.getResultId());
		stmt.setInt(3,result.getQuestionId());
		stmt.setInt(4,result.getAnswer());
		stmt.setFloat(5,result.getTimeTaken());
		stmt.executeUpdate();
		logger.info("Quiz saved.");
		con.close();
	}
	
	public List<Result> fetchResult(User user) throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		PreparedStatement stmt=con.prepareStatement("select * from ResultMaster where userId=?");
		stmt.setInt(1, user.getId());
		ResultSet rset=stmt.executeQuery();
		ArrayList<Result> list=new ArrayList<Result>();		
		while(rset.next())
		{
			Result result=new Result();
			result.setResultId(rset.getInt(1));
			result.setUserId(rset.getInt(2));
			result.setQuizId(rset.getInt(3));
			result.setResult(rset.getFloat(4));
			result.setDate(rset.getString(5));
			result.setTimeTaken(rset.getFloat(6));
			list.add(result);			
		}
		con.close();
		return list;
		
	}
	
	public List<Result> fetchResultforUsers() throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		PreparedStatement stmt=con.prepareStatement("select * from ResultMaster");
		ResultSet rset=stmt.executeQuery();
		ArrayList<Result> list=new ArrayList<Result>();		
		while(rset.next())
		{
			Result result=new Result();
			result.setResultId(rset.getInt(1));
			result.setUserId(rset.getInt(2));
			result.setQuizId(rset.getInt(3));
			result.setResult(rset.getFloat(4));
			result.setDate(rset.getString(5));
			result.setTimeTaken(rset.getFloat(6));
			list.add(result);			
		}
		con.close();
		return list;
		
	}
	
	public Result fetchResultGraph(User user,int rId) throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		PreparedStatement stmt=con.prepareStatement("select * from ResultMaster where userId=? and resultId=?");
		stmt.setInt(1, user.getId());
		stmt.setInt(2, rId);
		ResultSet rset=stmt.executeQuery();
		Result result=null;
		while(rset.next())
		{
			result=new Result();
			result.setResultId(rset.getInt(1));
			result.setUserId(rset.getInt(2));
			result.setQuizId(rset.getInt(3));
			result.setResult(rset.getFloat(4));
			result.setDate(rset.getString(5));
			result.setTimeTaken(rset.getFloat(6));			
		}
		con.close();
		return result;		
	}
	
	public Quiz getQuiz(int rid) throws Exception
	{
		logger.info("requesting connection ...");
		Connection con=getConnection();
		logger.info("connection obtained.");
		PreparedStatement stmt=con.prepareStatement("select * from QuizMaster where id=(select quizId from ResultMaster where resultId=?)");
		stmt.setInt(1, rid);
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
		
		return quiz;
	}
}
