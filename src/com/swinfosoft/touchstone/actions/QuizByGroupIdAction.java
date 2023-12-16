package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.models.Quiz;

public class QuizByGroupIdAction implements Action {

	public String processRequest() throws Exception {
		System.out.println("QuizByGroupIdAction is invoked.");
		ServletRequest request = ActionContext.getServletRequest();
		int gid = Integer.parseInt(request.getParameter("gId"));
		QuizDao dao = new QuizDao();
		List<Quiz> list = (List<Quiz>) dao.getQuizByGroupId(gid);
		JSONArray json = (JSONArray) JSONSerializer.toJSON(list);
		System.out.println(json + " is being sent as response...");
		ServletResponse response = ActionContext.getServletResponse();
		response.setContentType("text/JSON");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.close();
		return null;
	}

}
