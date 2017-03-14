package com.terry.task;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import com.terry.entity.AccessToken;
import com.terry.util.DateUtil;
import com.terry.util.SystemProperties;
import com.terry.util.WeixinUtil;


public class WeixinTask {
	
	private SystemProperties systemProperties;		
	private static AccessToken accessToken;
	
	private static String jsticket;
	
	private Logger log = Logger.getLogger(WeixinTask.class);
	
	public WeixinTask(){}
	
	@Scheduled(fixedRate = 5000*1000)
	public void updateAccessToken(){
		/******微信信息********/
		String appId = systemProperties.getProperties("WEIXIN_APPID");
		String appsecret = systemProperties.getProperties("WEIXIN_APPSECRET");
		
		log.info(DateUtil.getNow()+" : "+ appId+"--定时任务--"+appsecret);	
		
		//WeixinHandler handler = new WeixinHandler(appId,appsecret);
		//更新token
		accessToken = WeixinUtil.getAccessToken(appId, appsecret);
		//更新jsticket
		jsticket = WeixinUtil.getJsTicket(accessToken.getToken());
		
		log.info(DateUtil.getNow()+" : 更新accessToken为----------"+accessToken.getToken());
	}
	
	public static AccessToken getAccessToken(){		
		return accessToken;
	}

	public SystemProperties getSystemProperties() {
		return systemProperties;
	}

	public void setSystemProperties(SystemProperties systemProperties) {
		this.systemProperties = systemProperties;
	}

	public static String getJsticket() {
		return jsticket;
	}	
	
}
