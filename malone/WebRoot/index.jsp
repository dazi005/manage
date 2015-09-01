<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/comm.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<title>测试首页</title>
  	<script type="text/javascript">
  		function confirmClick(){
  			$.confirm({title:'提示',content:'您确定要xx吗？',callback:function(){
  				alert('您点击了确定按钮');
  			}})
  		}
  	</script>
  </head>
  <body>
  	<h4>模态框：</h4>
  	1、<a href="javascript:$.alert({title:'提示',content:'模态框提示内容'});">alert</a> <br/>
  	2、<a href="javascript:confirmClick();">confirm</a>
  </body>
</html>
