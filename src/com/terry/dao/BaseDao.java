package com.terry.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	
	//根据ID加载
	public T get(Class<T> entityClazz,Serializable id);
	//保存实体
	public T saveOrUpdate(T entity);
	//删除实体
	public void delete(T entity);
	//根据ID删除实体
	public void delete(Class<T> entityClazz ,Serializable id);
	//获取所有实体
	public List<T> findAll(Class<T> entityClazz);
	//获取所有实体,支持排序
	public List<T> findAll(Class<T> entityClass, String orderBy,boolean isAsc);
	//获取实体总数
	public long findCount(Class<T> entityClazz);
	//根据单个属性获取实体对象
	public T getBy(Class<T> entityClass,String propertyName,Object value); 
	//批量更新实体
	public void batchSaveOrUpdate(List<T> entitys);
}
