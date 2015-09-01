/**
 * 
 */
package com.malone.sys.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author pengl
 * 部门表
 * 2015-5-12下午5:04:10
 */
public class SysDept implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3220152156609212655L;
	
	private int dpid;
	private int updpid;
	private String dpname;
	private int dpstate;
	private int dplevel;
	private String dpdesc;
	private boolean checked = false;
	private boolean open = false;
	private List<SysDept> childrenDepts;
	
	public int getDpid() {
		return dpid;
	}
	public void setDpid(int dpid) {
		this.dpid = dpid;
	}
	public int getUpdpid() {
		return updpid;
	}
	public void setUpdpid(int updpid) {
		this.updpid = updpid;
	}
	public String getDpname() {
		return dpname;
	}
	public void setDpname(String dpname) {
		this.dpname = dpname;
	}
	public int getDpstate() {
		return dpstate;
	}
	public void setDpstate(int dpstate) {
		this.dpstate = dpstate;
	}
	public int getDplevel() {
		return dplevel;
	}
	public void setDplevel(int dplevel) {
		this.dplevel = dplevel;
	}
	public String getDpdesc() {
		return dpdesc;
	}
	public void setDpdesc(String dpdesc) {
		this.dpdesc = dpdesc;
	}
	public List<SysDept> getChildrenDepts() {
		return childrenDepts;
	}
	public void setChildrenDepts(List<SysDept> childrenDepts) {
		this.childrenDepts = childrenDepts;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
}
