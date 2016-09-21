package com.terry.controller.buildingMall;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.CommonVar;
import com.terry.controller.MyController;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingGoods;
import com.terry.entity.BuildingStore;
import com.terry.entity.WeixinUser;
import com.terry.entity.specialbean.UploadFile;
import com.terry.service.impl.BuildingGoodsService;
import com.terry.service.impl.BuildingStoreService;
import com.terry.util.ImageUtil;

@Controller("phoneBuildingMyCenterController")
@RequestMapping(value = "/phone/buildingMyCenter")
public class BuildingMyCenterController extends MyController{
	
	@Resource(name="buildingGoodsService")
	BuildingGoodsService buildingGoodsService;
	
	@Resource(name="buildingStoreService")
	BuildingStoreService buildingStoreService;
	
	
	/**
	 * 1.进入个人中心
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/myCenter")
	public String myCenter(Model model,HttpServletRequest request){	
		
		BuildingStore store = buildingStoreService.getStoreInfo(request);
		
		model.addAttribute("buildingStore", store);
		
		return "/phone/buildingMyCenter/myCenter";	
	}
	
	/**
	 * 3.进入店铺设置主页
	 * @param model
	 * @param request
	 * @param buildingStoreId
	 * @return
	 */
	@RequestMapping("/storeConfigIndex")
	public String storeConfigIndex(Model model,HttpServletRequest request,Long storeId){
				
		BuildingStore store = buildingStoreService.getStoreInfo(request);
		model.addAttribute("info",store);	
		return "/phone/buildingMyCenter/storeConfigIndex";
	}
	
	/**
	 * 3.1 跳转到店铺设置 具体信息修改页
	 * @param model
	 * @param request
	 * @param key 修改的键
	 * @return
	 */
	@RequestMapping("/storeInfoModify")
	public String modifyStoreInfo(Model model,HttpServletRequest request,String key){
		
		BuildingStore store = buildingStoreService.getStoreInfo(request);
		model.addAttribute("info",store);
		model.addAttribute("key",key);
		return "/phone/buildingMyCenter/storeInfoModify";
		
	}
	
	/**
	 * 3.2 跳转到店铺设置 商铺地址修改页面
	 * @param model
	 * @param request
	 * @param title
	 * @param buildingStoreId
	 * @return
	 */
	@RequestMapping("/storeAddressModify")
	public String modifyAddressInfo(Model model,HttpServletRequest request,String title,Long buildingStoreId){ 
		
		BuildingStore store = buildingStoreService.getStoreInfo(request);
		model.addAttribute("buildingStore",store);
		
		return "/phone/buildingMyCenter/storeAddressModify";
	}
	
	/**
	 * 3.2.2 跳转到店铺设置 商铺地址修改页面
	 * @param model
	 * @param request
	 * @param title
	 * @param buildingStoreId
	 * @return
	 */
	@RequestMapping("/saveAddressInfo")
	public ResponseEntity<String> modifyAddressInfo(Model model,HttpServletRequest request,Integer cityId,String city,String province,String detailAddress){ 
		Boolean status = true;
		String msg = "保存成功";			
		if(cityId == null) {
			status = false;
			msg = "请选择城市";
		}
		else if(StringUtils.isBlank(province)) {
			status = false;
			msg = "请输入省份";
		}
		else if(StringUtils.isBlank(detailAddress)) {
			status = false;
			msg = "请输入详细地址";
		}
		else {
			
			try {
				buildingStoreService.saveStoreAddress(request,cityId, city, province, detailAddress);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				status = false;
				msg = "保存失败";
				//BuildingStore store = buildingStoreService.getStoreInfo(request);
				Long storeId = buildingStoreService.getStoreId(request);
				if(storeId!=null){					
					log.error("保存店铺:"+storeId+",保存失败",e);
				}
				else {
					log.error("保存店铺,保存失败",e);
				}
				e.printStackTrace();
			}
		}								
		return renderMsg(status, msg); 
	}
	
