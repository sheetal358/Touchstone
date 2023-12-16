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

public class UpdateProfileAction implements Action {

	public String processRequest() throws Exception {
	Logger logger=Logger.getRootLogger();	
	HttpSession session=ActionContext.getSession();
	User user=(User)session.getAttribute("user");
	ServletRequest request=
			ActionContext.getServletRequest();
	//int id=Integer.parseInt(request.getParameter("id"));
	user.setName(request.getParameter("name"));
	user.setMailId(request.getParameter("mailId"));
	user.setPassword(request.getParameter("password"));
	logger.info("updating user");
	UserDao dao=new UserDao();
	dao.update(user);
	logger.info("updated, sending response...");
	 JSONObject json = (JSONObject)
	    		JSONSerializer.toJSON(user);
	    
		System.out.println(json+" is being sent as response...");
	    ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/JSON");
	    PrintWriter out=response.getWriter();
	    out.println(json);
	    out.close();
		return null;
		}

}
