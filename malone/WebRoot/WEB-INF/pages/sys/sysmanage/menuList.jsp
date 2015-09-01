<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/comm.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>通用后台管理系统 - 菜单管理首页</title>
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
  		//获取全部的菜单列表
  		var allMenus = '${allMenus}';
  		if(allMenus){
			window.allMenus = $.evalJSON(allMenus);
		}
  	</script>
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
						<li class="active">
							<a href="<%=path%>/sysmanage/menuConfig/menuList">菜单管理</a>
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
				<div class="sortable row-fluid">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2>
								<i class="icon-align-justify"></i> 菜单列表
							</h2>
							<div class="box-icon">
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
							<div class="dpContent"><ul id="mutree" class="ztree"></ul></div>
						</div>
					</div>
				</div>
			</div><!-- content ends -->
		</div><!--/#content.span10-->
		<hr>
		<!-- 引入尾部 -->
    	<jsp:include page="/WEB-INF/pages/sys/footer.jsp"></jsp:include>
	</div><!--/.fluid-container-->
	<script type="text/javascript" src="<%=path%>/comm/zTree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/js/tree.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/sysmanage/menuConfig.js"></script>
	<script type="text/javascript" src="<%=path%>/sys/sysmanage/menuEdit.js"></script>
  </body>
</html>
