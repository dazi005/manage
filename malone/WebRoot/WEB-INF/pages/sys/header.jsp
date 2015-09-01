<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- topbar begins -->
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			<a class="brand"><span style="width:200px;">通用后台管理系统</span></a>
			
			<!-- theme selector starts -->
			<div class="btn-group pull-right theme-container" >
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					<i class="icon-tint"></i><span class="hidden-phone"> 选择主题/皮肤 </span>
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" id="themes">
					<li><a data-value="classic" href="javascript:void(0);"><i class="icon-blank"></i> 经典灰</a></li>
					<li><a data-value="cerulean" href="javascript:void(0);"><i class="icon-blank"></i> 天际蓝</a></li>
					<li><a data-value="simplex" href="javascript:void(0);"><i class="icon-blank"></i> 简约浅</a></li>
					<li><a data-value="slate" href="javascript:void(0);"><i class="icon-blank"></i> 炫酷黑</a></li>
					<li><a data-value="spacelab" href="javascript:void(0);"><i class="icon-blank"></i> 时尚白</a></li>
				</ul>
			</div>
			<!-- theme selector ends -->
			
			<!-- user dropdown starts -->
			<div class="btn-group pull-right" >
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">
					<i class="icon-user"></i><span class="hidden-phone"> ${sysUser.usname}</span>
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="javascript:void(0);" id="toggle-fullscreen">全屏体验</a></li>
					<li class="divider"></li>
					<li><a href="javascript:void(0);" id="showModifyPass">修改密码</a></li>
					<li class="divider"></li>
					<li><a href="${pageContext.request.contextPath}/sys/loginout">退出登录</a></li>
				</ul>
			</div>
			<!-- user dropdown ends -->
			
			<!-- 控制台Begin -->
			<div class="btn-group pull-right" >
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">
					<i class="icon-globe"></i><span class="hidden-phone"> 控制台</span>
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="${pageContext.request.contextPath}/druid/index.html" target="_blank">系统性能监控</a></li>
					<li class="divider"></li>
					<li><a href="${pageContext.request.contextPath}/druid/index.html" target="_blank">SQL调试</a></li>
				</ul>
			</div>
			<!-- 控制台End -->
			
			<!-- 控制台Begin -->
			<div class="btn-group pull-right" >
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="javascript:void(0);">
					<i class="icon-th-large"></i><span class="hidden-phone"> 系统模块切换</span>
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<c:forEach items="${sysUser.menulist}" var="menuBean" varStatus="vs">
						<c:if test="${menuBean.upmuid==0}">
							<li><a href="${pageContext.request.contextPath}${menuBean.mulink}">${menuBean.muname}</a></li>
							<c:if test="${!vs.last}"><li class="divider"></li></c:if>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<!-- 控制台End -->
			
			<div class="top-nav nav-collapse">
				<ul class="nav">
					<li><a><i class="glyphicon glyphicon-globe"></i>Visit Site</a></li>
					<li>
						<form class="navbar-search pull-left">
							<input placeholder="Search" class="search-query span2" name="query" type="text">
						</form>
					</li>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/top.js"></script>
<!-- topbar ends -->