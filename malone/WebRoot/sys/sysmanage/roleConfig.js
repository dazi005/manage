var dtable;
$(function(){
    dtable = $("#roleTable").showtable({
    	trSelectEffect : true, //扩充参数 ：是否需要选中效果
		sAjaxSource : Malone.root() + '/sysmanage/roleConfig/roleListAjax',
		columns : ["roid","roname",
		   {
   			 "mDataProp": "rotype",
   			 "bSearchable": true,
   			 "bStorable": true,
   			 "fnRender": function (obj) {
   				if(obj.aData.rotype==1){
   					return "系统角色";
   				}else if(obj.aData.rotype==2){
   					return "用户角色";
   				}
   			 }
   		   },{
	   			"mDataProp": "rostate",
	   			"bSearchable": true,
	   			"bStorable": true,
	   			"fnRender": function (obj) {
	   				if(obj.aData.rostate==1){
	   					return "<span class='label label-success'>有效</span>";
	   				}else{
	   					return "<span class='label label-warning'>无效</span>";
	   				}
	   			}
   		   },"rodesc"
	   	]
	});
})
var _RoleConfig = {
	//获取选中的行主键
	getSelectedData : function(){
		var ids = ''; 
		var strbuf = new StringBuffer();
		var sels = dtable.$('tr.row_selected');
		if(sels.length > 0){
			for(var i=0;i<sels.length;i++){
				var id = $(sels[i]).find('td:first').html();
				strbuf.append(id + ",");
			}
			ids = strbuf.toString();
			ids = ids.substr(0 , ids.length - 1);
		}
		return ids;
	},
	//修改角色
	updateRole : function(){
		var ids = _RoleConfig.getSelectedData();
		if(ids.length==0){
			$.message_box({level:'error',content:'请从下面列表中选择一个角色进行修改'});
			return;
		}
		ids = ids.split(',');
		if(ids.length > 1){
			$.message_box({level:'error',content:'只能选择一个角色进行修改'});
		}else{
			window.location.href = Malone.root() + "/sysmanage/roleConfig/roleEdit?roid=" + ids;
		}
	},
	//删除角色
	deleteRole : function(){
		var ids = _RoleConfig.getSelectedData();
		if(ids.length==0){
			$.message_box({level:'error',content:'请从下面列表中选择一个角色进行删除'});
			return;
		}
		$.confirm({title:'WARN',content:'确定要删除选中的角色吗？',callback:function(){
			$.ajax({
		        type: "POST",
		        url: Malone.root()+ "/sysmanage/roleConfig/deleteRole?tt="+new Date(),
		        cache: false,
		        contentType: "application/json; charset=utf-8",
		        data:$.toJSON({'roids':ids}),
		        dataType:'json',
		        beforeSend:beforeSend,
		        async:false,
		        success: function(msg){
		        	if(msg.resultCode==0){
		        		$.alert({title:'success',content:'角色删除成功'});
		        		dtable.fnReloadAjax();
		        	}else{
		        		$.alert({title:'error',content:'角色删除失败'});
		        	}
				}
			});
		}});
	},
	//角色权限分配
	privRole : function(){
		var ids = _RoleConfig.getSelectedData();
		if(ids.length==0){
			$.message_box({level:'error',content:'请从下面列表中选择一个角色分配权限'});
			return;
		}
		ids = ids.split(',');
		if(ids.length > 1){
			$.message_box({level:'error',content:'只能选择一个角色分配权限'});
		}else{
			window.open(Malone.root() + "/sysmanage/roleConfig/rolePrivilege?roid=" + ids);
		}
	}
}