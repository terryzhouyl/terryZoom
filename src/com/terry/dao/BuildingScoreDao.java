package com.terry.dao;

import java.util.List;

import com.terry.entity.BuildingScore;

public interface BuildingScoreDao extends BaseDao<BuildingScore>{
	
	/**
	 * 获得平均分
	 * @return
	 */
	public Double getAvgScore(Long storeId);
	
	/**
	 * 获得评分
	 * @return
	 */
	public List<BuildingScore> queryList(BuildingScore scoreQuery);		
}
