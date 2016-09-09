package com.terry.controller;


import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.terry.dao.support.Page;
import com.terry.util.JacksonJsonUtil;


public class BaseController {
	
	public Logger log = Logger.getLogger(BaseController.class);

	/**
	 * 返回服务器地址 like http://192.168.1.1:8441/UUBean/
	 */
	public String getHostUrl(HttpServletRequest request) {
		String hostName = request.getServerName();
		Integer hostPort = request.getServerPort();
		String path = request.getContextPath();

		if (hostPort == 80) {
			return "http://" + hostName + path + "/";
		} else {
			return "http://" + hostName + ":" + hostPort + path + "/";
		}
	}

	/***
	 * 获取当前的website路径 String
	 */
	public static String getWebSite(HttpServletRequest request) {
		String returnUrl = request.getScheme() + "://"
				+ request.getServerName();

		if (request.getServerPort() != 80) {
			returnUrl += ":" + request.getServerPort();
		}

		returnUrl += request.getContextPath();

		return returnUrl;
	}




	/**
	 * 初始化HTTP头.
	 * 
	 * @return HttpHeaders
	 */
	public HttpHeaders initHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("text", "html",
				Charset.forName("utf-8"));
		headers.setContentType(mediaType);
		return headers;
	}

	/**
	 * 返回 信息数据
	 * 
	 * @param status
	 * @param msg
	 * @return
	 */
	public ResponseEntity<String> renderMsg(Boolean status, String msg) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		String str = "{\"status\":\"" + status + "\",\"msg\":\"" + msg + "\"}";
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(str,
				initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * 返回obj数据
	 * 
	 * @param status
	 * @param msg
	 * @param obj
	 * @return
	 */
	public ResponseEntity<String> renderData(Boolean status, String msg,
			Object obj) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"data\":" + JacksonJsonUtil.toJson(obj) + "");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}
	
	
	/**
	 * 返回两个obj数据
	 * 
	 * @param status
	 * @param msg
	 * @param obj
	 * @param obj1
	 * @return
	 */
	public ResponseEntity<String> renderData1(Boolean status, String msg,
			Object obj,Object obj1) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"data\":" + JacksonJsonUtil.toJson(obj) + ",");
		sb.append("\"data1\":" + JacksonJsonUtil.toJson(obj1) + "");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}
	
	
	/**
	 * 返回两个obj数据
	 * 
	 * @param status
	 * @param msg
	 * @param obj
	 * @param obj1
	 * @return
	 */
	public ResponseEntity<String> renderData2(Boolean status, String msg,
			Object obj,Object obj1,Object obj2) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"data\":" + JacksonJsonUtil.toJson(obj) + ",");
		sb.append("\"data1\":" + JacksonJsonUtil.toJson(obj1) + ",");
		sb.append("\"data2\":" + JacksonJsonUtil.toJson(obj2) + "");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}
	
	/**
	 * 返回list数据
	 * 
	 * @param status
	 * @param msg
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseEntity<String> renderListData(Boolean status, String msg,
			List list) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		if (list == null) {// || page.getRows().isEmpty()
			return renderMsg(false, "No data found");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"rows\":" + JacksonJsonUtil.toJson(list) + "");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	/**
	 * 返回page数据
	 * 
	 * @param status
	 * @param msg
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseEntity<String> renderPageData(Boolean status, String msg,
			Page page) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		if (page == null) {// || page.getRows().isEmpty()
			return renderMsg(false, "No data found");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"total\":\"" + page.getTotal() + "\",\"pageSize\":\""
				+ page.getPageSize() + "\",");
		sb.append("\"currentPage\":\"" + page.getCurrentPage() + "\",");
		sb.append("\"totalPage\":\""+page.getTotalPage()+"\",");
		sb.append("\"rows\":" + JacksonJsonUtil.toJson(page.getRows()) + "");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	/***
	 * 获取IP（如果是多级代理，则得到的是一串IP值）
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if (ip != null && ip.length() > 0) {
			String[] ips = ip.split(",");
			for (int i = 0; i < ips.length; i++) {
				if (!"unknown".equalsIgnoreCase(ips[i])) {
					ip = ips[i];
					break;
				}
			}
		}

		return ip;
	}

	/**
	 * 国际化获得语言内容
	 * 
	 * @param key
	 *            语言key
	 * @param args
	 * @param argsSplit
	 * @param defaultMessage
	 * @param locale
	 * @return
	 */
	public static String getLanguage(String key, String args, String argsSplit,
			String defaultMessage, String locale) {
		String language = "zh";
		String contry = "cn";
		String returnValue = defaultMessage;

		if (!StringUtils.isEmpty(locale)) {
			try {
				String[] localeArray = locale.split("_");
				language = localeArray[0];
				contry = localeArray[1];
			} catch (Exception e) {
			}
		}

		try {
			ResourceBundle resource = ResourceBundle.getBundle("lang.resource",
					new Locale(language, contry));
			returnValue = resource.getString(key);
			if (!StringUtils.isEmpty(args)) {
				String[] argsArray = args.split(argsSplit);
				for (int i = 0; i < argsArray.length; i++) {
					returnValue = returnValue.replace("{" + i + "}",
							argsArray[i]);
				}
			}
		} catch (Exception e) {
		}

		return returnValue;
	}
}
