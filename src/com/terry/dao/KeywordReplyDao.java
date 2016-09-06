package com.terry.dao;

import java.util.List;
import java.util.Map;

import com.terry.entity.KeywordReply;

public interface KeywordReplyDao extends BaseDao<KeywordReply>{
	
	public List<KeywordReply> queryReplyInfo(KeywordReply keywordReply) ;
		
}
