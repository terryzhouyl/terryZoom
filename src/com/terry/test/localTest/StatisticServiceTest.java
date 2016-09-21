package com.terry.test.localTest;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.terry.service.impl.StatisticService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StatisticServiceTest {
	
	@Resource(name = "statisticService")
	StatisticService statisticService;
	
	@Test
	public void testExportStoreData() throws IOException{
		statisticService.exportStoreData();
	} 
	
	@Test
	public void testImportStoreData() throws IOException {
		statisticService.ImportStoreData();
	}
}
