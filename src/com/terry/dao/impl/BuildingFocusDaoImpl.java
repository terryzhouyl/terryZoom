package com.terry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.dao.BuildingFocusDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingFocus;

@Repository("buildingFocusDaoImpl")
public class BuildingFocusDaoImpl extends BaseDaoImpl<BuildingFocus> implements BuildingFocusDao {
	
	public List<BuildingFocus> queryFocusList(BuildingFocus buildingFocus) {
		
		EnhancedRule rule = new EnhancedRule();	
		if(buildingFocus.getStoreId()!=null){
			rule.add(Restrictions.eq("storeId",buildingFocus.getStoreId()));
		}
		if(buildingFocus.getMemberId()!=null){
			rule.add(Restrictions.eq("memberId",buildingFocus.getMemberId()));
		}		
		return this.query(BuildingFocus.class, rule);
	};
	
	public Page<Map<String,Object>> queryFocusCasePage(Long memberId,int pageNum,int pageSize) {
		String sql = "SELECT bc.id,bc.storeId,bc.title,bc.description,bc.pictureUrl,bc.status,bs.coverPictureUrl as storePic,bs.title as storeTitle FROM "
				+ "el_building_case bc,el_building_store bs ,el_building_focus bf WHERE bf.storeId = bs.id AND bs.id = bc.storeId AND bf.memberId=:memberId order by bc.id desc ";
		String countSql = "select count(1) from el_building_case bc,el_building_store bs ,el_building_focus bf WHERE bf.storeId = bs.id AND bs.id = bc.storeId AND bf.memberId=:memberId";
		int offset = (pageNum-1)*pageSize;		
		Map<String,Object> paramsMap = new HashMap<>(); 
		paramsMap.put("memberId",memberId);	
		return this.queryPageForSql(sql, offset, pageSize, countSql, paramsMap);
	} 
}
