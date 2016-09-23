package com.terry.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.terry.CommonVar;
import com.terry.dao.BuildingStoreDao;
import com.terry.entity.BuildingStore;
import com.terry.entity.WeixinUser;
import com.terry.util.SystemProperties;

public class BaseService{
	
	@Resource(name="systemProperties")
	private SystemProperties systemProperties;

	/**
	 * 获得配置文件内容
	 */
	public String getConfig(String key) {
		return systemProperties.getProperties(key);
	}	
	
}
