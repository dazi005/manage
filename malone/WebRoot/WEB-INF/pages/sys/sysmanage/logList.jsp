<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/comm.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>通用后台管理系统 - 日志查询首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
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
							<a href="javascript:void(0);">日志管理</a><span class="divider">/</span>
						</li>
						<li class="active">
							<a href="<%=path%>/sysmanage/logConfig/logList">日志查询</a>
						</li>
					</ul>
				</div>
				<div class="breadcrumb" <c:if test="${fn:length(menulist)==0}">style="display:none;"</c:if>>
					<div class="btn-group">
						<c:forEach items="${menulist}" var="menuBean">
							<a class="btn btn-info" href="<c:if test='${!fn:containsIgnoreCase(menuBean.mulink,\"javascript\")}'><%=path%></c:if>${menuBean.mulink}"><i class="${menuBean.muicon} icon-white"></i> ${menuBean.muname}</a>
						</c:forEach>
					</div>
				</div>
				
				<!-- 查询条件 begin-->
				<!-- <div class="accordion-heading">
					  <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne"> 高级查询 </a>
				 </div> -->
				 <div id="collapseOne" class="accordion-body collapse">
				      <div class="accordion-inner">
						<form class="form-horizontal" >
							<table >
							<tr>
								<td>
									<div class="control-group">
										<label class="control-label" for="focusedInput">操作类型</label>
										<div class="controls">
											  <label class="checkbox inline">
												<input type="checkbox"  value="登录" name="opertype"> 登录
											  </label>
											  <label class="checkbox inline">
												<input type="checkbox" value="编辑/新增菜单" name="opertype"> 编辑/新增菜单
											  </label>
											  <label class="checkbox inline">
												<input type="checkbox" value="编辑/新增角色" name="opertype"> 编辑/新增角色
											  </label>
											  <label class="checkbox inline">
												<input type="checkbox" value="分配角色权限" name="opertype"> 分配角色权限
											  </label>
											  <label class="checkbox inline">
												<input type="checkbox" value="编辑/新增用户" name="opertype"> 编辑/新增用户
											  </label>
											  <label class="checkbox inline">
												<input type="checkbox" value="分配用户权限" name="opertype"> 分配用户权限
											  </label>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="control-group">
									<label class="control-label" for="selectError">操作时间</label>
									<div class="controls">
									  	<input type="text" id="startTime" class="input-xlarge datepicker" style="width:120px;" readonly="readonly" > - 
									  	<input type="text" id="endTime" class="input-xlarge datepicker" style="width:120px;" readonly="readonly" >
									  	<a href="javascript:void(0);" onclick="_LogConfig.resertTime();" >清空</a>
									</div>
								</td>
							</tr>
							</table>
						</form>
					</div>
				</div>
				<!-- 查询条件 end-->
				
				<div class="sortable row-fluid">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-align-justify"></i> 日志列表
							</h2>
							<div class="box-icon">
								<a class="btn  btn-round" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" title="点击展开/关闭条件查询" data-rel="tooltip"> <i class="icon-search"></i> </a>
								<a href="#" class="btn btn-setting btn-round"><i
									class="icon-cog"></i>
								</a> <a href="#" class="btn btn-minimize btn-round"><i
									class="icon-chevron-up"></i>
								</a> <a href="#" class="btn btn-close btn-round"><i
									class="icon-remove"></i>
								</a>
							</div>
						</div>
						<div class="box-content">
							<table class="table table-striped table-bordered bootstrap-datatable datatable" id="logTable">
								<thead>
									<tr>
										<th>LOGID</th>
										<th>业务名称</th>
										<th>操作类型</th>
										<th>操作内容</th>
										<th>操作时间</th>
										<th>系统</th>
										<th>浏览器</th>
										<th>操作IP</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div><!-- content ends -->
		</div><!--/#content.span10-->
		<hr>
		 <!-- 引入尾部 -->
    	<jsp:include page="/WEB-INF/pages/sys/footer.jsp"></jsp:include>
	</div><!--/.fluid-container-->
	<script type="text/javascript" src="<%=path%>/sys/sysmanage/logConfig.js"></script>
  </body>
</html>
