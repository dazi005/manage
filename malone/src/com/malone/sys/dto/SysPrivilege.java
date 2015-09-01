/**
 * 
 */
package com.malone.sys.dto;

import java.io.Serializable;

/**
 * @author pengl
 * 系统权限表
 * 2015-5-12下午5:32:29
 */
public class SysPrivilege implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3014769312975716573L;
	
	private int pvid;
	private String pvmaster;
	private String pvmastervalue;
	//private String pvaccess;   //默认 Menu  移除该字段  权限主体统称菜单（菜单、模块、按钮）
	private String pvaccessvalue;
	private String pvoperation;  //默认 Available
	
	public int getPvid() {
		return pvid;
	}
	public void setPvid(int pvid) {
		this.pvid = pvid;
	}
	public String getPvmaster() {
		return pvmaster;
	}
	public void setPvmaster(String pvmaster) {
		this.pvmaster = pvmaster;
	}
	public String getPvmastervalue() {
		return pvmastervalue;
	}
	public void setPvmastervalue(String pvmastervalue) {
		this.pvmastervalue = pvmastervalue;
	}
	public String getPvaccessvalue() {
		return pvaccessvalue;
	}
	public void setPvaccessvalue(String pvaccessvalue) {
		this.pvaccessvalue = pvaccessvalue;
	}
	public String getPvoperation() {
		return pvoperation;
	}
	public void setPvoperation(String pvoperation) {
		this.pvoperation = pvoperation;
	}

}
