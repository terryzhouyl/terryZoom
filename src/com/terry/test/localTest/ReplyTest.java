package com.terry.test.localTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.terry.entity.MenuConfig;
import com.terry.service.MenuConfigService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ReplyTest {

	@Resource(name="menuConfigServiceImpl")
	MenuConfigService menuConfigServiceImpl;
	
	@Test
	public void testGetMenu() {
		List<MenuConfig> list =	menuConfigServiceImpl.getMenuConfigList();
		for(int i=0;i<list.size();i++) {
			List<MenuConfig> subList = list.get(i).getListChildren();
			System.out.println(list.get(i).getMenuName());
			for(int j=0;j<list.size();j++) {
				System.out.println("	"+subList.get(j).getMenuName());
			}
		}
	}
	
}
