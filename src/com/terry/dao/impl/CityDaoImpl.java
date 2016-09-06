package com.terry.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.terry.dao.CityDao;
import com.terry.entity.City;

@Repository("cityDaoImpl")
public class CityDaoImpl extends BaseDaoImpl<City> implements CityDao{

	@Override
	public List<City> queryList(Integer provinceId) {
		// TODO Auto-generated method stub
		String hql = "from City c where c.pid = :pid";
		Map<String, Object> map = new HashMap<>();		
		map.put("pid", provinceId);
		return this.find(hql, map);
	}

	
}
