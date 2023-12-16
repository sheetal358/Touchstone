package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.GroupDao;

public class ResetStatusAction implements Action {

	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		int id=Integer.parseInt(request.getParameter("id"));
		int status=Integer.parseInt(request.getParameter("status"));
		GroupDao dao=new GroupDao();
		dao.changeStatus(id, status);
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    out.println("Status changed successfully.");
	    out.close();
		return null;
	}

}
