package com.terry.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.CommonVar;
import com.terry.dao.BuildingCaseDao;
import com.terry.dao.BuildingGoodsDao;
import com.terry.dao.CasePicDao;
import com.terry.dao.support.Page;
import com.terry.entity.BuildingCase;
import com.terry.entity.BuildingGoods;
import com.terry.entity.CasePic;
import com.terry.entity.specialbean.UploadFile;
import com.terry.util.FileUtil;
import com.terry.util.ImageUtil;

@Service("buildingGoodsService")
public class BuildingGoodsService extends BaseService{

	@Resource(name="buildingCaseDaoImpl")
	private BuildingCaseDao buildingCaseDaoImpl;
	
	@Resource(name="buildingGoodsDaoImpl")
	private BuildingGoodsDao buildingGoodsDaoImpl;
	
	@Resource(name="casePicDaoImpl")
	private CasePicDao casePicDaoImpl;
	
	
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

	public List<BuildingGoods> getGoodsList(BuildingGoods goodsQuery, Long buildingGoodsId) {
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

	public List<BuildingCase> getCaseList(BuildingCase caseQuery, Long buildingCaseId) {
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
		if(buildingCase.getId()==null){
			//增加
			saveBuildingCase(buildingCase.getDescription(),pictureUrl,buildingCase.getStoreId());
		}
		else{
			//更新
			StringBuilder builder = new StringBuilder();
			String[] picArray = pictureUrl.split(",");
			
			CasePic query = new CasePic();
			for(int i=0;i<picArray.length;i++) {
				String fileName = picArray[i].substring(picArray[i].indexOf("/")+1);
				query.setOriginalPicUrl(fileName);
				List<CasePic> caseList =	casePicDaoImpl.queryList(query);
				if(caseList!=null && caseList.size() > 0) { //数据库中已经存在无需添加
					continue;
				}
				else {
					builder.append(picArray[i]);
					builder.append(",");
				}				
			}		
			builder.deleteCharAt(builder.length()-1);
			saveBuildingCase(buildingCase.getDescription(),builder.toString(),buildingCase.getStoreId());			
		}		
	}	
	
	public void saveGoods(CommonsMultipartFile cmfile, BuildingGoods buildingGoods, Integer fsize) {
		// TODO Auto-generated method stub	
		BuildingGoods newGoods = buildingGoodsDaoImpl.saveOrUpdate(buildingGoods); 
		
		if(cmfile != null) { //上传图片
			
			String filePath = getConfig("goodsImgFile");
			
			//加入对图片的处理
			//上传原图
			UploadFile originalFile = ImageUtil.uploadPicture(cmfile, fsize,filePath);									
			//压缩图
			UploadFile appFile = ImageUtil.appResize(originalFile.getRealPath(),true);
			//剪裁图
			UploadFile smallFile = ImageUtil.cutSmallPic(originalFile.getRealPath());						 
			newGoods.setOriginalPicUrl(originalFile.getFileName());		
			newGoods.setPhonePicUrl(appFile.getFileName());
			newGoods.setSmallPicUrl(smallFile.getFileName());
			newGoods.setImageFile(filePath);
		}	
		
	}
	
	/**
	 * 保存建材案例
	 * @param description
	 * @param imagePath
	 */
	public Long saveBuildingCase(String description,String imagePath,Long storeId) {
		
		BuildingCase buildingCase = new BuildingCase();
		buildingCase.setDescription(description);
		buildingCase.setStatus(CommonVar.USE_ONUSE);
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
			UploadFile appFile = ImageUtil.appResize(path, true);
			//剪裁图
			UploadFile smallFile = ImageUtil.cutSmallPic(path);
			
			casePic.setCaseId(newCase.getId());
			casePic.setImageStatus(CommonVar.PICSTATUS_LOCAL);
			casePic.setCreateTime(new Date());
			casePic.setOriginalPicUrl(new UploadFile(path).getFileName());
			casePic.setPhonePicUrl(appFile.getFileName());
			casePic.setSmallPicUrl(smallFile.getFileName());
			casePic.setStatus(CommonVar.USE_ONUSE);
			casePic.setImageFile(appFile.getFilePath());
			list.add(casePic);
		}
		casePicDaoImpl.batchSaveOrUpdate(list);
		return newCase.getId();
	}
	
	/**
	 * 商品上下线
	 * @param goodsId
	 */
	public void putAwayGoods(Long goodsId) {
		BuildingGoods goods = buildingGoodsDaoImpl.get(BuildingGoods.class, goodsId);
		if(goods.getPutAwayStatus() == 1) { //商品下线
			goods.setPutAwayStatus(0);
		}
		else { //商品上线
			goods.setPutAwayStatus(1);
		}
		buildingGoodsDaoImpl.saveOrUpdate(goods);
	}
	
	/**
	 * 删除商品
	 * @param goodsId
	 */
	public void deleteGoods(Long goodsId) {
		BuildingGoods goods = buildingGoodsDaoImpl.get(BuildingGoods.class, goodsId);
		goods.setStatus(CommonVar.USE_NOUSE);
		buildingGoodsDaoImpl.saveOrUpdate(goods);
	}
}
