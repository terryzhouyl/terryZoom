package com.terry.dao;

import java.util.List;

import com.terry.entity.BuildingTag;

public interface BuildingTagDao extends BaseDao<BuildingTag>{

	/**
	 * 查询最大的标签
	 * @return
	 */
	public Integer queryMaxTagNo();
	
	/**
	 * 查询所有有效标签
	 * @return
	 */
	public List<BuildingTag> findAllTags();
	
	/**
	 * 通过标签来查询店铺
	 */
	public List<BuildingTag> queryBuildingStoresByTag();
}
