package com.terry.test.localTest;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.terry.service.impl.MemcacheService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MemcachedTest {

	@Autowired
	MemcacheService memcacheService;
	
	@Test
	public void putMemCached() {
		try {
			memcacheService.SetCached("my",600,"mine");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getMemCached() {
		Object value = memcacheService.getCached("my");
		System.out.println("memcached value is "+value);
	}
}
