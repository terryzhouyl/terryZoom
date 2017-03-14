package com.terry.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.dao.MaterialDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.entity.BuildingScore;
import com.terry.entity.CasePic;
import com.terry.entity.Material;

@Repository("materialDaoImpl")
public class MaterialDaoImpl extends BaseDaoImpl<Material> implements MaterialDao{

	@Override
	public List<Material> queryList(Material materialQuery) {
		
		EnhancedRule rule = new EnhancedRule();	
		if(materialQuery.getGroupId()!=null) {
			rule.add(Restrictions.eq("groupId",materialQuery.getGroupId()));
		}
		return this.query(Material.class,rule);
	}
}
