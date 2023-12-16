package com.swinfosoft.touchstone.actions;

import javax.servlet.*;
import javax.servlet.http.*;
import com.swinfosoft.mvc.web.*;
import com.swinfosoft.touchstone.dao.*;
import com.swinfosoft.touchstone.models.*;

public class RegisterAction implements Action {

	public String processRequest() throws Exception {
		User user=new User();
		ServletRequest request=
				ActionContext.getServletRequest();
	user.setName(request.getParameter("name"));
	user.setMailId(request.getParameter("mailId"));
	user.setPassword(request.getParameter("password"));
	user.setQuestion(request.getParameter("question"));
	user.setAns(request.getParameter("answer"));
	user.setRole(Integer.parseInt(request.getParameter("type")));
	if(user.getRole()==User.assessee)
		user.setGroupCode(request.getParameter("group"));		
	UserDao dao=new UserDao();
	dao.save(user);
	
	HttpSession session=ActionContext.getSession();
	session.setAttribute("user",user);
				return "loggedIn";
	}

}
