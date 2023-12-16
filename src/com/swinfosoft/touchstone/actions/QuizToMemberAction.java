package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;

public class QuizToMemberAction implements Action{

	@Override
	public String processRequest() throws Exception {
		// TODO Auto-generated method stub
		ServletRequest request=ActionContext.getServletRequest();
		int mId=Integer.parseInt(request.getParameter("mId"));
		int quizId=Integer.parseInt(request.getParameter("qId"));
		QuizDao dao=new QuizDao();
		boolean flag=dao.assignQuizToMember(mId, quizId);
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
