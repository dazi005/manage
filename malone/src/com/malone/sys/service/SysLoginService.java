/**
 * 
 */
package com.malone.sys.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Constants;
import com.malone.sys.dto.SysDatacs;
import com.malone.sys.dto.SysDept;
import com.malone.sys.dto.SysMenu;
import com.malone.sys.dto.SysOperLog;
import com.malone.sys.dto.SysRole;
import com.malone.sys.dto.SysUser;
import com.malone.sys.dto.UserAgent;
import com.malone.sys.tools.IpTool;
import com.malone.sys.tools.SysTool;
import com.malone.sys.tools.UserAgentUtil;
import com.malone.util.CookieUtil;

/**
 * @author pengl
 * 2015-5-13下午1:57:40
 */
@Service("sysLoginService")
public class SysLoginService{
	private Logger _logger = LogManager.getLogger();
	@Resource
	private SqlSessionFactory sessionFactory;
	@Resource(name="cookieUtil")
	private CookieUtil cookieUtil;
	@Resource(name="sysLogService")
	private SysLogService sysLogService;
	@Resource(name="sysTool")
    private SysTool sysTool;
	@Resource(name="ipTool")
    private IpTool ipTool;
	/**
	 * 系统用户登录
	 * @param parMap
	 * @param request
	 * @param response
	 * @return
	 */
	public Map<String, String> loginService(Map<String, Object> parMap,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> resMap = new HashMap<String, String>();
		SysOperLog sysOperLog = null;
		String randcode = parMap.get("randcode")+"";
		HttpSession session = request.getSession();
		//判断图形验证码是否正确
		String seImageCode = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(randcode==null || "".equals(randcode)){
			resMap.put("resultCode", "-1");
			resMap.put("resultMsg", "验证码不能为空");
			return resMap;
		}else if(!randcode.equalsIgnoreCase(seImageCode)){
			resMap.put("resultCode", "-1");
			resMap.put("resultMsg", "验证码输入错误");
			return resMap;
			
		}
		//验证通过 移除验证码
		session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		SqlSession sqlSession = null;
		UserAgent userAgent = UserAgentUtil.getUserAgent(request.getHeader("User-Agent"));
		userAgent.setIp(ipTool.getIpAddr(request));
		String usacct = parMap.get("usacct")+""; //登录帐号  用作日志记录参数
		try{
			sqlSession = sessionFactory.openSession();
			//验证用户名密码
			SysUser sysUser = sqlSession.selectOne("com.malone.sys.login.selectUserAndDeptByUsacctPass", parMap);
			if(sysUser == null){
				resMap.put("resultCode", "-1");
				resMap.put("resultMsg", "用户名或密码错误");
				sysOperLog = new SysOperLog(-1,"用户登录","登录",usacct + "执行了登录操作，登录失败，错误信息：【用户名或密码错误】","错误",userAgent);
				return resMap;
			}
			//查询角色列表
			List<SysRole> rolelist = sqlSession.selectList("com.malone.sys.login.selectRolesByUsid", sysUser.getUsid());
			if(rolelist==null || rolelist.size()==0){
				resMap.put("resultCode", "-1");
				resMap.put("resultMsg", "该账号未分配角色，请联系系统管理员");
				sysOperLog = new SysOperLog(-1,"用户登录","登录",usacct + "执行了登录操作，登录失败，错误信息：【账号未分配角色】","错误",userAgent);
				return resMap;
			}
			sysUser.setRolelist(rolelist);
			sysUser.setUserAgent(userAgent);
			//查询功能权限（模块、菜单、按钮）
			parMap.put("usid", sysUser.getUsid()+"");
			parMap.put("dpid", sysUser.getSysdept().getDpid()+"");
			parMap.put("rolelist", rolelist);
			List<SysMenu> menulist = sqlSession.selectList("com.malone.sys.login.selectMenusByUserRoleDept",parMap);
			sysUser.setMenulist(menulist);
			//查询数据权限列表
			List<SysDatacs> datalist = sqlSession.selectList("com.malone.sys.login.selectDatasByUsid", sysUser.getUsid());
			sysUser.setDatalist(datalist);
			//查询部门树
			List<SysDept> deptlist = sqlSession.selectList("com.malone.sys.login.selectDeptsByDepid",sysUser.getSysdept().getDpid());
			//保存用户名和密码在cookie中
			String remember = parMap.get("remember")+"";
			if(remember != null && "on".equals(remember)){
				cookieUtil.setCookie(request, response, "malone_user", parMap.get("usacct")+"",7*24*60*60);
				cookieUtil.setCookie(request, response, "malone_pass", parMap.get("uspass")+"",7*24*60*60);
				cookieUtil.setCookie(request, response, "malone_rememberme", "on" ,7*24*60*60);
			}else{
				cookieUtil.removeCookie(response, "malone_user");
				cookieUtil.removeCookie(response, "malone_pass");
				cookieUtil.removeCookie(response, "malone_rememberme");
			}
			//将登录用户信息放入session
			session.setAttribute("sysUser", sysUser);
			//用户名放入session ，用于Durid监控页面显示
			session.setAttribute("session_user_key", sysUser.getUsname());
			//将树形菜单放入session
			session.setAttribute("menuJson", sysTool.getMenuJson(menulist));
			//将树形部门放入session
			session.setAttribute("deptJson", sysTool.getDeptJson(deptlist,sysUser.getSysdept().getDpid()));
			resMap.put("resultCode", "0");
			sysOperLog = new SysOperLog(sysUser.getUsid(),"用户登录","登录",sysUser.getUsname() + "执行了登录操作，登录成功","信息",userAgent);
		}catch (Exception e) {
			_logger.error("【系统登录模块】登录异常，错误信息：" + e.getMessage());
			sysOperLog = new SysOperLog(-1,"用户登录","登录",usacct + "执行了登录操作，登录失败，错误信息：【"+e.getMessage()+"】","错误",userAgent);
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
	 * 修改密码
	 * @param request
	 * @return
	 */
	public Map<String, String> modifyPassService(Map<String, String> map , HttpServletRequest request) {
		Map<String, String> resMap = new HashMap<String, String>();
		SysOperLog sysOperLog = null;
		SqlSession sqlSession = null;
		SysUser sysUser = (SysUser) request.getSession().getAttribute("sysUser");
		try {
			sqlSession = sessionFactory.openSession();
			//先验证原始密码是否正确
			if(!sysUser.getUspass().equals(map.get("oldPass"))){
				resMap.put("resultCode", "-1");
				resMap.put("resultMsg", "原始密码输入错误");
				sysOperLog = new SysOperLog(sysUser.getUsid(),"密码修改","修改密码",sysUser.getUsname() + "执行了密码修改操作，修改失败，错误信息：【原始密码输入错误】","错误",sysUser.getUserAgent());
				return resMap;
			}
			//更新密码
			sysUser.setUspass(map.get("newPass"));
			int i = sqlSession.update("com.malone.sys.login.updateUserPassByUsid", sysUser);
			if(i>0){
				sysOperLog = new SysOperLog(sysUser.getUsid(),"密码修改","修改密码",sysUser.getUsname() + "执行了密码修改操作，修改成功","信息",sysUser.getUserAgent());
				resMap.put("resultCode", "0");
				resMap.put("resultMsg", "密码修改成功");
			}else{
				sysOperLog = new SysOperLog(sysUser.getUsid(),"密码修改","修改密码",sysUser.getUsname() + "执行了密码修改操作，修改失败，错误信息：【数据返回更新记录条数为0】","错误",sysUser.getUserAgent());
				resMap.put("resultCode", "-1");
				resMap.put("resultMsg", "密码修改失败");
			}
		} catch (Exception e) {
			_logger.error("【系统登录模块】修改密码异常，错误信息：" + e.getMessage());
			sysOperLog = new SysOperLog(-1,"密码修改","修改密码",sysUser.getUsname() + "执行了密码修改操作，修改失败，错误信息：【"+e.getMessage()+"】","错误",sysUser.getUserAgent());
			resMap.put("resultCode", "-1");
			resMap.put("resultMsg", "系统异常，请稍后再试");
			return resMap;
		}finally{
			sysLogService.InsertSysLog(sysOperLog , sqlSession);
			sqlSession.close();
		}
		return resMap;
	}
}
