package com.terry.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terry.entity.AccessToken;
import com.terry.entity.weixinbean.manager.Menu;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


/**
 * 微信公众号工具类
 * @author Administrator
 *
 */
public class WeixinUtil {	
	
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	
	private final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	private final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"; 
	
	private final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	/**
	 * 将微信消息中的createTime转换成标准格式时间(yyyy-MM-dd HH:mm:ss)
	 * @param createTime
	 * @return
	 */
	public static String formatTime(String createTime) {
		long msgCreateTime = Long.parseLong(createTime)*1000;
		return format.format(new Date(msgCreateTime));
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
			log.error("Weixin server connection timed out");
		}
		catch (Exception e) {
			log.error("https request error:{}",e);
		}
		return jsonObject;
	}
	
	/**
	 * 获取accessToken
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static AccessToken getAccessToken(String appid,String appsecret) {
		AccessToken accessToken = null;
		
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		
		//如果请求成功
		if (null != jsonObject) {
			try{
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			}
			catch (JSONException e){
				accessToken = null;
				//获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
			}
		}
		
		return accessToken;
	}
	
	
	
	/**
	 * 创建菜单
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return
	 */
	public static int createMenu(Menu menu,String accessToken) {
		int result = 0;
		
		//拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		//将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		
		System.out.println(jsonMenu);
		
		
		//调用接口创建菜单 
		JSONObject jsonObject = httpRequest(url,"POST",jsonMenu);
		
		if(null != jsonObject) {
			if(0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
			}
		}
		
		return result;
	}
	
	/**
	 * 删除菜单
	 * @param accessToken
	 * @return
	 */
	public static int deleteMenu(String accessToken) {
		
		int result = 0;
		String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		
		//调用接口创建菜单 
		JSONObject jsonObject = httpRequest(url,"POST",null);
		
		if(null !=jsonObject) {
			if(0 !=jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("删除菜单失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
			}
		}
		return result;
	}
	
}
