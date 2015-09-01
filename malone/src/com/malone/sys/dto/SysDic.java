/**
 * 
 */
package com.malone.sys.dto;

import java.io.Serializable;

/**
 * @author pengl
 * 系统字典表
 * 2015-5-15上午10:36:55
 */
public class SysDic  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1521799869351301898L;
	
	private int did;
	private String dname;
	private String dtype;
	private String dval;
	private int dstate;
	private String ddesc;
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public String getDval() {
		return dval;
	}
	public void setDval(String dval) {
		this.dval = dval;
	}
	public int getDstate() {
		return dstate;
	}
	public void setDstate(int dstate) {
		this.dstate = dstate;
	}
	public String getDdesc() {
		return ddesc;
	}
	public void setDdesc(String ddesc) {
		this.ddesc = ddesc;
	}
	
}
