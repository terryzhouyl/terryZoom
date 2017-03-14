package com.terry.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUtil {
	
	private static Logger logger = LoggerFactory.getLogger(TestUtil.class);
	
	public static void getBeanInfo(Object object) {
		try {
			Map map = BeanUtils.describe(object);
			for(Object o : map.keySet()){
				logger.info("{}---{}",o,map.get(o));
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
