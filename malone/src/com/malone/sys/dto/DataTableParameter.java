package com.malone.sys.dto;

/**
 * @description :
 * @author : pengl
 * @date : 2015-8-12
 *  jquery.datatable插件内置参数
 */
public class DataTableParameter {
	//过滤文本
	private String sSearch;
	//DataTable请求服务器端次数
	private String sEcho;
	//每页显示的数量
	private int iDisplayLength;
	//分页时每页跨度数量
	private int iDisplayStart;
	//列数
	private int iColumns;
	//排序列的数量
	private String iSortingCols;
	//逗号分割所有的列
	private String sColumns;
	
	//排序字段的下标
	private String iSortCol_0;
	//排序字段规则
	private String sSortDir_0;
	
	private String sortColumnName;
	
	
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getiColumns() {
		return iColumns;
	}
	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}
	public String getiSortingCols() {
		return iSortingCols;
	}
	public void setiSortingCols(String iSortingCols) {
		this.iSortingCols = iSortingCols;
	}
	public String getsColumns() {
		return sColumns;
	}
	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}
	public String getiSortCol_0() {
		return iSortCol_0;
	}
	public void setiSortCol_0(String iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}
	public String getsSortDir_0() {
		return sSortDir_0;
	}
	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}
	public String getSortColumnName() {
		return sortColumnName;
	}
	public void setSortColumnName(String sortColumnName) {
		this.sortColumnName = sortColumnName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DataTableParameter [sSearch=" + sSearch + ", sEcho=" + sEcho
				+ ", iDisplayLength=" + iDisplayLength + ", iDisplayStart="
				+ iDisplayStart + ", iColumns=" + iColumns + ", iSortingCols="
				+ iSortingCols + ", sColumns=" + sColumns + ", iSortCol_0="
				+ iSortCol_0 + ", sSortDir_0=" + sSortDir_0
				+ ", sortColumnName=" + sortColumnName + "]";
	}
}
