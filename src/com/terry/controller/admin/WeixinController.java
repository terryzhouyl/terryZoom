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
import com.terry.entity.MenuConfig;
import com.terry.service.impl.KeywordReplyService;
import com.terry.service.impl.MenuConfigService;

@Controller("adminweixinController")
@RequestMapping(value = "/admin/weixin")
public class WeixinController extends MyController{
	
	@Resource(name="keywordReplyService")
	KeywordReplyService keywordReplyService;
	
	@Resource(name="menuConfigService")
	MenuConfigService menuConfigService;	
	
	/**
	 * 加载所有关键字回复信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/replyIndex")
	public String replyIndex(Model model,HttpServletRequest request){		
		List<KeywordReply> list = keywordReplyService.queryReplyList();
		model.addAttribute("keywordList",list);
		return "/admin/weixin/replyIndex";
	}
	
	/**
	 * 保存关键字回复信息
	 * @param keywordReply
	 * @return
	 */
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
				keywordReplyService.saveReplyKeyword(keywordReply);
			}
			catch(Exception e){
				msg = "请求失败";
				status = false;
			}			
		}
		return renderMsg(status, msg);
	}
	
	/**
	 * 保存关键字
	 * @param id
	 * @param keyword
	 * @return
	 */
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
				keywordReplyService.savekey(id,keyword);
			}
			catch(Exception e) {
				msg = "请求失败";
				status = false;
			}			
		}
		return renderMsg(status,msg);
	}
		
	
	/**
	 * 加载微信菜单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/menuIndex")
	public String index(Model model,HttpServletRequest request){
		List<MenuConfig> list =	menuConfigService.getMenuConfigList();	
		model.addAttribute("listCustomMenu", list);
		return "/admin/customMenu/menuIndex";
	}
	
	/**
	 * 保存菜单树节点信息
	 * @param parentId
	 * @param menuName
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/saveMenu")
	public ResponseEntity<String> saveMenu(Integer parentId,String menuName,Integer id) {
		String msg = "保存成功";
		boolean status = true;
		if(StringUtils.isBlank(menuName)){
			status = false;
			msg = "菜单名不能为空";
		}
		else if(parentId == null) {
			status = false;
			msg = "父节点id不能为空";
		}
		else {			
			try{
				menuConfigService.saveMenu(id,menuName,parentId);
			}
			catch(Exception e){
				msg = "保存失败";
				status = false;
			}
		}
		return renderMsg(status, msg);
	}
	
	/**
	 * 保存菜单功能
	 * @param parentId
	 * @param menuName
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/saveMenuInfo")
	public ResponseEntity<String> saveMenuInfo(MenuConfig menuConfig) {
		String msg = "保存成功";
		boolean status = true;
		if(StringUtils.isBlank(menuConfig.getMenuKey())){
			status = false;
			msg = "请输入菜单的键";
		}
		else if(StringUtils.isBlank(menuConfig.getRespInfo())) {
			status = false;
			msg = "回复内容不能为空";
		}
		else if(StringUtils.isBlank(menuConfig.getRespType())) {
			status = false;
			msg = "请输入返回类型";
		}
		else if(menuConfig.getId() == null) {
			status = false;
			msg = "菜单id不能为空";
		}
		else if(menuConfig.getEventType() == null) {
			status = false;
			msg = "菜单类型不能为空";
		}
		else {			
			try{
				menuConfigService.saveMenuInfo(menuConfig);
			}
			catch(Exception e){
				msg = "保存失败";
				status = false;
			}
		}
		return renderMsg(status, msg);
	}
}
