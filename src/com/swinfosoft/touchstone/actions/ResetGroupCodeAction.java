package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.GroupDao;

public class ResetGroupCodeAction implements Action{

	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		int id=Integer.parseInt(request.getParameter("id"));
		RandomAlphanumericString rnd=new RandomAlphanumericString();
		String code=rnd.randomString(5);
		GroupDao dao=new GroupDao();
		dao.changeCode(id, code);
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    out.println(code);
	    out.close();
		return null;
	}

}
