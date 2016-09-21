package com.terry.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.terry.controller.MyController;

@Controller("adminstatisticController")
@RequestMapping("/admin/statistic")
public class StatisticController extends MyController{
	
	
	
	@RequestMapping("/exportStoreData")
	public ResponseEntity<String> exportStoreData() {
		Boolean status = true;
		String msg = "导出成功";
		try{
			
		}
		catch(Exception e){
			e.printStackTrace();
			status = false;
			msg = "导出失败";
		}
		return renderMsg(status, msg);
	}
}
