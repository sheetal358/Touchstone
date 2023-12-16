package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;

public class QuizToGroupAction implements Action{

	@Override
	public String processRequest() throws Exception {
		// TODO Auto-generated method stub
		ServletRequest request=ActionContext.getServletRequest();
		int groupId=Integer.parseInt(request.getParameter("gId"));
		int quizId=Integer.parseInt(request.getParameter("qId"));
		QuizDao dao=new QuizDao();
		boolean flag=dao.assignQuizToGroup(groupId, quizId);
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    if(flag)
	    	out.println("Quiz assigned successfully.");
	    else
	    	out.println("Quiz already assigned.");
	    out.close();
		return null;
	}

}
