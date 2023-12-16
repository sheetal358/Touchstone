package com.swinfosoft.touchstone.actions;

import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;

public class LogoutAction implements Action {

	public String processRequest() throws Exception {
		
	HttpSession session=ActionContext.getSession();
	session.invalidate();
	
	ActionContext.addModelObject("message", 
			"you have successfully logged out.");
	return "loggedOut";
	}

}
