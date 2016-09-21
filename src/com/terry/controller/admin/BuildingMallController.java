package com.terry.controller.admin;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.or.RendererMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.controller.MyController;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingStore;
import com.terry.entity.BuildingType;
import com.terry.service.impl.BuildingStoreService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("adminBuildingMallController")
@RequestMapping(value = "/admin/buildingMall")
public class BuildingMallController extends MyController{
	
	@Autowired
	BuildingStoreService buildingStoreService;
	
		
	/**
	 * 建材商铺列表 /admin/building/index.htm
	 * @param model
	 * @param request
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(Model model,HttpServletRequest request, BuildingStore query,Integer pageNum,Integer pageSize){
		Page<BuildingStore> page = buildingStoreService.getStorePage(pageSize, pageNum, query);	
		
	//	page.setHref("admin/buildingMall/index.htm");
		model.addAttribute("page", page);
		//model.addAttribute("list", list);
		
		return "/admin/buildingMall/index";
	}
	
	@RequestMapping(value = "/editStore")
	public String edit(Model model,HttpServletRequest request,Long storeId){
		if(storeId != null){			
			List<BuildingStore> list = buildingStoreService.getStoreList(null, storeId);
			if(list.size()>0) {
				model.addAttribute("buildingStore",list.get(0));
			}
		}		
		List<BuildingType> buildingTypeList = buildingStoreService.getBuildingType();
		model.addAttribute("buildingTypeList", buildingTypeList);
		return "/admin/buildingMall/editStore";
	} 
	
	/**
	 * 验证商家信息
	 * @param buildingStore
	 * @return
	 */
	public String[] checkStoreInfo(BuildingStore buildingStore){
		
		Boolean success = true;
		String msg = "操作成功";
		if(buildingStore.getBuildingTypeId() == null){
			msg = "请选择建材类型";
			success = false;
		}
		else if(StringUtils.isBlank(buildingStore.getContactPhone())){
			msg = "请输入电话号码";
			success = false;
		}
		else if(StringUtils.isBlank(buildingStore.getDescription())){
			msg = "请输入商家描述";
			success = false;
		}
		else if(StringUtils.isBlank(buildingStore.getMainBusiness())){
			msg = "请输入主营业务";
			success = false;
		}
		else if(StringUtils.isBlank(buildingStore.getTitle())){
			msg = "请输入标题";
			success = false;
		}
		else if(StringUtils.isBlank(buildingStore.getCity())){
			msg = "请选择市";
			success = false;
		}
		else if(StringUtils.isBlank(buildingStore.getDetailAddress())){
			msg = "请填写详细信息";
			success = false;
		}
		else if(StringUtils.isBlank(buildingStore.getPromotion())){
			msg = "请输入促销讯息";
			success = false;
		}
		return new String[]{success.toString(),msg};
	}
	
