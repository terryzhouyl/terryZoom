package com.terry.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.terry.CommonVar;
import com.terry.entity.AccessToken;
import com.terry.entity.WeixinUser;
import com.terry.service.CoreService;
import com.terry.task.WeixinTask;
import com.terry.util.SignUtil;
import com.terry.util.TestUtil;
import com.terry.util.WeixinUtil;

import net.sf.json.JSONObject;

@Controller("weixinController")
@RequestMapping(value = "/weixin")
public class WeixinController extends MyController{

	@Resource(name="coreService")
	CoreService coreService;
	
	/**
	 * 微信接口 
	 * @param model
	 * @param request
	 * @param response
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr 当为null是说明的信息推送，不为null时说明是网站接入
	 * @throws Exception
	 */
	@RequestMapping(value = "/reply")
	public void signature(Model model,HttpServletRequest request,HttpServletResponse response, String signature, String timestamp, String nonce, String echostr) throws Exception {
		if(null==echostr||echostr.isEmpty()){  
            responseMsg(request, response);  
        }else{  
        	System.out.println("site connect ...:"+new Date());
        	System.out.println("timestamp: "+timestamp);
        	System.out.println("nonce: "+nonce);
        	System.out.println("echostr: "+echostr);
        	
        	if(SignUtil.checkSignature(signature, timestamp, nonce)){
            	System.out.println("connect success!");
            	response.getWriter().write(echostr);
            }else{  
            	System.out.println("connect fail!");
            	response.getWriter().write("error");
            }  
            
            System.out.println();
        }  
	}
	
	/**
	 * 信息回复
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void responseMsg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		//调用核心业务类接收消息、处理消息
		String respMessage = coreService.processRequest(request);
		//响应消息
		pw.print(respMessage);	
		pw.close();		
	}
	
	/**
	 * 获取微信js的凭证
	 * url : 调用微信js的页面url
	 */
	public ResponseEntity<String> getJsTicket(String url){
		
		boolean status = true;
		String msg = "请求成功";
		Map<String, String> map = null;
		Map<String, Object>  resultMap=new HashMap<String, Object>();
		try{
		//获取公众号接口凭证  需要传入微信公众号appid 和appSecret 在公众号平台获取
		//String appId="wx69d041c3f5edb145";
		//String appSecret="0e8f423a4c24d99a67e74615dba36fa8";
		
		String appId = coreService.getConfig("WEIXIN_APPID");	
		//这里accessToken和jsticket 都有调用次数 应该放到缓存或是数据库  定时去刷新
		String jsticket=WeixinTask.getJsticket();
		
		//验证签名
		map=SignUtil.sign(jsticket, url);
		resultMap.put("appId", appId);
		resultMap.put("timestamp", map.get("timestamp"));
		resultMap.put("nonceStr", map.get("nonceStr"));
		resultMap.put("signature", map.get("signature"));
		}
		catch(Exception e){
			status = false;
			msg = "请求失败";
		}
		return renderData(status, msg, resultMap);
	}
		
	@RequestMapping("/getUserInfo")
	public  ResponseEntity<String> getUserInfo(HttpServletRequest request,Model model) {
		String msg = "获取成功";
		boolean status = true;
		HttpSession session = request.getSession();
		WeixinUser user = (WeixinUser)session.getAttribute(CommonVar.SESSION_WEIXIN);
		if(user == null) {
			status = false;
			msg = "用户未登录";
		}
		return renderData(status, msg, user);
	}
	
