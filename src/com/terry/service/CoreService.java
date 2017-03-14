package com.terry.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.terry.BusinessException;
import com.terry.dao.KeywordReplyDao;
import com.terry.dao.MaterialDao;
import com.terry.dao.MenuConfigDao;
import com.terry.dao.MessageTemplateDao;
import com.terry.entity.KeywordReply;
import com.terry.entity.Material;
import com.terry.entity.MenuConfig;
import com.terry.entity.MessageTemplate;
import com.terry.entity.WeixinUser;
import com.terry.entity.weixinbean.resp.Article;
import com.terry.service.impl.BaseService;
import com.terry.service.menuFunctionService.MenuFunction;
import com.terry.task.WeixinTask;
import com.terry.util.DateUtil;
import com.terry.util.MessageUtil;
import com.terry.util.WeixinUtil;

/**
 * 核心服务类
 * @author Administrator
 *
 */
@Service("coreService")
public class CoreService extends BaseService{
	
	public Logger log = Logger.getLogger(CoreService.class);
	
	@Resource(name="menuConfigDaoImpl")
	MenuConfigDao menuConfigDaoImpl;
	
	@Resource(name="keywordReplyDaoImpl")
	KeywordReplyDao keywordReplyDaoImpl;
	
	@Resource(name="messageTemplateDaoImpl")
	MessageTemplateDao messageTemplateDaoImpl;
	
	@Resource(name="materialDaoImpl")
	MaterialDao materialDaoImpl;
	
