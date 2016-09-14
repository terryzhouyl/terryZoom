package com.terry.dao.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.CommonVar;
import com.terry.dao.BuildingGoodsDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingGoods;

@Repository("buildingGoodsDaoImpl")
public class BuildingGoodsDaoImpl extends BaseDaoImpl<BuildingGoods> implements BuildingGoodsDao{

	@Override
	public Page<BuildingGoods> queryPage(BuildingGoods GoodsQuery, Integer pageSize, Integer pageNum) {
		// TODO Auto-generated method stub
		EnhancedRule rule = new EnhancedRule();	
		if(GoodsQuery.getStoreId()!=null){
			rule.add(Restrictions.eq("storeId", GoodsQuery.getStoreId()));
		}
		
		if(GoodsQuery.getPutAwayStatus() != null){
			rule.add(Restrictions.eq("putAwayStatus", GoodsQuery.getPutAwayStatus()));
		}		
		if(GoodsQuery.getStatus() == null) { //使用标志，默认为使用
			rule.add(Restrictions.eq("status",CommonVar.USE_ONUSE));
		}
		else {
			rule.add(Restrictions.eq("status",GoodsQuery.getStatus()));
		}
		
		rule.addOrder(Order.desc("id"));
		
		rule.setOffset(pageNum>0?(pageNum - 1)*pageSize:0);
		rule.setPageSize(pageSize);
		
		return this.queryForPage(BuildingGoods.class,rule);
				
	}

	@Override
	public List<BuildingGoods> queryList(BuildingGoods GoodsQuery) {
		// TODO Auto-generated method stub
		EnhancedRule rule = new EnhancedRule();	
		if(GoodsQuery.getStoreId()!=null){
			rule.add(Restrictions.eq("storeId", GoodsQuery.getStoreId()));
		}		
		if(GoodsQuery.getStatus() == null) { //使用标志，默认为使用
			rule.add(Restrictions.eq("status",CommonVar.USE_ONUSE));
		}
		else {
			rule.add(Restrictions.eq("status",GoodsQuery.getStatus()));
		}
		if(GoodsQuery.getPutAwayStatus() != null){
			rule.add(Restrictions.eq("putAwayStatus", GoodsQuery.getPutAwayStatus()));
		}
		return this.queryList(GoodsQuery);
	}
	
	
}
