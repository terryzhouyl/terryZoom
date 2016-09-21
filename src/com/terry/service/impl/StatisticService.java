package com.terry.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;

import com.terry.CommonVar;
import com.terry.dao.BuildingStoreDao;
import com.terry.entity.BuildingStore;
import com.terry.util.ExcelUtil;
import com.terry.util.FileUtil;

@Service("statisticService")
public class StatisticService {

	@Resource(name = "buildingStoreDaoImpl")
	BuildingStoreDao buildingStoreDaoImpl;
	
	/**
	 * 导出店铺数据
	 * @throws IOException
	 */
	public void exportStoreData() throws IOException {
		
		List<Map<String,Object>> storeList = buildingStoreDaoImpl.queryStoreStatisticInfo();
		
		String fileFolder = "exportFile";
		String filePath = CommonVar.IMG_PREFIX+"/"+fileFolder;
		//查看文件路徑是否存在
		FileUtil.getLocalFolder(filePath);
		
		String realPath = filePath + "/"+FileUtil.newFileName("sample.xls");
		OutputStream out = new FileOutputStream(realPath);
		try{
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
		
			Map<String,Object> headMap = storeList.get(0);
			
			//产生表格标题行
			HSSFRow rowTitle = sheet.createRow(0);
			List<String> titleList = new ArrayList<>(); 
			int headIndex = 0;
			for(String s:headMap.keySet()){
				HSSFCell cell = rowTitle.createCell(headIndex);
				cell.setCellStyle(headStyle);
				HSSFRichTextString text = new HSSFRichTextString(s);
				cell.setCellValue(text);
				headIndex++;
				titleList.add(s);
			}
								
			for(int i=0;i<storeList.size();i++) {
				
				Map<String,Object> dataMap = storeList.get(i);
				HSSFRow row = sheet.createRow(i+1);			
				for(int j=0;j<titleList.size();j++) {
					String t = titleList.get(j);
					Object o = dataMap.get(t);
					String value = o == null ? "":o.toString();
					
					HSSFCell cell = row.createCell(j);
					cell.setCellStyle(commonStyle);
					HSSFRichTextString richString = new HSSFRichTextString(value);
					cell.setCellValue(richString);
				}			
			}			
			System.out.println("导出成功");
			workbook.write(out);
		}
		finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
	}		
	
	/**
	 * 导入店铺数据
	 * @throws IOException 
	 */
	public void ImportStoreData() throws IOException {
		
//		InputStream is = new FileInputStream(CommonVar.IMG_PREFIX+"/exportFile/147426302997085.xls");
//		HSSFWorkbook workbook = new HSSFWorkbook(is);
//		HSSFSheet sheet = workbook.getSheetAt(0);
//		
//		HSSFRow row = sheet.getRow(0);
//		int colNum = row.getPhysicalNumberOfCells();
//		
//		int rowNum = sheet.getLastRowNum();
//		
//		StringBuilder builder = new StringBuilder();
//		//正文内容应该从第二行开始，第一行未表头的标题
//		for(int i=1;i<=rowNum;i++) {
//			row = sheet.getRow(i);
//			for(int j=0;j<colNum;j++){
//				String value = row.getCell(j).getStringCellValue();
//				builder.append(value);
//				builder.append("--");
//			}
//			builder.append("\n");
//		}
//		System.out.println(builder.toString());
		
		String[] headArray = new String[]{"storeNo","title","promotion","businessTime","mainBusiness","contactPhone"};
		List<BuildingStore> storeList =	ExcelUtil.ImportStoreData(CommonVar.IMG_PREFIX+"/exportFile/147426302997085.xls",BuildingStore.class,headArray);
		for(BuildingStore store : storeList) {
			System.out.println(store.getStoreNo()+"----"+store.getTitle()+ "----"+ store.getPromotion());
		}
	}
	
	/**
	 * 生成表头样式
	 * @param workbook
	 * @return
	 */
	private HSSFCellStyle generateHeadStyle (HSSFWorkbook workbook) {
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
	private HSSFCellStyle generateCommonStyle(HSSFWorkbook workbook){
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
