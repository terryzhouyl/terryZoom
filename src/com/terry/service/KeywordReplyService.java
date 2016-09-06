package com.terry.service;

import java.util.List;

import com.terry.entity.KeywordReply;

public interface KeywordReplyService {

	public List<KeywordReply> queryReplyList();
	
	public void saveReplyKeyword(KeywordReply keywordReply);
	
	public void savekey(Integer id,String keyword);
}
