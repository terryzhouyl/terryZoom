package com.terry.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.terry.dao.KeywordReplyDao;
import com.terry.dao.support.EnhancedRule;
import com.terry.entity.KeywordReply;

@Repository("keywordReplyDaoImpl")
public class KeywordReplyDaoImpl extends BaseDaoImpl<KeywordReply> implements KeywordReplyDao{

	@Resource(name="keywordReplyDaoImpl")
	KeywordReplyDao keywordReplyDaoImpl;
	
	@Override
	public List<KeywordReply> queryReplyInfo(KeywordReply keywordReply) {
		// TODO Auto-generated method stub
		
		EnhancedRule rule = new EnhancedRule();
		if(StringUtils.isNotBlank(keywordReply.getKeyword())){
			rule.add(Restrictions.ilike("keyword",keywordReply.getKeyword(), MatchMode.START));
		}				
		return this.query(KeywordReply.class,rule);
	}
			
	
}
