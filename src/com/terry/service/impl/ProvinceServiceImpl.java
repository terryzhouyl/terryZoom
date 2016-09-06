package com.terry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.terry.dao.ProvinceDao;
import com.terry.entity.Province;
import com.terry.service.ProvinceService;

@Service("provinceServiceImpl")
public class ProvinceServiceImpl implements ProvinceService{

	@Resource(name="provinceDaoImpl")
	ProvinceDao provinceDaoImpl;
	
	@Override
	public List<Province> queryAll() {
		// TODO Auto-generated method stub
		return 	provinceDaoImpl.findAll(Province.class);
	}
	
	
}
