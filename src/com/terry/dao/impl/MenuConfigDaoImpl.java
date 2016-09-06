package com.terry.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.dao.MenuConfigDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.entity.MenuConfig;

@Repository("menuConfigDaoImpl")
public class MenuConfigDaoImpl extends BaseDaoImpl<MenuConfig> implements MenuConfigDao{
	
	public List<MenuConfig> queryList(MenuConfig menuConfig) {
		
		EnhancedRule rule = new EnhancedRule();
		if(menuConfig.getNodeType() !=null){
			rule.add(Restrictions.eq("nodeType",menuConfig.getNodeType()));
		}
		if(menuConfig.getParentId() !=null) {
			rule.add(Restrictions.eq("parentId",menuConfig.getParentId()));
		}
		return this.query(MenuConfig.class, rule);
	}
}

