package com.terry.service.menuFunctionService;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.terry.util.HttpUtil;
import com.terry.util.MessageUtil;
import com.terry.util.SystemProperties;

import net.sf.json.JSONObject;

@Service("weatherForecast")
public class WeatherForecast extends MenuFunction{

	private String weather_url = "http://apis.baidu.com/apistore/weatherservice/weather";
			
	@Resource(name="systemProperties")
	SystemProperties systemProperties;
	 
	@Override
	public String executeFunction(String toUserName,String fromUserName,String args) {
		// TODO Auto-generated method stub
			
		String newUrl = weather_url + "?citypinyin=" + args; 
		String resp  = HttpUtil.httpRequestGetData(newUrl, "apikey", systemProperties.getProperties("apikey"));	
				
		 
		if(resp!= null) {
			JSONObject object = JSONObject.fromObject(resp);
			if("success".equals(object.getString("errMsg"))) {				
				StringBuilder builder = new StringBuilder(); 						
				JSONObject dataObject = object.getJSONObject("retData");
				builder.append("城市：").append(dataObject.getString("city")).append("\n");
				builder.append("发布时间：").append(dataObject.getString("date")).append(" ").append(dataObject.getString("time")).append("\n");
				builder.append("经度：").append(dataObject.getString("longitude")).append(" ").append("纬度：").append(dataObject.getString("altitude")).append("\n");
				builder.append("天气情况：").append(dataObject.getString("weather")).append("\n");
				builder.append("气温：").append(dataObject.getString("temp")).append("\n");
				builder.append("最低气温：").append(dataObject.getString("l_tmp")).append("\n");
				builder.append("最高气温：").append(dataObject.getString("h_tmp")).append("\n");
				builder.append("风向：").append(dataObject.getString("WD")).append("\n");
				builder.append("风力：").append(dataObject.getString("WS")).append("\n");
				return  MessageUtil.initRespText(toUserName,fromUserName,builder.toString());
			}
			else {
				throw new RuntimeException("参数输入有误");
			}
		}				
		return null;
	}
	
		
}
