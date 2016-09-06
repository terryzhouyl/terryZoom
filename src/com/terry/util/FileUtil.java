package com.terry.util;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.terry.BusinessException;
import com.terry.CommonVar;



public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	private FileUtil() {
	}
	/**
	 * 文件上传 
	 * @param request
	 * @param response
	 * @param newFile 自行组装新文件地址
	 * @return Boolean 返回是否上传成功
	 * @throws Exception
	 */
	public static Boolean upload(HttpServletRequest request,String filepath, String newFile) throws Exception {   
       try {
    	   // 转型为MultipartHttpRequest：   
           MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
           // 获得文件：   
           MultipartFile file = multipartRequest.getFile(filepath);   
           if(!file.isEmpty()){
//        	   String fileName=  file.getOriginalFilename();
//        	   String ext= fileName.substring(fileName.lastIndexOf("."));
//        	   File source = new File(newFile+ext);   
        	   File source = new File(newFileName(newFile));   
               file.transferTo(source);
           }
           return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
         
    }
	public static byte[] readFile(File file) {
		if (file.isDirectory()) {
			return null;
		}
		try {
			return FileCopyUtils.copyToByteArray(file);
		} catch (FileNotFoundException e) {
			logger.error("FileUtil readFile FileNotFoundException "+file.getName(), e);
		} catch (IOException e) {
			logger.error("FileUtil readFile IOException "+file.getName(), e);
		}
		return null;
	}

	public static void copytoFile(byte[] in, File out) {
		if (in == null) {
			return;
		}
		try {
			FileCopyUtils.copy(in, out);
		} catch (IOException e) {
			logger.error("FileUtil readFile IOException "+out.getName(), e);
		}
	}
	/**
	 * 文件重命名
	 * 
	 * @param fileName
	 *            文件名
	 * @return 根据日期重命名后的文件名
	 */
	public static String newFileName(String fileName) {                    
		Assert.notNull(fileName);
		String name = DateUtil.getNow().getTime()+""+(int)(Math.random()*99);
		if (fileName.indexOf(".") == -1) {
			return name;
		} else {
			return name+ fileName.substring(fileName.lastIndexOf("."));
		}
	}
	
	
	/**
	 * 根据路径创建   本地文件
	 * @param path
	 * @return
	 */
	public static void getLocalFolder(String path) {
		File file = new File(path);
		if(!file.exists()) {			
			StringBuilder builder = new StringBuilder();
			String[] folders = path.split("/");
			for(String s:folders) {
				if(StringUtils.isNotBlank(s)) {
					builder.append("/");
					builder.append(s);
					File f = new File(builder.toString());
					if(!f.exists()) {
						f.mkdirs();
					}
				}			
			} 	
		}
	}		
	
	
	
	

	
	/**
	 * 上传图片 适用于 ios
	 * @param request
	 * @param file 上传文件的 字节码
	 * @param fileType 上传图片的类型
	 * @param fsize 上传图片大小
	 * @param imgWidth 上传图片的宽度
	 * @param fileFolder 上传图片的文件路径
	 * @return
	 */
	public static String uploadPicture(HttpServletRequest request,String file,String fileType,Integer fsize,Integer imgWidth,String fileFolder) {
		String newPath = null;
		try {   
			file = file.replace("<", "");
			file = file.replace(">", "");
			file = file.replace(" ", "");
            byte[] bytes1 = hexStringToBytes(file.toUpperCase());
            
        	Uploader up = new Uploader(request);
			String folder = up.getFolder(fileFolder);
			newPath = folder+"/"+DateUtil.getNow().getTime()+""+(int)(Math.random()*99)+"."+fileType;
			String path = request.getServletContext().getRealPath("/") + newPath;
            File avatarFile = new File(path);
            if(!avatarFile.exists()){
            	avatarFile.createNewFile();
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(avatarFile));  
            out.write(bytes1);
            out.flush();
            out.close();
            newPath = newPath.replaceAll("\\\\", "/");
			
        } catch (Exception e) {   
            e.printStackTrace();              
        }  
		return newPath;
	}
	
	public static byte[] hexStringToBytes(String hexString) {   
	    if (hexString == null || hexString.equals("")) {   
	        return null;   
	    }   
	    hexString = hexString.toUpperCase();   
	    int length = hexString.length() / 2;   
	    char[] hexChars = hexString.toCharArray();   
	    byte[] d = new byte[length];   
	    for (int i = 0; i < length; i++) {   
	        int pos = i * 2;   
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
	    }   
	    return d;   
	} 
	
	/**  
	 * Convert char to byte  
	 * @param c char  
	 * @return byte  
	 */  
	 private static byte charToByte(char c) {   
	    return (byte) "0123456789ABCDEF".indexOf(c);   
	}  
	 
	 
	/**
	 * 拼接图片路径成json字符串 
	 * @param pictureUrl
	 * @param key
	 * @param value
	 * @return
	 */
	public static String addPicture(String pictureUrl,String[] keyArray,String[] valueArray) {
		if(pictureUrl == null||"[]".equals(pictureUrl)){
			StringBuilder builder = new StringBuilder();
			builder.append("[{");
			for(int i=0;i<keyArray.length;i++) {
				builder.append("\"");
				builder.append(keyArray[i]);
				builder.append("\":");
				builder.append("\"");
				builder.append(valueArray[i]);
				builder.append("\"");
				builder.append(",");
			}
			builder.deleteCharAt(builder.length()-1);
			builder.append("}]");
			return builder.toString();
		}	
		else {
			StringBuilder pictureBuilder = new StringBuilder(pictureUrl);
			pictureBuilder.deleteCharAt(pictureBuilder.length()-1);
			pictureBuilder.append(",");
			pictureBuilder.append("{");
			for(int i=0;i<keyArray.length;i++) {
				pictureBuilder.append("\"");
				pictureBuilder.append(keyArray[i]);
				pictureBuilder.append("\":");
				pictureBuilder.append("\"");
				pictureBuilder.append(valueArray[i]);
				pictureBuilder.append("\"");
				pictureBuilder.append(",");
			}
			pictureBuilder.deleteCharAt(pictureBuilder.length()-1);
			pictureBuilder.append("}]");
			return pictureBuilder.toString();
		}		
	} 
	 
	/**
	 * 拼接图片路径
	 * @param pictureUrl
	 * @param newOriginalUrl
	 * @param newAppUrl
	 * @return
	 */
	public static String addPicture(String pictureUrl,String newOriginalUrl,String newAppUrl){
		if(pictureUrl == null||"[]".equals(pictureUrl)){
			StringBuilder builder = new StringBuilder();
			builder.append("[{");
			builder.append("\"originalPicUrl\":");
			builder.append("\""+newOriginalUrl+"\"");
			builder.append(",");
			builder.append("\"phonePicUrl\":");
			builder.append("\""+newAppUrl+"\"");
			builder.append("}]");			
			return builder.toString();
		}
		else{
			StringBuilder pictureBuilder = new StringBuilder(pictureUrl);
			pictureBuilder.deleteCharAt(pictureBuilder.length()-1);
			pictureBuilder.append(",");
			pictureBuilder.append("{");
			pictureBuilder.append("\"originalPicUrl\":");
			pictureBuilder.append("\""+newOriginalUrl+"\"");
			pictureBuilder.append(",");
			pictureBuilder.append("\"phonePicUrl\":");
			pictureBuilder.append("\""+newAppUrl+"\"");
			pictureBuilder.append("}]");	
			return pictureBuilder.toString();
		}
	}  
	
	
	/**
	 * 拼接图片路径
	 * @param pictureUrl
	 * @param newOriginalUrl
	 * @param newAppUrl
	 * @return
	 */
	public static String addPicture(String pictureUrl,String newOriginalUrl,String newAppUrl,String newCutUrl){
		if(pictureUrl == null||"[]".equals(pictureUrl)){
			StringBuilder builder = new StringBuilder();
			builder.append("[{");
			builder.append("\"cutPicUrl\":");
			builder.append("\""+newCutUrl+"\"");
			builder.append(",");
			builder.append("\"originalPicUrl\":");
			builder.append("\""+newOriginalUrl+"\"");
			builder.append(",");
			builder.append("\"phonePicUrl\":");
			builder.append("\""+newAppUrl+"\"");
			builder.append("}]");			
			return builder.toString();
		}
		else{
			StringBuilder pictureBuilder = new StringBuilder(pictureUrl);
			pictureBuilder.deleteCharAt(pictureBuilder.length()-1);
			pictureBuilder.append(",");
			pictureBuilder.append("{");
			pictureBuilder.append("\"cutPicUrl\":");
			pictureBuilder.append("\""+newCutUrl+"\"");
			pictureBuilder.append(",");
			pictureBuilder.append("\"originalPicUrl\":");
			pictureBuilder.append("\""+newOriginalUrl+"\"");
			pictureBuilder.append(",");
			pictureBuilder.append("\"phonePicUrl\":");
			pictureBuilder.append("\""+newAppUrl+"\"");
			pictureBuilder.append("}]");	
			return pictureBuilder.toString();
		}
	}  
	
}
