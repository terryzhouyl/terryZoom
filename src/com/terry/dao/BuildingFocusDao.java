package com.terry.dao;

import java.util.List;
import java.util.Map;

import com.terry.dao.support.Page;
import com.terry.entity.BuildingFocus;

public interface BuildingFocusDao extends BaseDao<BuildingFocus> {
	
	public List<BuildingFocus> queryFocusList(BuildingFocus buildingFocus);
	
	public Page<Map<String,Object>> queryFocusCasePage(Long memberId,int pageNum,int pageSize);
}
