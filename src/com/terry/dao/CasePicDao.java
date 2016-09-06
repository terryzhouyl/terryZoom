package com.terry.dao;

import java.util.List;

import com.terry.entity.CasePic;

public interface CasePicDao extends BaseDao<CasePic>{

	public List<CasePic> queryList(CasePic query);
}
