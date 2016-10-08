package com.terry.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 店铺标签关系表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "store_tag")
public class StoreTag extends GenericEntity<Long>{
	
	private Long storeId;
	private Integer tagId;
	private String tagName;
	private Date createTime;
	
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
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
