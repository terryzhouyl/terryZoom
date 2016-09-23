package com.terry.dao.impl;

import java.util.List;

import com.terry.dao.BuildingTagDao;
import com.terry.entity.BuildingTag;

public class BuildingTagDaoImpl extends BaseDaoImpl<BuildingTag> implements BuildingTagDao{

	@Override
	public Integer queryMaxTagNo() {
		// TODO Auto-generated method stub
		
		String hql = "select max(tagNo) from BuildingTag";
		Object obj = this.getSession().createQuery(hql).uniqueResult();
		return Integer.parseInt(obj.toString());
	}

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
