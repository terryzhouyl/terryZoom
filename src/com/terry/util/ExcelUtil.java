package com.terry.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terry.CommonVar;


public class ExcelUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
	/**
	 * 导出店铺数据
	 * @throws IOException
	 */
	public static void exportStoreData(String filePath,List<Map<String,Object>> dataList,String[] headArray) {
		
		//List<Map<String,Object>> storeList = buildingStoreDaoImpl.queryStoreStatisticInfo();
		
		//查看文件路徑是否存在
		FileUtil.getLocalFolder(filePath);
		
		String realPath = filePath + "/"+FileUtil.newFileName("sample.xls");
		OutputStream out = null;
		try{			
			out = new FileOutputStream(realPath);
			//声明一个工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//生成一个表格
			HSSFSheet sheet = workbook.createSheet("店铺数据");
			//设置表格默认列宽度为15个字节
			sheet.setDefaultColumnWidth(15);
			//生成表头样式
			HSSFCellStyle headStyle = generateHeadStyle(workbook);
						
			//生成其余格子的样式
			HSSFCellStyle commonStyle = generateCommonStyle(workbook);
			
			//生成表头
			HSSFRow rowTitle = sheet.createRow(0);
			for(int i=0;i<headArray.length;i++) {
				HSSFCell cell = rowTitle.createCell(i);
				cell.setCellStyle(headStyle);
				HSSFRichTextString text = new HSSFRichTextString(headArray[i]);
				cell.setCellValue(text);
			}
			
			//生成表体
			for(int i=0;i<dataList.size();i++) {
				
				Map<String,Object> dataMap = dataList.get(i);
				HSSFRow row = sheet.createRow(i+1);			
				for(int j=0;j<headArray.length;j++) {
					Object o = dataMap.get(headArray[j]);
					
					String value = o == null ? "":o.toString();					
					HSSFCell cell = row.createCell(j);
					cell.setCellStyle(commonStyle);
					HSSFRichTextString richString = new HSSFRichTextString(value);
					//这里按理说是要设置数据类型的					
					cell.setCellValue(richString);
				}			
			}			
			System.out.println("导出成功");
			workbook.write(out);
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("生成excel失败",e);
		}
		finally{
			try{
				if(out!=null){
					out.flush();
					out.close();
				}				
			}
			catch(Exception e){
				logger.error("清理excel失败",e);
			}
		}
	}
	
	/**
	 * 导入店铺数据
	 * @throws IOException 
	 */
	public static <T> List<T> ImportStoreData(String filePath,Class<T> className,String[] headArray) throws IOException {
		
		InputStream is = new FileInputStream(filePath);
		HSSFWorkbook workbook = new HSSFWorkbook(is);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		HSSFRow row = sheet.getRow(0);
		//int colNum = row.getPhysicalNumberOfCells();		
		int rowNum = sheet.getLastRowNum();
		
		List<T> list = null;
		try{
			list = new ArrayList<>();
			Method[] methods = new Method[headArray.length];
			for(int i=0;i<headArray.length;i++) {			
				String setName = "set"+headArray[i].substring(0, 1).toUpperCase()+headArray[i].substring(1);
				//这里其实是要判断类型的
				methods[i] = className.getDeclaredMethod(setName, String.class);
			}							
			//正文内容应该从第二行开始，第一行未表头的标题
			for(int i=1;i<=rowNum;i++) {
				row = sheet.getRow(i);
				T t = className.newInstance();
				for(int j=0;j<methods.length;j++){					
					methods[j].invoke(t,row.getCell(j).getStringCellValue());
				}
				list.add(t);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("导入店铺数据失败:"+className.getName(),e);
		}
		return list;
	}
	
	
	/**
	 * 生成表头样式
	 * @param workbook
	 * @return
	 */
	private static HSSFCellStyle generateHeadStyle (HSSFWorkbook workbook) {
		//生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		//设置样式
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		//生成一个字体
		HSSFFont font = workbook.createFont();
	    font.setColor(HSSFColor.VIOLET.index);
	    font.setFontHeightInPoints((short) 12);
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    
	    // 把字体应用到当前的样式
	    style.setFont(font);
		return style;
	}
	
	/**
	 * 生成普通格子的样式
	 * @param workbook
	 * @return
	 */
	private static HSSFCellStyle generateCommonStyle(HSSFWorkbook workbook){
		  // 生成并设置另一个样式
	      HSSFCellStyle style2 = workbook.createCellStyle();
	      //style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	      style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      // 把字体应用到当前的样式
	      style2.setFont(font2);
	      return style2;
	} 
}
