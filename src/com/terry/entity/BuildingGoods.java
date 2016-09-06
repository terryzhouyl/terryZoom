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
@Entity
@Table(name = "el_building_goods")
public class BuildingGoods{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private Integer storeId; //商家id
	private String smallPicUrl; //封面图片
	private String goodsName; //商品名称
	private Double goodsPrice; //商品价格
	private String unit; //单位
	private Integer putAwayStatus; //1:上架   0:下架
	private String originalPicUrl;	//上传图片路径
	private String phonePicUrl;	//压缩图片
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getPutAwayStatus() {
		return putAwayStatus;
	}
	public void setPutAwayStatus(Integer putAwayStatus) {
		this.putAwayStatus = putAwayStatus;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
