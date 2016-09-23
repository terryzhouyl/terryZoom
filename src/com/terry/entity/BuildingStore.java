package com.terry.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 商家表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "el_building_store")
public class BuildingStore extends GenericEntity<Long>{
	
	private String title; //标题
	private String content; //内容
	private String mainBusiness; //主营业务
	private String province; //省
	private String city;//市
	private String district; //区
	private String detailAddress; //详细地址
	private String promotion; //促销信息
	private String contactPhone; //联系电话
	private String businessTime; //营业时间
	private String description; //描述
	private Long memberId; //用户Id
	private String imageFile; //文件路径
	private String coverPictureUrl; //店铺封面图片
	private Integer buildingTypeId; //建材类别id
	private String buildingTypeName; //建材类别名称
	private String storeNo; //店铺id
	private String originalPicUrl; //原始图片路径
	private String smallPicUrl; //小圖片
	private Integer cityId; //城市
	@Transient
	private Integer isFocus; //是否关注
	private Integer imageStatus; //是否上传至七牛云 1.是 2.否	
	private String tags; //相关标签组
	private Double score; //评分
			
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public Integer getImageStatus() {
		return imageStatus;
	}
	public void setImageStatus(Integer imageStatus) {
		this.imageStatus = imageStatus;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMainBusiness() {
		return mainBusiness;
	}
	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getBusinessTime() {
		return businessTime;
	}
	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCoverPictureUrl() {
		return coverPictureUrl;
	}
	public void setCoverPictureUrl(String coverPictureUrl) {
		this.coverPictureUrl = coverPictureUrl;
	}
	public String getBuildingTypeName() {
		return buildingTypeName;
	}
	public void setBuildingTypeName(String buildingTypeName) {
		this.buildingTypeName = buildingTypeName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getOriginalPicUrl() {
		return originalPicUrl;
	}
	public void setOriginalPicUrl(String originalPicUrl) {
		this.originalPicUrl = originalPicUrl;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Integer getBuildingTypeId() {
		return buildingTypeId;
	}
	public void setBuildingTypeId(Integer buildingTypeId) {
		this.buildingTypeId = buildingTypeId;
	}
	public String getSmallPicUrl() {
		return smallPicUrl;
	}
	public void setSmallPicUrl(String smallPicUrl) {
		this.smallPicUrl = smallPicUrl;
	}
	@Transient
	public Integer getIsFocus() {
		return isFocus;
	}
	@Transient
	public void setIsFocus(Integer isFocus) {
		this.isFocus = isFocus;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
}
