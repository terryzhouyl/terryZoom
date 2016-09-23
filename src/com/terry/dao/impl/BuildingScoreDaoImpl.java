package com.terry.dao.impl;

import org.springframework.stereotype.Repository;

import com.terry.dao.BaseDao;
import com.terry.dao.BuildingScoreDao;
import com.terry.entity.BuildingScore;

@Repository("buildingScoreDaoImpl")
public class BuildingScoreDaoImpl extends BaseDaoImpl<BuildingScore> implements BuildingScoreDao{

	@Override
	public double getAvgScore() {
		// TODO Auto-generated method stub
		String hql = "select avg() from buildingScore";
		Object obj = this.getSession().createQuery(hql).uniqueResult();		
		return (double)obj;
	}
	
	
}
