package com.terry.dao;

import java.util.List;

import com.terry.entity.StoreTag;

public interface StoreTagDao extends BaseDao<StoreTag>{
	
	public List<StoreTag> queryList(StoreTag storeTagQuery);
}
