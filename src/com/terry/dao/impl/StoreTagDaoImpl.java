package com.terry.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.dao.StoreTagDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.entity.StoreTag;

@Repository("storeTagDaoImpl")
public class StoreTagDaoImpl extends BaseDaoImpl<StoreTag> implements StoreTagDao{

	@Override
	public List<StoreTag> queryList(StoreTag storeTagQuery) {
		// TODO Auto-generated method stub
		
		EnhancedRule rule = new EnhancedRule();
		if(storeTagQuery.getStoreId() != null){
			rule.add(Restrictions.eq("storeId",storeTagQuery.getStoreId()));
		}			
		return this.query(StoreTag.class, rule);
	}	
	
}
