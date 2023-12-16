package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.GroupDao;
import com.swinfosoft.touchstone.dao.UserDao;
import com.swinfosoft.touchstone.models.User;
public class FindGroupNameAction implements Action
{
	public String processRequest() throws Exception 
	{
		System.out.println("validation is executed ..................");
		ServletRequest request=ActionContext.getServletRequest();
		String name=request.getParameter("name");
		GroupDao dao=new GroupDao();
		boolean flag=dao.findName(name);
		String msg="";
		if(flag)
			msg="*Name already exist.";
		else
			msg="*Valid name.";
		ServletResponse response=ActionContext.getServletResponse();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println(msg);
		out.close();
		return null;
	}

	
	
}
