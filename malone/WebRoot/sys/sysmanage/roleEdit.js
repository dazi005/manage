$(function(){
	$("input[autocomplete='off']").attr('name','weizhi');
	//表单验证
	$("#roleEdit").validate({
		submitHandler : function(form){
			_RoleEdit.saveEdit();
		},
		rules : {
			roname : "required"
		},
		messages : {
			roname : "请输入角色名称"
		}
	})
})
var _RoleEdit = {
	saveEdit : function(){
		/*if($("select[name='rotype']").val() == ''){
			$.message_box({level:'error',content:'请选择角色类型',time:1500});
			return;
		}*/
		$("#saveButton").button('loading');//执行等待状态切换操作
		var rostateCkbox = $("input[class='iphone-toggle']").attr('checked');
		if(rostateCkbox=='checked'){
			$("#rostate").val('1');
		}else{
			$("#rostate").val('-1');
		}
		var formstr = $("#roleEdit").serializeObject();
		$.ajax({
	        type: "POST",
	        url: Malone.root()+ "/sysmanage/roleConfig/saveRole",
	        cache: false,
	        data:formstr,
	        beforeSend:beforeSend,
	        async:false,
	        success: function(msg){
	        	if(msg.resultCode==0){
	        		$.alert({title:'success',content:'角色信息保存成功'});
	        		$("#saveButton").button('reset');
	        	}else{
	        		$.alert({title:'error',content:msg.resultMsg});
	        		$("#saveButton").button('reset');
	        	}
			}
		});
	}
}