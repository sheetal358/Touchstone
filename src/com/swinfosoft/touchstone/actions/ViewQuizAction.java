package com.swinfosoft.touchstone.actions;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.models.Quiz;
import com.swinfosoft.touchstone.models.User;

public class ViewQuizAction implements Action{

	public String processRequest() throws Exception {
		System.out.println("ViewQuizAction is invoked.");
		HttpSession session=ActionContext.getSession();
		User user=(User)session.getAttribute("user");
		QuizDao dao=new QuizDao();
		List<Quiz> list= dao.getAllQuiz(user.getId());
		ServletRequest request=
				ActionContext.getServletRequest();
		request.setAttribute("qlist", list);
		System.out.println("quizzes are saved in request scope.");
		return null;
	}

}
