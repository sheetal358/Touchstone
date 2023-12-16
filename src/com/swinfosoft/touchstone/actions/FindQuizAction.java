package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.dao.UserDao;
import com.swinfosoft.touchstone.models.User;

public class FindQuizAction implements Action {

	
	@Override
	public String processRequest() throws Exception {
	Logger logger=Logger.getRootLogger();
	ServletRequest request=ActionContext.getServletRequest();
	String name=request.getParameter("name");
	QuizDao dao=new QuizDao();
	boolean flag=dao.findByName(name);
	  ServletResponse response=
	    		ActionContext.getServletResponse();
	PrintWriter out=response.getWriter();
	response.setContentType("text/html");
	if(flag==true)
	{
		logger.debug("Quiz found.");		
		out.print("Quiz name not Available.");
	}
	else
	{
		logger.debug("no user found.");	
		out.print("Is available.");
	}
    out.close();
	return null;
	}

}
