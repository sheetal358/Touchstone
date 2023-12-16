package com.swinfosoft.touchstone.actions;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.ResultDao;
import com.swinfosoft.touchstone.models.Quiz;
import com.swinfosoft.touchstone.models.User;


public class ResultinGraphAction implements Action {

	@Override
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		String filePath = request.getRealPath("/Imag");
		//create the chart_folder folder if do not exists...
        File folder = new File(filePath);
        if(!folder.exists())
           {
                folder.mkdir();
           }
        String path="";
        int rid=Integer.parseInt(request.getParameter("rId"));	
        ResultDao dao=new ResultDao();
        Quiz quiz=dao.getQuiz(rid);      
        String quizName="Quiz Result of Group :  ";
        quizName+=quiz.getName();       
        String query="SELECT u.name,q.result FROM ResultMaster q,UserMaster u WHERE u.id=q.userId and q.resultId="+rid;
		JDBCCategoryDataset dataset=new JDBCCategoryDataset("jdbc:mysql://localhost:3306/touchstone",
		"com.mysql.jdbc.Driver","touchstone","1234");
		dataset.executeQuery(query);
		JFreeChart chart = ChartFactory.createBarChart3D( quizName ,"Name", "Percentage", dataset, PlotOrientation.VERTICAL,true, true, false);
		try
		{
		File f=new File(filePath+"/chart.png");
		path=filePath+"/chart.png";
		ChartUtilities.saveChartAsPNG(f, chart, 600, 400);
		}
		catch (IOException e)
		{
			System.out.println(e);
		System.out.println("Problem in creating chart.");
		}
		return null;
	}
}
