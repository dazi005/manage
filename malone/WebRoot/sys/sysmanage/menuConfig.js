var treeObj;
$(function(){
	_MenuConfig.initMenuTree();
})
var newCount = 1;
var _MenuConfig = {
	initMenuTree : function(){
		//初始化菜单树
		_Ztree.init({
			treeid : 'mutree',
			zNodes : window.allMenus,
			view : {
				expandSpeed : "normal",
				addHoverDom :  _MenuConfig.addHoverDom, //当鼠标移动到节点上时
		        removeHoverDom : _MenuConfig.removeHoverDom//当鼠标移开节点上时
			},
			data : {
				key : {
					title : 'muname',
					name : 'muname',
					children : 'childrenMenus'
				}
			}
		});
		treeObj = $.fn.zTree.getZTreeObj("mutree");
		//查找二级菜单
		var levelTwoNodes = treeObj.getNodesByFilter(function (node) { return node.mulevel == 2 });
		//展开二级节点
		for(var i=0;i<levelTwoNodes.length;i++){
			treeObj.expandNode(levelTwoNodes[i],true,false,true);
		}
	},
	//鼠标放上节点上触发函数
	addHoverDom : function(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        //判断当前节点是否处于编辑状态  或者 当前节点下已经存在增删改的图标
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        //拼装增删改字符串
        var operStr = new StringBuffer();
        operStr.append("<span class='button remove' id='removeBtn_" + treeNode.tId + "'></span>");
        operStr.append("<span class='button add' id='addBtn_" + treeNode.tId + "'></span>");
        operStr.append("<span class='button edit' id='editBtn_" + treeNode.tId + "'></span>");
        sObj.after(operStr.toString());
        var addBtn = $("#addBtn_"+treeNode.tId);
        var deleteBtn = $("#removeBtn_"+treeNode.tId);
        var editBtn = $("#editBtn_"+treeNode.tId);
        if (addBtn) 
        	addBtn.bind("click", function(event){event.stopPropagation();_MenuConfig.showAddMenu(treeNode)});
        if (deleteBtn)
        	deleteBtn.bind("click", function(event){event.stopPropagation();_MenuConfig.deleteMenu(treeNode)});
        if (editBtn)
        	editBtn.bind("click", function(event){event.stopPropagation();_MenuConfig.editMenu(treeNode)});
    },
    //鼠标移开节点上触发函数
    removeHoverDom : function(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
        $("#removeBtn_"+treeNode.tId).unbind().remove();
        $("#editBtn_"+treeNode.tId).unbind().remove();
    },
    //弹出新增菜单层
    showAddMenu : function(treeNode){
    	var eidtIndexUrl = Malone.root() + "/sysmanage/menuConfig/menuAdd?muid=" + treeNode.muid + "&muname=" + treeNode.muname + "&mulevel=" + treeNode.mulevel;
    	$.ajaxAlert({title:'新增菜单',ajaxUrl:eidtIndexUrl});
    	return false;
    },
    //删除菜单
    deleteMenu : function(treeNode){
    	if(treeNode.isParent){
    		$.message_box({level:'error',content:'请先删除节点下的子菜单'});
    		return;
    	}
    	$.confirm({title:'WARN',content:'确定要删除选中的菜单吗？',callback:function(){
			$.ajax({
		        type: "POST",
		        url: Malone.root()+ "/sysmanage/menuConfig/deleteMenu?tt="+new Date(),
		        cache: false,
		        contentType: "application/json; charset=utf-8",
		        data:$.toJSON({'muid':treeNode.muid}),
		        dataType:'json',
		        beforeSend:beforeSend,
		        async:false,
		        success: function(msg){
		        	if(msg.resultCode==0){
		        		$.alert({title:'success',content:'菜单删除成功'});
		        		_MenuConfig.initMenuTree();
		        	}else{
		        		$.alert({title:'error',content:'菜单删除失败'});
		        	}
				}
			});
		}});
    },
    //编辑菜单
    editMenu : function(treeNode){
    	var eidtIndexUrl = Malone.root() + "/sysmanage/menuConfig/menuEdit?muid=" + treeNode.muid;
    	$.ajaxAlert({title:'编辑菜单',ajaxUrl:eidtIndexUrl});
    	return false;
    }
}