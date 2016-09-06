package com.terry.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.dao.BuildingStoreDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingStore;

@Repository("buildingStoreDaoImpl")
public class BuildingStoreDaoImpl extends BaseDaoImpl<BuildingStore> implements BuildingStoreDao {

	@Override
	public Page<BuildingStore> queryPage(int pageSize, int pageNum, BuildingStore query) {
		// TODO Auto-generated method stub
		EnhancedRule rule = new EnhancedRule();	
		if(query.getBuildingTypeId() != null){
			rule.add(Restrictions.eq("buildingTypeId", query.getBuildingTypeId()));
		}			
		rule.setOffset(pageNum>0?(pageNum - 1)*pageSize:0);
		rule.setPageSize(pageSize);
		return this.queryForPage(BuildingStore.class,rule);
	}

	@Override
	public List<BuildingStore> queryList(BuildingStore query) {
		// TODO Auto-generated method stub
		EnhancedRule rule = new EnhancedRule();			
		return null;
	}
	
	
}
