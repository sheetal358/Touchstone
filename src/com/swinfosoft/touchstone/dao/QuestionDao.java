package com.swinfosoft.touchstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

//import com.sun.xml.fastinfoset.util.StringArray;
//import com.sun.xml.internal.fastinfoset.util.StringArray;
import com.swinfosoft.touchstone.models.Question;



public class QuestionDao extends AbstractDao  {

	Logger logger=Logger.getLogger(QuestionDao.class);
	
	public int save(Question q) 
	throws Exception {
	Connection con=getConnection();
		PreparedStatement stmt=
			con.prepareStatement(
				"insert into QuestionMaster values(?,?,?,?,?,?,?)");
		int id=1;
		ResultSet rset=stmt.executeQuery(
				"select max(id) from QuestionMaster");
		if(rset.next())
		{
			int i=rset.getInt(1);
			i++;
			id=i;
		}
		q.setId(id);
		stmt.setInt(1, id);
		stmt.setString(2,q.getDescription());
		stmt.setInt(3, q.getType());
		stmt.setInt(4, q.getDifficultyLevel());
		stmt.setInt(5,q.getAns());
		if(q.getType()==Question.MC)
		stmt.setString(6, options(q.getOptions()));
		else
		stmt.setString(6,null);	
		stmt.setInt(7, q.getQuizId());
		stmt.executeUpdate();
		con.close();
		return q.getId();
	}

	public Question getById(int id) 
	throws Exception {
		logger.debug("obtaining connection...");
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
		"select * from QuestionMaster  where id=?");
		stmt.setInt(1,id);
		logger.debug("Executing query...");
		ResultSet rset=stmt.executeQuery();
		
		logger.debug("Query completed.");
		Question q=null;
		if(rset.next())
		{
			logger.debug("question found");
			q=new Question();
			q.setId(rset.getInt(1));
			q.setDescription(rset.getString(2));
			q.setType(rset.getInt(3));
			q.setDifficultyLevel(rset.getInt(4));
			q.setAns(rset.getInt(5));
			String op=rset.getString(6);
			q.setOptions(parseOptions(op));
			q.setQuizId(rset.getInt(7));
			
		}
		con.close();
		return q;
	}

	private String[] parseOptions(String op)
	{
		if(op==null)
			return null;
		StringTokenizer str=new StringTokenizer(op,"||");
		//StringArray strArr=new StringArray();
		 List<String> strArr = new ArrayList<>();
		while(str.hasMoreTokens())
		{
			strArr.add(str.nextToken());
		}
		// Convert the list of tokens to a string array
	    String[] resultArray = new String[strArr.size()];
	    resultArray = strArr.toArray(resultArray);
		return resultArray;
	    //return strArr.getArray();
	}
	
	public Set<Question> getAllQuestionsOfQuiz(int quizId)
	throws Exception {
		
		logger.debug("obaining connection...");
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement(
		"select * from QuestionMaster where quizId=?");
		stmt.setInt(1, quizId);
		logger.debug("Executing query...");
		ResultSet rset=stmt.executeQuery();
		logger.debug("Query completed.");
		Set<Question> set=new HashSet<Question>();
		while(rset.next())
		{			
			Question q=new Question();
			//q=new Question();
			q.setId(rset.getInt(1));
			q.setDescription(rset.getString(2));
			q.setType(rset.getInt(3));
			q.setDifficultyLevel(rset.getInt(4));
			q.setAns(rset.getInt(5));
			String op=rset.getString(6);
			q.setOptions(parseOptions(op));
			q.setQuizId(rset.getInt(7));
			set.add(q);
		}
		con.close();
		return set;
	}

	

	
	
	
	
	public boolean remove(int id)
	throws Exception {
		boolean flag=false;
		Connection con=getConnection();
		PreparedStatement stmt=con.prepareStatement("delete from QuestionMaster where id=?");
		stmt.setInt(1, id);
		stmt.executeUpdate();
		con.close();
		flag=true;
		return flag;
	}

	
	
	private String options(String[] op)
	{
		StringBuilder b=new StringBuilder();
		for(int i=0;i<op.length;i++)
		{
			if(op[i]!=null)
			{
				if(!b.toString().equals(""))
					b.append("||");	
			b.append(op[i]);
			}
		}
		String str=b.toString();
		logger.info("options are: "+str);
		return str;
	}
	
	public void update(Question q)throws Exception {
		logger.info("updating...");
		Connection con=getConnection();
		PreparedStatement stmt=
			con.prepareStatement("update QuestionMaster set description=?,type=?,difficultyLevel=?,ans=?,options=? where id=?");
		
		
		stmt.setString(1,q.getDescription());
		
		stmt.setInt(2, q.getType());
		stmt.setInt(3, q.getDifficultyLevel());
			stmt.setInt(4,q.getAns());
			if(q.getType()==Question.MC)
				stmt.setString(5, options(q.getOptions()));
				else
				stmt.setString(5,null);	
			
			
			
			stmt.setInt(6, q.getId());
			
			try{
			stmt.executeUpdate();
			}catch(Exception ex)
			{
				System.out.println(ex);
			}
			logger.info("updated.");
		con.close();
	}
	
	
	


}
