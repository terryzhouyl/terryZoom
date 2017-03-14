package com.terry.test;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;


public class BaseTest {
	//public String host = "http://192.168.1.188:8099/enjoylife";
	//public String host = "http://www.sunjoypai.com";
	//public String xjphost = "http://localhost:8080/enjoylife";
	public String xjlhost = "http://localhost:8080/enjoyhome";
	//public String xjlhost = "http://192.168.1.188:8080/enjoyhome";
	//public String xjlhost = "http://180.173.159.204:8099/enjoylife";


	
	public String postMethod(String url, NameValuePair[] data) throws Exception {
		PostMethod httpPost = new PostMethod(url);
		httpPost.setRequestBody(data);
		httpPost.getParams().setContentCharset("utf-8");
		HttpClient client = new HttpClient(); 
		client.executeMethod(httpPost);
		return httpPost.getResponseBodyAsString();		
	}
	
	public String getMethod(String url) throws Exception {
		GetMethod httpGet = new GetMethod(url);
		httpGet.getParams().setContentCharset("utf-8");
		HttpClient clientGet = new HttpClient(); 
		clientGet.executeMethod(httpGet);
		return httpGet.getResponseBodyAsString();
	}
	
	public void printfList(List<?> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(int i=0;i<list.size();i++) {
			System.out.println("*******No."+(i+1)+"*******");
			printfObjectInfo(list.get(i));
		}
	}

	public void printfMap(Map<String,Object> map) {
		for(String s:map.keySet()) {
			System.out.println(s+":"+map.get(s));
		}
	}
	
	public void printfObjectInfo(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		Class<?> orderClass =  t.getClass();				
		Field[] fields = orderClass.getDeclaredFields();
		Method[] methods = orderClass.getMethods();
		for(Field f:fields){
			 String name = "get"+ f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
			 for(Method m:methods){
				 if(m.getName().equals(name)){
					 System.out.println(f.getName()+"----"+m.invoke(t, null));
				 }	
			 }
		}
	}
}
