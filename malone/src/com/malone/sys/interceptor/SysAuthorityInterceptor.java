/**
 * 
 */
package com.malone.sys.interceptor;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.druid.util.StringUtils;

/**
 * @author pengl
 * 2014-6-22下午6:09:33
 * 访问权限拦截
 */
public class SysAuthorityInterceptor  extends HandlerInterceptorAdapter {
	
	private Logger _logger = LogManager.getLogger();
	
	private List<String> excludeUrlPatterns;

	private PathMatcher pathMatcher = new AntPathMatcher();

	public void setExcludeUrlPatterns(List<String> excludeUrlPatterns) {
		this.excludeUrlPatterns = excludeUrlPatterns;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();
		String contextPath=request.getContextPath();
		_logger.warn("进入拦截器 ..." + "请求地址：" + url);
		for (String urlPattern : excludeUrlPatterns) {
			if (pathMatcher.match(urlPattern, url)) {
				_logger.warn("取消拦截。。。");
				return true;
			}
		}
		Object sysUser = request.getSession().getAttribute("sysUser");
		if(sysUser==null){
			String query = request.getQueryString();
			if(!StringUtils.isEmpty(query)){
				url = url + "?" + query;
			}
			response.sendRedirect(contextPath+"/sys/login.jsp?redirectURL="
                    + URLEncoder.encode(url));
			return false;
		}
		return true;
	}
}
