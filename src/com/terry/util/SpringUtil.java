package com.terry.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil  implements ApplicationContextAware{
	private static ApplicationContext context;
	
	public static Object getBean(String beanName){
		return getApplicationContext().getBean(beanName);
	}

	 @Override
	public void setApplicationContext(ApplicationContext acx) {
	     context = acx;
	 }

    public static ApplicationContext getApplicationContext() {
    	if(context == null){
    		 context =new  ClassPathXmlApplicationContext("classpath:spring/*.xml");
    	}
        return context;
    }
}
