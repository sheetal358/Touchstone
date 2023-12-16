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
import com.swinfosoft.touchstone.dao.UserDao;
import com.swinfosoft.touchstone.models.User;

public class FetchUserDetailsAction implements Action {

	
	public String processRequest() throws Exception {
	Logger logger=Logger.getRootLogger();
	ServletRequest request=ActionContext.getServletRequest();
	String mailId=request.getParameter("mailId");
	logger.debug("fetching user by following mailId: "+mailId);
	UserDao dao=new UserDao();
	User user=dao.findByMailId(mailId);
	  ServletResponse response=
	    		ActionContext.getServletResponse();
	PrintWriter out=response.getWriter();
	if(user != null)
	{
	logger.debug("user found.");	
	response.setContentType("text/JSON");
	JSONObject json = (JSONObject)
    		JSONSerializer.toJSON(user);
    
	System.out.println(json+" is being sent as response...");
    out.println(json);
	}
	else
	{
		logger.debug("no user found.");	
		response.setContentType("text/html");
		out.print("Invalid MailId");
	}
    out.close();
	return null;
	}

}
