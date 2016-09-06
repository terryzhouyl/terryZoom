package com.terry.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.dao.support.Page;
import com.terry.entity.BuildingCase;
import com.terry.entity.BuildingGoods;
import com.terry.entity.BuildingStore;
import com.terry.entity.BuildingType;

public interface BuildingStoreService {
	
	/**
	 * 微信登录
	 * @param request
	 * @param code
	 * @return
	 */
	public int weixinLogin(HttpServletRequest request,String code);
	
	/**
	 * 获得所有建材类型
	 * @return
	 */
	public List<BuildingType> getBuildingType();
	
	/**
	 * 分页获取商铺列表
	 * @return
	 */
	public Page<BuildingStore> getStorePage(Integer pageSize,Integer pageNum,BuildingStore query);
	
	/**
	 * 建材店铺详情
	 */
	public BuildingStore storeInfo(HttpServletRequest request,Integer storeId);
	
	/**
	 * 分页获取关注列表
	 */
	public Page<Map<String,Object>> getFocusList(HttpServletRequest request,Integer pageSize,Integer pageNum);
	
	/**
	 * 关注店铺
	 */
	public void focusStore(HttpServletRequest request,Integer userId,Integer storeId);
	
	/**
	 * 根据类型获取商铺数据，或直接根据Id获取数据
	 * @param goodsQuery
	 * @param storeId
	 * @return
	 */
	public List<BuildingStore> getStoreList(BuildingStore goodsQuery,Integer storeId); 
	
	/**
	 * 保存或更新店铺信息
	 * @return
	 */
	public void saveOrUpdateStoreInfo(BuildingStore buildingStore,CommonsMultipartFile cmfile,Integer fsize);
}
