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
import com.swinfosoft.touchstone.models.Question;

public class EditQuestionAction implements Action{

	static Logger logger=Logger.getLogger("AddQuestionAction");
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		HttpSession session=ActionContext.getSession();
		Question question=new Question();
		QuestionDao dao=new QuestionDao();
		logger.info("Editing question from session.");
		int qId=Integer.parseInt(request.getParameter("qId"));
		question=dao.getById(qId);
		session.setAttribute("question", question);
		
		return null;
	}
	
}
