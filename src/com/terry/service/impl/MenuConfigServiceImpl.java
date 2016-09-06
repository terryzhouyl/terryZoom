package com.terry.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.terry.dao.MenuConfigDao;
import com.terry.entity.MenuConfig;
import com.terry.service.MenuConfigService;

@Service("menuConfigServiceImpl")
public class MenuConfigServiceImpl implements MenuConfigService {

	@Resource(name="menuConfigDaoImpl")
	MenuConfigDao menuConfigDaoImpl;
	
	
	@Override
	public List<MenuConfig> getMenuConfigList() {
		// TODO Auto-generated method stub
		MenuConfig menuConfigQuery = new MenuConfig();
		menuConfigQuery.setNodeType(1); //父节点		
		List<MenuConfig> list =	menuConfigDaoImpl.queryList(menuConfigQuery);							
		for(int i=0;i<list.size();i++) {
			MenuConfig parentNode =	list.get(i);
			menuConfigQuery.setNodeType(2); //子节点
			menuConfigQuery.setParentId(parentNode.getId());
			List<MenuConfig> subList = menuConfigDaoImpl.queryList(menuConfigQuery); 
			parentNode.setListChildren(subList);
			parentNode.setChildrenListSize(subList.size());
		}				
		
		return list;
	}


	@Override
	public void saveMenu(Integer id,String menuName,Integer parentId) {
		// TODO Auto-generated method stub
		MenuConfig menuConfig = null;
		if(id == null) { //新增
			menuConfig = new MenuConfig();
			menuConfig.setMenuName(menuName);
			menuConfig.setParentId(parentId);
			menuConfig.setCreateTime(new Date());
			if(parentId!=null && parentId!=0) {
				menuConfig.setNodeType(2);
			}
			else {
				menuConfig.setNodeType(1);
			}
		}
		else { //编辑			
			menuConfig = menuConfigDaoImpl.get(MenuConfig.class, id);
			menuConfig.setMenuName(menuName);
			menuConfig.setParentId(parentId);
		}
		menuConfigDaoImpl.saveOrUpdate(menuConfig);		
	}


	@Override
	public void saveMenuInfo(MenuConfig menuConfig) {
		// TODO Auto-generated method stub
		
		MenuConfig menu = menuConfigDaoImpl.get(MenuConfig.class, menuConfig.getId());
		if(menu!=null) {
			menu.setMenuKey(menuConfig.getMenuKey());
			menu.setEventType(menuConfig.getEventType());
			menu.setRespInfo(menuConfig.getRespInfo());
			menu.setRespType(menuConfig.getRespType());
			menuConfigDaoImpl.saveOrUpdate(menu);
		}				
	}
	
}
