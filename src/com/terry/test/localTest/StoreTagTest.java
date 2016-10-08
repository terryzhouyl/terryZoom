package com.terry.test.localTest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.terry.entity.BuildingScore;
import com.terry.entity.BuildingStore;
import com.terry.service.impl.BuildingStoreService;
import com.terry.test.BaseTest;

/**
 * 建材标签测试
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StoreTagTest extends BaseTest{

	@Resource(name="buildingStoreService")
	BuildingStoreService buildingStoreService;
	
	/**
	 * 根据标签查询店铺
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testGetStoreByTag() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		List<BuildingStore> list = buildingStoreService.queryStoresByTag(1);
		this.printfList(list);
	}
	
	@Test
	public void saveScore() {		
		BuildingScore score = new BuildingScore();
		score.setScore(4.0);
		score.setStoreId(1L);
		score.setUserId(2L);
		buildingStoreService.saveScore(score);
	}
}
