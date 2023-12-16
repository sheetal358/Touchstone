package com.swinfosoft.touchstone.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.dao.UserDao;
import com.swinfosoft.touchstone.models.Quiz;
import com.swinfosoft.touchstone.models.User;

public class AssignedQuizAction implements Action {	
	public String processRequest() throws Exception {
	System.out.println("AssignedQuizAction is invoked.");
	HttpSession session=ActionContext.getSession();
	User user=(User) session.getAttribute("user");
	QuizDao dao=new QuizDao();
	List<Quiz> list=(List<Quiz>) dao.getAllQuiz(user.getId());
	List<Quiz> list2=new ArrayList<Quiz>();
	Iterator<Quiz> itr=list.iterator();
	while(itr.hasNext())
	{
		Quiz quiz=new Quiz();
		quiz=itr.next();
		if(quiz.getStatus()==Quiz.ASSIGNED)
			list2.add(quiz);
	}
	System.out.println(list2.size());	
	session.setAttribute("quizs", list2);	
	System.out.println("quiz list is saved in request scope.");
	return null;
	}

}
