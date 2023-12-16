package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.MessageDao;


public class RemoveMessageAction implements Action{

	static Logger logger=Logger.getRootLogger();
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		logger.info("Removing message from database.");
		int mId=Integer.parseInt(request.getParameter("mId"));
		MessageDao dao=new MessageDao();
		dao.deleteMessageById(mId);
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    out.println("Notification removed successfully.");
	    out.close();		
		return null;
	}
	
}
