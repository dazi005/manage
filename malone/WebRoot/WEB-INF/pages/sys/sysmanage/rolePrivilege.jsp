<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/comm.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>通用后台管理系统 - 角色管理 - 角色权限分配</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" href="<%=path%>/comm/zTree/css/zTreeStyle/metro.css">
	<style type="text/css">
		.dpContent{
			background-color: #eeeeee;
			height: 400px;
			overflow:auto;
			padding-left:10px;
		}
	</style>
  </head>
  
  <body>
  	<script type="text/javascript">
  		//获取角色权限列表
  		var roleMenus = '${roleMenus}';
  		if(roleMenus){
			window.roleMenus = $.evalJSON(roleMenus);
		}
  	</script>
  	<!-- 引入头部 -->
	<jsp:include page="/WEB-INF/pages/sys/header.jsp"></jsp:include>
	<input type="hidden" value="${curroid}" id="roid" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="content" class="span8 center">
				<div class="row-fluid">
					<div class="span12 well">
						<div class="tabbable">
							<!-- Only required for left/right tabs -->
							<ul class="nav nav-tabs">
								<li class="active"><a>角色权限分配</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tab1">
									<div id="dpContent" class="dpContent"><ul id="mutree" class="ztree"></ul></div>
								</div>
							</div>
						</div>
						<div class="form-actions">
							<button class="btn btn-primary" id="saveButton" onclick="_RolePrivilege.saveChange();" type="button">保存</button>
							<button onclick="_RolePrivilege.closePage();" class="btn" type="button">关闭</button>
						</div>
					</div>
					<!--/span-->
				</div>
			</div>
		</div>
		<hr>
		 <!-- 引入尾部 -->
    	<jsp:include page="/WEB-INF/pages/sys/footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript" src="<%=path%>/comm/zTree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/js/tree.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/sysmanage/rolePrivilege.js"></script>
</body>
</html>