	/**
	 * 微信授权登录api(静默登录)
	 */
	@RequestMapping("/wxbaseLogin")
	public String wxuserLogin(String returnUrl,HttpServletRequest request,String code,Model model) {
		HttpSession session = request.getSession();
		WeixinUser wxuser = (WeixinUser)session.getAttribute(CommonVar.SESSION_WEIXIN);
		if(wxuser == null){
			if(StringUtils.isBlank(code)){
				//如果code为空则进行授权登陆
				//网页授权方式(a:snsapi_userinfo;b:snsapi_base)
				String loginType = "snsapi_base";
				String redirect_uri = getWebSite(request) + "/weixin/wxbaseLogin.htm?returnUrl="+returnUrl;
				//授权地址
				String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +coreService.getConfig("WEIXIN_APPID") + "&redirect_uri=" + redirect_uri + "&response_type=code&scope="+loginType+"&state=state1#wechat_redirect";
				//跳转地址
				return "redirect:"+url;
			}
			else {
				//如果code不为空则 通过code获取用户信息
				//通过code获取微信账号的OpenId
				JSONObject openIdObject = WeixinUtil.getOpenIdByCode(code,coreService.getConfig("WEIXIN_APPID"),coreService.getConfig("WEIXIN_APPSECRET"));
				WeixinUser user = null;
				if(StringUtils.isNotBlank(openIdObject.getString("openid"))){
					//如果不为空
					user = WeixinUtil.getUserInfo(openIdObject.getString("openid"), WeixinTask.getAccessToken().getToken(),1);
					request.getSession().setAttribute(CommonVar.SESSION_WEIXIN,user);	
					System.out.println(user);
					TestUtil.getBeanInfo(user);
				}
				return "redirect:"+returnUrl;
			}
		}
		else {
			return "redirect:"+returnUrl;
		}
	}
	
	
	
	/**
	 * 微信授权登录api(用户授权登录)  两者差别何在？想了好久，关键在于，用户如果未关注该公众号，通过授权也能获取其用户信息
	 */
	@RequestMapping("/wxuserLogin")
	public String wxbaseLogin(String returnUrl,HttpServletRequest request,String code,Model model) {
		HttpSession session = request.getSession();
		WeixinUser wxuser = (WeixinUser)session.getAttribute(CommonVar.SESSION_WEIXIN);
		if(wxuser == null){
			if(StringUtils.isBlank(code)){
				//如果code为空则进行授权登陆
				//网页授权方式(a:snsapi_userinfo;b:snsapi_base)
				String loginType = "snsapi_userinfo";
				String redirect_uri = getWebSite(request) + "/weixin/wxuserLogin.htm?returnUrl="+returnUrl;
				//授权地址
				String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +coreService.getConfig("WEIXIN_APPID") + "&redirect_uri=" + redirect_uri + "&response_type=code&scope="+loginType+"&state=state1#wechat_redirect";
				//跳转地址
				return "redirect:"+url;
			}
			else {
				//如果code不为空则 通过code获取用户信息
				//通过code获取微信账号的OpenId
				JSONObject openIdObject = WeixinUtil.getOpenIdByCode(code,coreService.getConfig("WEIXIN_APPID"),coreService.getConfig("WEIXIN_APPSECRET"));
				String openId = openIdObject.getString("openid");
				WeixinUser user = null;
				if(StringUtils.isNotBlank(openId)){
					//这里返回的参数带access_token,有一个token过期的问题
					//首先判断access_token是否过期
					String access_token = openIdObject.getString("access_token");
					if(!WeixinUtil.validateAccessToken(access_token, openId)){ //accessToken无效
						//刷新accessToken
						JSONObject refreshObject = WeixinUtil.refreshToken(coreService.getConfig("WEIXIN_APPID"),openIdObject.getString("refresh_token"));
						if(refreshObject!=null){ 
							//获取刷新后的access_token
							access_token = refreshObject.getString("access_token");
						}
					}
					user = WeixinUtil.getUserInfo(openIdObject.getString("openid"),access_token,2);
					request.getSession().setAttribute(CommonVar.SESSION_WEIXIN,user);
					try {
						Map map = BeanUtils.describe(user);
						for(Object o : map.keySet()){
							System.out.println(o+"---"+map.get(o));
						}
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
				}
				return "redirect:"+returnUrl;
			}
		}
		else {
			return "redirect:"+returnUrl;
		}
	}

}
