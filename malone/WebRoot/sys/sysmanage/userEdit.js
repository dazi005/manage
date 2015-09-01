$(function(){
	//初始化部门树
	_Ztree.init({
		treeid : 'dptree',
		zNodes : _UserEdit.getTreeDept(),
		view : {
			expandSpeed : "normal",
			addHoverDom :  null, //当鼠标移动到节点上时
	        removeHoverDom : null//当鼠标移开节点上时
		},
		data : {
			key : {
				title : 'dpname',
				name : 'dpname',
				children : 'childrenDepts'
			}
		},
		callback: {
        	// 节点点击之前调用 如果返回 false，zTree 将不会选中节点，也无法触发 onClick 事件回调函数
            beforeClick: function(treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj(_Ztree.initpar.treeid);
                if (treeNode.isParent) {
                	//如果是父节点 点击折叠节点
                    zTree.expandNode(treeNode);
                    return false;
                } else {
                	$("#dpname").val(treeNode.dpname);
                	$("#dpid").val(treeNode.dpid);
                	_UserEdit.hideDpTree();
                    return true;
                }
            },
            onCheck : function(event, treeId, treeNode){
            	if(treeNode.checked){
            		$("#dpname").val(treeNode.dpname);
                	$("#dpid").val(treeNode.dpid);
                	_UserEdit.hideDpTree();
            	}
            }
        },
		check : {
			chkStyle: "radio",
			radioType: "all"
		}
	})
	//表单验证
	$("#userEdit").validate({
		submitHandler : function(form){
			_UserEdit.saveEdit();
		},
		rules : {
			usacct : "required",
			usname : "required",
			usphone : {
				required: false,
				isTel : true
			},
			usemail : {
				required: false,
				email : true
			},
			uscardno : {
				required : false,
				isIdCardNo : true
			}
		},
		messages : {
			usacct : "请输入登录帐号",
			usname : "请输入用户昵称"
		}
	})
})
var _UserEdit = {
	//获取部门树数据
	getTreeDept : function(){
		var depts;
		$.ajax({
	        type: "POST",
	        url: Malone.root()+ "/sysmanage/treeDept?tt="+new Date(),
	        cache: false,
	        contentType: "application/json; charset=utf-8",
	        dataType:'json',
	        async:false,
	        success: function(deptJson){
	        	depts = deptJson;
			}
		});
		return depts;
	},
    showDpTree : function(){
    	var dpObj = $("#dpname");
		var dpOffset = $("#dpname").offset();
		$("#dpContent").css({left:dpOffset.left + "px", top:dpOffset.top + dpObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", _UserEdit.onBodyDown);
    },
    hideDpTree : function(){
    	$("#dpContent").fadeOut("fast");
		$("body").unbind("mousedown", _UserEdit.onBodyDown);
    },
    onBodyDown : function(event) {
		if (!(event.target.id == "dpContent" || $(event.target).parents("#dpContent").length>0)) {
			_UserEdit.hideDpTree();
		}
	},
	saveEdit : function(){
		$("#saveButton").button('loading');//执行等待状态切换操作
		var ustateCkbox = $("input[class='iphone-toggle']:first").attr('checked');
		var loginagainCkbox = $("input[class='iphone-toggle']:last").attr('checked');
		if(ustateCkbox=='checked'){
			$("#ustate").val('1');
		}else{
			$("#ustate").val('-1');
		}
		if(loginagainCkbox=='checked'){
			$("#loginagain").val('1');
		}else{
			$("#loginagain").val('-1');
		}
		var formstr = $("#userEdit").serializeObject();
		$.ajax({
	        type: "POST",
	        url: Malone.root()+ "/sysmanage/userConfig/saveUser",
	        cache: false,
	        data:formstr,
	        beforeSend:beforeSend,
	        async:false,
	        success: function(msg){
	        	if(msg.resultCode==0){
	        		$.alert({title:'success',content:'用户信息保存成功'});
	        		$("#saveButton").button('reset');
	        	}else{
	        		$.alert({title:'error',content:msg.resultMsg});
	        		$("#saveButton").button('reset');
	        	}
			}
		});
	}
}