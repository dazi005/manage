<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/comm/comm.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>欢迎登陆通用后台管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  
  <body>
  <input type="hidden" value="${param.redirectURL}" id="redirectURL"/>
	 <div class="container-fluid">
		<div class="row-fluid">
			<div class="row-fluid">
				<div class="span12 center login-header">
					<h2>欢迎登陆通用后台管理系统</h2>
				</div><!--/span-->
			</div><!--/row-->
			
			<div class="row-fluid">
				<div class="well span5 center login-box">
					<div class="alert alert-info" id="errorMsg">
						请输入账号和密码登陆
					</div>
					<form class="form-horizontal" method="post" id="loginForm">
						<fieldset>
							<div class="input-prepend input-xlarge" title="请输入用户名" data-rel="tooltip">
								<span class="add-on"><i class="icon-user"></i></span><input autofocus class="input-large span10" name="usacct" type="text" value="" />
							</div>
							<div class="clearfix"></div>
	
							<div class="input-prepend input-xlarge" title="请输入密码" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span><input class="input-large span10" name="uspass" type="password" value="" />
							</div>
							<div class="clearfix"></div>
							
							<div class="input-prepend" title="请输入验证码" data-rel="tooltip">
								<span class="add-on"><i class="icon-picture"></i></span><input class="input-large span5" name="randcode" type="text" value="" />
								<img src="<%=path%>/kaptcha/kaptcha-image" style="vertical-align: middle;" width="100" height="30" id="kaptchaImage" onclick="Malone.kaptchaImage('kaptchaImage');"/>
							</div>
							<div class="clearfix"></div>
	
							<div class="input-prepend">
								<label class="remember"><input type="checkbox" /> 记住我的密码</label>
							</div>
							<div class="clearfix"></div>
							<p class="center span5">
								<a class="btn btn-primary" id="loginButton" onclick="_Login.login();">登陆</a>
							</p>
						</fieldset>
					</form>
				</div><!--/span-->
			</div><!--/row-->
		</div><!--/fluid-row-->
	</div>
	<script type="text/javascript" src="<%=path%>/sys/js/login.js"></script>
  </body>
</html>