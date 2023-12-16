package com.swinfosoft.touchstone.actions;

import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuestionDao;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.models.Question;
import com.swinfosoft.touchstone.models.Quiz;

public class QuizLoaderAction implements Action{

	public String processRequest() throws Exception {
		System.out.println("QuizLoaderAction is invoked.");
		HttpSession session=ActionContext.getSession();
		ServletRequest request=
				ActionContext.getServletRequest();
		int id=Integer.parseInt(request.getParameter("id"));
		Quiz quiz=(Quiz)session.getAttribute("quiz");
		Set<Question> qset=null;
		QuestionDao qdao=new QuestionDao();
		
	/*	if(quiz!=null && quiz.getId() ==id)
		{
		//qset=(Set<Question>)session.getAttribute("qset");
		//if(qset==null)
		//{
			 qset=qdao.getAllQuestionsOfQuiz(quiz.getId());
			 System.out.println("in quiz set");
				session.setAttribute("qset",qset);
		//}
		}
		else 
		{*/
			System.out.println("in question set");
			QuizDao dao=new QuizDao();
			quiz= dao.loadById(id);
			session.setAttribute("quiz", quiz);
			 qset=qdao.getAllQuestionsOfQuiz(quiz.getId());
			session.setAttribute("qset",qset);
			
		//}
		System.out.println("quiz is saved in request scope.");
		System.out.println("Questions set in session scope.");
		return null;
	}

}
