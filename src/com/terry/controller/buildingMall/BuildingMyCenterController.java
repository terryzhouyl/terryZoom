package com.terry.controller.buildingMall;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.controller.MyController;
import com.terry.entity.BuildingStore;
import com.terry.service.impl.BuildingGoodsService;
import com.terry.service.impl.BuildingStoreService;
import com.terry.util.ImageUtil;

@Controller("phoneBuildingMyCenterController")
@RequestMapping(value = "phone/buildingMyCenter")
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
	 * 2.商品管理
	 * @param goodsId	商品id
	 * @param operation	操作代码，1：删除  2：上架  3：下架
	 * @return
	 */
	@RequestMapping("/deleteGoods")
	public ResponseEntity<String> deleteGoods(Long goodsId,Integer operation){
		
	
		return null;
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
	@RequestMapping("/modifyAddressInfo")
	public String modifyAddressInfo(Model model,HttpServletRequest request,String title,Long buildingStoreId){ 
		return null;
	}
	
	/**
	 * 3.3 店铺设置 上传店铺头图
	 * @param model
	 * @param request
	 * @param title
	 * @param buildingStoreId
	 * @return
	 */
	@RequestMapping("/storeHeadImage")
	public String uploadStoreHeadImage(Model model,HttpServletRequest request,Long buildingStoreId){
		return null;
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
	 * 难道又是保存店铺头图 ？？？ 暂未可知
	 * @param 	
	 * @param 
	 * @return
	 */
	@RequestMapping("/saveHeadImage")
	public ResponseEntity<String> saveStoreHeadImage(HttpServletRequest request,String originalPicUrl,Long buildingStoreId){
		return null;
	}
	
	/**
	 * 5. 跳转个人设置页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/myConfigIndex")
	public String myConfigIndex(HttpServletRequest request,Model model){
		
		return "/phone/buildingMyCenter/myConfigIndex";
	}
	
	/**
	 * 5.1  保存 修改后的个人信息
	 * @return
	 */
	@RequestMapping("/savePersonalInfo")
	public ResponseEntity<String> savePersonalInfo(HttpServletRequest request,String nickname,Integer sex){
		return null;
	}
	
	
	/**
	 * 5.2 跳转修改个人信息页面  功能暂未可知
	 * @param request
	 * @param nickname
	 * @param sex
	 * @return
	 */
	@RequestMapping("/modifyPersonalInfo")
	public String modifyPersonalInfo(HttpServletRequest request,Model model,String modifyValue){
		
		model.addAttribute("modifyValue",modifyValue);
		return "/phone/buildingMyCenter/modifyPersonalInfo";
	}
	
	/**
	 * 6.跳转到商品管理页面
	 * @param storeId   店铺id  
	 * @param state		状态     1：上架    0：下架
	 * @return
	 */
	@RequestMapping("/goodsManage")
	public String goodsManage (Model model,HttpServletRequest request,Long storeId,Long state){
		return null;
	}
	
	/**
	 * 6.1 加载商品数据
	 * @param state		 状态     1：上架    0：下架
	 * @param storeId	 店铺id  
	 * @return
	 */
	@RequestMapping("/moreData")
	public ResponseEntity<String> moreData(Long state,Long storeId,int page){ 
		return null;
	}			
	
	/**
	 * 6.2添加商品页面
	 * @param model
	 * @param request
	 * @param storeId
	 * @return
	 */
	@RequestMapping("/addProduct")
	public String addProduct (Model model,HttpServletRequest request,Long storeId){
		return "/phone/buildingMyCenter/addProduct";
	}
	
	/**
	 * 6.3 保存新商品信息到数据库
	 * @param storeId
	 * @param url
	 * @param goodsName
	 * @param goodsPrice
	 * @return
	 */
	@RequestMapping("/add_product_sql")
	public ResponseEntity<String> add_product_sql(HttpServletRequest request,Long storeId,String url,String goodsName,Double goodsPrice){ 
		return null;
	}
	
	/**
	 * 6.4 商品管理(上架 下架)
	 * @param storeId   店铺id  
	 * @param state		状态     1：上架    0：下架
	 * @return
	 */
	@RequestMapping("/changeGoods")
	public ResponseEntity<String> goodsUnder(int page,Long storeId,Long state){
		return null;
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
		List<String> list = null;			
		BuildingStore store = buildingStoreService.getStoreInfo(request);
		if(store == null) {
			msg = "对不起，您暂未申请店铺";
			status = false;
		}
		else {			
			try{
				String path = "store/"+store.getId();
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
