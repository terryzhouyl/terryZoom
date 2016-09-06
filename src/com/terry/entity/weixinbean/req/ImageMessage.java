package com.terry.entity.weixinbean.req;

/**
 * 请求消息之图片消息
 * @author Administrator
 *
 */
public class ImageMessage extends BaseMessage{

	private String PicUrl; //图片路径

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
	
}
