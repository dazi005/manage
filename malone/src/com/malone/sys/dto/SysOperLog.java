/**
 * 
 */
package com.malone.sys.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author pengl
 * 系统操作日志
 * 2015-5-14上午10:55:16
 */
public class SysOperLog  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4055127571861678601L;
	private long logid;
	private int usid;
	private String modular;
	private String opertype;
	private String opercontext;
	private Date operdate;
	private String loglevel;
	private String ipaddress;
	private String ostype;
	private String browser;
	public SysOperLog(){}
	
	public SysOperLog(int usid,String modular,String opertype,String opercontext,String loglevel,UserAgent userAgent){
		this.setUsid(usid);
		this.setModular(modular);
		this.setOpertype(opertype);
		this.setOpercontext(opercontext);
		this.setLoglevel(loglevel);
		this.setBrowser(userAgent.getBrowserType() + " " +  userAgent.getBrowserType());
		this.setOstype(userAgent.getPlatformType() + " " + userAgent.getPlatformSeries());
		this.setIpaddress(userAgent.getIp());
	}
	public long getLogid() {
		return logid;
	}
	public void setLogid(long logid) {
		this.logid = logid;
	}
	public int getUsid() {
		return usid;
	}
	public void setUsid(int usid) {
		this.usid = usid;
	}
	public String getOpertype() {
		return opertype;
	}
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}
	public String getOpercontext() {
		return opercontext;
	}
	public void setOpercontext(String opercontext) {
		this.opercontext = opercontext;
	}
	public Date getOperdate() {
		return operdate;
	}
	public void setOperdate(Date operdate) {
		this.operdate = operdate;
	}
	public String getLoglevel() {
		return loglevel;
	}
	public void setLoglevel(String loglevel) {
		this.loglevel = loglevel;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getOstype() {
		return ostype;
	}
	public void setOstype(String ostype) {
		this.ostype = ostype;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getModular() {
		return modular;
	}

	public void setModular(String modular) {
		this.modular = modular;
	}

}
