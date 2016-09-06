package com.terry.dao;

import java.util.List;

import com.terry.dao.support.Page;
import com.terry.entity.BuildingStore;

public interface BuildingStoreDao extends BaseDao<BuildingStore>{
	
	Page<BuildingStore> queryPage(int pageSize,int pageNum,BuildingStore query);
	
	List<BuildingStore> queryList(BuildingStore query);
}	
