package com.terry.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terry.entity.AccessToken;
import com.terry.entity.weixinbean.manager.Button;
import com.terry.entity.weixinbean.manager.CommonButton;
import com.terry.entity.weixinbean.manager.ComplexButton;
import com.terry.entity.weixinbean.manager.Menu;
import com.terry.util.WeixinUtil;

public class MenuManager {
	
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	
	public static void main(String[] args) {
		
//		//第三方用户唯一凭证
//		String appId = "wxca088efb0873bd63";		
//		//第三方用户唯一凭证秘钥
//		String appSecret = "087e2f5c59e40f5ac58ef9aa3da933bb";
		
				
		//测试号
		//第三方用户唯一凭证
		String appId = "wx7b28d1ab10ae5581";		
		//第三方用户唯一凭证秘钥
		String appSecret = "8d258422c24f405a16e3b6a61d2e9e68";
		
		
		
		//调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		
		if(null != at) {
			//调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());	
			
			//调用接口删除菜单
			//int result = WeixinUtil.deleteMenu(at.getToken());
			
			if(0 == result) {
				log.info("菜单创建成功！");
			}
			else {
				log.info("菜单创建失败，错误码："+result);
			}
		}
	}
	
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("天气预报");
		btn11.setType("click");
		btn11.setKey("weatherForecast");
		
		CommonButton btn12 = new CommonButton();
		btn12.setName("公交查询");
		btn12.setType("click");
		btn12.setKey("transformSearch");
		
		CommonButton btn13 = new CommonButton();
		btn13.setName("公厕查询");
		btn13.setType("click");
		btn13.setKey("wcSearch");
		
		CommonButton btn14 = new CommonButton();
		btn14.setName("历史上的今天");
		btn14.setType("click");
		btn14.setKey("historyToday");
		
		CommonButton btn21 = new CommonButton();
		btn21.setName("歌曲点播");
		btn21.setType("click");
		btn21.setKey("musicRequest");
		
		CommonButton btn22 = new CommonButton();
		btn22.setName("今日头条");
		btn22.setType("click");
		btn22.setKey("todayNews");
		
		CommonButton btn23 = new CommonButton();
		btn23.setName("周边美食");
		btn23.setType("click");
		btn23.setKey("nearbyFood");
		
		CommonButton btn24 = new CommonButton();
		btn24.setName("人脸识别");
		btn24.setType("click");
		btn24.setKey("faceIdentification");
		
		CommonButton btn31 = new CommonButton();
		btn31.setName("Q友圈");
		btn31.setType("click");
		btn31.setKey("QFriend");
		
		CommonButton btn32 = new CommonButton();
		btn32.setName("电影推荐");
		btn32.setType("click");
		btn32.setKey("filmRecommend");
		
		CommonButton btn33 = new CommonButton();
		btn33.setName("幽默笑话");
		btn33.setType("click");
		btn33.setKey("humorJoke");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("生活助手");
		mainBtn1.setSub_button(new CommonButton[]{btn11, btn12, btn13,btn14});
		
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("休闲驿站");
		mainBtn2.setSub_button(new CommonButton[]{btn21, btn22, btn23,btn24});
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多体验");
		mainBtn3.setSub_button(new CommonButton[]{btn31, btn32, btn33});
		
		/**
		 * 这是公众号 terryroom 目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义 <br>
		 * 比如，第三个一个菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义: <br>
		 * menu.setButton(new Button[]{mainBtn1,mainBtn2,btn33})
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
		
		return menu;
	}
	

}
