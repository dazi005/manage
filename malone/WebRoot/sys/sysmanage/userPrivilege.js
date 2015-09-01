var treeObj ;
$(function(){
	//初始化角色分配选择框
	$('#roleSelect').bootstrapDualListbox({
		nonSelectedListLabel: '可分配角色',
		selectedListLabel: '已分配角色',
		bootstrap2Compatible : true,
		filterTextClear : '显示全部',
		filterPlaceHolder : '过滤',
		infoText : '总共： {0}',
		infoTextEmpty : '总共：0',
		helperSelectNamePostfix : '_roleHelper',
		infoTextFiltered: '<span class="label label-warning">筛选出</span> {0} 从 {1}'
	});
	//初始化菜单树
	_Ztree.init({
		treeid : 'mutree',
		zNodes : window.allDatacs,
		view : {
			expandSpeed : "normal",
			addHoverDom :  null, //当鼠标移动到节点上时
	        removeHoverDom : null//当鼠标移开节点上时
		},
		data : {
			key : {
				title : 'dtcsmemo',
				name : 'dtcsmemo',
				children : 'childrenDatacs'
			}
		},
		check : {
			chkboxType: { "Y": "", "N": "s" }
		},
		callback : {
			//点击ckbox 切换可点和不可点
			beforeCheck : function(treeId, treeNode){
				//如果选中节点 则使其子节点全部置为未选中状态，且不可点状态
				if(!treeNode.checked){
					if(treeNode.isParent){
						_UserPrivilege.setCanotCheck(treeNode);
					}
				}else{
					//全部置为可选状态
					_UserPrivilege.setCanCheck(treeNode);
				}
			}
		}
	});
	/**
	 * 除了根节点，其它节点全部置为不可点状态
	 */
	treeObj = $.fn.zTree.getZTreeObj("mutree");
	var checkedArray = treeObj.getCheckedNodes(true); //获取选中的节点集合
	//展开选中的节点
	for(var i=0;i<checkedArray.length;i++){
		if(checkedArray[i].isParent){
			treeObj.expandNode(checkedArray[i], true, false, true);
		}else{
			_UserPrivilege.expandNode(checkedArray[i]);
		}
		
	}
	//获取根节点的子节点
	var childrenNodes = _UserPrivilege.getTreeRoot().childrenDatacs;
	//子节点全部置为不可点状态
	for(var i=0; i<childrenNodes.length; i++){
    	treeObj.setChkDisabled(childrenNodes[i], true, false, true);
    }
})
 

var _UserPrivilege = {
	//根据叶子节点 展开其父节点
	expandNode : function(treeNode){
		var pnode = treeNode.getParentNode(); //获取父节点
		if(pnode.level != '0'){
			//如果不是根节点
			treeObj.expandNode(pnode, true, false, true);
			_UserPrivilege.expandNode(pnode);
		}else{
			treeObj.expandNode(pnode, true, false, true);
		}
	},
	//获取根节点
	getTreeRoot : function(){
		return treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);
	},
	//将节点下所有节点全部置为可选状态且未选中
	setCanCheck : function(node){
		if(node.isParent){
			var childrenNodes = node.childrenDatacs;
			for(var i=0; i<childrenNodes.length; i++){
				treeObj.setChkDisabled(childrenNodes[i], false, false, true);
		    	treeObj.checkNode(childrenNodes[i], false, true);
		    }
		}
	},
	//将节点下所有节点全部置为不可选状态且未选中
	setCanotCheck : function(node){
		if(node.isParent){
			var childrenNodes = node.childrenDatacs;
			for(var i=0; i<childrenNodes.length; i++){
				var node = childrenNodes[i];
				treeObj.setChkDisabled(node, false, false, true);
				treeObj.checkNode(node, false, true);
				treeObj.setChkDisabled(childrenNodes[i], true, false, true);
		    }
		}
	},
	//保存权限分配
	saveChange : function(){
		var selectedRoles = [];
		$("select[name='_roleHelper2'] option").each(function(i,t){
			selectedRoles.push($(t).val());
		});
		var checkedArray = treeObj.getCheckedNodes(true); //获取选中的节点集合
		var ckArray = [];
		for(var i=0;i<checkedArray.length;i++){
			ckArray.push(checkedArray[i].dtcsid);
		}
		var roles = selectedRoles.join(",");
		var datas = ckArray.join(",");
		$.ajax({
	        type: "POST",
	        url: Malone.root()+ "/sysmanage/userConfig/saveUserPrivilege?tt="+new Date(),
	        cache: false,
	        data:$.toJSON({roles : roles,datas : datas,usid : $("#usid").val()}),
	        contentType: "application/json; charset=utf-8",
	        dataType:'json',
	        beforeSend:beforeSend,
	        async:false,
	        success: function(msg){
	        	if(msg.resultCode==0){
	        		$.alert({title:'success',content:'用户权限分配成功'});
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