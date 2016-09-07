package com.terry.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.CommonVar;
import com.terry.dao.BuildingCaseDao;
import com.terry.dao.BuildingGoodsDao;
import com.terry.dao.CasePicDao;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingCase;
import com.terry.entity.BuildingGoods;
import com.terry.entity.BuildingStore;
import com.terry.entity.CasePic;
import com.terry.service.BuildingGoodsService;
import com.terry.util.FileUtil;
import com.terry.util.ImageUtil;

@Service("buildingGoodsServiceImpl")
public class BuildingGoodsServiceImpl extends BaseServiceImpl implements BuildingGoodsService{

	@Resource(name="buildingCaseDaoImpl")
	private BuildingCaseDao buildingCaseDaoImpl;
	
	@Resource(name="buildingGoodsDaoImpl")
	private BuildingGoodsDao buildingGoodsDaoImpl;
	
	@Resource(name="casePicDaoImpl")
	private CasePicDao casePicDaoImpl;
	
	private final String[] picArrayKey = new String[]{"originalPicUrl","phonePicUrl","cutPicUrl"};
	
	@Override
	public Page<BuildingCase> getCasePage(BuildingCase caseQuery, Integer pageSize, Integer pageNum) {
		// TODO Auto-generated method stub
		if(caseQuery.getStatus() == null) {
			caseQuery.setStatus(1); //可用
		}
		if(pageNum == null) {
			pageNum = 1;
		}
		if(pageSize == null) {
			pageSize = CommonVar.ADMIN_PAGESIZE;
		}		
		Page<BuildingCase> page = buildingCaseDaoImpl.queryPage(caseQuery, pageNum, pageSize);
		//此处按理说是要把图片再做解析，这里先试一下是否可以在页面上进行json解析。
		return page;
	}

	@Override
	public Page<BuildingGoods> getGoodsPage(BuildingGoods goodsQuery, Integer pageSize, Integer pageNum) {
		// TODO Auto-generated method stub
		if(goodsQuery.getPutAwayStatus() == null) {
			goodsQuery.setPutAwayStatus(1); //上架
		}
		if(pageNum == null) {
			pageNum = 1;
		}
		if(pageSize == null) {
			pageSize = CommonVar.ADMIN_PAGESIZE;
		}		
		return buildingGoodsDaoImpl.queryPage(goodsQuery, pageSize, pageNum);
		
	}

	@Override
	public List<BuildingGoods> getGoodsList(BuildingGoods goodsQuery, Integer buildingGoodsId) {
		// TODO Auto-generated method stub
		
		List<BuildingGoods> list = null;
		if(buildingGoodsId !=null) {
			BuildingGoods buildingGoods = buildingGoodsDaoImpl.get(BuildingGoods.class, buildingGoodsId);
			list = new ArrayList<>(1); 
			list.add(buildingGoods);
		}
		else if(goodsQuery!=null){
			list = buildingGoodsDaoImpl.queryList(goodsQuery); 
		}		
		return list;
	}

