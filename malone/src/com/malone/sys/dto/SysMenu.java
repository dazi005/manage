/**
 * 
 */
package com.malone.sys.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author pengl
 * 菜单表
 * 2015-5-12下午5:16:18
 */
public class SysMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1631013043642074993L;
	
	private int muid;
	private String muname;
	private int upmuid;
	private String muicon;
	private String mulink;
	private int mulevel;
	private String mutitle;
	private int mustate;
	private int muorder;
	private String mutype;
	private int creater;
	private String pvoperation;
	private boolean checked = false;
	private boolean open = false;
	private String is_leaf; //是否叶节点 Y 是  N 否
	private List<SysMenu> childrenMenus;
	
	public int getMuid() {
		return muid;
	}
	public void setMuid(int muid) {
		this.muid = muid;
	}
	public String getMuname() {
		return muname;
	}
	public void setMuname(String muname) {
		this.muname = muname;
	}
	public int getUpmuid() {
		return upmuid;
	}
	public void setUpmuid(int upmuid) {
		this.upmuid = upmuid;
	}
	public String getMuicon() {
		return muicon;
	}
	public void setMuicon(String muicon) {
		this.muicon = muicon;
	}
	public String getMulink() {
		return mulink;
	}
	public void setMulink(String mulink) {
		this.mulink = mulink;
	}
	public int getMulevel() {
		return mulevel;
	}
	public void setMulevel(int mulevel) {
		this.mulevel = mulevel;
	}
	public String getMutitle() {
		return mutitle;
	}
	public void setMutitle(String mutitle) {
		this.mutitle = mutitle;
	}
	public int getMustate() {
		return mustate;
	}
	public void setMustate(int mustate) {
		this.mustate = mustate;
	}
	public int getMuorder() {
		return muorder;
	}
	public void setMuorder(int muorder) {
		this.muorder = muorder;
	}
	public String getMutype() {
		return mutype;
	}
	public void setMutype(String mutype) {
		this.mutype = mutype;
	}
	public String getPvoperation() {
		return pvoperation;
	}
	public void setPvoperation(String pvoperation) {
		this.pvoperation = pvoperation;
	}
	public String getIs_leaf() {
		return is_leaf;
	}
	public void setIs_leaf(String is_leaf) {
		this.is_leaf = is_leaf;
	}
	public List<SysMenu> getChildrenMenus() {
		return childrenMenus;
	}
	public void setChildrenMenus(List<SysMenu> childrenMenus) {
		this.childrenMenus = childrenMenus;
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
	    if (obj instanceof SysMenu) {   
	    	SysMenu u = (SysMenu) obj;   
	        return this.muid == u.muid;   
	    }   
	    return super.equals(obj);  
    }
	public int getCreater() {
		return creater;
	}
	public void setCreater(int creater) {
		this.creater = creater;
	}
}
