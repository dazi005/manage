$(function(){
	//初始化菜单树
	_Ztree.init({
		treeid : 'mutree',
		zNodes : window.roleMenus,
		view : {
			expandSpeed : "normal",
			addHoverDom :  null, //当鼠标移动到节点上时
	        removeHoverDom : null//当鼠标移开节点上时
		},
		data : {
			key : {
				title : 'muname',
				name : 'muname',
				children : 'childrenMenus'
			}
		},
		check : {
			chkboxType: { "Y": "ps", "N": "s" }
		}
	});
})

var _RolePrivilege = {
	//保存权限分配
	saveChange : function(){
		var treeObj = $.fn.zTree.getZTreeObj("mutree");
		var changeArray = treeObj.getChangeCheckedNodes()
		var menuArray = [];
		var roid = $("#roid").val();
		for(var i=0;i<changeArray.length;i++){
			var munuObj = {
				muid : changeArray[i].muid,
				checked : changeArray[i].checked
			}
			menuArray.push(munuObj);
		}
		$("#saveButton").button('loading');//执行等待状态切换操作
		$.ajax({
	        type: "POST",
	        url: Malone.root()+ "/sysmanage/roleConfig/saveRolePrivilege?roid="+roid,
	        cache: false,
	        data:$.toJSON(menuArray),
	        contentType: "application/json; charset=utf-8",
	        dataType:'json',
	        beforeSend:beforeSend,
	        async:false,
	        success: function(msg){
	        	if(msg.resultCode==0){
	        		$.alert({title:'success',content:'角色权限分配成功'});
	        		$("#saveButton").button('reset');
	        	}else{
	        		$.alert({title:'error',content:msg.resultMsg});
	        		$("#saveButton").button('reset');
	        	}
			}
		});
	},
	//关闭当前页面
	closePage : function(){
		if(confirm("您确定要关闭本页吗？")){
			window.opener=null;
			window.open('','_self');
			window.close();
		}else{}
	}
}