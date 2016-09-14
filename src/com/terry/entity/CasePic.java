package com.terry.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "el_case_pic")
public class CasePic extends GenericEntity<Long>{	
	
	private Long caseId;
	private String originalPicUrl;
	private String phonePicUrl;
	private String smallPicUrl;
	private Integer imageStatus;  //图片上传状态 1.云 0.本地
	private Integer status;
	private Date createTime;
	private String imageFile;
	
	
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
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
	
	public Integer getImageStatus() {
		return imageStatus;
	}
	public void setImageStatus(Integer imageStatus) {
		this.imageStatus = imageStatus;
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
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	
}
