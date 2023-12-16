package com.swinfosoft.touchstone.actions;

import java.io.File;
import java.io.IOException;
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
import com.swinfosoft.touchstone.models.Quiz;
import com.swinfosoft.touchstone.models.Result;
import com.swinfosoft.touchstone.models.User;


public class ResultinGraphForAllQuizAction implements Action {

	@Override
	public String processRequest() throws Exception {
		ServletRequest request=ActionContext.getServletRequest();
		HttpSession session=ActionContext.getSession();
		User user=(User) session.getAttribute("user");
		String filePath = request.getRealPath("/Imag");
		//create the chart_folder folder if do not exists...
        File folder = new File(filePath);
        if(!folder.exists())
           {
                folder.mkdir();
           }
        String path="";	
        ResultDao dao=new ResultDao();
        String quizName="Result for All Quiz's ";
        List<Result> list=dao.fetchResult(user);
		JFreeChart chart = ChartFactory.createBarChart3D( quizName ,"Name","Percentage", createDataset(list,user), PlotOrientation.VERTICAL,true, true, false);
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
 
	   private CategoryDataset createDataset(List<Result> list,User user) throws Exception {
		   final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		   for (Result result : list) {
			   QuizDao dao=new QuizDao();
			   Quiz quiz=dao.loadById(result.getQuizId());
				dataset.addValue(result.getResult(), quiz.getName(), user.getName());
			}     
	        return dataset;
	        
	   }
}
