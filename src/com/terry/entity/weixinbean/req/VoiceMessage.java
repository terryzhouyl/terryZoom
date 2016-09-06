package com.terry.entity.weixinbean.req;
/**
 * 音频消息
 * @author Administrator
 *
 */
public class VoiceMessage extends BaseMessage{
	//媒体Id
	private String MediaId;
	//语音格式
	private String Format;
	
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	
	
}
