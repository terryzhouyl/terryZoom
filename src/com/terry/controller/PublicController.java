package com.terry.controller;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller()
@RequestMapping(value = "/public")
public class PublicController extends MyController {
	
	/**
	 * 公共页面
	 * @param model
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{page}")
	public String index(Model model,@PathVariable(value = "page") String page,String memberPhone) throws Exception {
		if(StringUtils.isNotBlank(memberPhone)){
			model.addAttribute("memberPhone", memberPhone);
		}
		return "/public/"+page;
	}
		
	/**
	 * 登录  /public/login.htm
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String login(Model model){
		return "/public/index";
	}
}
