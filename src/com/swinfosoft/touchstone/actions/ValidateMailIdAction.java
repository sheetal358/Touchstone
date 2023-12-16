package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.UserDao;
import com.swinfosoft.touchstone.models.User;
public class ValidateMailIdAction implements Action
{
	public String processRequest() throws Exception 
	{
		System.out.println("validation is executed ..................");
		ServletRequest request=ActionContext.getServletRequest();
		String mailId=request.getParameter("mailId");
		UserDao dao=new UserDao();
		User user=dao.findByMailId(mailId);
		String msg="";
		if(mailId.equals(user.getMailId()))
			msg="Already registered......";
		else
			msg="Is Available";
		ServletResponse response=ActionContext.getServletResponse();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println(msg);
		out.close();
		return null;
	}

	
	
}
