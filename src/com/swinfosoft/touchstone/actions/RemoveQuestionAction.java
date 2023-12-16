package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuestionDao;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.models.Question;

public class RemoveQuestionAction implements Action{

	static Logger logger=Logger.getLogger("RemoveQuestionAction");
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		logger.info("Removing question from database.");
		int qId=Integer.parseInt(request.getParameter("qId"));
		QuestionDao dao=new QuestionDao();
		boolean flag=dao.remove(qId);
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    if(flag)
	    out.println("Question removed successfully.");
	    else
	    	out.println("Question already removed.");
	    out.close();
		
		return null;
	}
	
}
