/**
 * 
 */
package com.malone.sys.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.s;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.malone.sys.dto.DataTableParameter;
import com.malone.sys.dto.SysMenu;
import com.malone.sys.dto.SysOperLog;
import com.malone.sys.dto.SysRole;
import com.malone.sys.dto.SysUser;
import com.malone.sys.service.SysManageService;
import com.malone.sys.tools.SysTool;
import com.malone.util.CookieUtil;

/**
 * @author pengl
 * 2015-5-18下午10:00:04
 * 系统管理模块
 */
@Controller
@RequestMapping("/sysmanage")
public class SysManageControl {
	@Resource(name="sysManageService")
	private SysManageService sysManageService;
	@Resource(name="cookieUtil")
	private CookieUtil cookieUtil;
	private Logger _logger = LogManager.getLogger();
	/**
	 * 跳转到用户管理首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userConfig/userList")
	public String userList(HttpServletRequest request,HttpServletResponse response){
		//获取当前菜单ID
		String curmuid = cookieUtil.getCookie(request, "curmuid");
		if(StringUtils.isEmpty(curmuid)){
			try {response.sendRedirect(request.getContextPath() + "/sys/index");} catch (IOException e) {}
		}
		//获取下级菜单列表
		List<SysMenu> menulist = sysManageService.selectSubMenusByMuid(curmuid,request);
		request.setAttribute("menulist", menulist);
		return "WEB-INF/pages/sys/sysmanage/userList";
	}
	/**
	 * 跳转到新增用户页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userConfig/userAdd")
	public String userAddIndex(HttpServletRequest request,HttpServletResponse response){
		return "WEB-INF/pages/sys/sysmanage/userEdit";
	}
	/**
	 * 跳转到编辑用户页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userConfig/userEdit")
	public String userEditIndex(@RequestParam int usid,HttpServletRequest request){
		SysUser sysUser = sysManageService.selectUserService(usid);
		request.setAttribute("userBean", sysUser);
		return "WEB-INF/pages/sys/sysmanage/userEdit";
	}
	/**
	 * 跳转到新增菜单页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/menuConfig/menuAdd")
	public String menuAddIndex(HttpServletRequest request){
		request.setAttribute("upmuid", request.getParameter("muid"));
		request.setAttribute("upmuname", request.getParameter("muname"));
		request.setAttribute("upmulevel", request.getParameter("mulevel"));
		return "WEB-INF/pages/sys/sysmanage/menuEdit";
	}
	/**
	 * 跳转到编辑菜单页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/menuConfig/menuEdit")
	public String menuEditIndex(HttpServletRequest request){
		String muid = request.getParameter("muid");
		//根据菜单ID获取菜单详情
		SysMenu sysMenu = sysManageService.selectMenuInfoByMuid(muid);
		request.setAttribute("sysMenu", sysMenu);
		return "WEB-INF/pages/sys/sysmanage/menuEdit";
	}
	/**
	 * 跳转到新增角色页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/roleConfig/roleAdd")
	public String roleAddIndex(HttpServletRequest request,HttpServletResponse response){
		return "WEB-INF/pages/sys/sysmanage/roleEdit";
	}
	/**
	 * 跳转到编辑角色页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/roleConfig/roleEdit")
	public String roleEditIndex(@RequestParam int roid,HttpServletRequest request){
		SysRole sysRole = sysManageService.selectRoleService(roid);
		request.setAttribute("roleBean", sysRole);
		return "WEB-INF/pages/sys/sysmanage/roleEdit";
	}
	/**
	 * 跳转到用户权限分配页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userConfig/userPrivilege")
	public String userPrivilegeIndex(@RequestParam int usid,HttpServletRequest request){
		return sysManageService.userPrivilegeIndexService(usid,request);
	}
	/**
	 * 获取部门树形菜单
	 * @return
	 */
	@RequestMapping(value = "/treeDept",method=RequestMethod.POST)
	@ResponseBody
	public String treeDept(HttpServletRequest request){
		return request.getSession().getAttribute("deptJson")+"";
	}
	/**
	 * 删除用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userConfig/deleteUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteUser(@RequestBody Map<String, String> map ,HttpServletRequest request){
		return sysManageService.deleteUserService(map , request);
	}
	/**
	 * 删除角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/roleConfig/deleteRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteRole(@RequestBody Map<String, String> map ,HttpServletRequest request){
		return sysManageService.deleteRoleService(map , request);
	}
	/**
	 * 删除菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/menuConfig/deleteMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteMenu(@RequestBody Map<String, String> map ,HttpServletRequest request){
		return sysManageService.deleteMenuService(map , request);
	}
	/**
	 * 保存用户信息
	 * @return
	 */
	@RequestMapping(value = "/userConfig/saveUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveUser(SysUser user , HttpServletRequest request){
		return sysManageService.saveUserService(user,request);
	}
	/**
	 * 保存用户权限分配
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userConfig/saveUserPrivilege",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveUserPrivilege(@RequestBody Map<String, String> map ,HttpServletRequest request){
		 return sysManageService.saveUserPrivilegeService(map,request);
	}
	/**
	 * 保存角色信息
	 * @return
	 */
	@RequestMapping(value = "/roleConfig/saveRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveRole(SysRole role , HttpServletRequest request){
		return sysManageService.saveRoleService(role,request);
	}
	/**
	 * ajax 获取用户列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userConfig/userListAjax",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userListAjax(HttpServletRequest request){
		List<SysUser> list = sysManageService.selectUsersByDpid(request);
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("aaData", list);
		rMap.put("iTotalRecords", list.size());
		rMap.put("iTotalDisplayRecords", list.size());
		return rMap;
	}
	/**
	 * 跳转到角色管理首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/roleConfig/roleList")
	public String roleList(HttpServletRequest request,HttpServletResponse response){
		//获取当前菜单ID
		String curmuid = cookieUtil.getCookie(request, "curmuid");
		if(StringUtils.isEmpty(curmuid)){
			try {response.sendRedirect(request.getContextPath() + "/sys/index");} catch (IOException e) {}
		}
		//获取下级菜单列表
		List<SysMenu> menulist = sysManageService.selectSubMenusByMuid(curmuid,request);
		request.setAttribute("menulist", menulist);
		return "WEB-INF/pages/sys/sysmanage/roleList";
	}
	/**
	 * ajax 获取角色列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/roleConfig/roleListAjax",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> roleListAjax(HttpServletRequest request){
		List<SysRole> list = sysManageService.selectRolesByUsid(request);
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("aaData", list);
		rMap.put("iTotalRecords", list.size());
		rMap.put("iTotalDisplayRecords", list.size());
		return rMap;
	}
	/**
	 * 跳转到菜单管理首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/menuConfig/menuList")
	public String menuList(HttpServletRequest request,HttpServletResponse response){
		//获取当前菜单ID
		String curmuid = cookieUtil.getCookie(request, "curmuid");
		if(StringUtils.isEmpty(curmuid)){
			try {response.sendRedirect(request.getContextPath() + "/sys/index");} catch (IOException e) {}
		}
		//获取下级菜单列表
		List<SysMenu> menulist = sysManageService.selectSubMenusByMuid(curmuid,request);
		request.setAttribute("menulist", menulist);
		
		//获取系统全部的菜单列表
		request.setAttribute("allMenus", sysManageService.selectAllMenus(request));
		return "WEB-INF/pages/sys/sysmanage/menuList";
	}
	/**
	 * 跳转到角色权限分配页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/roleConfig/rolePrivilege")
	public String rolePrivilegeIndex(@RequestParam int roid,HttpServletRequest request){
		return sysManageService.rolePrivilegeIndexService(roid,request);
	}
	/**
	 * 保存角色权限分配
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/roleConfig/saveRolePrivilege",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveRolePrivilege(@RequestBody ArrayList<Map<String, String>> parlist , @RequestParam int roid, HttpServletRequest request){
		 return sysManageService.saveRolePrivilegeService(parlist,roid,request);
	}
	/**
	 * 保存菜单信息
	 * @return
	 */
	@RequestMapping(value = "/menuConfig/saveMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveMenu(SysMenu menu , HttpServletRequest request){
		return sysManageService.saveMenuService(menu,request);
	}
	/**
	 * 跳转到资源管理首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/res/resConfig")
	public String proConfigIndex(HttpServletRequest request){
		return "WEB-INF/pages/sys/sysmanage/resconfig";
	}
	/**
	 * 跳转到日志列表查询首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logConfig/logList")
	public String logList(HttpServletRequest request,HttpServletResponse response){
		//获取当前菜单ID
		String curmuid = cookieUtil.getCookie(request, "curmuid");
		if(StringUtils.isEmpty(curmuid)){
			try {response.sendRedirect(request.getContextPath() + "/sys/index");} catch (IOException e) {}
		}
		//获取下级菜单列表
		List<SysMenu> menulist = sysManageService.selectSubMenusByMuid(curmuid,request);
		request.setAttribute("menulist", menulist);
		return "WEB-INF/pages/sys/sysmanage/logList";
	}
	
	/**
	 * ajax获取日志列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logConfig/logListAjax",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> logListAjax(DataTableParameter param,HttpServletRequest request,HttpSession session){
		_logger.warn("获取到控件参数：" + param);
		Map<String,Object> result = sysManageService.selectLogList(param,request);
		@SuppressWarnings("unchecked")
		List<SysOperLog> loglist = (List<SysOperLog>)result.get("loglist");
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("aaData", loglist);
		rMap.put("iTotalRecords", result.get("totalRecord"));
		rMap.put("iTotalDisplayRecords", result.get("totalRecord"));
		return rMap;
	}
}
