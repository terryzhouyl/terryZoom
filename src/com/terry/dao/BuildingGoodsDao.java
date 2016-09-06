package com.terry.dao;

import java.util.List;

import com.terry.dao.support.Page;
import com.terry.entity.BuildingGoods;

public interface BuildingGoodsDao extends BaseDao<BuildingGoods>{

	public Page<BuildingGoods> queryPage(BuildingGoods GoodsQuery,Integer pageSize,Integer PageNum);
	
	public List<BuildingGoods> queryList(BuildingGoods GoodsQuery);
}
