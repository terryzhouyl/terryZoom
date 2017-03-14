package com.terry.dao;

import java.util.List;

import com.terry.entity.Material;

public interface MaterialDao extends BaseDao<Material>{

	List<Material> queryList(Material materialQuery);
}
