package com.terry.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitServlet implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext context = arg0.getServletContext();
		Map<String,Integer> map = (Map<String,Integer>)context.getAttribute("paramMap");
		System.out.println("销毁了*********************");
		for(String s:map.keySet()){
			System.out.println(s+"---"+map.get(s));
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext context = arg0.getServletContext();
		Map<String, Integer> map = new HashMap<>();
		map.put("param",123);
		System.out.println("添加了*****************************************"+map);
		context.setAttribute("paramMap", map);
	}
	
	
}