	/**
	 * 3.3 店铺设置 跳转到上传店铺头图
	 * @param model
	 * @param request
	 * @param title
	 * @param buildingStoreId
	 * @return
	 */
	@RequestMapping("/storeHeadPage")
	public String uploadStoreHeadImage(Model model,HttpServletRequest request,Long buildingStoreId){
		
		BuildingStore buildingStore = buildingStoreService.getStoreInfo(request);		
		model.addAttribute("coverPictureUrl",buildingStore.getCoverPictureUrl());
		model.addAttribute("buildingStoreId",buildingStoreId);
		
		return "/phone/buildingMyCenter/storeHeadPage";		
		
	}
	
	
	
	/**
	 * 3.4 保存店铺设置 修改信息
	 * @param 	
	 * @param 
	 * @return
	 */
	@RequestMapping("/saveStoreInfo")
	public ResponseEntity<String> modifyStoreInfo(HttpServletRequest request){ 
		Boolean status = true;
		String msg = "保存成功";
		BuildingStore store = null;
		String key = null;
		try{			
			store = buildingStoreService.getStoreInfo(request);
			key = buildingStoreService.saveStoreInfo(request,store);
		}
		catch(Exception e){
			status = false;
			msg = "保存失败";
			if(store!=null ) {
				log.error("店铺"+store.getId()+"保存信息"+key+"失败");
			}
			else {
				log.error("店铺保存信息"+key+"失败");
			}
		}
		return renderMsg(status, msg); 
	}
	
	
	/**
	 * 3.4.1 保存店铺头图
	 * @param 	
	 * @param 
	 * @return
	 */
	@RequestMapping("/saveHeadImage")
	public ResponseEntity<String> saveStoreHeadImage(Model model,HttpServletRequest request,Long buildingStoreId,@RequestParam("cmfile") CommonsMultipartFile cmfile,Integer fsize){
		
		Boolean status = true;
		String msg = "保存成功";
		try{						
			buildingStoreService.saveHeadImage(cmfile, buildingStoreId, fsize);
		}
		catch(Exception e){
			status = false;
			msg = "保存失败";
			if(buildingStoreId!=null ) {
				log.error("店铺"+buildingStoreId+"上传店铺头图失败");
			}			
		}
		return renderMsg(status, msg); 
	}
	
	/**
	 * 3.5   跳转修改店铺类型信息页面
	 * @param request
	 * @param model
	 * @param modifyValue
	 * @return
	 */	
	@RequestMapping("/modifyBuildingType")
	public String modifyBuildingType(HttpServletRequest request,Model model,Long buildingStoreId) {				
		
		//获取所有店铺类型
		
		return "/phone/buildingMyCenter/modifyBuildingType";
	}
	
	/**
	 * 3.6 保存修改店铺类型信息
	 * @param storeId
	 * @param buildingTypeId
	 * @param buildingTypeName
	 * @param mainBusiness
	 * @return
	 */
	@RequestMapping("/saveBuildingType")
	public ResponseEntity<String> saveBuildingType(Long storeId,Long buildingTypeId,String buildingTypeName,String mainBusiness){	
		return null;
	}
	
	/**
	 * 4.申请开店
	 * @param 	
	 * @param 
	 * @return
	 */
	@RequestMapping("/applyStore")
	public ResponseEntity<String> applyStore(HttpServletRequest request,String activeCode){
		return null;
	}
	
	/**
	 * 5. 跳转个人设置页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/userInfo")
	public String myConfigIndex(HttpServletRequest request,Model model){
		WeixinUser user = buildingStoreService.getWeixinUser(request);
		model.addAttribute("userInfo", user);
		return "/phone/buildingMyCenter/userInfo";
	}
	
	
	/**
	 * 5.1 跳转修改个人信息页面  
	 * @param request
	 * @param nickname
	 * @param sex
	 * @return
	 */
	@RequestMapping("/userInfoModify")
	public String modifyPersonalInfo(HttpServletRequest request,Model model,String modifyValue){
		
		model.addAttribute("modifyValue",modifyValue);
		return "/phone/buildingMyCenter/modifyPersonalInfo";
	}
	
