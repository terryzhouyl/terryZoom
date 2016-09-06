package com.terry.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	
	public static void main(String[] args) {
		
		Logger logger = LoggerFactory.getLogger(LogTest.class);
		
		try{			
			InputStream is = new FileInputStream("D:/14684622430.jpg");
			logger.info("读取文件成功");
		}
		catch(Exception e){
			logger.error("读取文件失败",e);
		}
	}
}
