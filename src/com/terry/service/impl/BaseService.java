package com.terry.service.impl;

import javax.annotation.Resource;

import com.terry.util.SystemProperties;

public class BaseService {
	
	@Resource(name="systemProperties")
	private SystemProperties systemProperties;

	/**
	 * 获得配置文件内容
	 */
	public String getConfig(String key) {
		return systemProperties.getProperties(key);
	}
}
