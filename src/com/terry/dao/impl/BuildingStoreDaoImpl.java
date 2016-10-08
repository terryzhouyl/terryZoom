package com.terry.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.dao.BuildingStoreDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingStore;

@Repository("buildingStoreDaoImpl")
public class BuildingStoreDaoImpl extends BaseDaoImpl<BuildingStore> implements BuildingStoreDao {

	@Override
	public Page<BuildingStore> queryPage(int pageSize, int pageNum, BuildingStore query) {
		// TODO Auto-generated method stub
		EnhancedRule rule = new EnhancedRule();	
		if(query.getBuildingTypeId() != null){
			rule.add(Restrictions.eq("buildingTypeId", query.getBuildingTypeId()));
		}			
		rule.setOffset(pageNum>0?(pageNum - 1)*pageSize:0);
		rule.setPageSize(pageSize);
		return this.queryForPage(BuildingStore.class,rule);
	}

	@Override
	public List<BuildingStore> queryList(BuildingStore query) {
		// TODO Auto-generated method stub
		//EnhancedRule rule = new EnhancedRule();	
		
		return this.queryList(query);
	}
	
	@Override
	public List<BuildingStore> queryListByTag(Integer tagId) {		
		String hql = "select bs from BuildingStore bs,StoreTag st where bs.id = st.storeId and tagId=:tagId";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("tagId",tagId);
		return this.find(hql, paramMap);				
	}
	
	@Override
	public Long getStoreIdByMemberId(Long memberId) {
		// TODO Auto-generated method stub
		String sql = "select id from el_building_Store where memberId=:memberId";
		//List<Long> paramList = new ArrayList<>();
		//paramList.add(memberId);
		Object result =	getSession().createQuery(sql).setParameter("memberId",memberId).uniqueResult();
		if(result!=null) {
			return Long.parseLong(result.toString());
		}
		else {
			return null;
		}		
	}

	@Override
	public List<Map<String, Object>> queryStoreStatisticInfo() {
		// TODO Auto-generated method stub
		String sql = "SELECT "+
				 "bs.storeNo AS 'storeNo',wu.nickname AS 'nickname',bs.title AS 'title',bs.contactPhone AS 'contackPhone',"+
				 "CONCAT(bs.province,bs.city,bs.detailAddress) AS 'address',bs.promotion AS 'promotion',bs.mainBusiness AS 'mainBusiness',bs.businessTime AS 'businessTime' "+
				 "FROM el_building_store bs,weixin_user wu WHERE bs.`memberId` = wu.id ";
		return	this.queryListForSql(sql, null);
	}
	
	
}
