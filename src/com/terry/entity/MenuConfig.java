package com.terry.entity;

import java.util.ArrayList;
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
 * 菜单配置表
 * @author Administrator
 *
 */
@Entity
@Table(name="menu_config")
public class MenuConfig {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private String menuKey;
	private String menuName;
	private String eventType;
	private String respType;
	private String respInfo;
	private Integer nodeType;
	private Integer parentId;
	private Date createTime;
	@Transient
	private List<MenuConfig> listChildren = new ArrayList<>();
	@Transient
	private Integer childrenListSize;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuKey() {
		return menuKey;
	}
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getRespType() {
		return respType;
	}
	public void setRespType(String respType) {
		this.respType = respType;
	}
	public String getRespInfo() {
		return respInfo;
	}
	public void setRespInfo(String respInfo) {
		this.respInfo = respInfo;
	}
	public Integer getNodeType() {
		return nodeType;
	}
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Transient
	public List<MenuConfig> getListChildren() {
		return listChildren;
	}
	@Transient
	public void setListChildren(List<MenuConfig> listChildren) {
		this.listChildren = listChildren;
	}
	@Transient
	public Integer getChildrenListSize() {
		return childrenListSize;
	}
	@Transient
	public void setChildrenListSize(Integer childrenListSize) {
		this.childrenListSize = childrenListSize;
	}
	
	
	
}
