package com.terry.dao.impl;

import org.springframework.stereotype.Repository;

import com.terry.dao.ProvinceDao;
import com.terry.entity.Province;

@Repository("provinceDaoImpl")
public class ProvinceDaoImpl extends BaseDaoImpl<Province> implements ProvinceDao{

}
