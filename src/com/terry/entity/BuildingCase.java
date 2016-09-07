package com.terry.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 实务案例
 * @author Administrator
 *
 */
@Entity
@Table(name = "el_building_case")
public class BuildingCase{
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private Integer storeId; //店铺id
	@Transient
	private String storePic; //店铺图片
	private String title; //标题
	private String description; //描述
	private String pictureUrl; //封面图片路径	
	private Integer status;	//(1.使用 0.删除)	
	private Date createTime; //创建时间
		
	@Transient
	private List<String> caselist;
	@Transient
	public List<String> getCaselist() {
		return caselist;
	}
	@Transient
	public void setCaselist(List<String> caselist) {
		this.caselist = caselist;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Transient
	public String getStorePic() {
		return storePic;
	}
	@Transient
	public void setStorePic(String storePic) {
		this.storePic = storePic;
	}			
}
