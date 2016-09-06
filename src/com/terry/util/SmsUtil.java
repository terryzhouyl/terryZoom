package com.terry.util;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author albaba1903
 */
public class SmsUtil {
	
	private static String SERVICES_URL = "http://gbk.sms.webchinese.cn";
    
	public static void main(String[] args) {
		sendSms("sunjoypai", "041a2e17440804a313fb", "13921246798", "您的随机验证码是：880977");
	}
    /**
     * 发送短信
     * @param uid //短信接口uid
     * @param key //短信接口秘钥
     * @param phone 要发送的手机号码
     * @param smsContent 要发送的短信内容 
     * @return
     */
    @SuppressWarnings("unused")
	public static Integer sendSms(String uid,String key,String phone,String smsContent){
    	String status = "0";
		String msg="短信发送成功";
		try {
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod(SERVICES_URL);
			post.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
			NameValuePair[] data = { new NameValuePair("Uid", uid),
					new NameValuePair("Key", key),
					new NameValuePair("smsMob", phone),
					new NameValuePair("smsText", smsContent)
			};
			post.setRequestBody(data);
			client.executeMethod(post);
			int statusCode = post.getStatusCode();
			if(statusCode==200){
				status = new String(post.getResponseBodyAsString().getBytes("gbk"));
				if(status.equals("-1")){
					msg = "没有该用户账户";
				}else if(status.equals("-2")){
					msg = "接口密钥不正确";
				}else if(status.equals("-21")){
					msg = "MD5接口密钥加密不正确";
				}else if(status.equals("-3")){
					msg = "短信数量不足";
				}else if(status.equals("-11")){
					msg = "该用户被禁用";
				}else if(status.equals("-14")){
					msg = "短信内容出现非法字符";
				}else if(status.equals("-4")){
					msg = "手机号格式不正确";
				}else if(status.equals("-41")){
					msg = "手机号码为空";
				}else if(status.equals("-42")){
					msg = "短信内容为空";
				}else if(status.equals("-51")){
					msg = "短信签名格式不正确";
				}else if(status.equals("-6")){
					msg = "IP限制";
				}
			}else{
				msg = "请求超时";
			}
			post.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "发送异常";
		}
		System.out.println("send msg status:" + status + "----------phone---------" + phone);
        return Integer.parseInt(status);
    }
    
}