	/**
	 * 5.2  保存 修改后的个人信息
	 * @return
	 */
	@RequestMapping("/saveUserInfo")
	public ResponseEntity<String> savePersonalInfo(HttpServletRequest request,String nickname,Integer sex){
		boolean status = true;
		String msg = "加载成功";
		
		try{
			buildingStoreService.saveUserInfo(request, sex, nickname);
		}
		catch(Exception e){
			e.printStackTrace();
			status = false;
			msg = "操作失败";  
			WeixinUser user = (WeixinUser)request.getSession().getAttribute(CommonVar.SESSION_WEIXIN);
			if(user == null) {				
				log.error("保存用户个人信息失败",e);				
			}
			else {
				log.error("保存用户:"+user.getId()+"个人信息失败",e);
			}
		}		
		return renderMsg(status, msg);
	}
	
	
	/**
	 * 6.跳转到商品管理页面
	 * @param storeId   店铺id  
	 * @param state		状态     1：上架    0：下架
	 * @return
	 */
	@RequestMapping("/goodsManage")
	public String goodsManage (Model model,HttpServletRequest request,Long storeId){
		model.addAttribute("storeId", storeId);
		return "/phone/buildingMyCenter/goodsManage";
	}
	
	/**
	 * 6.1 加载商品数据
	 * @param putAwayStatus		 状态     1：上架    0：下架
	 * @param storeId	 店铺id  
	 * @return
	 */
	@RequestMapping("/getGoodsPage")
	public ResponseEntity<String> getGoodsPage(HttpServletRequest request,BuildingGoods goodsQuery,Integer pageSize,Integer pageNum){
		boolean status = true;
		String msg = "加载成功";
		Page<BuildingGoods> page = null;
		if(goodsQuery.getPutAwayStatus() == null) {
			msg = "请选择商品类型";
			status = false;
		}
		else if(goodsQuery.getStoreId() == null) {
			msg = "请输入店铺";
			status = false;
		}
		else {			
			try{			
				page = buildingGoodsService.getGoodsPage(goodsQuery, pageSize, pageNum);
			}
			catch(Exception e) {
				e.printStackTrace();
				status = false;
				msg = "加载失败";
				log.error("加载店铺"+goodsQuery.getStoreId()+"商品数据第"+pageNum+"页失败",e);
			}
		}
		return renderPageData(status, msg, page);
	}			
	
	/**
	 * 6.2跳转到添加商品页面
	 * @param model
	 * @param request
	 * @param storeId
	 * @return
	 */
	@RequestMapping("/addGoods")
	public String addGoods (Model model,HttpServletRequest request,Long storeId){		
		model.addAttribute("storeId", storeId);
		return "/phone/buildingMyCenter/addGoods";
	}
	
	/**
	 * 6.3 保存新商品信息到数据库
	 * @param storeId
	 * @param url
	 * @param goodsName
	 * @param goodsPrice
	 * @return
	 */
	@RequestMapping("/saveGoods")
	public ResponseEntity<String> saveGoods(HttpServletRequest request,@RequestParam("cmfile") CommonsMultipartFile cmfile,BuildingGoods buildingGoods){
		boolean status = true;
		String msg = "保存成功";
		if(buildingGoods.getStoreId() == null) {
			status = false;
			msg = "请输入店铺id"; 
		}		
		else if(StringUtils.isBlank(buildingGoods.getGoodsName())){
			status = false;
			msg = "请输入商店名称";
		}
		else if(buildingGoods.getGoodsPrice() == null){
			status = false;
			msg = "请输入商品价格";
		}
		else {			
			try{
				buildingGoodsService.saveGoods(cmfile, buildingGoods, null);
			}
			catch(Exception e){
				status = false;
				msg = "保存失败";
				log.error("店铺"+buildingGoods.getStoreId() + "保存商品失败",e);
			}
		}
		return renderMsg(status, msg);
	}
	
	/**
	 * 6.4 商品管理(上架 下架)
	 * @param storeId   店铺id  
	 * @param state		状态     1：上架    0：下架
	 * @return
	 */
	@RequestMapping("/putAwayGoods")
	public ResponseEntity<String> putAwayGoods(Long goodsId){
		boolean status = true;
		String msg = "保存成功";		
		try{
			buildingGoodsService.putAwayGoods(goodsId);
		}
		catch(Exception e){
			status = false;
			msg = "保存失败";
			log.error("商品:"+goodsId+"上下架失败",e);
		} 
		return renderMsg(status, msg);
	}
	
