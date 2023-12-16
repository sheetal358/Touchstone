package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.GroupDao;
import com.swinfosoft.touchstone.models.Group;
import com.swinfosoft.touchstone.models.User;

public class NewGroupAction implements Action{

	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		RandomAlphanumericString rd=new RandomAlphanumericString();
		String code=rd.randomString(5);
		User user=(User) ActionContext.getSession().getAttribute("user");
		Group group=new Group();
		group.setName(request.getParameter("name"));
		group.setDescription(request.getParameter("description"));
		group.setMembers(Integer.toString(user.getId()));
		group.setCode(code);
		group.setStatus(Group.locked);		
		GroupDao dao=new GroupDao();
		dao.save(group);		
		ServletResponse response=ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    out.println("successfully New Group created, Group-Code is: "+code);
	    out.close();
		
		return null;
	}
	
}
