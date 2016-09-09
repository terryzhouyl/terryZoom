package com.terry.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.dao.CasePicDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.entity.CasePic;

@Repository("casePicDaoImpl")
public class CasePicDaoImpl extends BaseDaoImpl<CasePic> implements CasePicDao{

	@Override
	public List<CasePic> queryList(CasePic casePicQuery) {
		// TODO Auto-generated method stub
		EnhancedRule rule = new EnhancedRule();	
		if(casePicQuery.getCaseId()!=null) {
			rule.add(Restrictions.eq("caseId",casePicQuery.getCaseId()));
		}
		if(casePicQuery.getStatus()==null){
			rule.add(Restrictions.eq("status",1));  //有效
 		}
		else {
			rule.add(Restrictions.eq("status",casePicQuery.getStatus()));
		}
		
		return this.query(CasePic.class, rule);
	}

	
}
