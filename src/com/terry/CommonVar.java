package com.terry;

/**
 * 常量类
 * @author Administrator
 *
 */
public interface CommonVar {
	
	/*默认手机分页数*/
	public static int PHONE_PAGESIZE = 5;
	/*默认后台管理分页数*/
	public static int ADMIN_PAGESIZE = 10;
	
	/***session 用户 key **/
	public static String SESSION_WEIXIN = "SESSION_WEIXIN";
	
	public static String SESSION_STORE = "SESSION_STORE";
	
	/***上传图片前缀****/
	public static String IMG_PREFIX = "/resource/enjoylife";
	
	/******成功与否标志********/
	public static int SF_SUCCESS = 1;
	public static int SF_FAILURE = 0;
	
	/******图片上传状态********/
	public static int PICSTATUS_LOCAL = 1; //本地
	public static int PICSTATUS_CLOUD = 2; //云
	
	/*******使用标志*************/
	public static int USE_ONUSE = 1;
	public static int USE_NOUSE = 0;
}
