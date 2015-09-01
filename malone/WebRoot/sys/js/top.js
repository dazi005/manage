$(function(){
	/** 修改密码弹出框 **/
	$("#showModifyPass").click(function(){
		var body = "<div class='control-group'><label class='control-label'>原始密码</label><div class='controls'><input class='input-xlarge focused' type='password' id='oldPass'/></div></div><div class='control-group'><label class='control-label'>新密码</label><div class='controls'><input class='input-xlarge focused' type='password' id='newPass'/></div></div><div class='control-group'><label class='control-label'>再次输入密码</label><div class='controls'><input class='input-xlarge focused' type='password' id='newPass2'/></div></div><div class='form-actions'><button class='btn btn-primary' type='button' onclick='_ModifyPass.modifyPass();' id='passModfButton'>确定修改</button></div>";
		$.alert({title:'修改密码',content:body});
	})
})

var _ModifyPass = {
	errorTip : function(emsg){
		alert(emsg);
	},
	getFormData : function(){
		return {
			oldPass : $("#oldPass").val(),
			newPass : $("#newPass").val(),
			newPass2 : $("#newPass2").val()
		}
	},
	modifyPassVerify : function(data){
		if(data.oldPass.trim().length==0){
			alert('原始密码不能为空');
			return;
		}else if(data.newPass.trim().length==0){
			alert('新密码不能为空');
			return;
		}else if(data.newPass2.trim().length==0){
			alert('请再次输入密码');
			return;
		}else if(data.newPass2.trim()!=data.newPass.trim()){
			alert('两次密码输入不一致');
			return;
		}else{
			return true;
		}
	},
	modifyPass : function(){
		var data = _ModifyPass.getFormData();
		if(_ModifyPass.modifyPassVerify(data)){
			$("#passModfButton").button('loading');//执行等待状态切换操作
			var rjson = $.toJSON(data);
			$.ajax({
		        type: "POST",
		        url: Malone.root()+ "/sys/modifypass?tt="+new Date(),
		        cache: false,
		        contentType: "application/json; charset=utf-8",
		        data:rjson,
		        dataType:'json',
		        async:false,
		        success: function(msg){
		        	if(msg.resultCode==0){
		        		$("#passModfButton").button('reset');//从等待状态切回
		        		alert('密码修改成功，请重新登录');
		        		window.location.href = Malone.root() + "/sys/loginout";
		        	}else{
		        		alert(msg.resultMsg);
		        		$("#passModfButton").button('reset');//从等待状态切回
		        	}
				}
			});
		}
	}
}