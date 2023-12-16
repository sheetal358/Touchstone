package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.GroupDao;
import com.swinfosoft.touchstone.models.Group;
import com.swinfosoft.touchstone.models.User;

public class JoinGroupAction implements Action{

	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		HttpSession session=ActionContext.getSession();
		User user=(User) session.getAttribute("user");
		String code=request.getParameter("code");		
		GroupDao dao=new GroupDao();
		boolean flag=dao.joinGroup(code,user);		
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    if(flag)
	    	out.println("Group successfully joined.");
	    else
	    	out.println("Group not available.");
	    out.close();		
		return null;
	}
	
}
