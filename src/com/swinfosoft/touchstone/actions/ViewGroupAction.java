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

public class ViewGroupAction implements Action{

	public String processRequest() throws Exception {
		HttpSession session=ActionContext.getSession();
		User user=(User)session.getAttribute("user");
		GroupDao dao=new GroupDao();
		List<Group> list= dao.getAllGroups(user.getId());
		 JSONArray json = (JSONArray)
				 JSONSerializer.toJSON(list);
			System.out.println(json+" is being sent as response...");
		    ServletResponse response=
		    		ActionContext.getServletResponse();
		    response.setContentType("text/JSON");
		    PrintWriter out=response.getWriter();
		    out.println(json);
		    out.close();
		return null;
	}

}
