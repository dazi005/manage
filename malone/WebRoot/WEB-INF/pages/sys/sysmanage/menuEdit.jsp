<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <body>
	
	<form class="form-horizontal" id="roleEdit">
		<input type="hidden" value="<c:if test="${sysMenu.muid==null}">${upmuid}</c:if><c:if test="${sysMenu.muid!=null}">${sysMenu.upmuid}</c:if>" id="upmuid" />
		<input type="hidden" value="<c:if test="${sysMenu.muid==null}">${upmulevel}</c:if><c:if test="${sysMenu.muid!=null}">${sysMenu.mulevel}</c:if>" id="upmulevel" />
		<input type="hidden" id="muid" value="<c:if test="${sysMenu.muid==null}">0</c:if><c:if test="${sysMenu.muid!=null}">${sysMenu.muid}</c:if>" />
		<c:if test="${sysMenu.muid==null}">
			<div class="control-group">
				<label class="control-label" for="typeahead">上级菜单</label>
				<div class="controls"><input class="input-xlarge focused" type="text" value="${upmuname}" readonly="readonly"/></div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label" for="typeahead">菜单名称</label>
			<div class="controls">
				<input class="input-xlarge focused" type="text"
					id="muname" value="${sysMenu.muname}" onblur="Malone.verify.notNull(this);">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="typeahead">菜单链接</label>
			<div class="controls">
				<input class="input-xlarge focused" type="text"
					id="mulink" value="${sysMenu.mulink}" onblur="Malone.verify.notNull(this);">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="date01">菜单类型</label>
			<div class="controls">
				<select id="mutype" data-rel="chosen" data-placeholder="请选择菜单类型">
					<option value=""></option>
					<option value="Application" <c:if test="${sysMenu.mutype == 'Application'}">selected="true"</c:if> >子模块</option>
					<option value="Menu" <c:if test="${sysMenu.mutype == 'Menu'}">selected="true"</c:if> >菜单</option>
					<option value="Button" <c:if test="${sysMenu.mutype == 'Button'}">selected="true"</c:if> >按钮</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="typeahead">菜单图标</label>
			<div class="controls">
				<input class="input-xlarge focused" type="text" id="muicon" value="${sysMenu.muicon}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="typeahead">排序值</label>
			<div class="controls">
				<input class="input-xlarge focused span1" type="text" id="muorder" title="请输入数字" data-rel="tooltip" value="${sysMenu.muorder}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="selectError">菜单状态</label>
			<div class="controls">
				<select id="mustate" data-rel="chosen" data-placeholder="请选择菜单状态">
					<option value=""></option>
					<option value="1" <c:if test="${sysMenu.mustate == '1'}">selected="true"</c:if> >启用</option>
					<option value="-1" <c:if test="${sysMenu.mustate == '-1'}">selected="true"</c:if> >停用</option>
				</select>
			</div>
		</div>
		<div class="form-actions">
			<button type="button" class="btn btn-primary" id="saveButton" onclick="_MenuEdit.saveMenu();">保存</button>
		</div>
</form>
</body>
</html>