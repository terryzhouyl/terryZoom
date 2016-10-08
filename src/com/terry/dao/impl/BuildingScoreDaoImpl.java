package com.terry.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.dao.BuildingScoreDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.entity.BuildingScore;

@Repository("buildingScoreDaoImpl")
public class BuildingScoreDaoImpl extends BaseDaoImpl<BuildingScore> implements BuildingScoreDao{

	@Override
	public Double getAvgScore(Long storeId) {
		// TODO Auto-generated method stub
		String hql = "select avg(b.score) from BuildingScore b where storeId=:storeId";
		Object obj = this.getSession().createQuery(hql).setLong("storeId",storeId).uniqueResult();		
		return (Double)obj;
	}

	@Override
	public List<BuildingScore> queryList(BuildingScore scoreQuery) {
		// TODO Auto-generated method stub
		EnhancedRule rule = new EnhancedRule();	
		if(scoreQuery.getStoreId() !=null) {			
			rule.add(Restrictions.eq("storeId", scoreQuery.getStoreId()));
		}
		if(scoreQuery.getUserId() !=null) {
			rule.add(Restrictions.eq("userId", scoreQuery.getUserId()));
		}
		return this.query(BuildingScore.class,rule);
	}
	
	
}
