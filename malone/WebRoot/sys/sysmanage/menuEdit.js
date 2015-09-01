var _MenuEdit = {
		//错误提示
		errorTip : function(emsg){
			$.message_box({level:'error',content:emsg});
		},
		//获取表单数据
		getFormData : function(){
			var muid = $("#muid").val();
			return {
				muid : muid,
				upmuid : $("#upmuid").val(),
				mulevel : muid == 0? parseInt($("#upmulevel").val())+1 : $("#upmulevel").val(),
				muname : $("#muname").val(),
				mulink : $("#mulink").val(),
				mutype : $("#mutype").val(),
				muicon : $("#muicon").val(),
				muorder : $("#muorder").val(),
				mustate : $("#mustate").val()
			}
		},
		//验证表单
		formVerify : function(data){
			if(data.muname.trim().length==0){
				_MenuEdit.errorTip('请输入菜单名称');
				return false;
			}/*else if(data.mulink.trim().length==0){
				_MenuEdit.errorTip('请输入菜单链接');
				return false;
			}*/else if(data.mutype == ''){
				_MenuEdit.errorTip('请选择菜单类型');
				return false;
			}else if(data.muorder.trim().length>0 && Malone.verify.isInteger(data.muorder)!= '0'){
				_MenuEdit.errorTip('排序值请输入整数');
				return false;
			}else if(data.mustate == ''){
				_MenuEdit.errorTip('请选择菜单状态');
				return false;
			}else{
				return true;
			}
		},
		//保存菜单
		saveMenu : function(){
			var data = _MenuEdit.getFormData();
			if(_MenuEdit.formVerify(data)){
				$("#saveButton").button('loading');//执行等待状态切换操作
				$.ajax({
			        type: "POST",
			        url: Malone.root()+ "/sysmanage/menuConfig/saveMenu?tt="+new Date(),
			        cache: false,
			        data:data,
			        async:false,
			        success: function(msg){
			        	if(msg.resultCode==0){
			        		alert('菜单信息保存成功');
			        		$("#saveButton").button('reset');
			        		_MenuConfig.initMenuTree();
			        		$('#comm_modal').modal('hide');
			        	}else{
			        		alert(msg.resultMsg);
			        		$("#saveButton").button('reset');
			        	}
					}
				});
			}
		}
}