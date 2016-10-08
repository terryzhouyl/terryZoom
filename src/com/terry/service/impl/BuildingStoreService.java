package com.terry.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.util.privilegedactions.GetDeclaredMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.BusinessException;
import com.terry.CommonVar;
import com.terry.dao.BuildingFocusDao;
import com.terry.dao.BuildingScoreDao;
import com.terry.dao.BuildingStoreDao;
import com.terry.dao.BuildingTagDao;
import com.terry.dao.BuildingTypeDao;
import com.terry.dao.StoreTagDao;
import com.terry.dao.impl.StoreTagDaoImpl;
import com.terry.dao.impl.WeixinUserDaoImpl;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingFocus;
import com.terry.entity.BuildingScore;
import com.terry.entity.BuildingStore;
import com.terry.entity.BuildingTag;
import com.terry.entity.BuildingType;
import com.terry.entity.StoreTag;
import com.terry.entity.WeixinUser;
import com.terry.entity.specialbean.UploadFile;
import com.terry.util.ImageUtil;

@Service("buildingStoreService")
public class BuildingStoreService extends BaseService{
	
	@Resource(name="buildingTypeDaoImpl")
	private BuildingTypeDao buildingTypeDaoImpl;
	
	@Resource(name="buildingStoreDaoImpl")
	private BuildingStoreDao buildingStoreDaoImpl;
	
	@Resource(name="buildingFocusDaoImpl")
	private BuildingFocusDao buildingFocusDaoImpl;
	
	@Resource(name="weixinUserDaoImpl")
	private WeixinUserDaoImpl weixinUserDaoImpl;
	
	@Resource(name="buildingTagDaoImpl")
	private BuildingTagDao buildingTagDaoImpl;
	
	@Resource(name="buildingScoreDaoImpl")
	private BuildingScoreDao buildingScoreDaoImpl;
	
	@Resource(name="storeTagDaoImpl")
	private StoreTagDao storeTagDaoImpl;
		 
	
	public int weixinLogin(HttpServletRequest request, String code) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	public List<BuildingType> getBuildingType() {
		// TODO Auto-generated method stub
		return buildingTypeDaoImpl.findAll(BuildingType.class);
	}

	public Page<BuildingStore> getStorePage(Integer pageSize, Integer pageNum,BuildingStore query) {
		// TODO Auto-generated method stub
		if(pageSize == null) {
			pageSize = CommonVar.PHONE_PAGESIZE;
		}
		if(pageNum == null) {
			pageNum = 1;
		}
		Page<BuildingStore> page = buildingStoreDaoImpl.queryPage(pageSize, pageNum,query);
		StoreTag tagQuery = new StoreTag();
		
		for(BuildingStore store : page.getRows()){
			tagQuery.setStoreId(store.getId());
			store.setTagList(queryStoreTag(tagQuery));
		}		
		return page;				
	}

	public BuildingStore storeInfo(HttpServletRequest request,Long storeId) {
		// TODO Auto-generated method stub
	    		
		BuildingStore buildingStore =	buildingStoreDaoImpl.get(BuildingStore.class,storeId);
		if(buildingStore!=null) {
			//判断是否关注
			WeixinUser user = (WeixinUser)request.getSession().getAttribute(CommonVar.SESSION_WEIXIN);						
			
			BuildingFocus focusQuery = new BuildingFocus();
			focusQuery.setMemberId(user.getId());
			focusQuery.setStoreId(storeId);			
			List<BuildingFocus> list = buildingFocusDaoImpl.queryFocusList(focusQuery);						
			if(list.size()>0){
				buildingStore.setIsFocus(1); //关注
			}
			else {
				buildingStore.setIsFocus(0); //不关注
			}
		}
		
		return buildingStore;
	}

	public Page<Map<String, Object>> getFocusList(HttpServletRequest request, Integer pageSize, Integer pageNum) {
		// TODO Auto-generated method stub
		 WeixinUser user = (WeixinUser)request.getSession().getAttribute(CommonVar.SESSION_WEIXIN);
		 
		 if(pageSize == null) {
			 pageSize = CommonVar.PHONE_PAGESIZE;
		 }
		 if(pageNum == null) {
			 pageNum = 1;
		 }
		 Page<Map<String,Object>> page = null;
		 if(user != null) {
			 page = buildingFocusDaoImpl.queryFocusCasePage(user.getId(), pageNum, pageSize);
			 
			//此处按理说是要把图片再做解析，这里先试一下是否可以在页面上进行json解析。
			 
		 }
		return page;
	}

