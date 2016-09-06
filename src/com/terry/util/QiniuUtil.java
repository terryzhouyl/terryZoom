package com.terry.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.terry.CommonVar;
import com.terry.task.ImageTask;

/**
 * 七牛云存储工具类
 * @author Administrator
 *
 */
public class QiniuUtil {		 
	
	private static String access_key = "F1hEvixiNcBiuPNbC6ESGCel8HZwj_xbSwUBTLUL";
	private static String secret_key = "G6tvaghmfamQlbmToMYvKBh_wIBhRrIWAvT8Kn7";
	private static String bucketname = "terryzoom";
	private static Logger logger = LoggerFactory.getLogger(ImageTask.class);
	
	/**
	 * 
	 * @param fileName 上传到七牛后保存的文件名
	 * @param filePath 文件所在本地路径
	 */
	public static int uploadPicture(String fileName,String filePath) {								
		//秘钥配置
		Auth auth = Auth.create(access_key, secret_key);
		//创建上传对象
		UploadManager uploadManager = new UploadManager();
		
		//简单上传 获取token
		String token = auth.uploadToken(bucketname);
				
		try {			
			//调用Put方法上传
			Response res = uploadManager.put(filePath, fileName, token);
			//打印返回的信息
			System.out.println(res.bodyString());
			return CommonVar.SF_SUCCESS;
		} catch (QiniuException e) {
			 Response r = e.response;
	          // 请求失败时打印的异常的信息
	          System.out.println(r.toString());
	          logger.error("上传图片失败，图片本地路径为"+filePath,e);
	          try {
	              //响应的文本信息
	            System.out.println(r.bodyString());
	          } catch (QiniuException e1) {
	              //ignore
	        	  logger.error("上传图片响应失败",e1);
	          }
	          return CommonVar.SF_FAILURE;
		}			
	}
}
