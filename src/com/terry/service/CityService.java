package com.terry.service;

import java.util.List;

import com.terry.entity.City;

public interface CityService {

	public List<City> queryAll(Integer provinceId);
}
