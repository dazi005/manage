<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<link rel="shortcut icon" href="<%=path%>/favicon.ico">
<link id="bs-css" href="<%=path%>/comm/charisma/css/bootstrap-simplex.css" rel="stylesheet">
<style type="text/css">
body {
	padding-bottom: 40px;
}
.sidebar-nav {
	padding: 9px 0;
}
.accordion > ul{
	margin-left: 8px;
	display: none;
	margin-bottom : 0;
}
</style>
<link href="<%=path%>/comm/charisma/css/bootstrap-responsive.css" rel="stylesheet">
<link href="<%=path%>/comm/charisma/css/charisma-app.css" rel="stylesheet">
<!--[if lt IE 9]>
  <script src="<%=path%>/comm/charisma/js/html5shiv.js"></script>
<![endif]-->
<link href="<%=path%>/comm/charisma/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
<link href='<%=path%>/comm/charisma/css/fullcalendar.css' rel='stylesheet'>
<link href='<%=path%>/comm/charisma/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
<link href='<%=path%>/comm/charisma/css/chosen.css' rel='stylesheet'>
<link href='<%=path%>/comm/charisma/css/uniform.default.css' rel='stylesheet'>
<link href='<%=path%>/comm/charisma/css/colorbox.css' rel='stylesheet'>
<link href='<%=path%>/comm/charisma/css/jquery.cleditor.css' rel='stylesheet'>
<link href='<%=path%>/comm/charisma/css/jquery.noty.css' rel='stylesheet'>
<link href='<%=path%>/comm/charisma/css/noty_theme_default.css' rel='stylesheet'>
<link href='<%=path%>/comm/charisma/css/elfinder.min.css' rel='stylesheet'>
<link href='<%=path%>/comm/charisma/css/elfinder.theme.css' rel='stylesheet'>
<link href='<%=path%>/comm/charisma/css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='<%=path%>/comm/charisma/css/opa-icons.css' rel='stylesheet'>
<!-- jQuery -->
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.cookie.js"></script>
<!-- jQuery validate 1.13.1 -->
<script type="text/javascript" src="http://cdn.bootcss.com/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/jqueryValidate/jquery.validate.extend.js"></script>
<!-- charisma效果 -->
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-transition.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-alert.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-modal.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-scrollspy.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-tab.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-tooltip.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-popover.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-button.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-collapse.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-carousel.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-typeahead.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/bootstrap-tour.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/fullcalendar.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.dataTables.min.js"></script>
<!-- chart libraries start -->
<script src="<%=path%>/comm/charisma/js/excanvas.js"></script>
<script src="<%=path%>/comm/charisma/js/jquery.flot.min.js"></script>
<script src="<%=path%>/comm/charisma/js/jquery.flot.pie.min.js"></script>
<script src="<%=path%>/comm/charisma/js/jquery.flot.stack.js"></script>
<script src="<%=path%>/comm/charisma/js/jquery.flot.resize.min.js"></script>
<!-- chart libraries end -->
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.chosen.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.uniform.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.colorbox.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.cleditor.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.noty.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.elfinder.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/i18n/elfinder.zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.raty.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.iphone.toggle.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.autogrow-textarea.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/jquery.history.js"></script>
<script type="text/javascript" src="<%=path%>/comm/charisma/js/charisma.js"></script>
<!-- 自定义封装 -->
<script type="text/javascript" src="<%=path%>/sys/js/modal.js"></script>
<script type="text/javascript" src="<%=path%>/comm/comm.js"></script>
<!-- 统一模态框装载容器 -->
<div class="modal hide fade" id="comm_modal"></div>
<!-- 统一消息提示框装载容器 -->
<div class="alert alert-info" id="comm_box" style="display:none;width:500px;position:fixed;left:50%;margin-left:-250px;top:30px;z-index:10000;"></div>