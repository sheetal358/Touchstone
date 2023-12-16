package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;

public class UnassignQuizAction implements Action{

	@Override
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		int id=Integer.parseInt(request.getParameter("qId"));
		QuizDao dao=new QuizDao();
		dao.deleteQuizFromQuizGroup(id);
		ServletResponse response=ActionContext.getServletResponse();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("Quiz Unassigned.");
		return null;
	}

}
