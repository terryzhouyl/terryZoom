package com.terry.service;

import java.util.List;

import com.terry.entity.MenuConfig;

public interface MenuConfigService {
	
	public List<MenuConfig> getMenuConfigList();
	
	public void saveMenu(Integer id,String menuName,Integer parentId);
	
	public void saveMenuInfo(MenuConfig menuConfig);
}
