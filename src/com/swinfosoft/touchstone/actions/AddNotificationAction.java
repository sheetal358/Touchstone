package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.MessageDao;
import com.swinfosoft.touchstone.models.Notification;
import com.swinfosoft.touchstone.models.User;

public class AddNotificationAction implements Action{

	@Override
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		HttpSession session=ActionContext.getSession();
		User user=(User) session.getAttribute("user");
		Notification note=new Notification();
		note.setNotification(request.getParameter("note"));
		note.setUserName(user.getName());
		Date d=new Date();
		DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
		String dt=df.format(d);
		note.setCreatedOn(dt);
		MessageDao dao=new MessageDao();
		dao.saveNotification(note);
		ServletResponse response=ActionContext.getServletResponse();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("Notification Saved.");
		return null;
	}

}
