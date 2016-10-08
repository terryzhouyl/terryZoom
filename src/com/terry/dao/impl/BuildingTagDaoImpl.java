package com.terry.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.terry.dao.BuildingTagDao;
import com.terry.entity.BuildingTag;

@Service("buildingTagDaoImpl")
public class BuildingTagDaoImpl extends BaseDaoImpl<BuildingTag> implements BuildingTagDao{

	@Override
	public List<BuildingTag> findAllTags() {
		// TODO Auto-generated method stub
		String hql = "from BuildingTag b where b.status = 1 ";		
		return this.find(hql,null);
	}

	@Override
	public List<BuildingTag> queryBuildingStoresByTag() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	
}
