package com.terry.entity;

import java.util.Date;


@SuppressWarnings("serial")
public class CasePic extends GenericEntity<Long>{	
	
	private Integer caseId;
	private String originalPicUrl;
	private String phonePicUrl;
	private String smallPicUrl;
	private String ImageStatus;
	private Integer status;
	private Date createTime;
	
	
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	public String getOriginalPicUrl() {
		return originalPicUrl;
	}
	public void setOriginalPicUrl(String originalPicUrl) {
		this.originalPicUrl = originalPicUrl;
	}
	public String getPhonePicUrl() {
		return phonePicUrl;
	}
	public void setPhonePicUrl(String phonePicUrl) {
		this.phonePicUrl = phonePicUrl;
	}
	public String getSmallPicUrl() {
		return smallPicUrl;
	}
	public void setSmallPicUrl(String smallPicUrl) {
		this.smallPicUrl = smallPicUrl;
	}
	public String getImageStatus() {
		return ImageStatus;
	}
	public void setImageStatus(String imageStatus) {
		ImageStatus = imageStatus;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
