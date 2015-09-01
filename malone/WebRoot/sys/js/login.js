$(function(){
	/** 提示用户使用火狐浏览器体验 **/
	$.noty({"text":"为更好体验，请使用最新版本火狐浏览器，<a href='http://www.firefox.com.cn/download/' target='_blank'>点击下载</a>","layout":"top","type":"error","closeButton":"true"});
	/** 读取Cookie中的用户名和密码 **/
	var malone_user = $.cookie('malone_user');
	var malone_pass = $.cookie('malone_pass');
	var rememberMe = $.cookie('malone_rememberme');
	$("input[name='usacct']").val(malone_user);
	$("input[name='uspass']").val(malone_pass);
	if(rememberMe == 'on'){
		var allcheck = $("input[type='checkbox']").attr('checked',true);
		$.uniform.update(allcheck);
	}
	/** 监听键盘回车事件 **/
	document.onkeydown = function(e){
		var ev = document.all ? window.event : e;
		if(ev.keyCode==13) {
			_Login.login();//处理事件
		}
	}
})

var _Login = {
	errorTip : function(emsg){
		$("#errorMsg").attr('class','alert alert-error').html(emsg);
	},
	getFormData : function(){
		return {
			usacct : $("input[name='usacct']").val(),
			uspass : $("input[name='uspass']").val(),
			randcode : $("input[name='randcode']").val(),
			remember : $("input[type='checkbox']:checked").val(),
			redirectURL : $("#redirectURL").val()
		}
	},
	loginVerify : function(data){
		if(data.usacct.trim().length==0){
			_Login.errorTip('请输入登录账号');
			return false;
		}else if(data.uspass.trim().length==0){
			_Login.errorTip('请输入密码');
			return false;
		}else if(data.randcode.trim().length==0){
			_Login.errorTip('请输入验证码');
			return false;
		}else{
			return true;
		}
	},
	login : function(){
		var data = _Login.getFormData();
		if(_Login.loginVerify(data)){
			$("#loginButton").button('loading');//执行等待状态切换操作
			var rjson = $.toJSON(data);
			$.ajax({
		        type: "POST",
		        url: Malone.root()+ "/sys/login?tt="+new Date(),
		        cache: false,
		        contentType: "application/json; charset=utf-8",
		        data:rjson,
		        dataType:'json',
		        async:false,
		        success: function(msg){
		        	if(msg.resultCode==0){
		        		$("#errorMsg").attr('class','alert alert-success').html('登录成功，正在跳转中...');
		        		(data.redirectURL == null || data.redirectURL == '') ? window.location.href = Malone.root()+ "/sys/index" : window.location.href = Malone.root() + data.redirectURL;
		        	}else{
		        		$("#loginButton").button('reset');//执行等待状态切换操作
		        		_Login.errorTip(msg.resultMsg);
		        		Malone.kaptchaImage('kaptchaImage'); //刷新验证码
		        	}
				}
			});
		}
	}
}