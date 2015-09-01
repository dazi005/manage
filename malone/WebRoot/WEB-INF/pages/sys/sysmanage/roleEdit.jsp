<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/comm.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>通用后台管理系统 - 角色管理 - 新增/修改角色</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" href="<%=path%>/comm/zTree/css/zTreeStyle/metro.css">
	<style type="text/css">
		.dpContent{
			display:none; 
			position: absolute;
			background-color: #eeeeee;
			width : 280px;
			height: 300px;
			overflow:auto;
		}
	</style>
  </head>
  
  <body>
    <!-- 引入头部 -->
    <jsp:include page="/WEB-INF/pages/sys/header.jsp"></jsp:include>
 	<div class="container-fluid">
		<div class="row-fluid">
			<!-- 引入左侧菜单栏 -->	
			<jsp:include page="/WEB-INF/pages/sys/left.jsp"></jsp:include>
			<div id="content" class="span10">
				<!-- content starts -->
				<div>
					<ul class="breadcrumb">
						<li>
							<a href="<%=path%>/sys/index">主页</a> <span class="divider">/</span>
						</li>
						<li>
							<a href="javascript:void(0);">系统管理</a><span class="divider">/</span>
						</li>
						<li>
							<a href="<%=path%>/sysmanage/roleConfig/roleList">角色管理</a><span class="divider">/</span>
						</li>
						<li class="active">
							<a href="javascript:void(0);"><c:if test="${roleBean.roid==null}">角色新增</c:if><c:if test="${roleBean.roid!=null}">角色编辑</c:if></a>
						</li>
					</ul>
				</div>
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> <c:if test="${roleBean.roid==null}">新增角色</c:if><c:if test="${roleBean.roid!=null}">编辑角色</c:if></h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" id="roleEdit">
								<input type="hidden" name="roid" value="<c:if test="${roleBean.roid==null}">0</c:if><c:if test="${roleBean.roid!=null}">${roleBean.roid}</c:if>" />
								<fieldset>
									<legend>基本信息</legend>
									<div class="control-group">
										<label class="control-label" for="typeahead">角色名称</label>
										<div class="controls">
											<input class="input-xlarge focused" type="text"
												name="roname" value="${roleBean.roname}" onblur="Malone.verify.notNull(this);">
										</div>
									</div>
									<!-- <div class="control-group">
										<label class="control-label" for="date01">角色类型</label>
										<div class="controls">
											<select name="rotype" data-rel="chosen" data-placeholder="请选择角色类型">
												<option value=""></option>
												<option value="1" <c:if test="${roleBean.rotype == 1}">selected="true"</c:if> >系统角色</option>
												<option value="2" <c:if test="${roleBean.rotype == 2}">selected="true"</c:if> >用户角色</option>
											</select>
										</div>
									</div> -->
									
									<div class="control-group">
										<label class="control-label" for="selectError">角色状态</label>
										<div class="controls">
											<input type="hidden" value="${roleBean.rostate}" name="rostate" id="rostate"/>
											<input data-no-uniform="true" type="checkbox" name="ck1" class="iphone-toggle"
												<c:if test="${roleBean.rostate==1}">checked='checked'</c:if>>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="date01">角色描述</label>
										<div class="controls">
											<textarea class="autogrow" name="rodesc" style="width:270px;">${roleBean.rodesc}</textarea>
										</div>
									</div>
								</fieldset>
								<!-- 提交表单 -->
								<div class="form-actions">
									<button type="submit" id="saveButton" class="btn btn-primary">保存</button>
									<button type="button" class="btn"
										onclick="cancelEdit('<%=path%>/sysmanage/roleConfig/roleList');">取消</button>
								</div>
							</form>    
					</div>
				</div><!--/span-->
			</div><!--/row-->
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="<%=path%>/sys/index">主页</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="javascript:void(0);">系统管理</a><span class="divider">/</span>
					</li>
					<li>
						<a href="<%=path%>/sysmanage/roleConfig/roleList">角色管理</a><span class="divider">/</span>
					</li>
					<li class="active">
						<a href="javascript:void(0);"><c:if test="${roleBean.roid==null}">角色新增</c:if><c:if test="${roleBean.roid!=null}">角色编辑</c:if></a>
					</li>
				</ul>
			</div>
			</div><!-- content ends -->
		</div><!--/#content.span10-->
		<hr>
		 <!-- 引入尾部 -->
    	<jsp:include page="/WEB-INF/pages/sys/footer.jsp"></jsp:include>
	</div><!--/.fluid-container-->
	<script type="text/javascript" src="<%=path%>/comm/zTree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/js/tree.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/sysmanage/roleEdit.js"></script>
  </body>
</html>
