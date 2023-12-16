package com.swinfosoft.touchstone.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.models.Quiz;
import com.swinfosoft.touchstone.models.User;

public class ShowQuizAction implements Action{

	public String processRequest() throws Exception {
		HttpSession session=ActionContext.getSession();
		User user=(User)session.getAttribute("user");
		QuizDao dao=new QuizDao();
		List<Quiz> list= dao.getAllAssignQuiz(user);
		List<Quiz> uniques = new ArrayList<Quiz>();
		for (Quiz element : list) {
			if(uniques.size()==0)
				uniques.add(element);
		
			for (Quiz quiz : uniques) {
				if(element.getId()!=quiz.getId())
				{
					uniques.add(element);
					break;
				}else{break;}
			}
		}
		 session.setAttribute("qList", uniques);
		return null;
	}

}
