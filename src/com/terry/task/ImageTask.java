package com.terry.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.terry.CommonVar;
import com.terry.dao.BuildingCaseDao;
import com.terry.dao.BuildingGoodsDao;
import com.terry.dao.BuildingStoreDao;
import com.terry.entity.BuildingStore;
import com.terry.util.QiniuUtil;

/**
 * 定时上传图片资源到七牛云服务器
 * @author Administrator
 *
 */
@Component("imageTask")
public class ImageTask {

	@Resource(name = "buildingStoreDaoImpl")
	private BuildingStoreDao buildingStoreDaoImpl;
	
	@Resource(name = "buildingGoodsDaoImpl")
	private BuildingGoodsDao buildingGoodsDaoImpl;
	
	@Resource(name = "buildingCaseDaoImpl")
	private BuildingCaseDao buildingCaseDaoImpl;
	
	private Logger logger = LoggerFactory.getLogger(ImageTask.class);
	
	//上传店铺图片至七牛云
	public void uploadStoreImage() {
		BuildingStore storeQuery = new BuildingStore();
		storeQuery.setImageStatus(0);  //未上传至七牛云
		
		//这里有问题，如果数据量过大需要优化。1.分页 2.用多线程
		List<BuildingStore> storeList =  buildingStoreDaoImpl.queryList(storeQuery);
		int[] result = new int[3];
		logger.info("********开始上传店铺图片至七牛云********");
		for(int i=0;i<storeList.size();i++) {
			BuildingStore buildingStore = storeList.get(i);
			String phoneImage = buildingStore.getCoverPictureUrl();
			String smallImage = buildingStore.getSmallPicUrl();
			
			if(uploadPicToCloud(phoneImage,result)
					&&(uploadPicToCloud(smallImage,result))){
				buildingStore.setImageStatus(CommonVar.PICSTATUS_CLOUD);  //全部上传成功
			}
			else {
				buildingStore.setImageStatus(CommonVar.PICSTATUS_LOCAL); //未上传成功
			}
		}
		logger.info("********上传店铺图片至七牛云结束，本应上传图片"+result[0]+"张,成功"+result[1]+"张,失败"+result[2]+"张********");
		//批量保存
		buildingStoreDaoImpl.batchSaveOrUpdate(storeList);
	}
	
	/**
	 * 
	 * @param imagePath
	 * @param result[]  result[0]上传数, result[1]成功数 ,result[2]失败数  
	 */
	private boolean uploadPicToCloud(String imagePath,int[] result) {
		boolean successFlag = true;
		if(new File(imagePath).exists()){ //该图片存在，则上传
			int status = QiniuUtil.uploadPicture(getPicName(imagePath), imagePath);
			if(status == CommonVar.SF_SUCCESS) { //上传成功
				result[1] +=1;					
			}
			else {
				result[2]+=1;
				successFlag = false;
			}
			result[0]+=1;
		}
		else {
			result[2]+=1;
		}
		return successFlag;
	}
	
	private String getPicName(String path){
		return path.substring(path.lastIndexOf("/")+1);
	}
}
