package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.swinfosoft.mvc.web.*;
import com.swinfosoft.touchstone.dao.MessageDao;
import com.swinfosoft.touchstone.dao.QuizDao;


public class RemoveNotificationAction implements Action{

	static Logger logger=Logger.getLogger("RemoveNotificationAction");
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		logger.info("Removing notification from database.");
		int nId=Integer.parseInt(request.getParameter("nId"));
		MessageDao dao=new MessageDao();
		dao.deleteNotificationById(nId);
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    out.println("Notification removed successfully.");
	    out.close();		
		return null;
	}
	
}