	/**
	 * 保存商家信息("有图片上传")
	 * @param model
	 * @param request
	 * @param buildingStore
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ResponseEntity<String> save(Model model,HttpServletRequest request,BuildingStore buildingStore,@RequestParam("cmfile") CommonsMultipartFile cmfile, Integer fsize){

		String[] result = checkStoreInfo(buildingStore);
		Boolean success = Boolean.valueOf(result[0]);
		String msg = result[1];
		if(success){
			try{
				buildingStoreService.saveOrUpdateStoreInfo(buildingStore,cmfile,fsize);				
			}
			catch (Exception e) {
				log.error("后台管理系统保存店铺失败",e);
				e.printStackTrace();
				success = false;
				msg = "保存失败";
			}
		}
		return renderMsg(success, msg);						
	}
	
	/**
	 * 保存商家信息 （没有上传图片）
	 * @param model
	 * @param request
	 * @param buildingTypeId
	 * @param buildingStore
	 * @return
	 */
	@RequestMapping(value = "/saveNoPic")
	public ResponseEntity<String> save(Model model,HttpServletRequest request,Integer buildingTypeId, BuildingStore buildingStore){
		
		String[] result = checkStoreInfo(buildingStore);
		Boolean success = Boolean.valueOf(result[0]);
		String msg = result[1];
		if(success){
			try{
				buildingStoreService.saveOrUpdateStoreInfo(buildingStore,null,null);				
			}
			catch (Exception e) {
				log.error("后台管理系统保存店铺失败",e);
				e.printStackTrace();
				success = false;
				msg = "保存失败";
			}
		}
		return renderMsg(success, msg);		
	} 
	
	
//	
//	/**
//	 * 编辑商品
//	 * @param model
//	 * @param request
//	 * @param buildingStoreId
//	 * @return
//	 */
//	@RequestMapping(value = "/editGoods")
//	public String editGoods(Model model,HttpServletRequest request,Long buildingGoodsId,Long buildingStoreId){
//		if(buildingGoodsId != null){						
//			BuildingGoods buildingGoods = buildingGoodsService.get(buildingGoodsId);
//			if(buildingGoods!=null){				
//				model.addAttribute("buildingGoods",buildingGoods);
//			}
//		}
//		model.addAttribute("buildingStoreId",buildingStoreId);
//		return "/admin/buildingMall/editGoods";
//	} 
//	
//	
//	/**
//	 * 保存商品
//	 * @param model
//	 * @param request
//	 * @param buildingGoods
//	 * @return
//	 */
//	@RequestMapping(value = "/saveGoods")
//	public ResponseEntity<String> saveGoods(Model model,HttpServletRequest request,BuildingGoods buildingGoods){
//		Boolean success = true;
//		String msg = "操作成功";
//		
//		if(buildingGoods.getStoreId() == null){
//			msg = "请传入商家id";
//			success = false;
//		}
//		else if(StringUtils.isBlank(buildingGoods.getGoodsName())){
//			msg = "请输入商品名称";
//			success = false;
//		}		
//		else if(StringUtils.isBlank(buildingGoods.getOriginalPicUrl())){
//			msg = "请上传图片";
//			success = false;
//		}	
//		else {
//			try{			
//				if(buildingGoods.getId() == null){ //新增
//					buildingGoods.setPutAwayStatus(1L);
//					String reSizePic = this.appResize(request, buildingGoods.getOriginalPicUrl(),true);
//					String cutPic = FileUtil.cutPic(request, reSizePic, "upload/cut", 0, 0, 320, 320);
//					buildingGoods.setCoverPictureUrl(cutPic);
//					buildingGoods.setPhonePicUrl(reSizePic);
//					buildingGoodsService.saveOrUpdate(buildingGoods);				
//				}
//				else {
//					BuildingGoods oldGoods = buildingGoodsService.get(buildingGoods.getId());					
//					buildingGoods.setPutAwayStatus(oldGoods.getPutAwayStatus());						
//					if(!buildingGoods.getOriginalPicUrl().equals(oldGoods.getOriginalPicUrl())){
//						//图片修改
//						String reSizePic = this.appResize(request, buildingGoods.getOriginalPicUrl(),true);
//						String cutPic = FileUtil.cutPic(request, buildingGoods.getOriginalPicUrl(), "upload/cut", 0, 0, 320, 320);
//						buildingGoods.setCoverPictureUrl(cutPic);
//						buildingGoods.setPhonePicUrl(reSizePic);
//					}
//					else{
//						buildingGoods.setCoverPictureUrl(oldGoods.getCoverPictureUrl());
//						buildingGoods.setPhonePicUrl(oldGoods.getPhonePicUrl());
//					}
//					buildingGoodsService.saveOrUpdate(buildingGoods);	
//				}
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//				success = false;
//				msg = "操作失败";
//			}
//		}			
//		return renderMsg(success, msg);	
//	}
//		

//	
//	/**
//	 * 保存案例
//	 * @param model
//	 * @param request
//	 * @param buildingGoods
//	 * @return
//	 */
//	@RequestMapping(value = "/saveCase")
//	public ResponseEntity<String> saveCase(Model model,HttpServletRequest request,BuildingCase buildingCase){
//		Boolean success = true;
//		String msg = "操作成功";
//		
//		if(buildingCase.getStoreId() == null){
//			msg = "请传入商家id";
//			success = false;
//		}
//		else if(StringUtils.isBlank(buildingCase.getPictureUrl())){
//			msg = "请上传案例图片";
//			success = false;
//		}
//		else if(StringUtils.isBlank(buildingCase.getDescription())){
//			msg = "请填写描述";
//			success = false;
//		}
//		else if(StringUtils.isBlank(buildingCase.getTitle())){
//			msg = "标题不能为空";
//		}
//		else {
//			try{				
//				String pictureUrl = buildingCase.getPictureUrl();
//				String picJson = null;
//				if(buildingCase.getId()==null){
//					//增加
//					String[] picArray = pictureUrl.split(",");
//					for(int i=0;i<picArray.length;i++){
//						String appPicUrl =  this.appResize(request, picArray[i],true);
//						String cutPicUrl =	FileUtil.cutPic(request, appPicUrl, "upload/cut", 0, 0, 320, 320);
//						picJson = FileUtil.addPicture(picJson,picArray[i],appPicUrl,cutPicUrl);
//					}				
//				}
//				else{
//					//更新
//					BuildingCase oldCase = buildingCaseService.get(buildingCase.getId());
//					String oldCasePic = oldCase.getPictureUrl();
//					picJson = oldCase.getPictureUrl();
//					String[] picArray = pictureUrl.split(",");
//					for(int i=0;i<picArray.length;i++){
//						if(oldCasePic.indexOf(picArray[i])>-1){
//							//已经存在
//							continue;
//						}
//						else{
//							String appPicUrl =  this.appResize(request, picArray[i],true);
//							String cutPicUrl =	FileUtil.cutPic(request, appPicUrl, "upload/cut", 0, 0, 320, 320);
//							picJson = FileUtil.addPicture(picJson,picArray[i],appPicUrl,cutPicUrl);
//						}
//					}															
//				}
//				buildingCase.setPictureUrl(picJson);
//				buildingCase.setStatus(1);
//				buildingCaseService.saveOrUpdate(buildingCase);				
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//				success = false;
//				msg = "操作失败";
//			}
//		}			
//		return renderMsg(success, msg);	
//	}
//	
//	/**
//	 * 删除案例图片
//	 * @param model
//	 * @param request
//	 * @param buildingGoods
//	 * @return
//	 */
//	@RequestMapping(value = "/delPicture")
//	public ResponseEntity<String> delCase(Model model,HttpServletRequest request,Long id,Integer index){
//		
//		Boolean success = true;
//		String msg = "删除成功";
//		
//		try{
//			BuildingCase buildingCase =	buildingCaseService.get(id);
//			JSONArray jsonArray = JSONArray.fromObject(buildingCase.getPictureUrl());
//			for(int i=0;i<jsonArray.size();i++){			
//				if(i == index){	
//					String context = request.getServletContext().getRealPath("/");
//					JSONObject jsonObject = (JSONObject)jsonArray.get(i);
//					String cutPicUrl = jsonObject.getString("cutPicUrl");
//					if(StringUtils.isNotBlank(cutPicUrl)){
//						File file = new File(context+cutPicUrl);
//						if(file.exists()){
//							file.delete();
//						}
//					}
//					String originalPicUrl = jsonObject.getString("originalPicUrl");
//					if(StringUtils.isNotBlank(originalPicUrl)){
//						File file = new File(context+originalPicUrl);
//						if(file.exists()){
//							file.delete();
//						}
//					}					
//					String phonePicUrl = jsonObject.getString("phonePicUrl");
//					if(StringUtils.isNotBlank(phonePicUrl)){
//						File file = new File(context+phonePicUrl);
//						if(file.exists()){
//							file.delete();
//						}
//					}		
//					jsonArray.remove(i);
//				}			
//			}
//			buildingCase.setPictureUrl(jsonArray.toString());
//			buildingCaseService.saveOrUpdate(buildingCase);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			success = false;
//			msg = "删除失败";
//		} 				
//		return renderMsg(success,msg);
//	}
//	
//	/**
//	 * 删除案例
//	 * @return
//	 */
//	@RequestMapping(value = "/delCase")
//	public ResponseEntity<String> deleteCase(Long caseId) {
//		String msg = "删除成功";
//		boolean status = true;
//		BuildingCase buildingCase =	buildingCaseService.get(caseId);	
//		if(buildingCase!=null){
//			try{
//				buildingCase.setStatus(0); //删除案例
//				buildingCaseService.saveOrUpdate(buildingCase);
//			}
//			catch (Exception e){
//				e.printStackTrace();
//				msg = "删除失败";
//				status = false;
//			}
//		}
//		else {
//			msg = "案例不存在";
//			status = false;
//		}
//		return renderMsg(status, msg);
//	} 
//	
//	/**
//	 * 商品上下架
//	 * @param goodsId
//	 * @return
//	 */
//	@RequestMapping(value = "/putAwayGoods")
//	public ResponseEntity<String> putAwayGoods (Long goodsId) {
//		String msg = "修改成功";
//		boolean status = true;
//		BuildingGoods buildingGoods = buildingGoodsService.get(goodsId);	
//		if(buildingGoods!=null){
//			try{
//				if(buildingGoods.getPutAwayStatus() == 1){ //在架上，要下架
//					buildingGoods.setPutAwayStatus(0L);
//				} 
//				else {
//					buildingGoods.setPutAwayStatus(1L);
//				}
//				buildingGoodsService.saveOrUpdate(buildingGoods);
//			}
//			catch (Exception e){
//				e.printStackTrace();
//				msg = "修改失败";
//				status = false;
//			}
//		}
//		else {
//			msg = "商品不存在";
//			status = false;
//		}
//		return renderMsg(status, msg);
//	}
}
