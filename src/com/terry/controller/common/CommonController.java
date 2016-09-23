package com.terry.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.terry.controller.MyController;
import com.terry.entity.City;
import com.terry.entity.Province;
import com.terry.service.impl.CityService;
import com.terry.service.impl.CommonService;
import com.terry.service.impl.ProvinceService;
import com.terry.util.Auth;

@Controller("commonController")
@RequestMapping(value = "/common")
public class CommonController extends MyController{
	
	@Resource(name="cityService")
	CityService cityService;
	
	@Resource(name="provinceService")
	ProvinceService provinceService;
		
	@Resource(name="commonService")
	CommonService commonService; 
	
	@RequestMapping("/getCities")
	public ResponseEntity<String> getCities (Integer provinceId) {
		
		String msg = "请求成功";
		boolean status = true;
		List<City> list = null; 
		try{
			list = cityService.queryAll(provinceId);
		}
		catch(Exception e){			
			status = false;
			msg = "请求失败";
			e.printStackTrace();
		}
		return renderData(status, msg, list);	
	}
	
	@RequestMapping("/getProvinces")
	public ResponseEntity<String> getProvinces () {
		
		String msg = "请求成功";
		boolean status = true;
		List<Province> list = null; 
		try{
			list = provinceService.queryAll();
		}
		catch(Exception e){			
			status = false;
			msg = "请求失败";
			e.printStackTrace();
		}
		return renderData(status, msg, list);
	}
	
	@RequestMapping("/getUploadToken")
	public ResponseEntity<String> getUploadToken () {
		
		String msg = "请求成功";
		boolean status = true;
		Map<String, String> map = new HashMap<>();
		try{
			map.put("uptoken",commonService.getQiniuUploadToken());
		}
		catch(Exception e){			
			status = false;
			msg = "请求失败";
			log.error("获取七牛上传凭证失败");
			e.printStackTrace();
		}
		return renderData(status, msg, map);
	}
}