	@Override
	public List<BuildingCase> getCaseList(BuildingCase caseQuery, Integer buildingCaseId) {
		// TODO Auto-generated method stub
		List<BuildingCase> list = null;
		if(buildingCaseId !=null) {
			BuildingCase buildingCase = buildingCaseDaoImpl.get(BuildingCase.class, buildingCaseId);
			list = new ArrayList<>(1);
			list.add(buildingCase);
		}
		else if(caseQuery!=null) {
			list = buildingCaseDaoImpl.queryList(caseQuery);
		}
		return list;
	}
	
	
	public void saveCase(BuildingCase buildingCase) {
		String pictureUrl = buildingCase.getPictureUrl();
		String picJson = null;		
		if(buildingCase.getId()==null){
			//增加
			String[] picArray = pictureUrl.split(",");
			for(int i=0;i<picArray.length;i++){
				String appPicUrl =  ImageUtil.appResize(picArray[i], true);
				String cutPicUrl = 	ImageUtil.cutPicToSquare(picArray[i]);				
				picJson = FileUtil.addPicture(picJson,picArrayKey,new String[]{picArray[i],appPicUrl,cutPicUrl});
			}	
			buildingCase.setPictureUrl(picJson);
			buildingCase.setStatus(1);
			buildingCaseDaoImpl.saveOrUpdate(buildingCase);	
		}
		else{
			//更新
			BuildingCase oldCase = buildingCaseDaoImpl.get(BuildingCase.class,buildingCase.getId());
			String oldCasePic = oldCase.getPictureUrl();
			picJson = oldCase.getPictureUrl();
			String[] picArray = pictureUrl.split(",");
			for(int i=0;i<picArray.length;i++){
				if(oldCasePic.indexOf(picArray[i])>-1){
					//已经存在
					continue;
				}
				else{
					String appPicUrl =  ImageUtil.appResize(picArray[i], true);
					String cutPicUrl = 	ImageUtil.cutPicToSquare(picArray[i]);
					picJson = FileUtil.addPicture(picJson,picArrayKey,new String[]{picArray[i],appPicUrl,cutPicUrl});
				}
			}
			oldCase.setPictureUrl(picJson);
			oldCase.setDescription(buildingCase.getDescription());
			oldCase.setStoreId(buildingCase.getStoreId());
			oldCase.setStatus(buildingCase.getStatus());
			oldCase.setTitle(buildingCase.getTitle());
			oldCase.setStorePic(buildingCase.getStorePic());
			buildingCaseDaoImpl.saveOrUpdate(oldCase);	
		}
	}	
	
	@Override
	public void saveGoods(CommonsMultipartFile cmfile, BuildingGoods buildingGoods, Integer fsize) {
		// TODO Auto-generated method stub	
		BuildingGoods newGoods = buildingGoodsDaoImpl.saveOrUpdate(buildingGoods); 
		
		if(cmfile != null) { //上传图片
			//加入对图片的处理
			//上传原图
			String path = ImageUtil.uploadPicture(cmfile, fsize,"store/"+newGoods.getStoreId());
			//压缩图
			String appResize = ImageUtil.appResize(path,true);
			//剪裁图
			String smallPic = ImageUtil.cutSmallPic(path);						 
			newGoods.setOriginalPicUrl(path);		
			newGoods.setPhonePicUrl(appResize);
			newGoods.setSmallPicUrl(smallPic);    						
		}	
		
	}
	
	/**
	 * 保存建材案例
	 * @param description
	 * @param imagePath
	 */
	public Integer saveBuildingCase(String description,String imagePath,Integer storeId) {
		
		BuildingCase buildingCase = new BuildingCase();
		buildingCase.setDescription(description);
		buildingCase.setStatus(1);
		buildingCase.setStoreId(storeId);
		buildingCase.setCreateTime(new Date());
		BuildingCase newCase = buildingCaseDaoImpl.saveOrUpdate(buildingCase);
		
		String[] imageArray = imagePath.split(",");
		List<CasePic> list = new ArrayList<>();
		for(int i=0;i<imageArray.length;i++) {			
			CasePic casePic = new CasePic();			
			//原图
			String path = imageArray[i];
			//压缩图
			String appResize = ImageUtil.appResize(path, true);
			//剪裁图
			String smallPic = ImageUtil.cutSmallPic(path);
			
			casePic.setCaseId(newCase.getId());
			casePic.setImageStatus(1);
			casePic.setCreateTime(new Date());
			casePic.setOriginalPicUrl(path);
			casePic.setPhonePicUrl(appResize);
			casePic.setSmallPicUrl(smallPic);
			casePic.setStatus(1); 
			list.add(casePic);
		}
		casePicDaoImpl.batchSaveOrUpdate(list);
		return newCase.getId();
	}
}
