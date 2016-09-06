package com.terry.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.terry.controller.MyController;
import com.terry.entity.KeywordReply;
import com.terry.service.KeywordReplyService;

@Controller("adminKeywordReplyController")
@RequestMapping(value = "/admin/replyKeyword")
public class KeywordReplyController extends MyController{
	
	@Resource(name="keywordReplyServiceImpl")
	KeywordReplyService keywordReplyServiceImpl;
	
	@RequestMapping(value="/replyIndex")
	public String index(Model model,HttpServletRequest request){
		
		List<KeywordReply> list = keywordReplyServiceImpl.queryReplyList();
		
		model.addAttribute("keywordList",list);
				
		return "/admin/replyKeyword/replyIndex";
	}
	
	@RequestMapping(value="/keywordSave")
	public ResponseEntity<String> keywordSave(KeywordReply keywordReply) {
		
		String msg = "请求成功";
		boolean status = true;
		
		if(StringUtils.isBlank(keywordReply.getKeyword())){
			msg = "请设置回复关键字";
			status = false;
		}
		else if(StringUtils.isBlank(keywordReply.getReplyContent())){
			msg = "请设置回复内容";
			status = false;
		}
		else {			
			try{
				keywordReplyServiceImpl.saveReplyKeyword(keywordReply);
			}
			catch(Exception e){
				msg = "请求失败";
				status = false;
			}			
		}
		return renderMsg(status, msg);
	}
	
	
	@RequestMapping(value="/savekey")
	public ResponseEntity<String> keywordSave(Integer id,String keyword){
		String msg = "请求成功";
		boolean status = true;
		
		if(StringUtils.isBlank(keyword)){
			msg = "请输入关键字";
			status = false;
		}
		else {			
			try {
				keywordReplyServiceImpl.savekey(id,keyword);
			}
			catch(Exception e) {
				msg = "请求失败";
				status = false;
			}			
		}
		return renderMsg(status,msg);
				
	}
		
}
