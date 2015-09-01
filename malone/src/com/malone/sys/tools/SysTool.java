/**
 * 
 */
package com.malone.sys.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.malone.sys.dto.DataTableParameter;
import com.malone.sys.dto.SysDatacs;
import com.malone.sys.dto.SysDept;
import com.malone.sys.dto.SysMenu;

/**
 * @author pengl
 * 2015-5-14上午10:17:32
 */
@Service("sysTool")
public class SysTool {
	private Logger _logger = LogManager.getLogger();
	/**
	 * 将菜单列表转换成树形菜单
	 * @param menuList
	 * @return
	 */
	public String getMenuJson(List<SysMenu> menuList) {
		String jsonMenu = "";
		List<SysMenu> nodeList = new ArrayList<SysMenu>();
		for (SysMenu node1 : menuList) {
			boolean mark = false;
			for (SysMenu node2 : menuList) {
				if (node1.getUpmuid() != 0
						&& node1.getUpmuid() == node2.getMuid()) {
					mark = true;
					if (node2.getChildrenMenus() == null)
						node2.setChildrenMenus(new ArrayList<SysMenu>());
					node2.getChildrenMenus().add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}
		// 转为json格式
		jsonMenu = JSONObject.toJSONString(nodeList);
		return jsonMenu;
	}
	
	/**
	 * 将部门列表转换成树形部门
	 * @param menuList
	 * @return
	 */
	public String getDeptJson(List<SysDept> deptList ,int dpid) {
		String jsonMenu = "";
		List<SysDept> nodeList = new ArrayList<SysDept>();
		for (SysDept node1 : deptList) {
			if(node1.getDpid() == dpid){
				node1.setChecked(true);
				node1.setOpen(true);
			}
			boolean mark = false;
			for (SysDept node2 : deptList) {
				if (node1.getUpdpid() != 0
						&& node1.getUpdpid() == node2.getDpid()) {
					mark = true;
					if (node2.getChildrenDepts() == null)
						node2.setChildrenDepts(new ArrayList<SysDept>());
					node2.getChildrenDepts().add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}
		// 转为json格式
		jsonMenu = JSONObject.toJSONString(nodeList);
		return jsonMenu;
	}
	
	/**
	 * 将数据权限列表转换成树形结构
	 * @param menuList
	 * @return
	 */
	public String getDataJson(List<SysDatacs> datacsList , List<SysDatacs> curDatacsList) {
		String jsonDatacs = "";
		List<SysDatacs> nodeList = new ArrayList<SysDatacs>();
		for (SysDatacs node1 : datacsList) {
			//展开根节点
			//if(node1.getDtcsupid() == 0){
				//node1.setOpen(true);
			//}
			if(curDatacsList.contains(node1)){
				node1.setChecked(true);
				//node1.setOpen(true);
			}
			boolean mark = false;
			for (SysDatacs node2 : datacsList) {
				if (node1.getDtcsupid() != 0
						&& node1.getDtcsupid() == node2.getDtcsid()) {
					mark = true;
					if (node2.getChildrenDatacs() == null)
						node2.setChildrenDatacs(new ArrayList<SysDatacs>());
					node2.getChildrenDatacs().add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}
		// 转为json格式
		jsonDatacs = JSONObject.toJSONString(nodeList);
		return jsonDatacs;
	}
	/**
	 * 将数据权限列表转换成树形结构
	 * @param menuList
	 * @return
	 */
	public List<SysDatacs> getDataJson2(List<SysDatacs> datacsList , List<SysDatacs> curDatacsList) {
		List<SysDatacs> nodeList = new ArrayList<SysDatacs>();
		for (SysDatacs node1 : datacsList) {
			if(curDatacsList.contains(node1)){
				node1.setChecked(true);
			}
			boolean mark = false;
			for (SysDatacs node2 : datacsList) {
				if (node1.getDtcsupid() != 0
						&& node1.getDtcsupid() == node2.getDtcsid()) {
					mark = true;
					if (node2.getChildrenDatacs() == null)
						node2.setChildrenDatacs(new ArrayList<SysDatacs>());
					node2.getChildrenDatacs().add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}
		return nodeList;
	}
	/**
	 * 将菜单权限列表转换成树形结构
	 * @param menuList
	 * @return
	 */
	public String getMenuJson(List<SysMenu> menusList , List<SysMenu> curMenusList) {
		String jsonDatacs = "";
		List<SysMenu> nodeList = new ArrayList<SysMenu>();
		for (SysMenu node1 : menusList) {
			//展开根节点
			if(node1.getUpmuid() == 0){
				node1.setOpen(true);
			}
			if(curMenusList.contains(node1)){
				node1.setChecked(true);
				node1.setOpen(true);
			}
			boolean mark = false;
			for (SysMenu node2 : menusList) {
				if (node1.getUpmuid() != 0
						&& node1.getUpmuid() == node2.getMuid()) {
					mark = true;
					if (node2.getChildrenMenus() == null)
						node2.setChildrenMenus(new ArrayList<SysMenu>());
					node2.getChildrenMenus().add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}
		// 转为json格式
		jsonDatacs = JSONObject.toJSONString(nodeList);
		return jsonDatacs;
	}
	
	/**
	 * @创建日期:2015年8月12日
	 * @方法备注：	构建jquery.datatables控件参数
	 * @param param
	 * @param request
	 */
	public Map<String, Object> buildDataTableParam(DataTableParameter param,HttpServletRequest request){
		Map<String, Object> reMap = new HashMap<String, Object>();
		if(param.getiSortCol_0() != null){
			String sortName = request.getParameter("mDataProp_"+param.getiSortCol_0()+"");
			reMap.put("sortColumnName", sortName);
			reMap.put("sortRule", param.getsSortDir_0());
		}
		reMap.put("sSearch", param.getsSearch()); 
		reMap.put("iDisplayLength", param.getiDisplayLength());
		reMap.put("iDisplayStart", param.getiDisplayStart()+1);
		reMap.put("iDisplayEnd", param.getiDisplayStart() + param.getiDisplayLength());
		return reMap;
	}
}
