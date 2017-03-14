package com.terry.test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.terry.entity.AccessToken;
import com.terry.entity.WeixinUser;
import com.terry.util.WeixinUtil;

import net.sf.json.JSONObject;

public class WeixinTest extends BaseTest{
	
	private final String WEIXIN_APPID = "wx69d041c3f5edb145";	
	private final String WEIXIN_APPSECRET = "0e8f423a4c24d99a67e74615dba36fa8";
	
	private final String WEIXIN_APPID_MYTEST = "wx7b28d1ab10ae5581";
	private final String WEIXIN_APPSECRET_MYTEST = "8d258422c24f405a16e3b6a61d2e9e68";
	
	@Test
	public void TestGetUserInfo() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//根据openId获取微信用户信息
		AccessToken token = WeixinUtil.getAccessToken(WEIXIN_APPID, WEIXIN_APPSECRET);
		//Map tokenMap = BeanUtils.describe(token);
		//this.printfMap(tokenMap);
		WeixinUser wxUser = WeixinUtil.getUserInfo("oqfGLs38EWV1krxTbQlQkkKwBacc", token.getToken(),1);
		Map userMap = BeanUtils.describe(wxUser);
		printfMap(userMap);
	}
	
	@Test
	public void getQrcode(){
		AccessToken token = WeixinUtil.getAccessToken(WEIXIN_APPID_MYTEST, WEIXIN_APPSECRET_MYTEST);
		JSONObject json = WeixinUtil.generateQRCode(token.getToken(),1,"1234");
		System.out.println(json);
	}
}