	/**
	 * 关注店铺
	 * @param request
	 * @param userId
	 * @param storeId
	 */
	public void focusStore(HttpServletRequest request, Long userId, Long storeId) {
		// TODO Auto-generated method stub
		BuildingFocus query = new BuildingFocus();
		query.setMemberId(userId);
		query.setStoreId(storeId);
		List<BuildingFocus> focusList = buildingFocusDaoImpl.queryFocusList(query);
		if(focusList.size() > 0){
			throw new BusinessException("您已经关注过该店铺");
		}		
		else{				
			BuildingStore store = buildingStoreDaoImpl.getBy(BuildingStore.class,"memberId",storeId);
			if(store!=null && store.getId().intValue()  == storeId.intValue()){								
				throw new BusinessException("不能关注自己的店铺");
			}
			else{
				BuildingFocus buildingFocus = new BuildingFocus();
				buildingFocus.setMemberId(userId);
				buildingFocus.setStoreId(storeId);
				buildingFocus.setCreateTime(new Date());
				buildingFocusDaoImpl.saveOrUpdate(buildingFocus);
			}
		}		
		
	}



	public List<BuildingStore> getStoreList(BuildingStore goodsQuery, Long storeId) {
		// TODO Auto-generated method stub
		List<BuildingStore> list = null;
		if(storeId !=null) {
			list = new ArrayList<>(1);
			BuildingStore store = buildingStoreDaoImpl.get(BuildingStore.class, storeId);
			list.add(store);
		}
		else if(goodsQuery != null) {
			list = buildingStoreDaoImpl.queryList(goodsQuery);
		}
		return list;
	}

	/**
	 * 后台保存更新店铺信息
	 * @param buildingStore
	 * @param cmfile
	 * @param fsize
	 */
	public void saveOrUpdateStoreInfo(BuildingStore buildingStore,CommonsMultipartFile cmfile,Integer fsize) {
		// TODO Auto-generated method stub
		//buildingStore.setBuildingTypeName(buildingTypeService.get(buildingStore.getBuildingTypeId()).getTypeName());
		//buildingStoreService.saveOrUpdate(buildingStore);
		
		BuildingType type = buildingTypeDaoImpl.get(BuildingType.class,buildingStore.getBuildingTypeId());
		buildingStore.setBuildingTypeName(type.getTypeName());
		BuildingStore newStore = buildingStoreDaoImpl.saveOrUpdate(buildingStore);		
		if(cmfile!= null) { //未修改图片		
			
			String filePath = getConfig("storeImgFile");
			
			//加入对图片的处理
			//上传原图
			UploadFile originalFile = ImageUtil.uploadPicture(cmfile, fsize,filePath);
			//压缩图
			UploadFile appFile = ImageUtil.appResize(originalFile.getRealPath(),true);
			//剪裁图
			UploadFile smallFile = ImageUtil.cutSmallPic(appFile.getRealPath());			
			
			newStore.setOriginalPicUrl(originalFile.getFileName());			
			newStore.setCoverPictureUrl(appFile.getFileName());
			newStore.setSmallPicUrl(smallFile.getFileName());
			newStore.setImageFile(originalFile.getFilePath());			
		}
		
	}
	
	/**
	 * 前端保存店铺头图
	 * @param originalPicUrl
	 * @param cmfile
	 * @param buildingStoreId
	 * @param fsize
	 */
	public void saveHeadImage(@RequestParam("cmfile") CommonsMultipartFile cmfile,Long buildingStoreId,Integer fsize) {
		BuildingStore store = buildingStoreDaoImpl.get(BuildingStore.class, buildingStoreId);
		if(cmfile!= null) { //未修改图片			
			//加入对图片的处理
			//上传原图
			String filePath = getConfig("storeImgFile");
			
			//加入对图片的处理
			//上传原图
			UploadFile originalFile = ImageUtil.uploadPicture(cmfile, fsize,filePath);
			//压缩图
			UploadFile appFile = ImageUtil.appResize(originalFile.getRealPath(),true);
			//剪裁图
			UploadFile smallFile = ImageUtil.cutSmallPic(appFile.getRealPath());	
			
			store.setOriginalPicUrl(originalFile.getFileName());			
			store.setCoverPictureUrl(appFile.getFileName());
			store.setSmallPicUrl(smallFile.getFileName());  
			store.setImageFile(originalFile.getFilePath());
			
			buildingStoreDaoImpl.saveOrUpdate(store);
		}
	}
	
	
	
