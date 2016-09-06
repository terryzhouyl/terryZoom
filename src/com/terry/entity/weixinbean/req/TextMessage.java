package com.terry.entity.weixinbean.req;
/**
 * 请求消息之文本
 * @author Administrator
 *
 */
public class TextMessage extends BaseMessage{
	
	private String Content; //文本消息内容

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
