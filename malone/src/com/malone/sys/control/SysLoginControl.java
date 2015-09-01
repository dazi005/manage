/**
 * 
 */
package com.malone.sys.control;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.malone.sys.service.SysLoginService;
import com.malone.sys.tools.SysView;

/**
 * @author pengl
 * 系统登录模块
 * 2015-5-12下午4:47:41
 */
@Controller
@RequestMapping("/sys")
public class SysLoginControl {
	@Resource(name="sysLoginService")
	private SysLoginService sysLoginService;
	
	/**
	 * 系统用户登录
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(@RequestBody Map<String, Object> parMap,HttpServletRequest request,HttpServletResponse response){
		return sysLoginService.loginService(parMap,request,response);
	}
	
	/**
	 * 系统首页
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request){
		return SysView.index;
	}
	
	/**
	 * 获取用户树形菜单
	 * @return
	 */
	@RequestMapping(value = "/treeMenu",method=RequestMethod.POST)
	@ResponseBody
	public String treeMenu(HttpServletRequest request){
		return request.getSession().getAttribute("menuJson")+"";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value = "/modifypass",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> modifyPass(@RequestBody Map<String, String> map , HttpServletRequest request){
		return sysLoginService.modifyPassService(map,request);
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping(value = "/loginout")
	public String loginOut(HttpServletRequest request){
		request.getSession().removeAttribute("sysUser");
		return SysView.login;
	}
}
