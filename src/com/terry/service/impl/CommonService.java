package com.terry.service.impl;

import org.springframework.stereotype.Service;

import com.qiniu.util.Auth;

@Service("commonService")
public class CommonService extends BaseService{
	
	/**
	 * 获得七牛的上传凭证
	 * @return
	 */
	public String getQiniuUploadToken(){
		String accessKey = getConfig("qiniu_access_key");
		String secretKey = getConfig("qiniu_secret_key");
		String bucketname = getConfig("qiniu_bucketname");
		Auth auth = Auth.create(accessKey, secretKey);
		return auth.uploadToken(bucketname);
	}
}
