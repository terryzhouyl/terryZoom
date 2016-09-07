package com.terry.controller.buildingMall;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.terry.BusinessException;
import com.terry.CommonVar;
import com.terry.controller.MyController;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingCase;
import com.terry.entity.BuildingGoods;
import com.terry.entity.BuildingStore;
import com.terry.entity.BuildingType;
import com.terry.entity.WeixinUser;
import com.terry.service.BuildingGoodsService;
import com.terry.service.BuildingStoreService;

@Controller("phoneBuildingMallController")
@RequestMapping(value = "phone/buildingMall")
public class BuildingStoreController extends MyController {
	
	@Resource(name="buildingStoreServiceImpl")
	BuildingStoreService buildingStoreServiceImpl;
		
	@Resource(name="buildingGoodsServiceImpl")
	BuildingGoodsService buildingGoodsServiceImpl;
	
	/****
	 * 异步加载 路径命名都以 动词开头  并且每个catch中必须都得 打印错误日志
	 * 跳转页面 路径命名都是 名词
	 */

	/**
	 * 1.微信登录
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value="/weixinLogin")
	public String weixinLogin(Model model,HttpServletRequest request,HttpServletResponse response,String code) { 
		
		
		return "redirect:/phone/buildingMall/index.htm";
	}
	
	/**
	 * 2.首页  建材列表 获取建材类型
	 */
	@RequestMapping(value="/index")
	public String index(Model model,HttpServletRequest request){ 
		
		/********测试数据，模拟用户登录的入口**********/
		WeixinUser user = new WeixinUser();
		user.setId(1);
		user.setNickname("刘欣星");
		user.setHeadimgurl("http://wx.qlogo.cn/mmopen/PL5y7QQHZgJicBb9y5ibeyHIydlrccylPOqYK08icarePso7zyyedGumdHTvv5MjZsLVjibdunBqNhiaMYRO0J5KNmBBicJv9Ol3Fh/0");	
		user.setSex(1);
		
		/********测试数据，获取用户的店铺信息**********/
		BuildingStore query = new BuildingStore();
		query.setMemberId(user.getId());
		List<BuildingStore> list = buildingStoreServiceImpl.getStoreList(query,null);
		if(list.size()>0) {			
			request.getSession().setAttribute(CommonVar.SESSION_STORE,list.get(0));
		}		
	    request.getSession().setAttribute(CommonVar.SESSION_WEIXIN, user);
	    
		List<BuildingType> typeList = buildingStoreServiceImpl.getBuildingType();
		model.addAttribute("typeList",typeList);
		
		return "phone/buildingMall/index";
	}
	
	/**
	 * 3.根据建材类型  异步 分页获得商家店铺
	 * @return
	 */
	@RequestMapping("/getStoreList")
	public ResponseEntity<String> getBuildingStoreList(Integer pageNum,Integer pageSize,BuildingStore buildingStore){
		
		Boolean status = true;
		String msg = "请求成功";
		Page<BuildingStore> page = null;
		if(buildingStore.getBuildingTypeId() == null) {
			status = false;
			msg = "请选择建材类型";
		}		
		else {			
			try{
				page = buildingStoreServiceImpl.getStorePage(pageSize, pageNum, buildingStore);					
			}catch(Exception e){
				status = false;
				msg = "请求失败";
				log.error("查询建材类型 buildingTypeId : "+buildingStore.getBuildingTypeId()+"下第"+pageNum+"页店铺信息失败",e);
			}		
		}
		return renderPageData(status, msg, page);
	} 
	
		
	/**
	 * 4.建材店铺详情
	 * @param model
	 * @param request
	 * @param storeId
	 * @return
	 */
	@RequestMapping(value="/storeInfo")
	public String storeInfo(Model model,HttpServletRequest request,Integer storeId) {
				
		if(storeId != null) {			
			BuildingStore storeInfo = buildingStoreServiceImpl.storeInfo(request, storeId);
			model.addAttribute("info",storeInfo);
		} 		
		return "phone/buildingMall/storeInfo";
	}
	
	/**
	 * 4.2 分页加载建材店铺  案例或商品
	 * @param type 1案例 2商品 
	 * @param storeId 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getStoreCaseOrGoods")
	public ResponseEntity<String> getStoreCaseOrGoods(Integer type,Integer storeId,Integer pageNum,Integer pageSize){ 
		
		Boolean status = true;
		String msg = "请求成功";
				
		Page<BuildingCase> casePage = null;
		Page<BuildingGoods> goodsPage = null;
		if(storeId == null) {
			status = false;
			msg = "请选择店铺";
		}
		else {			
			try{ 
				if(type == 1) {
					BuildingCase caseQuery = new BuildingCase();
					caseQuery.setStoreId(storeId);
					casePage = buildingGoodsServiceImpl.getCasePage(caseQuery, pageSize, pageNum);
				}
				else {
					BuildingGoods goodsQuery = new BuildingGoods();
					goodsQuery.setStoreId(storeId);
					goodsPage = buildingGoodsServiceImpl.getGoodsPage(goodsQuery, pageSize, pageNum);
				}
			}
			catch(Exception e){
				if(type == 1) {
					log.error("查询店铺 storeId : "+storeId+"第"+pageNum+"页案例失败 ",e);
				}
				else {
					log.error("查询店铺  storeId : "+storeId+"第"+pageNum+"页商品失败",e);
				}
				status = false;
				msg = "请求失败";
			}	
			
		}
		if(type == 1) {
			return renderPageData(status, msg, casePage);
		}
		else {
			return renderPageData(status, msg, goodsPage);
		}
	}
	
	/**
	 * 5.跳转关注页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/myFocus")
	public String myFocus(Model model,HttpServletRequest request){						
		return "phone/buildingMall/myFocus";
	}
	
	/**
	 * 6. 分页获取关注列表
	 * @param request
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getFocusList")
	public ResponseEntity<String> getFocusList(HttpServletRequest request,Integer pageNum,Integer pageSize){
		
		Boolean status = true;
		String msg = "请求成功";
		
		Page<Map<String,Object>> page = null;	
		try{
			page = buildingStoreServiceImpl.getFocusList(request, pageSize, pageNum);
		}
		catch(Exception e){
			status = false;
			msg = "请求失败";
			WeixinUser user = (WeixinUser)request.getSession().getAttribute(CommonVar.SESSION_WEIXIN);			
			log.error("用户 ："+user.getId()+","+user.getNickname()+" 获取第"+pageNum+"页关注列表失败",e);
		}
		return renderPageData(status, msg, page);
	}
	
	/**
	 * 7. 关注店铺
	 * @param request
	 * @param userId
	 * @param storeId
	 * @return
	 */
	@RequestMapping("/focusStore")
	public ResponseEntity<String> focusStore(HttpServletRequest request,Integer storeId){
		
		Boolean status = true;
		String msg = "关注成功";
		WeixinUser user = (WeixinUser)request.getSession().getAttribute(CommonVar.SESSION_WEIXIN);
		
		if(storeId == null) {
			status = false;
			msg = "请选择要关注的店铺";
		}
		else if(user == null){
			status = false;
			msg = "请关注我们的公众号";
		}
		try {
			buildingStoreServiceImpl.focusStore(request, user.getId(), storeId);
		}
		catch(BusinessException e) {
			status = false;
			msg = e.getMessage();
		}
		catch(Exception e) {
			status = false;
			msg = "请求失败";
			log.error("用户："+user.getId() +","+user.getNickname()+" 关注店铺"+storeId+"失败",e);
		}		
		return renderMsg(status, msg);
	}
	
	
}
