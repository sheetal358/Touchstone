package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.models.User;

public class UserProfileAction implements Action {

	public String processRequest() throws Exception {
		User user;
		
	HttpSession session=ActionContext.getSession();
	user=(User)session.getAttribute("user");
    JSONObject json = (JSONObject)
    		JSONSerializer.toJSON(user);
    
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
