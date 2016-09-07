package com.terry.service;

import javax.servlet.http.HttpServletRequest;

import com.terry.entity.BuildingStore;

public interface BaseService {

	/**
	 * 获取配置信息
	 * @param key
	 * @return
	 */
	public String getConfig(String key);
	
	/**
	 * 获取店铺信息
	 * @param request
	 * @return
	 */
	public BuildingStore getStoreInfo(HttpServletRequest request); 
}
