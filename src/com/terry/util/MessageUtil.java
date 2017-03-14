package com.terry.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.terry.entity.weixinbean.resp.Article;
import com.terry.entity.weixinbean.resp.MusicMessage;
import com.terry.entity.weixinbean.resp.NewsMessage;
import com.terry.entity.weixinbean.resp.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	//返回消息类型：文本
	public static final String RESP_MESSAGE_TEXT = "text";
	//返回消息类型：音乐
	public static final String RESP_MESSAGE_MUSIC = "music";
	//返回消息类型：图文
	public static final String RESP_MESSAGE_NEWS = "news";
	
	//请求消息类型：文本
	public static final String REQ_MESSAGE_TEXT = "text";
	//请求消息类型：图文
	public static final String REQ_MESSAGE_IMAGE = "image";
	//请求消息类型：链接
	public static final String REQ_MESSAGE_LINK = "link";
	//请求消息类型：地理位置
	public static final String REQ_MESSAGE_LOCATION = "location";
	//请求消息类型：音频
	public static final String REQ_MESSAGE_VOICE = "voice";
	//请求消息类型：推送
	public static final String REQ_MESSAGE_EVENT = "event";
	
	//事件类型：subscribe（订阅）
	public static final String EVENT_SUBSCRIBE = "subscribe";
	//事件类型：unsubscribe（取消订阅）
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	//事件类型：CLICK(自定义菜单点击事件)
	public static final String EVENT_CLICK = "CLICK";
	//事件类型：scan(扫描)
	public static final String EVENT_SCAN = "SCAN";
	
	
	public static final String MESSAGE_VIDEO = "video";	
	public static final String MESSAGE_VIEW = "view";
	
	
	/**
	 * xml转换成集合,解析微信发来的请求(XML)
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String,String> map = new HashMap<>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list =root.elements();
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	
	
	/**
	 * 文本消息对象转换成xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();		
		//看不懂这句是什么意思
		xstream.alias("xml",textMessage.getClass()); 
		return xstream.toXML(textMessage);
		
	}		
	
	/**
	 * 音乐消息对象转换成xml
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		XStream xstream = new XStream();	
		xstream.alias("xml",musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	
	/**
	 * 图文消息对象转换成xml
	 * @param musicMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		XStream xstream = new XStream();	
		xstream.alias("xml",newsMessage.getClass());
		xstream.alias("item",Article.class);
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * 扩展xstream,使其支持CDATA块 感觉多线程下可能会有问题暂且不用
	 */
	/*private static XStream xstream = new XStream(new XppDriver(){
			public HierarchicalStreamWriter createWriter(Writer out) {	
				return new PrettyPrintWriter(out){
					boolean cdata = true;
					
					public void startNode(String name,Class clazz){
						super.startNode(name, clazz);
					}
					
					protected void writeText(QuickWriter writer,String text){
						if(cdata) {
							writer.write("<![CDATA[");
							writer.write(text);
							writer.write("]]>");
						}
						else {
							writer.write(text);
						}
					}	
				};
			}
		}	
	);*/
	

	
	
	/**
	 * 生成文本响应消息
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initRespText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.RESP_MESSAGE_TEXT);
		text.setContent(content);
		text.setCreateTime(new Date().getTime());
		return MessageUtil.textMessageToXml(text); 	
	}
	
	
	/**
	 * 生成图文响应消息
	 * @param toUserName
	 * @param fromUserName
	 * @param articleList
	 * @return
	 */
	public static String initRespImageNews(String toUserName,String fromUserName,List<Article> articleList){
		
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_NEWS);
		newsMessage.setFuncFlag(0);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);		
		return MessageUtil.newsMessageToXml(newsMessage); 	
	}
	
	
	/**
	 * 生成图文响应消息 Demo
	 * @param toUserName
	 * @param fromUserName
	 * @param 
	 * @return
	 */
	public static String initRespImageNews(String toUserName,String fromUserName,int imageType){
		
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_NEWS);
		newsMessage.setFuncFlag(0);
		
		
		List<Article> articleList = new ArrayList<>();
				
		if(imageType == 1) { //单图文消息
			Article article = new Article();
			article.setTitle("terryzhou 微信公众账号开发");
			article.setDescription("周有亮  90后  程序员 单身，希望认识更多的人");
			article.setPicUrl("http://wx.qlogo.cn/mmopen/PiajxSqBRaELVXy1r2ZLxNWfEZiataM3LT0iabzkXOUQd9cFCCz5ia01N2RJdtvh7xicI47vbtQyvIWcLgIghqL3YQg/0");  //
			article.setUrl("https://www.zhihu.com/people/mu-rong-liang-hong");  //我的知乎主页
			
			articleList.add(article);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			
		}
		else if(imageType == 2) { //单图文消息 - 不含图片
			Article article = new Article();
			article.setTitle("terryzhou 微信公众账号开发");
			article.setDescription("周有亮  90后  程序员 单身，希望认识更多的人");
			article.setPicUrl(""); //图片制空
			article.setUrl("https://www.zhihu.com/people/mu-rong-liang-hong");  //我的知乎主页
			
			articleList.add(article);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
		}
		else if(imageType == 3) { //多图文消息
			Article article1 = new Article();
			article1.setTitle("terryzhou 微信公众账号开发");
			article1.setDescription("");
			article1.setPicUrl("http://wx.qlogo.cn/mmopen/X71c3MsZXbz6gibF6uAHI8YjefLibiaJFqib9qrb46PpfbPictwSGONicaQGMnEhE3v8uYynWuzSG9ic931jEXWq2oK7HmGib17K9YeO/0");
			article1.setUrl("https://www.zhihu.com/people/mu-rong-liang-hong");  //我的知乎主页
			
			Article article2 = new Article();
			article2.setTitle("terryzhou 微博主页");
			article2.setDescription("");
			article2.setPicUrl("http://wx.qlogo.cn/mmopen/X71c3MsZXbz6gibF6uAHI8YjefLibiaJFqib9qrb46PpfbPictwSGONicaQGMnEhE3v8uYynWuzSG9ic931jEXWq2oK7HmGib17K9YeO/0");
			article2.setUrl("http://weibo.com/u/2611558377?from=myfollow_all");  //微博主页
			
			
			articleList.add(article1);
			articleList.add(article2);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
		}
		//多图文消息---首条消息不含图片
		else if(imageType == 4) { 
			Article article1 = new Article();
			article1.setTitle("terryzhou 微信公众账号开发");
			article1.setDescription("");
			article1.setPicUrl("");
			article1.setUrl("https://www.zhihu.com/people/mu-rong-liang-hong");  //我的知乎主页
			
			Article article2 = new Article();
			article2.setTitle("terryzhou 微博主页");
			article2.setDescription("");
			article2.setPicUrl("http://wx.qlogo.cn/mmopen/X71c3MsZXbz6gibF6uAHI8YjefLibiaJFqib9qrb46PpfbPictwSGONicaQGMnEhE3v8uYynWuzSG9ic931jEXWq2oK7HmGib17K9YeO/0");
			article2.setUrl("http://weibo.com/u/2611558377?from=myfollow_all");  //微博主页
			
			Article article3 = new Article();
			article3.setTitle("Linda 的 微博主页");
			article3.setDescription("");
			article3.setPicUrl("http://wx.qlogo.cn/mmopen/X71c3MsZXbz6gibF6uAHI8YjefLibiaJFqib9qrb46PpfbPictwSGONicaQGMnEhE3v8uYynWuzSG9ic931jEXWq2oK7HmGib17K9YeO/0");
			article3.setUrl("http://weibo.com/u/5620965675?from=myfollow_all");  //微博主页
			
			
			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
		}
		else if(imageType == 5) { //多图文消息，最后一条消息不含图片
			Article article1 = new Article();
			article1.setTitle("terryzhou 微信公众账号开发");
			article1.setDescription("");
			article1.setPicUrl("");
			article1.setUrl("https://www.zhihu.com/people/mu-rong-liang-hong");  //我的知乎主页
			
			Article article2 = new Article();
			article2.setTitle("terryzhou 微博主页");
			article2.setDescription("");
			article2.setPicUrl("http://wx.qlogo.cn/mmopen/X71c3MsZXbz6gibF6uAHI8YjefLibiaJFqib9qrb46PpfbPictwSGONicaQGMnEhE3v8uYynWuzSG9ic931jEXWq2oK7HmGib17K9YeO/0");
			article2.setUrl("http://weibo.com/u/2611558377?from=myfollow_all");  //微博主页
			
			Article article3 = new Article();
			article3.setTitle("Linda 的 微博主页");
			article3.setDescription("");
			article3.setPicUrl("");
			article3.setUrl("http://weibo.com/u/5620965675?from=myfollow_all");  //微博主页
						
			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
		}
		return MessageUtil.newsMessageToXml(newsMessage); 	
	}
	
	/**
	 * 主菜单
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，请按照菜单提示进行操作:\n\n");
		sb.append("1.天气预报\n");
		sb.append("2.公交查询\n");
		sb.append("3.公厕查询\n");		
		sb.append("4.历史上的今天\n");
		sb.append("5.电影推荐\n");
		sb.append("6.歌曲点播\n");
		sb.append("7.今日头条\n");		
		sb.append("8.周边美食\n");
		sb.append("9.人脸识别\n\n");
		sb.append("回复?显示此帮助菜单。");
		return sb.toString();
	}

}
