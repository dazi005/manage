/**
 * 
 */
package com.malone.sys.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author pengl
 * 系统用户表
 * 2015-5-12下午5:00:13
 */
public class SysUser implements Serializable {
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public int usid;
	public String usacct;
	public String usname;
	public String usrealname;
	public String uspass;
	public String usphone;
	public String usemail;
	public int ustate;
	public String uscardno;
	public Date createdate;
	public int loginagain;
	public List<SysRole> rolelist;
	public SysDept sysdept;
	public List<SysMenu> menulist;
	public UserAgent userAgent;
	public List<SysDatacs> datalist;
	
	public int getUsid() {
		return usid;
	}
	public void setUsid(int usid) {
		this.usid = usid;
	}
	public String getUsacct() {
		return usacct;
	}
	public void setUsacct(String usacct) {
		this.usacct = usacct;
	}
	public String getUsname() {
		return usname;
	}
	public void setUsname(String usname) {
		this.usname = usname;
	}
	public String getUsrealname() {
		return usrealname;
	}
	public void setUsrealname(String usrealname) {
		this.usrealname = usrealname;
	}
	public String getUspass() {
		return uspass;
	}
	public void setUspass(String uspass) {
		this.uspass = uspass;
	}
	public String getUsphone() {
		return usphone;
	}
	public void setUsphone(String usphone) {
		this.usphone = usphone;
	}
	public String getUsemail() {
		return usemail;
	}
	public void setUsemail(String usemail) {
		this.usemail = usemail;
	}
	public int getUstate() {
		return ustate;
	}
	public void setUstate(int ustate) {
		this.ustate = ustate;
	}
	public String getUscardno() {
		return uscardno;
	}
	public void setUscardno(String uscardno) {
		this.uscardno = uscardno;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getLoginagain() {
		return loginagain;
	}
	public void setLoginagain(int loginagain) {
		this.loginagain = loginagain;
	}
	public List<SysRole> getRolelist() {
		return rolelist;
	}
	public void setRolelist(List<SysRole> rolelist) {
		this.rolelist = rolelist;
	}
	public SysDept getSysdept() {
		return sysdept;
	}
	public void setSysdept(SysDept sysdept) {
		this.sysdept = sysdept;
	}
	public List<SysMenu> getMenulist() {
		return menulist;
	}
	public void setMenulist(List<SysMenu> menulist) {
		this.menulist = menulist;
	}
	public UserAgent getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(UserAgent userAgent) {
		this.userAgent = userAgent;
	}
	public List<SysDatacs> getDatalist() {
		return datalist;
	}
	public void setDatalist(List<SysDatacs> datalist) {
		this.datalist = datalist;
	}
	
}
