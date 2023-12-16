package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.models.Quiz;

public class UpdateQuizAction implements Action{

	static Logger logger=Logger.getLogger("AddQuestionAction");
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		Quiz quiz=new Quiz();
		logger.info("Updating quiz...");
		quiz.setId(Integer.parseInt(request.getParameter("quizId")));
		quiz.setName(request.getParameter("name"));
		quiz.setDuration(Integer.parseInt(request.getParameter("duration")));
		QuizDao dao=new QuizDao();
		
		dao.update(quiz);
		logger.info("quiz updated.");
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    out.println("Quiz updated successfully.");
	    out.close();
		
		return null;
	}
	
}