	/**
	 * 6.5 删除商品
	 * @param goods  店铺id  
	 * @return
	 */
	@RequestMapping("/deleteGoods")
	public ResponseEntity<String> deleteGoods(Long goodsId){
		boolean status = true;
		String msg = "保存成功";		
		try{
			buildingGoodsService.deleteGoods(goodsId);
		}
		catch(Exception e){
			status = false;
			msg = "保存失败";
			log.error("商品:"+goodsId+"删除失败",e);
		} 
		return renderMsg(status, msg);
	}
	
	/**
	 * 7. 案例管理页面
	 * @param storeId   店铺id  
	 * @return
	 */
	@RequestMapping("/caseManage")
	public String caseManage (Model model,HttpServletRequest request,Long storeId){ 
		return null;
	}
	
	/**
	 * 7.1 加载案例数据
	 * @param storeId
	 * @param page
	 * @return
	 */
	@RequestMapping("/moreCaseData")
	public ResponseEntity<String> moreCaseData(Long storeId,int page){
		return null;
	}
	
	/**
	 * 7.2跳转 添加新案例页面
	 * @param model
	 * @param request
	 * @param storeId
	 * @return
	 */
	@RequestMapping("/addCase")
	public String add_case (Model model,HttpServletRequest request,Long storeId){ 						
		return "/phone/buildingMyCenter/addCase";
	}
	
	/**
	 * 7.3上传案例图片
	 * @return
	 */
	@RequestMapping(value = "/uploadCasePic")
	public ResponseEntity<String> uploadCasePic(Model model,@RequestParam("cmfile") CommonsMultipartFile[] cmfiles,HttpServletRequest request){
		boolean status = true;
		String msg = "上传成功";
		List<UploadFile> list = null;			
		BuildingStore store = buildingStoreService.getStoreInfo(request);
		if(store == null) {
			msg = "对不起，您暂未申请店铺";
			status = false;
		}
		else {			
			try{
				String path = buildingStoreService.getConfig("caseImgFile");
				list =	ImageUtil.uploadMutiPicture(cmfiles,null,path);
			}
			catch(Exception e){
				list = new ArrayList<>();
				status = false;
				msg = "上传失败";
				log.error("店铺"+store+"案例图片上传失败",e);
			}				
		}
		return renderData(status, msg, list);
	}
	
	/**
	 * 7.4 保存案例
	 * @param id
	 * @return
	 */
	@RequestMapping("/saveCase")
	public ResponseEntity<String> saveCase(String description,String imagePath,HttpServletRequest request){ 
		
		boolean status = true;
		String msg = "保存案例成功";
		Long caseId = null;	
		BuildingStore store = buildingStoreService.getStoreInfo(request);		
		if(store == null) {
			msg = "您暂未开启店铺，无法上传案例";
			status = false;
		}		
		else {			
			try{			
				caseId = buildingGoodsService.saveBuildingCase(description, imagePath,store.getId());
			}
			catch(Exception e) {
				status = false;
				msg = "保存案例失败";
				if(caseId == null) {				
					log.error("店铺"+store.getId()+"保存案例失败",e);
				}
				else {
					log.error("店铺"+store.getId()+"保存案例"+caseId+"失败",e);
				}
			}				
		}
		return renderMsg(status, msg);
	}
	
	/**
	 * 7.5 案例删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/dele")
	public ResponseEntity<String> dele(Long id){ 
		return null;
	}
	
	/**
	 * 4.跳转到激活店铺页面
	 * @param 	
	 * @param 
	 * @return
	 */
	@RequestMapping("/activeStoreIndex")
	public String activeStoreIndex(){
		return "/phone/buildingMyCenter/activeStoreIndex";
	}
	
	/**
	 * 5.激活店铺
	 * @param 	
	 * @param 
	 * @return
	 */
	@RequestMapping("/activeStore")
	public ResponseEntity<String> activeStore(HttpServletRequest request,String activeCode){ 
		return null;
	}
}
