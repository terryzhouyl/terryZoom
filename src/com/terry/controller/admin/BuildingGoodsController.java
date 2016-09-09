package com.terry.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.controller.MyController;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingCase;
import com.terry.entity.BuildingGoods;
import com.terry.service.impl.BuildingGoodsService;
import com.terry.util.ImageUtil;

@Controller("adminbuildingGoodsController")
@RequestMapping("/admin/buildingGoods")
public class BuildingGoodsController extends MyController{
	
	@Resource(name="buildingGoodsService")
	private BuildingGoodsService buildingGoodsService;
	
	/**
	 * 商品列表页
	 * @param model
	 * @param request
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/goodsIndex")
	public String goodsIndex(Model model,HttpServletRequest request,Integer pageNum,Integer pageSize,BuildingGoods goodsQuery){
		
		Page<BuildingGoods> page = buildingGoodsService.getGoodsPage(goodsQuery, pageSize, pageNum);				
		model.addAttribute("page", page);
		model.addAttribute("storeId",goodsQuery.getStoreId());
		return "/admin/buildingMall/goodsIndex";
	}
	
	
	/**
	 * 商品案例列表页
	 * @param model
	 * @param request
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/caseIndex")
	public String caseIndex(Model model,HttpServletRequest request,BuildingCase caseQuery,Integer pageSize,Integer pageNum){
		Page<BuildingCase> page = buildingGoodsService.getCasePage(caseQuery, pageSize, pageNum);
		model.addAttribute("page", page);
		model.addAttribute("storeId", caseQuery.getStoreId());
		return "/admin/buildingMall/caseIndex";
	}
	
	/**
	 * 编辑案例
	 * @param model
	 * @param request
	 * @param buildingStoreId
	 * @return
	 */
	@RequestMapping(value = "/editCase")
	public String editCase(Model model,HttpServletRequest request,Long buildingCaseId,Long storeId){
		if(buildingCaseId != null){						
			List<BuildingCase> list =	buildingGoodsService.getCaseList(null, buildingCaseId);
			BuildingCase buildingCase = null;
			if(list.size() > 0) {
				buildingCase = list.get(0);
			}
			model.addAttribute("buildingCase",buildingCase);
		}
		model.addAttribute("storeId",storeId);
		return "/admin/buildingMall/editCase";
	} 
	
	/**
	 * 上传案例图片
	 */
	@RequestMapping(value = "/uploadCasePic")
	public ResponseEntity<String> uploadCasePic(@RequestParam("cmfile") CommonsMultipartFile[] cmfiles, Integer fsize,String fileFolder,Integer storeId) {
		boolean status = true;
		String msg = "上传成功";
		List<String> list = null;
		try{
			String path = "store/"+storeId;
			list =	ImageUtil.uploadMutiPicture(cmfiles,null,path);
		}
		catch(Exception e){
			list = new ArrayList<>();
			status = false;
			msg = "上传失败";
			log.error("店铺"+storeId+"案例图片上传失败",e);
		}				
		return renderData(status, msg, list);
	}
	
	
	/**
	 * 保存案例
	 * @param model
	 * @param request
	 * @param buildingGoods
	 * @return
	 */
	@RequestMapping(value = "/saveCase")
	public ResponseEntity<String> saveCase(Model model,HttpServletRequest request,BuildingCase buildingCase){
		Boolean success = true;
		String msg = "操作成功";
		
		if(buildingCase.getStoreId() == null){
			msg = "请传入商家id";
			success = false;
		}
		else if(StringUtils.isBlank(buildingCase.getPictureUrl())){
			msg = "请上传案例图片";
			success = false;
		}
		else if(StringUtils.isBlank(buildingCase.getDescription())){
			msg = "请填写描述";
			success = false;
		}
		else if(StringUtils.isBlank(buildingCase.getTitle())){
			msg = "标题不能为空";
		}
		else {
			try{				
				buildingGoodsService.saveCase(buildingCase);			
			}
			catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = "操作失败";
				log.error("案例 :"+buildingCase.getId()+"保存失败",e);
			}
		}			
		return renderMsg(success, msg);	
	}
	
	
	/**
	 * 编辑商品页面
	 * @param model
	 * @param request
	 * @param buildingStoreId
	 * @return
	 */
	@RequestMapping(value = "/editGoods")
	public String editGoods(Model model,HttpServletRequest request,Long goodsId,Long storeId){

		List<BuildingGoods> list =	buildingGoodsService.getGoodsList(null,goodsId);
		if(list.size() > 0) {
			model.addAttribute("buildingGoods", list.get(0));
		}
		model.addAttribute("storeId",storeId);		
		return "/admin/buildingMall/editGoods";
	} 
	
	/**
	 * 保存商品信息 有上传图片
	 * @param model
	 * @param request
	 * @param buildingGoods
	 * @return
	 */
	@RequestMapping(value = "/saveGoods")
	public ResponseEntity<String> saveGoods(Model model,HttpServletRequest request,BuildingGoods buildingGoods,@RequestParam("cmfile") CommonsMultipartFile cmfile, Integer fsize){
		
		String msg = "保存成功";
		boolean success = true;
		
		if(buildingGoods.getStoreId() == null){
			msg = "请传入商家id";
			success = false;
		}
		else if(StringUtils.isBlank(buildingGoods.getGoodsName())){
			msg = "请输入商品名称";
			success = false;
		}		
		else if(StringUtils.isBlank(buildingGoods.getOriginalPicUrl())){
			msg = "请上传图片";
			success = false;
		}	
		if(success){
			try{
				buildingGoodsService.saveGoods(cmfile,buildingGoods,fsize);				
			}
			catch (Exception e) {
				log.error("后台管理系统保存商品失败",e);
				e.printStackTrace();
				success = false;
				msg = "保存失败";
			}
		}
		return renderMsg(success, msg);				
	}
	
	/**
	 * 保存商品信息(无图片)
	 * @param model
	 * @param request
	 * @param buildingStore
	 * @return
	 */
	@RequestMapping(value = "/saveGoodsNoPic")
	public ResponseEntity<String> save(Model model,HttpServletRequest request,BuildingGoods buildingGoods){
		
		String msg = "保存成功";
		boolean success = true;
		if(buildingGoods.getStoreId() == null){
			msg = "请传入商家id";
			success = false;
		}
		else if(StringUtils.isBlank(buildingGoods.getGoodsName())){
			msg = "请输入商品名称";
			success = false;
		}		
		else if(StringUtils.isBlank(buildingGoods.getOriginalPicUrl())){
			msg = "请上传图片";
			success = false;
		}			
		if(success){
			try{
				buildingGoodsService.saveGoods(null,buildingGoods,null);				
			}
			catch (Exception e) {
				log.error("后台管理系统保存商品失败",e);
				e.printStackTrace();
				success = false;
				msg = "保存失败";
			}
		}
		return renderMsg(success, msg);						
	}
}
