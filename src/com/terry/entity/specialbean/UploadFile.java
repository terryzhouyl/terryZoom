package com.terry.entity.specialbean;

/**
 * 上传文件类
 * 上传的文件路径 都是相对路径，即使用统一项目前缀
 * 文件路径样式为 ../../..
 * @author Administrator
 *
 */
public class UploadFile {
	
	private String filePath;  //文件路径
	private String fileName;  //文件名
	private String realPath; //完全路径 还是绝对路径
	
	
	public UploadFile(String filePath, String fileName) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
		this.realPath = filePath + "/" + fileName;
	}
	
	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public UploadFile(String realPath){
		int index = realPath.lastIndexOf("/");
		String filePath = realPath.substring(0,index);
		String fileName = realPath.substring(index+1);
		this.filePath = filePath;
		this.fileName = fileName;
		this.realPath = realPath;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
