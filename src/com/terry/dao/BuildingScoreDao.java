package com.terry.dao;

import com.terry.entity.BuildingScore;

public interface BuildingScoreDao extends BaseDao<BuildingScore>{
	
	/**
	 * 获得平均分
	 * @return
	 */
	public double getAvgScore();
}
