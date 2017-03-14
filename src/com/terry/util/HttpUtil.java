package com.terry.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

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
	 * 用 json发起  http 请求
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
	
	/**
	 * 发起https 请求并获取结果 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,String requestMethod,String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		
		try{

			//创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = {new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			
			sslContext.init(null, tm, new SecureRandom());
			//从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			
			//设置请求方式(GET/POST)
			httpUrlConn.setRequestMethod(requestMethod);
			
			if("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}
			
			//当有数据需要提交时
			if(null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				//注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			
			//将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			//释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		}
		catch (ConnectException ce) {
			logger.error("Weixin server connection timed out");
		}
		catch (Exception e) {
			logger.error("https request error:{}",e);
		}
		return jsonObject;
	}
}
