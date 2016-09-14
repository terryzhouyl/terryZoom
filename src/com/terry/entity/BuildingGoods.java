package com.terry.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 建材商品
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "el_building_goods")
public class BuildingGoods extends GenericEntity<Long>{
	
	private Long storeId; //商家id
	private String smallPicUrl; //封面图片
	private String goodsName; //商品名称
	private Double goodsPrice; //商品价格
	private String unit; //单位
	private String description; //描述
	private Integer putAwayStatus; //1:上架   0:下架
	private String originalPicUrl;	//上传图片路径
	private String phonePicUrl;	//压缩图片
	private String imageFile; //图片路径
	private Integer status;
	
	public String getPhonePicUrl() {
		return phonePicUrl;
	}
	public void setPhonePicUrl(String phonePicUrl) {
		this.phonePicUrl = phonePicUrl;
	}
	public String getOriginalPicUrl() {
		return originalPicUrl;
	}
	public void setOriginalPicUrl(String originalPicUrl) {
		this.originalPicUrl = originalPicUrl;
	}
	
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Integer getPutAwayStatus() {
		return putAwayStatus;
	}
	public void setPutAwayStatus(Integer putAwayStatus) {
		this.putAwayStatus = putAwayStatus;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSmallPicUrl() {
		return smallPicUrl;
	}
	public void setSmallPicUrl(String smallPicUrl) {
		this.smallPicUrl = smallPicUrl;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}	
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
