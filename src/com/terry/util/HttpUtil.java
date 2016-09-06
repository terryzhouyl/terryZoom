package com.terry.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import net.sf.json.JSONObject;

public class HttpUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	/**
	 * http 请求
	 * @param data 请求数据
	 * @param http_url 请求url
	 * @return json字符串
	 */
	public static String httpRequestPostData(NameValuePair[] data,String http_url) {
				
		String res = null;
		try {
			PostMethod httpPost = new PostMethod(http_url);		
			httpPost.setRequestBody(data);
			httpPost.getParams().setContentCharset("utf-8");
			HttpClient client = new HttpClient();
			int status;
			status = client.executeMethod(httpPost);
			if (status == HttpStatus.SC_OK) {
				res = httpPost.getResponseBodyAsString();
				return res;
			} else {
				logger.info("请求失败");
			}
		} catch (Exception e) {
			logger.info("异常");
			e.printStackTrace();
		}	
		return null;
	}
	
	/**
	 * http 请求
	 * @param data 请求数据
	 * @param http_url 请求url
	 * @return json字符串
	 */
	public static String httpRequestGetData(String http_url,String headerName,String headerValue) {
				
		String res = null;
		try {			
			GetMethod httpGet =	new GetMethod(http_url);
			httpGet.getParams().setContentCharset("utf-8");			
			httpGet.setRequestHeader(headerName, headerValue);						
			HttpClient client = new HttpClient();
			int status;
			status = client.executeMethod(httpGet);
			if (status == HttpStatus.SC_OK) {
				res = httpGet.getResponseBodyAsString();
				System.out.println(res);
				return res;
			} else {
				logger.info("请求失败");
			}
		} catch (Exception e) {
			logger.info("异常");
			e.printStackTrace();
		}	
		return null;
	}
}
