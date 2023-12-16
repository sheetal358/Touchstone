package com.swinfosoft.touchstone.actions;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.MessageDao;
import com.swinfosoft.touchstone.models.Notification;

public class FetchAllNotificationAction implements Action{

	@Override
	public String processRequest() throws Exception {
		MessageDao dao=new MessageDao();
		List<Notification> list=dao.fetchNotifications();
		HttpSession session=ActionContext.getSession();
		session.setAttribute("notes",list);
		return null;
	}
}
