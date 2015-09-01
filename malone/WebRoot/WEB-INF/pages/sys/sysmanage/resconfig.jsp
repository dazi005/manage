<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/comm.jsp"%>
<html>
  <head>
    <title>通用后台管理系统 - 资源管理</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
							<a href="javascript:void(0);">系统配置</a><span class="divider">/</span>
						</li>
						<li class="active">
							<a href="<%=path%>/sysmanage/res/resConfig">资源管理器</a>
						</li>
					</ul>
				</div>
			<!-- 产品管理面板-->
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i> File Manager</h2>
						<div class="box-icon">
							<a href="javascript:void)(0);" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="javascript:void)(0);" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="javascript:void)(0);" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<div class="alert alert-error">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<i class="icon-info-sign"></i> 您可以在这里完成通用后台系统文件的维护 (服务器空间有限，请勿上传系统以外的文件)，请不要使用中文命名文件。
						</div>
						<div class="file-manager"></div>
					</div>
				</div><!--/span-->
			</div><!--/row-->
			</div><!-- content ends -->
		</div><!--/#content.span10-->
		<hr>
		<jsp:include page="/WEB-INF/pages/sys/footer.jsp"></jsp:include>
	</div><!--/.fluid-container-->
  </body>
</html>