	/**
	 * 保存店铺信息
	 * @param request
	 * @throws Exception
	 */
	public String saveStoreInfo(HttpServletRequest request,BuildingStore buildingStore) throws Exception {
		Enumeration<String> keys = request.getParameterNames();
		String key = null;
		while(keys.hasMoreElements()) {
			key = keys.nextElement();
		}
		String setMethodName = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
		
		Method method =	buildingStore.getClass().getMethod(setMethodName,String.class); 
		method.invoke(buildingStore, request.getParameter(key));
		
		buildingStoreDaoImpl.saveOrUpdate(buildingStore);
		
		return key;		
	}
	
	public void saveStoreAddress(HttpServletRequest request,Integer cityId,String city,String province,String detailAddress) {
		
		BuildingStore store = getStoreInfo(request);
		store.setCity(city);
		store.setCityId(cityId);
		store.setProvince(province);
		store.setDetailAddress(detailAddress);
		buildingStoreDaoImpl.saveOrUpdate(store);
	}  
	
	/**
	 * 根据请求查询店铺信息
	 * @param request
	 * @return
	 */
	public BuildingStore getStoreInfo(HttpServletRequest request) {
		WeixinUser wxuser =	(WeixinUser)request.getSession().getAttribute(CommonVar.SESSION_WEIXIN);		
		return buildingStoreDaoImpl.getBy(BuildingStore.class,"memberId",wxuser.getId());
	}
		
	/**
	 * 根据请求查询店铺Id
	 * @param request
	 * @return
	 */
	public Long getStoreId(HttpServletRequest request) {
		WeixinUser wxuser =	(WeixinUser)request.getSession().getAttribute(CommonVar.SESSION_WEIXIN);		
		return buildingStoreDaoImpl.getStoreIdByMemberId(wxuser.getId());
	}
	
	/**
	 * 根据请求查询当前登录用户信息
	 * @param request
	 */
	public WeixinUser getWeixinUser(HttpServletRequest request) {
		WeixinUser wxuser =	(WeixinUser)request.getSession().getAttribute(CommonVar.SESSION_WEIXIN);	
		return wxuser;
	}
	
	/**
	 * 保存用户信息
	 * @param request
	 * @param sex
	 * @param nickname
	 */
	public void saveUserInfo(HttpServletRequest request,Integer sex,String nickname) {
		WeixinUser wxuser =	(WeixinUser)request.getSession().getAttribute(CommonVar.SESSION_WEIXIN);
		wxuser.setSex(sex);
		wxuser.setNickname(nickname);
		weixinUserDaoImpl.saveOrUpdate(wxuser);
	}
	
	/**
	 * 查询所有有效的标签列表
	 */
	public List<BuildingTag> getAllTags() {		
		return buildingTagDaoImpl.findAllTags(); 				
	}
	
	/**
	 * 根据标签查询店铺
	 * @return
	 */
	public List<BuildingStore> queryStoresByTag(Integer tagId) {						
		return buildingStoreDaoImpl.queryListByTag(tagId);
	}
	
	public List<StoreTag> queryStoreTag(StoreTag storeTagQuery){
		return storeTagDaoImpl.queryList(storeTagQuery);
	}
	
	/**
	 * 店铺评分
	 */
	public void saveScore(BuildingScore score){
		
		//WeixinUser wxuser =	(WeixinUser)request.getSession().getAttribute(CommonVar.SESSION_WEIXIN);		
		//score.setUserId(wxuser.getId());
		
		List<BuildingScore> scoreList = buildingScoreDaoImpl.queryList(score);
		if(scoreList.size() > 0){
			throw new BusinessException("对不起，您已经评过分了");
		}
		score.setCreateTime(new Date());				
		buildingScoreDaoImpl.saveOrUpdate(score);
		
		double avgScore =  buildingScoreDaoImpl.getAvgScore(score.getStoreId());
		BuildingStore store = buildingStoreDaoImpl.get(BuildingStore.class,score.getStoreId());
		store.setScore(avgScore);
		buildingStoreDaoImpl.saveOrUpdate(store);
	}
	
	
}
