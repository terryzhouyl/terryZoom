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
import com.terry.entity.MenuConfig;
import com.terry.service.MenuConfigService;

@Controller("adminMenuConfigController")
@RequestMapping("/admin/customMenu")
public class MenuConfigController extends MyController{
	
	@Resource(name="menuConfigServiceImpl")
	MenuConfigService menuConfigServiceImpl;		
	
	@RequestMapping(value="/menuIndex")
	public String index(Model model,HttpServletRequest request){
		

		List<MenuConfig> list =	menuConfigServiceImpl.getMenuConfigList();	
		model.addAttribute("listCustomMenu", list);
		return "/admin/customMenu/menuIndex";
	}
	
	//保存菜单
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
				menuConfigServiceImpl.saveMenu(id,menuName,parentId);
			}
			catch(Exception e){
				msg = "保存失败";
				status = false;
			}
		}
		
		return renderMsg(status, msg);
	}
	
	/**
	 * 保存所有
	 * @param parentId
	 * @param menuName
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/saveMenuInfo")
	public ResponseEntity<String> saveMenu(MenuConfig menuConfig) {
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
				menuConfigServiceImpl.saveMenuInfo(menuConfig);
			}
			catch(Exception e){
				msg = "保存失败";
				status = false;
			}
		}
		
		return renderMsg(status, msg);
	}
}
