package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;
import com.swinfosoft.mvc.web.*;
import com.swinfosoft.touchstone.dao.QuizDao;


public class RemoveQuizAction implements Action{

	static Logger logger=Logger.getLogger("RemoveQuizAction");
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		logger.info("Removing Quiz from database.");
		int qId=Integer.parseInt(request.getParameter("qId"));
		QuizDao dao=new QuizDao();
		dao.deleteQuizById(qId);
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    out.println("Quiz removed successfully.");
	    out.close();
		
		return null;
	}
	
}
