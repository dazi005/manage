<%@ page language="java" import="java.util.*,com.alibaba.fastjson.*,com.malone.sys.dto.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/comm.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>通用后台管理系统 - 用户管理 - 用户权限分配</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=path%>/comm/charisma/css/bootstrap-duallistbox.min.css" rel="stylesheet">
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
  		//获取数据权限列表
  		var allDatacs = '${allDatacs}';
  		if(allDatacs){
			window.allDatacs = $.evalJSON(allDatacs);
		}
  	</script>
  	<!-- 引入头部 -->
	<jsp:include page="/WEB-INF/pages/sys/header.jsp"></jsp:include>
	<input type="hidden" value="${curusid}" id="usid" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="content" class="span8 center">
				<div class="row-fluid">
					<div class="span12 well">
						<div class="tabbable">
							<!-- Only required for left/right tabs -->
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab1" data-toggle="tab">角色分配</a></li>
								<li><a href="#tab2" data-toggle="tab">数据权限-区域</a></li>
								<li><a href="#tab3" data-toggle="tab">数据权限-客户类型</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tab1">
									<select multiple="multiple" size="${fn:length(allRoles)}" id="roleSelect">
										<c:forEach items="${allRoles}" var="roleBean">
											<option value="${roleBean.roid}" 
											<c:forEach items="${curRoles}" var="curRoleBean">
												<c:if test="${roleBean.roid == curRoleBean.roid}">selected="selected"</c:if>
											</c:forEach>
											>${roleBean.roname}</option>
										</c:forEach>
									</select>
								</div>
								<div class="tab-pane" id="tab2">
									<div id="dpContent" class="dpContent"><ul id="mutree" class="ztree"></ul></div>
								</div>
								<div class="tab-pane" id="tab3">
									该功能暂未开放
								</div>
							</div>
						</div>
						<div class="form-actions">
							<button class="btn btn-primary" id="saveButton" onclick="_UserPrivilege.saveChange();" type="button">保存</button>
							<button onclick="_UserPrivilege.closePage();" class="btn" type="button">关闭</button>
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
	<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.bootstrap-duallistbox.min.js"></script>
	<script type="text/javascript" src="<%=path%>/comm/zTree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/js/tree.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/sysmanage/userPrivilege.js"></script>
</body>
</html>