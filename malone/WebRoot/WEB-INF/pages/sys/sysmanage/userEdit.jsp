<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/comm.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>通用后台管理系统 - 用户管理 - 新增/修改用户</title>
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
							<a href="<%=path%>/sysmanage/userConfig/userList">用户管理</a><span class="divider">/</span>
						</li>
						<li class="active">
							<a href="javascript:void(0);">用户编辑</a>
						</li>
					</ul>
				</div>
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-edit"></i> 用户编辑</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" id="userEdit">
								<input type="hidden" name="usid" value="<c:if test="${userBean.usid==null}">0</c:if><c:if test="${userBean.usid!=null}">${userBean.usid}</c:if>" />
								<fieldset>
									<legend>基本信息</legend>
									<div class="control-group">
										<label class="control-label" for="typeahead">登录帐号</label>
										<div class="controls">
											<input id="atitle" class="input-xlarge focused" type="text"
												name="usacct" value="${userBean.usacct}" onblur="Malone.verify.notNull(this);">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="date01">用户昵称</label>
										<div class="controls">
											<input class="input-xlarge focused span5" type="text"
												name="usname" value="${userBean.usname}" onblur="Malone.verify.notNull(this);">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="date01">真实姓名</label>
										<div class="controls">
											<input class="input-xlarge focused span5" type="text"
												name="usrealname" value="${userBean.usrealname}">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="selectError">用户状态</label>
										<div class="controls">
											<input type="hidden" value="${userBean.ustate}" name="ustate" id="ustate"/>
											<input data-no-uniform="true" type="checkbox" name="ck1" class="iphone-toggle"
												<c:if test="${userBean.ustate==1}">checked='checked'</c:if>>
										</div>
									</div>
									<c:if test="${userBean.createdate!=null}">
										<div class="control-group">
											<label class="control-label" for="date01">创建日期</label>
											<div class="controls">
												<input class="input-xlarge focused span5" type="text" value="${userBean.createdate}" 
												readonly="readonly"/>
											</div>
										</div>
									</c:if>
									<div class="control-group">
										<label class="control-label" for="selectError">是否可重复登录</label>
										<div class="controls">
											<input type="hidden" value="${userBean.loginagain}" name="loginagain" id="loginagain"/>
											<input data-no-uniform="true" type="checkbox" name="ck2" class="iphone-toggle"
												<c:if test="${userBean.loginagain==1}">checked='checked'</c:if>>
										</div>
									</div>
								</fieldset>
								<fieldset>
									<legend>部门信息</legend>
									<div class="controls">
										<input class="input-xlarge focused" type="text" value="${userBean.sysdept.dpname}" id="dpname" onclick="_UserEdit.showDpTree()" title="点击选择部门" data-rel="tooltip" readonly="readonly">
										<input type="hidden" value="${userBean.sysdept.dpid}" name="sysdept.dpid" id="dpid"/>
										<div id="dpContent" class="dpContent"><ul id="dptree" class="ztree"></ul></div>
									</div>
									
								</fieldset>
								<fieldset>
									<legend>联系信息</legend>
									<div class="control-group">
										<label class="control-label" for="date01">联系电话</label>
										<div class="controls">
											<input class="input-xlarge focused span5" type="text"
												name="usphone" value="${userBean.usphone}">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="typeahead">邮箱</label>
										<div class="controls">
											<input class="input-xlarge focused span5 typeahead" type="text" name="usemail" data-items="10"
											 data-provide="EmailAuto" data-source='["@qq.com","@gmail.com","@126.com","@163.com","@hotmail.com","@yahoo.com","@yahoo.com.cn","@live.com","@sohu.com","@sina.com"]' value="${userBean.usemail}" >
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="date01">身份证号</label>
										<div class="controls">
											<input class="input-xlarge focused span5" type="text"
												name="uscardno" value="${userBean.uscardno}">
										</div>
									</div>
								</fieldset>
								<!-- 提交表单 -->
								<div class="form-actions">
									<button type="submit" id="saveButton" class="btn btn-primary">保存</button>
									<button type="button" class="btn"
										onclick="cancelEdit('<%=path%>/sysmanage/userConfig/userList');">取消</button>
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
						<a href="<%=path%>/sysmanage/userConfig/userList">用户管理</a><span class="divider">/</span>
					</li>
					<li class="active">
						<a href="javascript:void(0);">用户编辑</a>
					</li>
				</ul>
			</div>
			</div><!-- content ends -->
		</div><!--/#content.span10-->
		<hr>
		 <!-- 引入尾部 -->
    	<jsp:include page="/WEB-INF/pages/sys/footer.jsp"></jsp:include>
	</div><!--/.fluid-container-->
	<script type="text/javascript" src="<%=path%>/sys/js/emailAutoComplete.js"></script>
	<script type="text/javascript" src="<%=path%>/comm/zTree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/js/tree.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/sysmanage/userEdit.js"></script>
  </body>
</html>
