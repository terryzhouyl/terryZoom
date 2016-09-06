package com.terry.test.localTest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.terry.dao.support.Page;
import com.terry.entity.BuildingStore;
import com.terry.entity.BuildingType;
import com.terry.service.impl.BuildingStoreServiceImpl;
import com.terry.test.BaseTest;

/**
 * 建材商城service测试
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BuildingMallTest extends BaseTest{
	
	@Resource(name="buildingStoreServiceImpl")
	BuildingStoreServiceImpl buildingStoreServiceImpl;
	
	/**
	 * 获取建材类型列表
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGetTypeList() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<BuildingType> list = buildingStoreServiceImpl.getBuildingType();
		this.printfList(list);
	}
	
	/**
	 * 获取建材店铺列表
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGetBuildingStore() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//Page<BuildingStore> page =	buildingStoreServiceImpl.getStorePage(10, 1, 1);
		//this.printfList(page.getRows());
	}
	
}
