package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;






import org.json.JSONObject;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuestionDao;
import com.swinfosoft.touchstone.dao.ResultDao;
import com.swinfosoft.touchstone.models.Question;
import com.swinfosoft.touchstone.models.QuizResult;
import com.swinfosoft.touchstone.models.Result;
import com.swinfosoft.touchstone.models.User;

public class SaveQuizAction implements Action{

	@Override
	public String processRequest() throws Exception {

		ServletRequest request=ActionContext.getServletRequest();
		HttpSession session=ActionContext.getSession();
		User user=(User) session.getAttribute("user");
		int quizId=Integer.parseInt(request.getParameter("id"));
		//List<Question>list=request.getParameter("questions");
		double timeTaken=Integer.parseInt(request.getParameter("timeTaken"));
		int totalQuestion=Integer.parseInt(request.getParameter("totalQuestion"));
		int duration=Integer.parseInt(request.getParameter("duration"));
		timeTaken=(duration*60)-timeTaken;
		ResultDao dao=new ResultDao();
		QuestionDao qDao=new QuestionDao();
		Result result=new Result();
		Set<QuizResult> qResults=new HashSet<QuizResult>();
		int totalMarks=0;
		int obtainedMarks=0;
		for (int i = 1; i <= totalQuestion; i++) {
			String strQuestion=request.getParameter("question"+i);
			JSONObject jsonObj = new JSONObject(strQuestion);
			QuizResult qResult= new QuizResult();		
			qResult.setQuestionId(jsonObj.getInt("questionId"));
			qResult.setAnswer(jsonObj.getInt("answer"));
			qResult.setTimeTaken(jsonObj.getInt("timeTaken"));
			qResults.add(qResult);
			
			Question ques=qDao.getById(qResult.getQuestionId());
			int ans=ques.getAns();
			int dLevel=ques.getDifficultyLevel();
			totalMarks+=dLevel;			
			if(qResult.getAnswer()==ans)
			{
				obtainedMarks+=dLevel;				
			}
		}
		float resultinpercent=0;
		if(obtainedMarks!=0)
			resultinpercent=( obtainedMarks* 100)/totalMarks;	
			
		
		Date d=new Date();
		DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
		String dt=df.format(d);
		System.out.println(dt);
		result.setUserId(user.getId());
		result.setQuizId(quizId);
		result.setDate(dt);
		result.setResult(resultinpercent);
		result.setTimeTaken((float)timeTaken);
		result.setQuizResults(qResults);
		dao.save(result);
		
		ServletResponse response=ActionContext.getServletResponse();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("Quiz completed successfully.");
		out.close();
		return null;
	}

}
