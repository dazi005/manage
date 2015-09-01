var dtable;
$(function(){
    dtable = $("#userTable").showtable({
    	trSelectEffect : true, //扩充参数 ：是否需要选中效果
		sAjaxSource : Malone.root() + '/sysmanage/userConfig/userListAjax',
		columns : ["usid","usacct","usname","sysdept.dpname","usphone",
	   		   {
	   			"mDataProp": "ustate",
	   			"bSearchable": true,
	   			"bStorable": true,
	   			"fnRender": function (obj) {
	   				if(obj.aData.ustate==1){
	   					return "<span class='label label-success'>有效</span>";
	   				}else{
	   					return "<span class='label label-warning'>无效</span>";
	   				}
	   			}
	   		   }
	   	]
	});
})
var _UserConfig = {
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
	//修改用户
	updateUser : function(){
		var ids = _UserConfig.getSelectedData();
		if(ids.length==0){
			$.message_box({level:'error',content:'请从下面列表中选择一位用户进行修改'});
			return;
		}
		ids = ids.split(',');
		if(ids.length > 1){
			$.message_box({level:'error',content:'只能选择一位用户进行修改'});
		}else{
			window.location.href = Malone.root() + "/sysmanage/userConfig/userEdit?usid=" + ids;
		}
	},
	//删除用户
	deleteUser : function(){
		var ids = _UserConfig.getSelectedData();
		if(ids.length==0){
			$.message_box({level:'error',content:'请从下面列表中选择一位用户进行删除'});
			return;
		}
		$.confirm({title:'WARN',content:'确定要删除选中的用户吗？',callback:function(){
			$.ajax({
		        type: "POST",
		        url: Malone.root()+ "/sysmanage/userConfig/deleteUser?tt="+new Date(),
		        cache: false,
		        contentType: "application/json; charset=utf-8",
		        data:$.toJSON({'usids':ids}),
		        dataType:'json',
		        beforeSend:beforeSend,
		        async:false,
		        success: function(msg){
		        	if(msg.resultCode==0){
		        		$.alert({title:'success',content:'用户删除成功'});
		        		dtable.fnReloadAjax();
		        	}else{
		        		$.alert({title:'error',content:'用户删除失败'});
		        	}
				}
			});
		}});
	},
	//用户权限分配
	privUser : function(){
		var ids = _UserConfig.getSelectedData();
		if(ids.length==0){
			$.message_box({level:'error',content:'请从下面列表中选择一位用户分配权限'});
			return;
		}
		ids = ids.split(',');
		if(ids.length > 1){
			$.message_box({level:'error',content:'只能选择一位用户分配权限'});
		}else{
			window.open(Malone.root() + "/sysmanage/userConfig/userPrivilege?usid=" + ids);
		}
	}
}