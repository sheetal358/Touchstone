package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.GroupDao;
import com.swinfosoft.touchstone.dao.MessageDao;
import com.swinfosoft.touchstone.models.Message;
import com.swinfosoft.touchstone.models.Notification;
import com.swinfosoft.touchstone.models.User;

public class AddMessageAction implements Action{

	@Override
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		HttpSession session=ActionContext.getSession();
		User user=(User) session.getAttribute("user");
		Message msg=new Message();
		msg.setMessage(request.getParameter("msg"));
		msg.setUserName(user.getName());
		Date d=new Date();
		DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
		String dt=df.format(d);
		msg.setCreatedOn(dt);
		String[] str=request.getParameterValues("check");
		MessageDao dao=new MessageDao();
		dao.saveMessage(msg,str);
		ServletResponse response=ActionContext.getServletResponse();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("Message Saved.");
		return null;
	}

}
