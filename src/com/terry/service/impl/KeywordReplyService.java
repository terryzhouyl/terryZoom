package com.terry.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.terry.dao.KeywordReplyDao;
import com.terry.entity.KeywordReply;


@Service("keywordReplyService")
public class KeywordReplyService{

	@Resource(name="keywordReplyDaoImpl")
	KeywordReplyDao keywordReplyDaoImpl;
	
	public List<KeywordReply> queryReplyList() {
		// TODO Auto-generated method stub
		
		List<KeywordReply> list =	keywordReplyDaoImpl.findAll(KeywordReply.class);
				
		return list;
	}

	public void saveReplyKeyword(KeywordReply keywordReply) {
		// TODO Auto-generated method stub
		
		if(keywordReply.getId() == null) { //新增
			keywordReply.setCreateTime(new Date());
			keywordReplyDaoImpl.saveOrUpdate(keywordReply);
		}
		else {
			KeywordReply oldKeywordReply = 	keywordReplyDaoImpl.get(KeywordReply.class, keywordReply.getId());
			if(oldKeywordReply != null) {
				oldKeywordReply.setKeyword(keywordReply.getKeyword());
				oldKeywordReply.setReplyContent(keywordReply.getReplyContent());
				oldKeywordReply.setReplyType(keywordReply.getReplyType());
				oldKeywordReply.setMaterialId(keywordReply.getMaterialId());
				keywordReplyDaoImpl.saveOrUpdate(oldKeywordReply);
			}
		}						
	}

	public void savekey(Integer id,String keyword) {
		// TODO Auto-generated method stub
		if(id == null) {
			KeywordReply keywordReply = new KeywordReply();
			keywordReply.setKeyword(keyword);
			keywordReply.setCreateTime(new Date());
			keywordReplyDaoImpl.saveOrUpdate(keywordReply);
		}
		else {
			KeywordReply keywordReply =	keywordReplyDaoImpl.get(KeywordReply.class, id);
			if(keywordReply!= null) {				
				keywordReply.setKeyword(keyword);
				keywordReplyDaoImpl.saveOrUpdate(keywordReply);
			}
		}
	}
	
	
}
