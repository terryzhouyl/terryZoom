package com.terry.test;

import org.junit.Test;

import com.qiniu.util.Auth;

public class SimpleTest {
	
	@Test
	public void getUploadTokenTest(){
		
		String accessKey = "F1hEvixiNcBiuPNbC6ESGCel8HZwj_xbSwUBTLUL";
		String secretKey = "G6tvaghmfamQlbmToMYvKBh_wIBhRrIWAvT8Kn7";
		Auth auth = Auth.create(accessKey, secretKey);
		
		System.out.println(auth.uploadToken("terryzoom"));
	}
}
