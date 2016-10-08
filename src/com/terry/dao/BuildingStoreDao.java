package com.terry.dao;

import java.util.List;
import java.util.Map;

import com.terry.dao.support.Page;
import com.terry.entity.BuildingStore;

public interface BuildingStoreDao extends BaseDao<BuildingStore>{
	
	Page<BuildingStore> queryPage(int pageSize,int pageNum,BuildingStore query);
	
	List<BuildingStore> queryList(BuildingStore query);
	
	Long getStoreIdByMemberId(Long memberId);
	
	List<Map<String, Object>> queryStoreStatisticInfo();
	
	/**
	 * 通过tag查询storeList
	 * @param tagId
	 * @return
	 */
	List<BuildingStore> queryListByTag(Integer tagId);
}	
