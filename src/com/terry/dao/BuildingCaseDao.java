package com.terry.dao;

import java.util.List;

import com.terry.dao.support.Page;
import com.terry.entity.BuildingCase;

public interface BuildingCaseDao extends BaseDao<BuildingCase>{
	
	public Page<BuildingCase> queryPage(BuildingCase buildingCase,Integer pageNum,Integer pageSize);
	
	public List<BuildingCase> queryList(BuildingCase buildingCase);
}
