package com.swinfosoft.touchstone.actions;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.GroupDao;
import com.swinfosoft.touchstone.dao.MessageDao;
import com.swinfosoft.touchstone.models.Group;
import com.swinfosoft.touchstone.models.Message;
import com.swinfosoft.touchstone.models.Notification;
import com.swinfosoft.touchstone.models.User;

public class FetchAllMessageAction implements Action{

	@Override
	public String processRequest() throws Exception {
		HttpSession session=ActionContext.getSession();
		User user=(User) session.getAttribute("user");
		GroupDao dao=new GroupDao();
		List<Group> group=dao.getAllActiveGroups(user);
		MessageDao mdao=new MessageDao();
		List<Message> list=mdao.fetchMessages(group,user);
		session.setAttribute("msgs",list);
		return null;
	}
}
