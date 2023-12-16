package com.swinfosoft.touchstone.actions;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuestionDao;
import com.swinfosoft.touchstone.models.Question;

public class UpdateQuestionAction implements Action{

	static Logger logger=Logger.getLogger("AddQuestionAction");
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		Question question=new Question();
		logger.info("Saving question...");
		int qType=Integer.parseInt(request.getParameter("qType"));
		if(qType==Question.TF)
		{
			question.setDescription(request.getParameter("questionTF"));
			question.setAns(Integer.parseInt(request.getParameter("answer1")));
			
		}
		else if(qType==Question.MC)
		{
			
			question.setDescription(request.getParameter("questionMC"));
			question.setAns(Integer.parseInt(request.getParameter("answer2")));
			int count=Integer.parseInt(request.getParameter("optCount"));
			logger.info(count+" options are in question.");
			String op[]=new String[count];
			for(int i=1;i<=count;i++)
			{
				String pname="option"+(i);
				op[i-1]=request.getParameter(pname);
				logger.info("value of "+pname+" is: "+op[i-1]);
			}
		
			
			question.setOptions(op);
			
		}
		question.setId(Integer.parseInt(request.getParameter("id")));
		question.setType(qType);
		question.setDifficultyLevel(Integer.parseInt(request.getParameter("dLevel")));
		QuestionDao qdao=new QuestionDao();
		
		qdao.update(question);
		logger.info("question saved.");
		ServletResponse response=
	    		ActionContext.getServletResponse();
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    out.println("Question updated successfully.");
	    out.close();
		
		return null;
	}
	
}
