package com.terry.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.terry.service.CoreService;
import com.terry.util.SignUtil;

@Controller("weixinController")
@RequestMapping(value = "/weixin")
public class ReplyController {

	@Resource(name="coreService")
	CoreService coreService;
	
	/**
	 * 微信接口 
	 * @param model
	 * @param request
	 * @param response
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr 当为null是说明的信息推送，不为null时说明是网站接入
	 * @throws Exception
	 */
	@RequestMapping(value = "/reply")
	public void signature(Model model,HttpServletRequest request,HttpServletResponse response, String signature, String timestamp, String nonce, String echostr) throws Exception {
		if(null==echostr||echostr.isEmpty()){  
            responseMsg(request, response);  
        }else{  
        	System.out.println("site connect ...:"+new Date());
        	System.out.println("timestamp: "+timestamp);
        	System.out.println("nonce: "+nonce);
        	System.out.println("echostr: "+echostr);
        	
        	if(SignUtil.checkSignature(signature, timestamp, nonce)){
            	System.out.println("connect success!");
            	response.getWriter().write(echostr);
            }else{  
            	System.out.println("connect fail!");
            	response.getWriter().write("error");
            }  
            
            System.out.println();
        }  
	}
	
	/**
	 * 信息回复
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void responseMsg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		//调用核心业务类接收消息、处理消息
		String respMessage = coreService.processRequest(request);
		//响应消息
		pw.print(respMessage);	
		pw.close();		
	}
}
