/**
 * 
 */
package com.malone.sys.dto;

import java.io.Serializable;

/**
 * @author pengl
 * 角色表
 * 2015-5-12下午5:06:27
 */
public class SysRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3181926907573132900L;
	
	private int roid;
	private String roname;
	private int rotype;
	private int rostate;
	private String rodesc;
	private int creater;
	
	public int getRoid() {
		return roid;
	}
	public void setRoid(int roid) {
		this.roid = roid;
	}
	public String getRoname() {
		return roname;
	}
	public void setRoname(String roname) {
		this.roname = roname;
	}
	public int getRotype() {
		return rotype;
	}
	public void setRotype(int rotype) {
		this.rotype = rotype;
	}
	public int getRostate() {
		return rostate;
	}
	public void setRostate(int rostate) {
		this.rostate = rostate;
	}
	public String getRodesc() {
		return rodesc;
	}
	public void setRodesc(String rodesc) {
		this.rodesc = rodesc;
	}
	public int getCreater() {
		return creater;
	}
	public void setCreater(int creater) {
		this.creater = creater;
	}
	
}
