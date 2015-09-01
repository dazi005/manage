<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/comm/comm.jsp"%>
<!doctype html>
<html>
<head>
<base href="<%=basePath%>">

<title>极意网络</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
body {
	background-color: #FEFEFE;
}

.wrap {
	width: 960px;
	margin: 100px auto;
	font-size: 125%;
}

.row {
	margin: 30px 0;
}
</style>

<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="<%=path%>/sys/secret/geetest.js"></script>

</head>

<body>
	<div class="wrap">
		<h1>JavaEE站点安装Demo页面</h1>
		<form method="post" action="<%=path%>/geetest/GeetestVerifyServlet">
			<div class="row">
				<label for="name">邮箱</label> <input type="text" id="email"
					name="email" value="geetest@126.com" />
			</div>
			<div class="row">
				<label for="passwd">密码</label> <input type="password" id="passwd"
					name="passwd" value="gggggggg" />
			</div>

			<%--Start  Code--%>
			<div class="row">
				<div id="div_id_embed"></div>

				<%--End  Code--%>


				<div class="row">
					<input type="submit" value="登录" id="submit-button" />
				</div>

				<script type="text/javascript">
					//get  geetest server status, use the failback solution
					$.ajax({
						url : Malone.root() + "/geetest/GeetestStateServlet",
						type : "get",
						dataType : 'JSON',
						success : function(result) {
							console.log(result);
							if (result.success) {
								//1. use geetest capthca
								window.gt_captcha_obj = new window.Geetest({
									gt : result.gt,
									challenge : result.challenge,
									product : 'embed'
								});

								gt_captcha_obj.appendTo("#div_id_embed");

								//Ajax request demo,if you use submit form ,then ignore it 
								gt_captcha_obj.onSuccess(function() {
									//geetest_ajax_results()
								});

							} else {
								//failback :use your own captcha template
								//Geetest Server is down,Please use your own captcha system	in your web page
								//or use the simple geetest failback solution
								$("#div_id_embed").html('failback:gt-server is down ,please use your own captcha front');
								//document.write('gt-server is down ,please use your own captcha')
							}

						}
					})
				</script>
				<div class="row">
					<input type="button" value="测试自定义刷接口" onclick="geetest_refresh()" />
				</div>
			</div>

			<script type="text/javascript">
				function geetest_refresh() {
					console.log("you can use this api in your own js function")
					gt_captcha_obj.refresh();
				}

				function geetest_ajax_results() {
					$.ajax({
						url : Malone.root() + "/geetest/GeetestVerifyServlet.do",//todo:set the servelet of your own
						type : "post",
						data : gt_captcha_obj.getValidate(),
						success : function(sdk_result) {
							//console.log(sdk_result)
						}
					});
				}
			</script>

		</form>
	</div>
</body>
</html>
