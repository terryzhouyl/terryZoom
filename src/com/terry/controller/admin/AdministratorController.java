package com.terry.controller.admin;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("adminAdministratorController")
@RequestMapping(value = "/admin/administrator")
public class AdministratorController {

	
	/**
	 * 后台首页
	 * @param model
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index")
	public String index(Model model,HttpServletRequest request) throws Exception {
		return "/admin/index";
	}
	
	
}
