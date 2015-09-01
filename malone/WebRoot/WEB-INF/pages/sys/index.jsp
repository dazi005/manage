<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/comm.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>通用后台管理系统 - 系统首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" href="<%=path%>/comm/zTree/css/zTreeStyle/metro.css">
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
							<a href="#">主页</a> <span class="divider">/</span>
						</li>
					</ul>
				</div>
				<div class="sortable row-fluid">
					<!-- <ul id="tree" class="ztree" style="width:560px; overflow:auto;"></ul>	 -->
				</div>
			</div><!-- content ends -->
		</div><!--/#content.span10-->
		<hr>
		 <!-- 引入尾部 -->
    	<jsp:include page="/WEB-INF/pages/sys/footer.jsp"></jsp:include>
	</div><!--/.fluid-container-->
  </body>
</html>
