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
import com.terry.service.impl.BuildingStoreService;
import com.terry.test.BaseTest;

/**
 * 建材商城service测试
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BuildingMallTest extends BaseTest{
	
	@Resource(name="buildingStoreService")
	BuildingStoreService buildingStoreService;
	
	/**
	 * 获取建材类型列表
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGetTypeList() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<BuildingType> list = buildingStoreService.getBuildingType();
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
		Page<BuildingStore> page =	buildingStoreService.getStorePage(10, 1, new BuildingStore());
		this.printfList(page.getRows());
	}
	
}
