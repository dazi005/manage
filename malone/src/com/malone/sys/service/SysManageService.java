/**
 * 
 */
package com.malone.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.python.modules.newmodule;
import org.springframework.stereotype.Service;

import weblogic.descriptor.descriptorgen.interfacegen;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.bea.core.repackaged.aspectj.weaver.ast.Var;
import com.malone.sys.dto.DataTableParameter;
import com.malone.sys.dto.SysDatacs;
import com.malone.sys.dto.SysMenu;
import com.malone.sys.dto.SysOperLog;
import com.malone.sys.dto.SysRole;
import com.malone.sys.dto.SysUser;
import com.malone.sys.tools.SysTool;

/**
 * @author pengl
 * 2015-5-19下午4:10:38
 * 系统管理模块业务层
 */
@Service("sysManageService")
public class SysManageService {
	private Logger _logger = LogManager.getLogger();
	@Resource
	private SqlSessionFactory sessionFactory;
	@Resource(name="sysLogService")
	private SysLogService sysLogService;
	@Resource(name="sysTool")
    private SysTool sysTool;
	
	/**
	 * 查询用户列表
	 * 根据当前登录用户所属部门查询当前用户所属部门及其子部门下所有用户
	 * @return
	 */
	public List<SysUser> selectUsersByDpid(HttpServletRequest request) {
		List<SysUser> userlist = null;
		SysUser sysUser = (SysUser)request.getSession().getAttribute("sysUser");
		int dpid = sysUser.getSysdept().getDpid();
		SqlSession sqlSession = null;
		try{
			sqlSession = sessionFactory.openSession();
			userlist = sqlSession.selectList("com.malone.sys.sysmanage.selectUsersByDpid" , dpid);
		}catch (Exception e) {
			_logger.error("【系统管理-用户管理模块-用户列表】查询用户列表异常，错误信息：" + e.getMessage());
		}finally{
			sqlSession.close();
		}
		return userlist;
	}
	/**
	 * 根据菜单ID查询子菜单列表
	 * @param request
	 * @return
	 */
	public List<SysMenu> selectSubMenusByMuid(String curmuid,HttpServletRequest request){
		List<SysMenu> menulist = null;
		SqlSession sqlSession = null;
		SysUser sysUser = (SysUser)request.getSession().getAttribute("sysUser");
		Map<String,Object> parMap = new HashMap<String, Object>();
		parMap.put("curmuid", curmuid);
		parMap.put("usid", sysUser.getUsid());
		parMap.put("dpid", sysUser.getSysdept().getDpid());
		parMap.put("rolelist", sysUser.getRolelist());
		try {
			sqlSession = sessionFactory.openSession();
			menulist = sqlSession.selectList("com.malone.sys.sysmanage.selectSubMenusByMuid" , parMap);
		} catch (Exception e) {
			_logger.error("【系统管理-查询子菜单列表】查询子菜单列表异常，错误信息：" + e.getMessage());
		}finally{
			sqlSession.close();
		}
		return menulist;
	}
	/**
	 * 删除用户
	 * @param usids
	 * @return
	 */
	public Map<String, String> deleteUserService(Map<String, String> map , HttpServletRequest request) {
		Map<String, String> resMap = new HashMap<String, String>();
		SysUser sysUser = (SysUser)request.getSession().getAttribute("sysUser");
		String usids = map.get("usids");
		SysOperLog sysOperLog = null;
		SqlSession sqlSession = null;
		try{
			sqlSession = sessionFactory.openSession();
			int i = sqlSession.update("com.malone.sys.sysmanage.updateUserByUsids", usids.split(","));
			if(i > 0){
				sysOperLog = new SysOperLog(sysUser.getUsid(),"用户删除","删除用户",sysUser.getUsname() + "执行了删除用户操作，删除成功，删除用户ID："+usids,"信息",sysUser.getUserAgent());
				resMap.put("resultCode", "0");
				resMap.put("resultMsg", "删除成功");
			}else{
				sysOperLog = new SysOperLog(sysUser.getUsid(),"用户删除","删除用户",sysUser.getUsname() + "执行了删除用户操作，删除失败，错误信息：【数据返回更新记录条数为0】","错误",sysUser.getUserAgent());
				resMap.put("resultCode", "-1");
				resMap.put("resultMsg", "删除失败");
			}
		}catch (Exception e) {
			_logger.error("【系统管理-用户管理模块-用户删除】删除用户异常，错误信息：" + e.getMessage());
			sysOperLog = new SysOperLog(sysUser.getUsid(),"用户删除","删除用户",sysUser.getUsname() + "执行了删除用户操作，删除失败，错误信息：【"+e.getMessage()+"】","错误",sysUser.getUserAgent());
		}finally{
			sysLogService.InsertSysLog(sysOperLog , sqlSession);
			sqlSession.close();
		}
		return resMap;
	}
	/**
	 * 删除角色
	 * @param usids
	 * @return
	 */
	public Map<String, String> deleteRoleService(Map<String, String> map , HttpServletRequest request) {
		Map<String, String> resMap = new HashMap<String, String>();
		SysUser sysUser = (SysUser)request.getSession().getAttribute("sysUser");
		String usids = map.get("roids");
		SysOperLog sysOperLog = null;
		SqlSession sqlSession = null;
		try{
			sqlSession = sessionFactory.openSession();
			int i = sqlSession.update("com.malone.sys.sysmanage.deleteRoleByRoids", usids.split(","));
			if(i > 0){
				sysOperLog = new SysOperLog(sysUser.getUsid(),"角色删除","删除角色",sysUser.getUsname() + "执行了删除角色操作，删除成功，删除角色ID："+usids,"信息",sysUser.getUserAgent());
				resMap.put("resultCode", "0");
				resMap.put("resultMsg", "角色删除成功");
			}else{
				sysOperLog = new SysOperLog(sysUser.getUsid(),"角色删除","删除角色",sysUser.getUsname() + "执行了删除角色操作，删除失败，错误信息：【数据返回更新记录条数为0】","错误",sysUser.getUserAgent());
				resMap.put("resultCode", "-1");
				resMap.put("resultMsg", "角色删除失败");
			}
		}catch (Exception e) {
			_logger.error("【系统管理-角色管理模块-删除角色】删除角色异常，错误信息：" + e.getMessage());
			sysOperLog = new SysOperLog(sysUser.getUsid(),"角色删除","删除角色",sysUser.getUsname() + "执行了删除角色操作，删除失败，错误信息：【"+e.getMessage()+"】","错误",sysUser.getUserAgent());
		}finally{
			sysLogService.InsertSysLog(sysOperLog , sqlSession);
			sqlSession.close();
		}
		return resMap;
	}
	/**
	 * 删除菜单
	 * @param usids
	 * @return
	 */
	public Map<String, String> deleteMenuService(Map<String, String> map , HttpServletRequest request) {
		Map<String, String> resMap = new HashMap<String, String>();
		SysUser sysUser = (SysUser)request.getSession().getAttribute("sysUser");
		String muid = map.get("muid");
		SysOperLog sysOperLog = null;
		SqlSession sqlSession = null;
		try{
			sqlSession = sessionFactory.openSession();
			int i = sqlSession.update("com.malone.sys.sysmanage.deleteMenuByMuid", muid);
			if(i > 0){
				sysOperLog = new SysOperLog(sysUser.getUsid(),"菜单删除","删除菜单",sysUser.getUsname() + "执行了删除菜单操作，删除成功，删除菜单ID："+muid,"信息",sysUser.getUserAgent());
				resMap.put("resultCode", "0");
				resMap.put("resultMsg", "菜单删除成功");
			}else{
				sysOperLog = new SysOperLog(sysUser.getUsid(),"菜单删除","删除菜单",sysUser.getUsname() + "执行了删除菜单操作，删除失败，错误信息：【数据返回更新记录条数为0】","错误",sysUser.getUserAgent());
				resMap.put("resultCode", "-1");
				resMap.put("resultMsg", "菜单删除失败");
			}
		}catch (Exception e) {
			_logger.error("【系统管理-菜单管理模块-删除菜单】删除菜单异常，错误信息：" + e.getMessage());
			sysOperLog = new SysOperLog(sysUser.getUsid(),"菜单删除","删除菜单",sysUser.getUsname() + "执行了删除菜单操作，删除失败，错误信息：【"+e.getMessage()+"】","错误",sysUser.getUserAgent());
		}finally{
			sysLogService.InsertSysLog(sysOperLog , sqlSession);
			sqlSession.close();
		}
		return resMap;
	}
	/**
	 * 根据用户ID查询用户
	 * @param usid
	 * @return SysUser
	 */
	public SysUser selectUserService(int usid) {
		SqlSession sqlSession = null;
		SysUser sysUser = null;
		try{
			sqlSession = sessionFactory.openSession();
			sysUser = sqlSession.selectOne("com.malone.sys.sysmanage.selectUserByUsid", usid);
		}catch (Exception e) {
			_logger.error("【系统管理-用户管理模块-用户修改】根据用户ID查询用户异常，错误信息：" + e.getMessage());
		}finally{
			sqlSession.close();
		}
		return sysUser;
	}
	/**
	 * 根据角色ID查询角色
	 * @param usid
	 * @return SysUser
	 */
	public SysRole selectRoleService(int roid) {
		SqlSession sqlSession = null;
		SysRole sysRole = null;
		try{
			sqlSession = sessionFactory.openSession();
			sysRole = sqlSession.selectOne("com.malone.sys.sysmanage.selectRoleByRoid", roid);
		}catch (Exception e) {
			_logger.error("【系统管理-角色管理模块-角色修改】根据角色ID查询角色异常，错误信息：" + e.getMessage());
		}finally{
			sqlSession.close();
		}
		return sysRole;
	}
	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	public Map<String, String> saveUserService(SysUser user , HttpServletRequest request) {
		Map<String, String> resMap = new HashMap<String, String>();
		SysOperLog sysOperLog = null;
		SqlSession sqlSession = null;
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		try{
			sqlSession = sessionFactory.openSession();
			int i = 0;
			if(user.getUsid() == 0){
				i = sqlSession.insert("com.malone.sys.sysmanage.insertUser", user);
			}else{
				i = sqlSession.update("com.malone.sys.sysmanage.updateUserByUsid", user);
			}
			if(i>0){
				sysOperLog = new SysOperLog(sysUser.getUsid(),"用户编辑/新增","编辑/新增用户",sysUser.getUsname() + "执行了用户编辑/新增操作，保存成功，保存参数：" + JSON.toJSONString(user),"信息",sysUser.getUserAgent());
				resMap.put("resultCode", "0");
				resMap.put("resultMsg", "用户信息保存成功");
			}else{
				sysOperLog = new SysOperLog(sysUser.getUsid(),"用户编辑/新增","编辑/新增用户",sysUser.getUsname() + "执行了用户编辑/新增操作，保存失败，错误信息：【数据返回更新记录条数为0】，保存参数：" + JSON.toJSONString(user),"错误",sysUser.getUserAgent());
				resMap.put("resultCode", "-1");
				resMap.put("resultMsg", "用户信息保存失败");
			}
		}catch (Exception e) {
			_logger.error("【系统管理-用户管理模块-用户新增/保存】保存用户信息异常，错误信息：" + e.getMessage());
			sysOperLog = new SysOperLog(sysUser.getUsid(),"用户编辑/新增","编辑/新增用户",sysUser.getUsname() + "执行了用户编辑/新增操作，保存失败，错误信息：【"+e.getMessage()+"】","错误",sysUser.getUserAgent());
			resMap.put("resultCode", "-1");
			resMap.put("resultMsg", "系统异常，请稍后再试");
			return resMap;
		}finally{
			sysLogService.InsertSysLog(sysOperLog , sqlSession);
			sqlSession.close();
		}
		return resMap;
	}
	/**
	 * 保存角色信息
	 * @param user
	 * @return
	 */
	public Map<String, String> saveRoleService(SysRole role , HttpServletRequest request) {
		Map<String, String> resMap = new HashMap<String, String>();
		SysOperLog sysOperLog = null;
		SqlSession sqlSession = null;
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		role.setCreater(sysUser.getUsid());
		role.setRotype(2);
		try{
			sqlSession = sessionFactory.openSession();
			int i = 0;
			if(role.getRoid() == 0){
				i = sqlSession.insert("com.malone.sys.sysmanage.insertRole", role);
			}else{
				i = sqlSession.update("com.malone.sys.sysmanage.updateRoleByRoid", role);
			}
			if(i>0){
				sysOperLog = new SysOperLog(sysUser.getUsid(),"角色编辑/新增","编辑/新增角色",sysUser.getUsname() + "执行了角色编辑/新增操作，保存成功，保存参数：" + JSON.toJSONString(role),"信息",sysUser.getUserAgent());
				resMap.put("resultCode", "0");
				resMap.put("resultMsg", "角色信息保存成功");
			}else{
				sysOperLog = new SysOperLog(sysUser.getUsid(),"角色编辑/新增","编辑/新增角色",sysUser.getUsname() + "执行了角色编辑/新增操作，保存失败，错误信息：【数据返回更新记录条数为0】，保存参数：" + JSON.toJSONString(role),"错误",sysUser.getUserAgent());
				resMap.put("resultCode", "-1");
				resMap.put("resultMsg", "角色信息保存失败");
			}
		}catch (Exception e) {
			_logger.error("【系统管理-角色管理模块-角色新增/保存】保存角色信息异常，错误信息：" + e.getMessage());
			sysOperLog = new SysOperLog(sysUser.getUsid(),"用户编辑/新增","编辑/新增用户",sysUser.getUsname() + "执行了角色编辑/新增操作，保存失败，错误信息：【"+e.getMessage()+"】","错误",sysUser.getUserAgent());
			resMap.put("resultCode", "-1");
			resMap.put("resultMsg", "系统异常，请稍后再试");
			return resMap;
		}finally{
			sysLogService.InsertSysLog(sysOperLog , sqlSession);
			sqlSession.close();
		}
		return resMap;
	}
	/**
	 * 用户权限分配首页数据查询
	 * @param usid
	 * @param request
	 * @return
	 */
	public String userPrivilegeIndexService(int usid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute("sysUser");
		SqlSession sqlSession = null;
		List<SysRole> allRoles = null;
		List<SysRole> curRoles = null;
		try{
			sqlSession = sessionFactory.openSession();
			//获取当前可赋予的角色列表 ： 当前用户拥有的角色 + 当前用户创建的角色 
			allRoles = sqlSession.selectList("com.malone.sys.sysmanage.selectAllRolesByUsidNotInvalid", sysUser.getUsid());
			//获取当前选中的用户所拥有的角色列表
			curRoles = sqlSession.selectList("com.malone.sys.login.selectRolesByUsid", usid);
			//获取当前登录用户所有的数据权限列表(暂时仅限区域数据类型)
			List<SysDatacs> allDatacs = sqlSession.selectList("com.malone.sys.sysmanage.selectAllDatacsByDtcsids", sysUser.getDatalist());
			//获取当前选中的用户所拥有的数据权限列表
			List<SysDatacs> curDatacs = sqlSession.selectList("com.malone.sys.login.selectDatasByUsid", usid);
			request.setAttribute("allDatacs", sysTool.getDataJson(allDatacs , curDatacs));
			//request.setAttribute("datalist", sysTool.getDataJson(allDatacs , curDatacs));
		}catch (Exception e) {
			_logger.error("【系统管理-用户管理模块-用户权限分配】获取用户各种权限列表异常，错误信息：" + e.getMessage());
		}finally{
			sqlSession.close();
		}
		request.setAttribute("allRoles", allRoles);
		request.setAttribute("curRoles", curRoles);
		request.setAttribute("curusid", usid); //将当前需要赋权限的用户ID放入request
		return "WEB-INF/pages/sys/sysmanage/userPrivilege";
	}
	/**
	 * 根据登录用户ID获取登录用户角色列表
	 * @param request
	 * @return
	 */
	public List<SysRole> selectRolesByUsid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute("sysUser");
		SqlSession sqlSession = null;
		List<SysRole> allRoles = null;
		try{
			sqlSession = sessionFactory.openSession();
			//获取当前有权限的角色列表 ： 当前用户拥有的角色 + 当前用户创建的角色  
			allRoles = sqlSession.selectList("com.malone.sys.sysmanage.selectAllRolesByUsid", sysUser.getUsid());
		}catch (Exception e) {
			_logger.error("【系统管理-角色管理模块-角色列表】获取用户拥有权限的角色列表列表异常，错误信息：" + e.getMessage());
		}finally{
			sqlSession.close();
		}
		return allRoles;
	}
	/**
	 * 角色权限分配首页数据查询
	 * @param usid
	 * @param request
	 * @return
	 */
	public String rolePrivilegeIndexService(int roid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute("sysUser");
		SqlSession sqlSession = null;
		List<SysMenu> allMenus = null;
		List<SysMenu> curMenus = null;
		
		Map<String,Object> parMap = new HashMap<String, Object>();
		parMap.put("usid", sysUser.getUsid()+"");
		parMap.put("dpid", sysUser.getSysdept().getDpid()+"");
		parMap.put("rolelist", sysUser.getRolelist());
		try{
			sqlSession = sessionFactory.openSession();
			//获取当前可赋予的菜单列表 ： 当前用户拥有的菜单
			allMenus = sqlSession.selectList("com.malone.sys.sysmanage.selectMenusByUserRoleDept", parMap);
			//获取当前选中的角色所拥有的菜单列表
			curMenus = sqlSession.selectList("com.malone.sys.sysmanage.selectMenusByRoid", roid);
			request.setAttribute("roleMenus", sysTool.getMenuJson(allMenus , curMenus));
		}catch (Exception e) {
			_logger.error("【系统管理-角色管理模块-角色权限分配】获取角色各种权限列表异常，错误信息：" + e.getMessage());
		}finally{
			sqlSession.close();
		}
		request.setAttribute("curroid", roid); //将当前需要赋权限的角色ID放入request
		return "WEB-INF/pages/sys/sysmanage/rolePrivilege";
	}
	/**
	 * 角色权限分配
	 * @param parlist
	 * @param request
	 * @return
	 */
	public Map<String, String> saveRolePrivilegeService(
			ArrayList<Map<String, String>> parlist, int roid , HttpServletRequest request) {
		Map<String, String> resMap = new HashMap<String, String>();
		HttpSession session = request.getSession();
		SysOperLog sysOperLog = null;
		SysUser sysUser = (SysUser) session.getAttribute("sysUser");
		SqlSession sqlSession = null;
		try{
			sqlSession = sessionFactory.openSession();
			for(Map<String, String> map : parlist){
				map.put("roid", roid+"");
				String ck = map.get("checked");
				boolean checked = Boolean.parseBoolean(ck);
				if(checked){
					//选中状态 说明是新增权限
					sqlSession.insert("com.malone.sys.sysmanage.insertRolePrivilege", map);
				}else{
					//去掉选中状态 说明是删除权限
					sqlSession.delete("com.malone.sys.sysmanage.deleteRolePrivilege", map);
				}
			}
			sqlSession.commit(true);
			sysOperLog = new SysOperLog(sysUser.getUsid(),"角色权限分配","分配角色权限",sysUser.getUsname() + "执行了角色权限分配操作，保存成功，保存参数：" + JSON.toJSONString(parlist) + ",roid：" + roid,"信息",sysUser.getUserAgent());
			resMap.put("resultCode", "0");
			resMap.put("resultMsg", "角色权限分配保存成功");
		}catch (Exception e) {
			sysOperLog = new SysOperLog(sysUser.getUsid(),"角色权限分配","分配角色权限",sysUser.getUsname() + "执行了角色权限分配操作，保存失败，错误信息：【"+e.getMessage()+"】","错误",sysUser.getUserAgent());
			_logger.error("【系统管理-角色管理模块-角色权限分配保存角色权限分配异常，错误信息：" + e.getMessage());
			sqlSession.commit(false);
			resMap.put("resultCode", "-1");
			resMap.put("resultMsg", "角色权限分配失败");
		}finally{
			sysLogService.InsertSysLog(sysOperLog , sqlSession);
			sqlSession.close();
		}	
		return resMap;
	}
	/**
	 * 用户权限分配
	 * @param map
	 * @param request
	 * @return
	 */
	public Map<String, String> saveUserPrivilegeService(
			Map<String, String> map, HttpServletRequest request) {
		Map<String, String> resMap = new HashMap<String, String>();
		HttpSession session = request.getSession();
		SysOperLog sysOperLog = null;
		SysUser sysUser = (SysUser) session.getAttribute("sysUser");
		SqlSession sqlSession = null;
		try{
			sqlSession = sessionFactory.openSession();
			String roles = map.get("roles"); //角色
			String datas = map.get("datas"); //数据权限
			if(!StringUtils.isEmpty(roles)){
				//先删除用户原有的角色映射
				sqlSession.delete("com.malone.sys.sysmanage.deleteUserRolesByUsid", map);
				//插入新的用户角色映射
				String[] roids = roles.split(",");
				for(String roid : roids){
					map.put("roid", roid);
					sqlSession.insert("com.malone.sys.sysmanage.insertUserRolesByUsid", map);
				}
			}else{
				//删除用户原有的角色映射
				sqlSession.delete("com.malone.sys.sysmanage.deleteUserRolesByUsid", map);
			}
			if(!StringUtils.isEmpty(datas)){
				//先删除用户原有的数据权限
				sqlSession.delete("com.malone.sys.sysmanage.deleteUserDatacsByUsid", map);
				//插入新的用户角色映射
				String[] dtids = datas.split(",");
				for(String dtid : dtids){
					map.put("dtid", dtid);
					sqlSession.insert("com.malone.sys.sysmanage.insertUserDatacsByUsid", map);
				}
			}else{
				//删除用户原有的数据权限
				sqlSession.delete("com.malone.sys.sysmanage.deleteUserDatacsByUsid", map);
			}
			sqlSession.commit(true);
			sysOperLog = new SysOperLog(sysUser.getUsid(),"用户权限分配","分配用户权限",sysUser.getUsname() + "执行了用户权限分配操作，保存成功，保存参数：" + JSON.toJSONString(map),"信息",sysUser.getUserAgent());
			resMap.put("resultCode", "0");
			resMap.put("resultMsg", "用户权限分配保存成功");
		}catch (Exception e) {
			sysOperLog = new SysOperLog(sysUser.getUsid(),"用户权限分配","分配用户权限",sysUser.getUsname() + "执行了用户权限分配操作，保存失败，错误信息：【"+e.getMessage()+"】","错误",sysUser.getUserAgent());
			_logger.error("【系统管理-用户管理模块-用户权限分配保存用户权限分配异常，错误信息：" + e.getMessage());
			sqlSession.commit(false);
			resMap.put("resultCode", "-1");
			resMap.put("resultMsg", "角色权限分配失败");
		}finally{
			sysLogService.InsertSysLog(sysOperLog , sqlSession);
			sqlSession.close();
		}	
		return resMap;
	}
	/**
	 * 菜单管理 - 获取系统全部的菜单
	 * @param request
	 * @return
	 */
	public String selectAllMenus(HttpServletRequest request) {
		String menuJson = "";
		SqlSession sqlSession = null;
		try{
			sqlSession = sessionFactory.openSession();
			List<SysMenu> memulist = sqlSession.selectList("com.malone.sys.sysmanage.selectAllMenus");
			menuJson = sysTool.getMenuJson(memulist);
		}catch (Exception e) {
			_logger.error("【系统管理-菜单管理模块-菜单列表】查询系统全部菜单列表异常，错误信息：" + e.getMessage());
		}finally{
			sqlSession.close();
		}	
		return menuJson;
	}
	/**
	 * 保存菜单信息
	 * @param menu
	 * @param request
	 * @return
	 */
	public Map<String, String> saveMenuService(SysMenu menu,
			HttpServletRequest request) {
		Map<String, String> resMap = new HashMap<String, String>();
		SysOperLog sysOperLog = null;
		SqlSession sqlSession = null;
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		menu.setCreater(sysUser.getUsid());
		try{
			sqlSession = sessionFactory.openSession();
			int i = 0;
			if(menu.getMuid() == 0){
				i = sqlSession.insert("com.malone.sys.sysmanage.insertMenu", menu);
			}else{
				i = sqlSession.update("com.malone.sys.sysmanage.updateMenuByMuid", menu);
			}
			if(i>0){
				sysOperLog = new SysOperLog(sysUser.getUsid(),"菜单编辑/新增","编辑/新增菜单",sysUser.getUsname() + "执行了菜单编辑/新增操作，保存成功，保存参数：" + JSON.toJSONString(menu),"信息",sysUser.getUserAgent());
				resMap.put("resultCode", "0");
				resMap.put("resultMsg", "菜单信息保存成功");
			}else{
				sysOperLog = new SysOperLog(sysUser.getUsid(),"菜单编辑/新增","编辑/新增菜单",sysUser.getUsname() + "执行了菜单编辑/新增操作，保存失败，错误信息：【数据返回更新记录条数为0】，保存参数：" + JSON.toJSONString(menu),"错误",sysUser.getUserAgent());
				resMap.put("resultCode", "-1");
				resMap.put("resultMsg", "菜单信息保存失败");
			}
		}catch (Exception e) {
			_logger.error("【系统管理-菜单管理模块-菜单新增/保存】保存菜单信息异常，错误信息：" + e.getMessage());
			sysOperLog = new SysOperLog(sysUser.getUsid(),"菜单编辑/新增","编辑/新增菜单",sysUser.getUsname() + "执行了菜单编辑/新增操作，保存失败，错误信息：【"+e.getMessage()+"】","错误",sysUser.getUserAgent());
			resMap.put("resultCode", "-1");
			resMap.put("resultMsg", "系统异常，请稍后再试");
			return resMap;
		}finally{
			sysLogService.InsertSysLog(sysOperLog , sqlSession);
			sqlSession.close();
		}
		return resMap;
	}
	/**
	 * 根据菜单ID查询菜单详情信息
	 * @param muid
	 * @return
	 */
	public SysMenu selectMenuInfoByMuid(String muid) {
		SqlSession sqlSession = null;
		try{
			sqlSession = sessionFactory.openSession();
			SysMenu sysMenu = sqlSession.selectOne("com.malone.sys.sysmanage.selectMenuInfoByMuid",muid);
			return sysMenu;
		}catch (Exception e) {
			_logger.error("【系统管理-菜单管理模块-菜单新增/编辑】查询菜单信息异常，错误信息：" + e.getMessage());
		}finally{
			sqlSession.close();
		}	
		return null;
	}
	/**
	 * @创建日期:2015年8月12日
	 * @方法备注：	查询日志列表
	 * @param param
	 * @param request
	 * @return
	 */
	public Map<String,Object> selectLogList(DataTableParameter param,HttpServletRequest request) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		SqlSession sqlSession = null;
		Map<String, Object> parMap = new HashMap<String, Object>();
		parMap = sysTool.buildDataTableParam(param, request);
		parMap.put("startTime", request.getParameter("startTime"));
		parMap.put("endTime", request.getParameter("endTime"));
		String opertype = request.getParameter("opertype");
		if(opertype == null || "".equals(opertype)){
			parMap.put("opertype", null);
		}else{
			parMap.put("opertype", opertype.split(","));
		}
		try{
			sqlSession = sessionFactory.openSession();
			List<SysOperLog> loglist =  sqlSession.selectList("com.malone.sys.sysmanage.selectLogList", parMap);
			int counts = sqlSession.selectOne("com.malone.sys.sysmanage.selectLogCounts",parMap);
			resMap.put("loglist", loglist);
			resMap.put("totalRecord", counts);
			return resMap;
		}catch(Exception e) {
			_logger.error("【日志管理-日志查询模块】查询日志列表异常，错误信息：" + e.getMessage());
		}finally{
			sqlSession.close();
		}	
		return null;
	}
	
}
