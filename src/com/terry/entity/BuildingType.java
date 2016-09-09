package com.terry.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 装饰分类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "el_building_type")
public class BuildingType extends GenericEntity<Integer>{
	
	private String typeName;  //建材类别名称
	private Integer status;  //使用标志 1.启用 0.没用
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	} 
	
	
}
