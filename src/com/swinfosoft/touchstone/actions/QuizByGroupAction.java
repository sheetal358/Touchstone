package com.swinfosoft.touchstone.actions;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.GroupDao;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.models.Group;
import com.swinfosoft.touchstone.models.Quiz;
import com.swinfosoft.touchstone.models.User;

public class QuizByGroupAction implements Action {

	
	public String processRequest() throws Exception {
	System.out.println("QuizByGroupAction is invoked.");
	HttpSession session=ActionContext.getSession();
	User user=(User) session.getAttribute("user");
	QuizDao dao=new QuizDao();
	List<Quiz> list=(List<Quiz>) dao.getAllQuiz(user.getId());
	GroupDao dao2=new GroupDao();
	List<Group> list1=dao2.getAllGroups(user.getId());
	session.setAttribute("qlist", list);
	session.setAttribute("glist", list1);
	System.out.println("quiz list is saved in request scope.");
	return null;
	}

}