	public String processRequest(HttpServletRequest request){
		String respMessage = null;
		String fromUserName = null;
		String toUserName = null;
		try{
			//默认返回的文本消息内容
			String respContent = "未查询到结果，请检查输入信息";
			//xml请求解析
			Map<String, String> requestMap = MessageUtil.xmlToMap(request);
			fromUserName = requestMap.get("FromUserName"); //发送方账号
			toUserName = requestMap.get("ToUserName");//公众账号
			String msgType = requestMap.get("MsgType"); //消息类型
			String content = requestMap.get("Content"); //内容
			//文本消息
			if(MessageUtil.REQ_MESSAGE_TEXT.equals(msgType)){
				respContent = "您发送的是文本消息!该功能暂未开通!";
				String keyword = null;
				if(content.indexOf(">")>-1) {
					keyword = content.substring(0,content.indexOf(">"));
				}
				else {
					keyword = content;
				}
				KeywordReply kewordReply = new KeywordReply();
				kewordReply.setKeyword(keyword);
				kewordReply.setMessageType(1);
				List<KeywordReply> replyList =	keywordReplyDaoImpl.queryReplyInfo(kewordReply);
				if(replyList!=null&&replyList.size()>0) {
					KeywordReply kr = replyList.get(0);
					if("text".equals(kr.getReplyType())) {
						respMessage = MessageUtil.initRespText(toUserName, fromUserName, kr.getReplyContent());
					}
					else if("method".equals(kr.getReplyType())){
						ServletContext servletContext =	request.getServletContext();
						ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
				 		MenuFunction menuFunction =	(MenuFunction)ac.getBean(kr.getReplyContent());
						String args = content.substring(content.indexOf(">")+1); 
						respMessage = menuFunction.executeFunction(toUserName,fromUserName,args);
					}
					else if("imageText".equals(kr.getReplyType())){
						Integer materialId = kr.getRelativeId();
						Material materialQuery = new Material();
						materialQuery.setGroupId(materialId);
						List<Material> list = materialDaoImpl.queryList(materialQuery);
						List<Article> articleList = new ArrayList<>();
						for(int i=0;i<list.size();i++){
							Material m = list.get(i);
							Article article = new Article();
							article.setTitle(m.getTitle());
							article.setUrl(m.getUrlLink());
							article.setPicUrl(m.getPictureUrl());
							article.setDescription(m.getDescription());
							articleList.add(article);
						}
						respMessage = MessageUtil.initRespImageNews(toUserName, fromUserName, articleList);
					}
				}	
				else {
					respMessage = MessageUtil.initRespText(toUserName, fromUserName, respContent);
					//String accessToken = WeixinTask.getAccessToken().getToken();
					//WeixinUtil.sendModelMessage(fromUserName,"亮~~",accessToken);
					//关注之后调用消息模板
					KeywordReply kr = new KeywordReply();
					kr.setKeyword(MessageUtil.EVENT_SUBSCRIBE);
					kr.setReplyType("template");
					kr.setMessageType(2);
					List<KeywordReply> list = keywordReplyDaoImpl.queryReplyInfo(kr);
					KeywordReply reply = list.size() > 0?list.get(0):null;
					if(reply!=null) {
						String accessToken = WeixinTask.getAccessToken().getToken();
						MessageTemplate mt = messageTemplateDaoImpl.get(MessageTemplate.class,reply.getRelativeId());
						String templateBody = mt.getBody();
						WeixinUser userInfo =	WeixinUtil.getUserInfo(fromUserName, accessToken,1);
						String jsonBody = String.format(templateBody,fromUserName,DateUtil.getNow(null),userInfo.getNickname());
						WeixinUtil.sendModelMessage(accessToken,jsonBody);
					}
				}
			}
			//图片消息
			else if(MessageUtil.REQ_MESSAGE_IMAGE.equals(msgType)){ 
				respContent = "您发送的图片消息!";
			}
			//地理位置消息
			else if(MessageUtil.REQ_MESSAGE_LOCATION.equals(msgType)){		
				respContent = "您发送的是地理位置消息";
			}
			//链接消息
			else if(MessageUtil.REQ_MESSAGE_LINK.equals(msgType)){			
				respContent = "您发送的是链接消息";
			}
			//音频消息
			else if(MessageUtil.REQ_MESSAGE_VOICE.equals(msgType)) {
				respContent = "您发送的是音频消息";
			}	
			//事件推送
			else if(MessageUtil.REQ_MESSAGE_EVENT.equals(msgType)){
				//事件类型
				String eventType = requestMap.get("Event");
				//订阅  消息模板
				if(eventType.equals(MessageUtil.EVENT_SUBSCRIBE)){  //EventKey为scene_id
					if(requestMap.get("EventKey")!=null && requestMap.get("EventKey").startsWith("qrscene")){
						//二维码扫描
						String eventKey = requestMap.get("EventKey");
						String sceneId = eventKey.substring(eventKey.indexOf("_")+1);
						log.info("sceneId---------"+sceneId);
					}
					
					//respContent = "谢谢您的关注";
				}
				else if(eventType.equals(MessageUtil.EVENT_SCAN)){ //EventKey为 scene_id (qrscene_二维码参数值)
					if(requestMap.get("EventKey")!=null){
						//二维码扫描关注
						String eventKey = requestMap.get("EventKey");
						log.info("sceneId---------"+eventKey);
					}
				}
				//取消订阅
				else if(eventType.equals(MessageUtil.EVENT_UNSUBSCRIBE)){
					//取消订阅后用户再收不到公众号发送的消息，因此不需要回复
				}
				//自定义菜单点击事件
				else if(eventType.equals(MessageUtil.EVENT_CLICK)){
					//事件KEY值，与创建自定义菜单时指定的key值对应
					String eventKey = requestMap.get("EventKey");				 															
					MenuConfig menuConfig =	menuConfigDaoImpl.getBy(MenuConfig.class,"menuKey",eventKey);
					if("text".equals(menuConfig.getRespType())){ //如果返回类型为文本类型						
						//回复文本消息
						respMessage = MessageUtil.initRespText(toUserName, fromUserName, menuConfig.getRespInfo());
					}
					else if("method".equals(menuConfig.getRespType())) { //如果返回类型为 调用方法类型
						ServletContext servletContext =	request.getServletContext();
						//获取spring上下文
						ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
						//获取service对象
						MenuFunction menuFunction =	(MenuFunction)ac.getBean(menuConfig.getRespInfo().trim());
						//方法调用结果
						respMessage = menuFunction.executeFunction(toUserName,fromUserName,null);
					}										
				}
			}			
			
		}
		catch (BusinessException be){
			respMessage = MessageUtil.initRespText(toUserName, fromUserName,be.getErrorMessage());
			be.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return respMessage; 	
	}
}
