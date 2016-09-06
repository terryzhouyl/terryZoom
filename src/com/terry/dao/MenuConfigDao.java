package com.terry.dao;

import java.util.List;

import com.terry.entity.MenuConfig;

public interface MenuConfigDao extends BaseDao<MenuConfig>{
	
	public List<MenuConfig> queryList(MenuConfig menuConfig);
}
