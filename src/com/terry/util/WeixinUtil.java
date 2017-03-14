package com.terry.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.terry.entity.AccessToken;
import com.terry.entity.MessageTemplate;
import com.terry.entity.WeixinUser;
import com.terry.entity.weixinbean.manager.Menu;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


/**
 * 微信公众号工具类
 * @author Administrator
 *
 */
public class WeixinUtil {	
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	
	//获取accesstoken
	private final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//通过openId 获取用户信息 (只有该用户关注了公众号，才能获取用户的信息)
	private final static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	//创建菜单
	private final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"; 
	//删除菜单
	private final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	//发送消息模板
    private final static String send_template_url= "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    //private final static String template_id = "_YMA4j0kMua0UgRZ-tk0ridGtzZj3SoGDnvCBmwWy6U";
    //创建二维码 
    private final static String create_qrcode_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
    //查看二维码地址
    public final static String display_qrcode_url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
    
    
    /********微信网页授权********/
    //通过授权code 换取 appid 和 appsecret 换取 openId	
  	private final static String authorize_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";
  	//用户授权登录 通过openId和code获取用户信息（即使未关注公众号也能获取）
  	private final static String authorize_user_info_url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
  	//判断access_token是否有效
  	private final static String validate_access_token = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
  	//刷新access_token
  	private final static String refresh_access_token = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
  	//获取获得jsapi_ticket的接口地址(GET) 有效期7200秒
    private final static String get_jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    
	/**
	 * 将微信消息中的createTime转换成标准格式时间(yyyy-MM-dd HH:mm:ss)
	 * @param createTime
	 * @return
	 */
	public static String formatTime(String createTime) {
		long msgCreateTime = Long.parseLong(createTime)*1000;
		return format.format(new Date(msgCreateTime));
	}

	
	/**
	 * 获取accessToken
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static AccessToken getAccessToken(String appid,String appsecret) {
		AccessToken accessToken = null;
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = HttpUtil.httpRequest(requestUrl, "GET", null);
		//如果请求成功
		if (null != jsonObject) {
			try{
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			}
			catch (JSONException e){
				accessToken = null;
				//获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	/**
	 * 通过code换取openId
	 * @param code
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static JSONObject getOpenIdByCode(String code,String appid,String secret){
		//使用code换取openId,注意与基础accesstoke的区别
		JSONObject openIdObject = null;
		String url = authorize_url.replace("APPID",appid).replace("APPSECRET",secret).replace("CODE",code);
		JSONObject jsonObject = HttpUtil.httpRequest(url,"GET",null);
		if (null != jsonObject) {
			if(!jsonObject.containsKey("errcode")||jsonObject.getInt("errcode")!=0) {
				openIdObject = jsonObject;
			}
			else {				
				log.error("微信授权失败",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
			}
		}		
		return openIdObject;
	}
	
	/**
	 * 通过openid获取用户信息
	 * @param flag 1.只有关注用户才能获取用户信息 2.用户未关注也能获取用户信息
	 * @param openId
	 * @return
	 */
	public static WeixinUser getUserInfo(String openId,String accessToken,int flag) {
		WeixinUser weixinUser = null;
		String requestUrl = null;
		if(flag == 1){			
			requestUrl = user_info_url.replace("ACCESS_TOKEN",accessToken).replace("OPENID", openId);			
		}
		else {
			requestUrl = String.format(authorize_user_info_url,accessToken,openId);
		}
		JSONObject jsonObject = HttpUtil.httpRequest(requestUrl, "GET", null);
		if(jsonObject !=null && !jsonObject.isEmpty()){
			try{
				weixinUser = new WeixinUser();
				weixinUser.setCity(jsonObject.getString("city"));
				weixinUser.setSex(jsonObject.getInt("sex"));
				weixinUser.setProvince(jsonObject.getString("province"));				
				weixinUser.setNickname(jsonObject.getString("nickname"));
				weixinUser.setOpenid(jsonObject.getString("openid"));
				weixinUser.setCountry(jsonObject.getString("country"));
				weixinUser.setHeadimgurl(jsonObject.getString("headimgurl"));				
			}
			catch(Exception e){
				e.printStackTrace();
				log.error("JSONObject fail parse to weixinUser",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
			}
		}
		return weixinUser;
	}
	
	
	/**
     * 公众号用于调用微信JS接口的临时票据,正常情况下，jsapi_ticket的有效期为7200秒，需定时刷新
     * getJsTicket(获取JSSDK接口的临时票据)
     * @param accessToken  公众号接口凭证
     * @return 
     * @throws
     * @author tianba
     * @date 2016年7月1日 下午2:52:45
     */
    public  static String  getJsTicket(String accessToken){
    	String jsapi_ticket=null;
    	String ticketUrl = get_jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = HttpUtil.httpRequest(ticketUrl,"GET", null);
		if (null != jsonObject && jsonObject.getInt("errcode") == 0) {
			jsapi_ticket=jsonObject.getString("ticket");
		}
		return jsapi_ticket;
    }
	
	/**
	 * 创建菜单
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return
	 */
	public static int createMenu(Menu menu,String accessToken) {
		int result = 0;
		//拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		//将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
		//调用接口创建菜单 
		JSONObject jsonObject = HttpUtil.httpRequest(url,"POST",jsonMenu);
		if(null != jsonObject) {
			if(0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
			}
		}
		return result;
	}
	
	/**
	 * 删除菜单
	 * @param accessToken
	 * @return
	 */
	public static int deleteMenu(String accessToken) {
		int result = 0;
		String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		//调用接口创建菜单 
		JSONObject jsonObject = HttpUtil.httpRequest(url,"POST",null);
		if(null !=jsonObject) {
			if(0 !=jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("删除菜单失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
			}
		}
		return result;
	}
	
	/**
	 * 发送模板消息
	 */
	public static String sendModelMessage(String accessToken,String jsonString){
		String url = send_template_url.replace("ACCESS_TOKEN", accessToken);
		String msgid = "";
		JSONObject respObject = HttpUtil.httpRequest(url,"POST",jsonString);
		if(null !=respObject) {
			if(0 !=respObject.getInt("errcode")) {
				log.error("发送消息模板失败 errcode:{} errmsg:{}",respObject.getInt("errcode"),respObject.getString("errmsg"));
			}
			else {
				msgid = respObject.getString("msgid");
			}
		}
		return msgid;
	}
	
	
	/**
	 * 创建二维码
	 * @param accessToken 
	 * @param type 1.临时 2.永久
	 */
	public static JSONObject generateQRCode(String accessToken,int type,String sceneId) {
		String url = create_qrcode_url.replace("ACCESS_TOKEN",accessToken);
		JSONObject request = new JSONObject();
		if(type == 1) { //临时
			request.put("expire_seconds",1800); //超时时间为30m
			request.put("action_name","QR_SCENE");
			JSONObject scene_object = new JSONObject();
			scene_object.put("scene_id",Integer.parseInt(sceneId));
			JSONObject action_info_object = new JSONObject();
			action_info_object.put("scene",scene_object);
			request.put("action_info",action_info_object);
		}
		else { //永久
			request.put("action_name","QR_LIMIT_STR_SCENE");
			JSONObject scene_object = new JSONObject();
			scene_object.put("scene_str",sceneId);
			JSONObject action_info_object = new JSONObject();
			action_info_object.put("scene",scene_object);
			request.put("action_info",action_info_object);
		}
		JSONObject respObject = HttpUtil.httpRequest(url,"POST",request.toString());
		if(null !=respObject) {
			if(!respObject.containsKey("errcode")){				
				return respObject;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * 验证accessToken是否有效
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public static boolean validateAccessToken(String accessToken,String openid) {
		String url = validate_access_token.replace("ACCESS_TOKEN",accessToken).replace("OPENID",openid);
		JSONObject respObject =	HttpUtil.httpRequest(url,"GET", null);
		if(null!=respObject){
			return respObject.getInt("errcode")==0?true:false;
		}
		return false;
	}
	
	/**
	 * 刷新access_token
	 */
	public static JSONObject refreshToken(String appid,String refreshToken) {
		String url = refresh_access_token.replace("APPID", appid).replace("REFRESH_TOKEN",refreshToken);
		JSONObject respObject = HttpUtil.httpRequest(url,"GET",null);
		if(respObject!=null){
			return respObject;
		}
		return null;
	}
}
