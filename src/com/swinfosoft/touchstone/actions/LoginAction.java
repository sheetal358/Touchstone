package com.swinfosoft.touchstone.actions;

import javax.servlet.*;
import javax.servlet.http.*;

import com.swinfosoft.mvc.web.*;
import com.swinfosoft.touchstone.dao.*;
import com.swinfosoft.touchstone.models.*;

public class LoginAction implements Action {

	public String processRequest() throws Exception {
		User user=new User();
		ServletRequest request=
				ActionContext.getServletRequest();
	user.setMailId(request.getParameter("mailId"));
	user.setPassword(request.getParameter("password"));
	
	UserDao dao=new UserDao();
	if (dao.find(user))
	{
	HttpSession session=ActionContext.getSession();
	session.setAttribute("user",user);
	return "loggedIn";
	}
	else
	{
		ActionContext.addModelObject("message", 
				"Either mailId or password is incorrect!");
		return "loggedOut";
	}
				
	}

}
