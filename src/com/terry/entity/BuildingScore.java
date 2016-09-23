package com.terry.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 建材评分表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "el_building_tag")
public class BuildingScore extends GenericEntity<Long>{
	
	private Double score;
	private Long userId;
	private Long storeId;
	private Date createTime;
	
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
