package com.terry.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.terry.dao.support.Page;
import com.terry.util.JacksonJsonUtil;


public class MyController extends BaseController {
	
	public ResponseEntity<String> renderMyData(Boolean status, String msg,
			Object obj1) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"data\":" + JacksonJsonUtil.toJson(obj1) + "");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	public ResponseEntity<String> renderMyPageData(Boolean status, String msg,
			Object obj1, Object obj2) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"data\":" + JacksonJsonUtil.toJson(obj1) + ",");
		sb.append("\"page\":" + JacksonJsonUtil.toJson(obj2) + "");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	public ResponseEntity<String> renderPlusSncodeReturn(Boolean status,
			String msg, Integer playCount, String content, Long snId,
			String snCode, Long logId) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		String str = "{\"status\":\"" + status + "\",\"msg\":\"" + msg
				+ "\",\"content\":\"" + content + "\",\"playCount\":\""
				+ playCount + "\", \"snId\":\"" + snId + "\",\"snCode\":\""
				+ snCode + "\",\"logId\":\"" + logId + "\"}";
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(str,
				initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	public ResponseEntity<String> renderPlus(Boolean status, String msg,
			Integer playCount, String content) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		String str = "{\"status\":\"" + status + "\",\"msg\":\"" + msg
				+ "\",\"content\":\"" + content + "\",\"playCount\":\""
				+ playCount + "\"}";
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(str,
				initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	// 群发图文 参数 group_id \ mediaId\ msgtype\content
	public String initStringsForMedia(String groupId, String media_id) {
		String result = "{\"filter\":{\"group_id\":\"" + groupId
				+ "\"},\"mpnews\":{\"media_id\":\"" + media_id
				+ "\"},\"msgtype\":\"mpnews\"}";
		if (StringUtils.isBlank(groupId)) {
			result = "{\"filter\":{\"is_to_all\":true},\"mpnews\":{\"media_id\":\""
					+ media_id + "\"},\"msgtype\":\"mpnews\"}";
		}
		return result;
	}

	// 群发文字内容
	public String initStringsForText(String groupId, String content) {
		String result = "{\"filter\":{\"group_id\":\"" + groupId
				+ "\"},\"text\":{\"content\":\"" + content
				+ "\"},\"msgtype\":\"text\"}";
		if (StringUtils.isBlank(groupId)) {
			result = "{\"filter\":{\"is_to_all\": true},\"text\":{\"content\":\""
					+ content + "\"},\"msgtype\":\"text\"}";
		}
		return result;
	}

	/* api请求 */
	public ResponseEntity<String> renderApiMsg(Integer status, String msg) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		String str = "{\"status\":\"" + status + "\",\"msg\":\"" + msg + "\"}";
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(str,
				initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	public ResponseEntity<String> renderApiData(Integer status, String msg,
			Object obj) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\"");
		if (status == 1) {
			sb.append(",\"data\":" + JacksonJsonUtil.toJson(obj) + "");
		}
		sb.append("}");
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	public ResponseEntity<String> renderApiObject(Integer status, String msg,
			String content) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"data\":\"" + content + "\"");
		sb.append("}");
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	public ResponseEntity<String> renderApiHomeData(Integer status, String msg,
			Object obj, Object obje) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"slideshowData\":" + JacksonJsonUtil.toJson(obj) + ",");
		sb.append("\"happinessTemplateData\":" + JacksonJsonUtil.toJson(obje)
				+ "");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity<String> renderApiPage(Integer status, String msg,
			Page page) {
		if (StringUtils.isEmpty(msg)) {
			msg = "";
		}
		if (page == null) {// || page.getRows().isEmpty()
			return renderApiMsg(status, msg);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"status\":\"" + status + "\",\"msg\":\"" + msg + "\",");
		sb.append("\"total\":\"" + page.getTotal() + "\",\"pageSize\":\""
				+ page.getPageSize() + "\",");
		sb.append("\"current\":\"" + page.getCurrentPage() + "\",");
		sb.append("\"data\":" + JacksonJsonUtil.toJson(page.getRows()) + "");
		sb.append("}");

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(
				sb.toString(), initHttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	/*
	 * 生成随机数
	 */
	public static String getRandom() {
		java.util.Random r = new java.util.Random();
		StringBuffer random = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			random.append(Math.abs(r.nextInt(10)));
		}
		return random.toString();
	}

}