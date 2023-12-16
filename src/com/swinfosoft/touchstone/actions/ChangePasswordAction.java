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

public class ChangePasswordAction implements Action {

	public String processRequest() throws Exception {
	Logger logger=Logger.getRootLogger();	
	ServletRequest request=
			ActionContext.getServletRequest();
	int id=Integer.parseInt(request.getParameter("id"));
	String p=request.getParameter("password");
	logger.info("changing password");
	UserDao dao=new UserDao();
	dao.changePassword(id,p);
	logger.info("changed, sending message...");
	ServletResponse response=
    		ActionContext.getServletResponse();
    response.setContentType("text/html");
    PrintWriter out=response.getWriter();
    out.println("successfully changed.");
    out.close();
	return null;
	}

}
