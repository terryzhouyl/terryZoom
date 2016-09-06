package com.terry.service.menuFunctionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.terry.entity.weixinbean.resp.Article;
import com.terry.entity.weixinbean.resp.NewsMessage;
import com.terry.util.HttpUtil;
import com.terry.util.MessageUtil;
import com.terry.util.SystemProperties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("todayNewsService")
public class TodayNewsService extends MenuFunction{

	private final String news_url = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news"; 
	
	@Resource(name="systemProperties")
	SystemProperties systemProperties;
	
	@Override
	public String executeFunction(String toUserName,String fromUserName,String args) {
		// TODO Auto-generated method stub
		
		String httpArg = "?channelId=5572a109b3cdc86cf39001db&channelName=互联网焦点&title=%E4%B8%8A%E5%B8%82&page=1&needContent=0&needHtml=0";
		
		String real_url = news_url + httpArg;
		
		JSONObject json = JSONObject.fromObject(HttpUtil.httpRequestGetData(real_url,"apikey", systemProperties.getProperties("apikey")));   
		
		
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_NEWS);
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<>();

		JSONArray jsonArray = ((JSONObject)((JSONObject)json.get("showapi_res_body")).get("pagebean")).getJSONArray("contentlist");
		for (int i = 0; i < jsonArray.size(); i++) {
		      Article article = new Article();

		      article.setTitle(((JSONObject) jsonArray.get(i)).getString("title"));

		      article.setUrl(((JSONObject) jsonArray.get(i)).getString("link"));
		      String imageUrl="";
		      
		      imageUrl=((JSONObject)((JSONObject) jsonArray.get(i)).getJSONArray("imageurls").get(0)).getString("url");

		      article.setPicUrl(imageUrl);
		      article.setDescription(((JSONObject) jsonArray.get(i))
		          .getString("desc"));
		      articleList.add(article);
		      if (i == 4) {
		        break;
		      }
		 }
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		
		return MessageUtil.newsMessageToXml(newsMessage);
	}
	
	
}
