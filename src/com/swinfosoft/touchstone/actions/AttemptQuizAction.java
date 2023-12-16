package com.swinfosoft.touchstone.actions;

import java.io.Console;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuestionDao;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.models.Question;
import com.swinfosoft.touchstone.models.Quiz;

public class AttemptQuizAction implements Action {

	@Override
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		int id=Integer.parseInt(request.getParameter("qId"));
		QuizDao qDao=new QuizDao();
		QuestionDao dao=new QuestionDao();
		Quiz quiz=qDao.loadById(id);
		Set<Question> questions=dao.getAllQuestionsOfQuiz(id);
		 JSONObject jsonquiz = (JSONObject)JSONSerializer.toJSON(quiz);
		 JSONArray jsonquestion = (JSONArray)JSONSerializer.toJSON(questions);
			/*int i=0;
		 for (Question quest : questions) {
			String[] opt=quest.getOptions();
			Set<String> optSet=new HashSet<String>();
			for (String strOpt : opt) {
				if(strOpt!= null)
					optSet.add(strOpt);
			}
			JSONArray jsonOpt=(JSONArray)JSONSerializer.toJSON(optSet);
		System.out.println(jsonOpt.toString());
			jsonquestion.element(i).element("options").set(i, jsonOpt);
			i+=1;
		}*/
		    /*ServletResponse response=
		    		ActionContext.getServletResponse();
		    response.setContentType("text/JSON");
		    PrintWriter out=response.getWriter();
		    out.println(json);
		    out.close();
		*/
		
		HttpSession session=ActionContext.getSession();
		session.setAttribute("quiz", jsonquiz.toString());
		session.setAttribute("questions", jsonquestion.toString());
		return null;
	}

}
