package com.terry.service.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import net.spy.memcached.MemcachedClient;

//@Service("memcacheService")
public class MemcacheService extends BaseService{

	private MemcachedClient mcc = null;
	
	@PostConstruct
	@Override
	protected void init() {
		super.init();
		String host = getConfig("memcached_host");
		String port = getConfig("memcached_port");
		try {
			mcc = new MemcachedClient(new InetSocketAddress(host, Integer.parseInt(port)));
			//初始化
			log.info("connection to memcached server successful");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Set进缓存
	public void SetCached(String key,int exp,String value) throws InterruptedException, ExecutionException {
		Future fo = mcc.set(key, exp, value);
		log.info("set "+key +":"+value+" status : "+fo.get());
		System.out.println(key + "value in cache - "+mcc.get(key));
	}
	
	//取值
	public Object getCached(String key) {
		Object value = mcc.get(key);
		return value;
	}
	
	//关闭连接
	@PreDestroy
	public void release() {
		mcc.shutdown();
		log.info("release memcached connection ... ");
	}
}
