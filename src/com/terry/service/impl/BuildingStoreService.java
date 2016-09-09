package com.terry.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.BusinessException;
import com.terry.CommonVar;
import com.terry.dao.BuildingFocusDao;
import com.terry.dao.BuildingStoreDao;
import com.terry.dao.BuildingTypeDao;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingFocus;
import com.terry.entity.BuildingStore;
import com.terry.entity.BuildingType;
import com.terry.entity.WeixinUser;
import com.terry.util.ImageUtil;

@Service("buildingStoreService")
public class BuildingStoreService{
	
	@Resource(name="buildingTypeDaoImpl")
	private BuildingTypeDao buildingTypeDaoImpl;
	
	@Resource(name="buildingStoreDaoImpl")
	private BuildingStoreDao buildingStoreDaoImpl;
	
	@Resource(name="buildingFocusDaoImpl")
	private BuildingFocusDao buildingFocusDaoImpl;
	
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
		
		return buildingStoreDaoImpl.queryPage(pageSize, pageNum,query);		
		
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

	public void saveOrUpdateStoreInfo(BuildingStore buildingStore,CommonsMultipartFile cmfile,Integer fsize) {
		// TODO Auto-generated method stub
		//buildingStore.setBuildingTypeName(buildingTypeService.get(buildingStore.getBuildingTypeId()).getTypeName());
		//buildingStoreService.saveOrUpdate(buildingStore);
		
		BuildingType type = buildingTypeDaoImpl.get(BuildingType.class,buildingStore.getBuildingTypeId());
		buildingStore.setBuildingTypeName(type.getTypeName());
		BuildingStore newStore = buildingStoreDaoImpl.saveOrUpdate(buildingStore);		
		if(cmfile!= null) { //未修改图片			
			//加入对图片的处理
			//上传原图
			String path = ImageUtil.uploadPicture(cmfile, fsize,"store/"+newStore.getId());
			//压缩图
			String appResize = ImageUtil.appResize(path,true);
			//剪裁图
			String smallPic = ImageUtil.cutSmallPic(path);			
			
			newStore.setOriginalPicUrl(path);			
			newStore.setCoverPictureUrl(appResize);
			newStore.setSmallPicUrl(smallPic);    
		}
		
	}
	
}
