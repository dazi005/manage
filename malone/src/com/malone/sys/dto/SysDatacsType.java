/**
 * 
 */
package com.malone.sys.dto;

import java.io.Serializable;

/**
 * @author pengl
 * 数据权限类型表
 * 2015-5-29上午9:42:17
 */
public class SysDatacsType implements Serializable{

	private static final long serialVersionUID = -2853913234290854105L;
	
	private int dtcstype;
	private String dtcstypename;
	
	public int getDtcstype() {
		return dtcstype;
	}
	public void setDtcstype(int dtcstype) {
		this.dtcstype = dtcstype;
	}
	public String getDtcstypename() {
		return dtcstypename;
	}
	public void setDtcstypename(String dtcstypename) {
		this.dtcstypename = dtcstypename;
	}
	
	
}
