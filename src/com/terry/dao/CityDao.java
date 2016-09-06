package com.terry.dao;

import java.util.List;

import com.terry.entity.City;

public interface CityDao extends BaseDao<City>{

	public List<City> queryList(Integer provinceId);
}
