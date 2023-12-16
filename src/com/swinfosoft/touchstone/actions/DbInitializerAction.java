package com.swinfosoft.touchstone.actions;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.swinfosoft.mvc.web.Action;
import com.swinfosoft.mvc.web.ActionContext;
import com.swinfosoft.touchstone.dao.InitializerDao;

public class DbInitializerAction implements Action {

	public String processRequest() throws Exception {
	Logger logger=Logger.getRootLogger();	
	ServletContext ctx=
			ActionContext.getServletContext();
	logger.info("finding path of db.sql");
	String path=ctx.getRealPath("/WEB-INF/classes/db.sql");
	logger.info("path of db.sql on server is : "+path);
	File f=new File(path);
	FileInputStream fin=new FileInputStream(f);
	byte data[]=new byte[fin.available()];
	fin.read(data);
	fin.close();
	String queries=new String(data);
	InitializerDao dao=new InitializerDao();
	dao.initializeDb(queries, "/");
	ActionContext.addModelObject("message",
			"successfully initialized.");
	return "dbInitialized";
	}

}
