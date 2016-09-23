package com.terry.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 建材标签表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "el_building_tag")
public class BuildingTag extends GenericEntity<Integer>{
	
	private Integer tagNo;
	private String tagName;
	private Date createTime;
			
	public Integer getTagNo() {
		return tagNo;
	}
	public void setTagNo(Integer tagNo) {
		this.tagNo = tagNo;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
