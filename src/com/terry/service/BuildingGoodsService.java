package com.terry.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.dao.support.Page;
import com.terry.entity.BuildingCase;
import com.terry.entity.BuildingGoods;

public interface BuildingGoodsService extends BaseService{

	/**
	 * 分页获取案例数据
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public Page<BuildingCase> getCasePage(BuildingCase caseQuery,Integer pageSize,Integer pageNum);
	
	/**
	 * 根据查询条件 或 id直接获取数据
	 * @param goodsQuery
	 * @param BuildingGodsId
	 * @return
	 */
	public List<BuildingCase> getCaseList(BuildingCase caseQuery,Integer buildingCaseId);
	
	
	/**
	 * 分页获取商品数据
	 * @param goodsQuery
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public Page<BuildingGoods> getGoodsPage(BuildingGoods goodsQuery,Integer pageSize,Integer pageNum);
	
	/**
	 * 根据查询条件 或 id直接获取数据
	 * @param goodsQuery
	 * @param BuildingGodsId
	 * @return
	 */
	public List<BuildingGoods> getGoodsList(BuildingGoods goodsQuery,Integer buildingGoodsId);
		
	/**
	 * 保存案例
	 * @param buildingCase
	 */
	public void saveCase(BuildingCase buildingCase);
	
	/**
	 * 保存商品
	 */
	public void saveGoods( CommonsMultipartFile cmfile,BuildingGoods buildingGoods,Integer fsize);
	
	/**
	 * 保存建材案例
	 * @param description
	 * @param imagePath
	 */
	public Integer saveBuildingCase(String description,String imagePath,Integer storeId);
}
