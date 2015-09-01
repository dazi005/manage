package com.malone.test.control;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import com.danga.MemCached.MemCachedClient;
import com.malone.test.service.TestService;
import com.malone.util.CookieUtil;
import com.malone.util.PropertiesExtra;
import com.malone.util.ReadPropertiesUtil;

@Controller
@RequestMapping("/test")
public class TestControl {
	private Logger _logger = LogManager.getLogger();
	@Resource(name="testImpl")
	private TestService test;
	@Resource(name="readPropertiesUtil")
    private ReadPropertiesUtil readPropertiesUtil; //读取属性文件
	@Resource(name="cookieUtil")
    private CookieUtil cookieUtil; //cookie操作
	@Resource
    private MemCachedClient memcachedClient; //
	
	@RequestMapping(value = "/tess")
	public String tess(HttpServletRequest request) throws IOException{
		memcachedClient.set("config", new PropertiesExtra("contentconfig.properties").getProperties());
		return "index";
	}
	
	@RequestMapping(value = "/tess2")
	public String tess2(HttpServletRequest request) throws IOException{
		_logger.warn("===" + readPropertiesUtil.getProperty("config","test"));
		return "index";
	}
	@RequestMapping(value = "/tess3")
	public String tess3(HttpServletRequest request) throws IOException{
		ServletContext sc = request.getSession().getServletContext();
		ApplicationContext applicationContext = (WebApplicationContext) sc.getAttribute(WebApplicationContext.SCOPE_APPLICATION); 
		String[] s = applicationContext.getBeanDefinitionNames();
		for(String ss : s){
			_logger.warn(ss);
		}
		return "index";
	}
}
