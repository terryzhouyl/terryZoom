package com.terry.entity.weixinbean.resp;

/**
 * 响应消息之文本消息
 * @author Administrator
 *
 */
public class TextMessage extends BaseMessage{
	
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
		
}
