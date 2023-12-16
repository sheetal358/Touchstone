package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.GroupDao;
import com.swinfosoft.touchstone.models.Group;
import com.swinfosoft.touchstone.models.User;

public class ShowGroupAction implements Action{

	public String processRequest() throws Exception {
		HttpSession session=ActionContext.getSession();
		User user=(User)session.getAttribute("user");
		GroupDao dao=new GroupDao();
		List<Group> list= dao.getAllActiveGroups(user);
		 session.setAttribute("gList", list);
		return null;
	}

}
