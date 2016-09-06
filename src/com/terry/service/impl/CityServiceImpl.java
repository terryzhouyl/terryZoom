package com.terry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.terry.dao.CityDao;
import com.terry.entity.City;
import com.terry.service.CityService;

@Service("cityServiceImpl")
public class CityServiceImpl implements CityService{

	@Resource(name="cityDaoImpl")
	private CityDao cityDaoImpl;
	
	@Override
	public List<City> queryAll(Integer provinceId) {
		// TODO Auto-generated method stub
		return cityDaoImpl.queryList(provinceId);
	}
	
	
}
