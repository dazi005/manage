/**
 * 
 */
package com.malone.sys.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author pengl
 * 2015-5-28下午3:18:55
 * 数据权限表
 */
public class SysDatacs implements Serializable  {

	private static final long serialVersionUID = -8966497340766739481L;
	
	private int dtcsid;
	private String dtcsvalue;
	private int dtcsupid;
	private int dtcstype;
	private String dtcsmemo;
	private boolean checked = false;
	private boolean open = false;
	private List<SysDatacs> childrenDatacs;
	
	public int getDtcsid() {
		return dtcsid;
	}
	public void setDtcsid(int dtcsid) {
		this.dtcsid = dtcsid;
	}
	public String getDtcsvalue() {
		return dtcsvalue;
	}
	public void setDtcsvalue(String dtcsvalue) {
		this.dtcsvalue = dtcsvalue;
	}
	public int getDtcsupid() {
		return dtcsupid;
	}
	public void setDtcsupid(int dtcsupid) {
		this.dtcsupid = dtcsupid;
	}
	public int getDtcstype() {
		return dtcstype;
	}
	public void setDtcstype(int dtcstype) {
		this.dtcstype = dtcstype;
	}
	public String getDtcsmemo() {
		return dtcsmemo;
	}
	public void setDtcsmemo(String dtcsmemo) {
		this.dtcsmemo = dtcsmemo;
	}
	public List<SysDatacs> getChildrenDatacs() {
		return childrenDatacs;
	}
	public void setChildrenDatacs(List<SysDatacs> childrenDatacs) {
		this.childrenDatacs = childrenDatacs;
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
	/**
	 * 重写equals方法
	 */
    public boolean equals(Object obj) {   
	    if (obj instanceof SysDatacs) {   
	    	SysDatacs u = (SysDatacs) obj;   
	        return this.dtcsid == u.dtcsid;   
	    }   
	    return super.equals(obj);  
    }
}
