package com.terry.dao.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.dao.BuildingCaseDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingCase;

@Repository("buildingCaseDaoImpl")
public class BuildingCaseDaoImpl extends BaseDaoImpl<BuildingCase> implements BuildingCaseDao{

	@Override
	public Page<BuildingCase> queryPage(BuildingCase buildingCase, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub	
		EnhancedRule rule = new EnhancedRule();		
		if(buildingCase.getStoreId() != null){
			rule.add(Restrictions.eq("storeId", buildingCase.getStoreId()));
		}
		
		if(buildingCase.getStatus() != null){
			rule.add(Restrictions.eq("status", buildingCase.getStatus()));
		}
		rule.addOrder(Order.desc("id"));	
		
		rule.setOffset(pageNum>0?(pageNum - 1)*pageSize:0);
		rule.setPageSize(pageSize);
		
		return this.queryForPage(BuildingCase.class, rule);
	}

	@Override
	public List<BuildingCase> queryList(BuildingCase buildingCase) {
		// TODO Auto-generated method stub
		EnhancedRule rule = new EnhancedRule();		
		if(buildingCase.getStoreId() != null){
			rule.add(Restrictions.eq("storeId", buildingCase.getStoreId()));
		}
		
		if(buildingCase.getStatus() != null){
			rule.add(Restrictions.eq("status", buildingCase.getStatus()));
		}
		return this.queryList(buildingCase);
	}
	
	
}
