package com.swinfosoft.touchstone.actions;

import java.util.Random;

import com.swinfosoft.touchstone.dao.GroupDao;

public class RandomAlphanumericString {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();
	String randomString( int len ) throws Exception
	{
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ )
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   GroupDao dao=new GroupDao();
	   boolean flag=dao.findCode(sb.toString());
	   if(flag==true)
	   {
		  // RandomAlphanumericString rnd=new RandomAlphanumericString();
		  
		   return randomString(5);
		   
	   }
	   else
	   return sb.toString();
	}
	
}
