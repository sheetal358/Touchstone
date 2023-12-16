package com.swinfosoft.touchstone.actions;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.ResultDao;
import com.swinfosoft.touchstone.models.Result;
import com.swinfosoft.touchstone.models.User;

public class ShowResultAction implements Action{

	@Override
	public String processRequest() throws Exception {
		HttpSession session=ActionContext.getSession();
		User user=(User) session.getAttribute("user"); 
		ResultDao dao=new ResultDao();
		List<Result> list=dao.fetchResult(user);
		session.setAttribute("rList", list);		
		return null;
	}

}
