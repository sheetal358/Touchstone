package com.swinfosoft.touchstone.actions;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.QuizDao;
import com.swinfosoft.touchstone.dao.ResultDao;
import com.swinfosoft.touchstone.dao.UserDao;
import com.swinfosoft.touchstone.models.Quiz;
import com.swinfosoft.touchstone.models.Result;
import com.swinfosoft.touchstone.models.User;


public class ResultinGraphForAllUserAction implements Action {

	@Override
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		HttpSession session=ActionContext.getSession();
		UserDao udao=new UserDao();
		List<User> user=udao.getAllUsers();
		String filePath = request.getRealPath("/Image");
		//create the chart_folder folder if do not exists...
        File folder = new File(filePath);
        if(!folder.exists())
           {
                folder.mkdir();
           }
        String path="";	
        String quizName="Result for All User.";
		JFreeChart chart = ChartFactory.createBarChart3D( quizName ,"Name","Percentage", createDataset(user), PlotOrientation.VERTICAL,true, true, false);
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
 
	   private CategoryDataset createDataset(List<User> users) throws Exception {
		   final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		   for (User u : users) {
			   ResultDao dao=new ResultDao();
			   List<Result> list=dao.fetchResult(u);
			   for (Result result : list) {
				   QuizDao qdao=new QuizDao();
				   Quiz quiz=qdao.loadById(result.getQuizId());
					dataset.addValue(result.getResult(), quiz.getName(), u.getName());
				} 
		   }		       
	        return dataset;	        
	   }
}
