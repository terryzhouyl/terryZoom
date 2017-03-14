package com.terry.main;

import com.terry.entity.AccessToken;
import com.terry.util.WeixinUtil;

import net.sf.json.JSONObject;

public class MaterialManager {
	
	public static void main(String[] args) {
		
		
		//第三方用户唯一凭证
		String appId = "wx7b28d1ab10ae5581";		
		//第三方用户唯一凭证秘钥
		String appSecret = "8d258422c24f405a16e3b6a61d2e9e68";
		
		//调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		
		String json = "{\"type\":news,\"offset\":0,\"count\":1}";
		
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
		String newUrl = url.replace("ACCESS_TOKEN",at.getToken());
		
		//JSONObject jsonObject = WeixinUtil.httpRequest(newUrl, "POST", json);
	}
}
