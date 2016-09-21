package com.terry.service.initService;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class ExcelService {
	
	
	
	public void buildExportXml() throws DocumentException{
		
		//InputStream is = this.getClass().getResourceAsStream("statistics_export.xml");
		SAXReader reader = new SAXReader();  
		File file = new File("statistics_export.xml");
		Document document = reader.read(file);
		Element root = document.getRootElement();
		List<Element> unitElements = root.elements();
		for(Element unit: unitElements) {
			
		}
		
		
		System.out.println();
	}
	
	public static void main(String[] args) throws DocumentException {
		ExcelService ex = new ExcelService();
		ex.buildExportXml();
	}
}
