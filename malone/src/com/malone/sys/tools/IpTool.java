/**
 * 
 */
package com.malone.sys.tools;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.malone.sys.dto.UserAgent;

/**
 * @author Administrator
 *
 */
@Service("ipTool")
public class IpTool {
	private Logger _logger = LogManager.getLogger();
	/**获取客户端ip地址
	 * @Description: 
	 * @param @param request
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	public  String getIpAddr(HttpServletRequest request) {
	     String ipAddress = null;
	     ipAddress = request.getHeader("x-forwarded-for");
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	      ipAddress = request.getHeader("Proxy-Client-IP");
	     }
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	         ipAddress = request.getHeader("WL-Proxy-Client-IP");
	     }
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
	      ipAddress = request.getRemoteAddr();
	      if(ipAddress.equals("127.0.0.1")||ipAddress.equals("0:0:0:0:0:0:0:1")){
		       //根据网卡取本机配置的IP
		       InetAddress inet=null;
			    try {
			     inet = InetAddress.getLocalHost();
			    } catch (UnknownHostException e) {
			     e.printStackTrace();
			    }
			    ipAddress= inet.getHostAddress();
	      }
	        
	     }
	     //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
	     if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
	         if(ipAddress.indexOf(",")>0){
	             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
	         }
	     }
	     return ipAddress;
	}
	
	public static void main(String[] args) {
		String ip = "134.64.37.42";
		UserAgent userAgent = new UserAgent();
		userAgent.setIp(ip);
	}
}
