package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.models.Quiz;
import com.swinfosoft.touchstone.models.User;

public class NewQuizAction implements Action{

	public String processRequest() throws Exception {
		ServletRequest request=
				ActionContext.getServletRequest();
		Quiz quiz=new Quiz();
		quiz.setName(request.getParameter("name"));
		quiz.setDuration(Integer.parseInt(
				request.getParameter("duration")));
		Date d=new Date();
		DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
		String dt=df.format(d);
		//System.out.println("Date is "+dt);
		quiz.setCreatedOn(dt);
		quiz.setStatus(Quiz.UNASSIGNED);
		
		HttpSession session=ActionContext.getSession();
		User user=(User)session.getAttribute("user");
		
		quiz.setOwnerId(user.getId());
		
		QuizDao dao=new QuizDao();
		dao.save(quiz);
		session.setAttribute("quiz", quiz);
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    out.println("saved");
	    out.close();
		
		return null;
	}
	
}
