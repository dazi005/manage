$(function(){
	var local = String(window.location);
	var flag = false; //标识当前链接是否属于左侧菜单中的链接
	//加载菜单
	_Menu.getTreeMenu();
	//选中当前菜单
	$('ul.main-menu li a').each(function(){
		var _this = $(this);
		if($(_this)[0].href==local){
			_this.parent().addClass('active');
			flag = true;
			//如果当前菜单有父节点，展开其父节点
			_this.parent().parent().prev().find('.icon-plus-sign').attr('class','icon-minus-sign')
		}
	});
	if(!flag){
		//当前菜单不属于左侧菜单  表示当前菜单是按钮级或模块级
		//重新对比左侧连接 对比前一级别是否相同
		$('ul.main-menu li a').each(function(){
			if(compare($($(this))[0].href,local))
				$(this).parent().addClass('active');
		});
	}
	
	$('.accordion li.active:first').parents('ul').slideDown();
})
var menuStr = new StringBuffer();
menuStr.append("<li class='nav-header hidden-tablet'>主菜单</li>");
//获取菜单数据
var _Menu = {
	//获取菜单数据
	getTreeMenu : function(){
		$.ajax({
	        type: "POST",
	        url: Malone.root()+ "/sys/treeMenu?tt="+new Date(),
	        cache: false,
	        contentType: "application/json; charset=utf-8",
	        dataType:'json',
	        async:false,
	        success: function(msg){
	        	_Menu.parseMenuJson(msg);
	        	$("#menuUl").html(menuStr.toString());
			}
		});
	},
	
	parseMenuJson : function(menuJson){
		for(var i=0; i<menuJson.length; i++){
			var upmuid = menuJson[i].upmuid;
			if(menuJson[i].childrenMenus){
				if(upmuid == '0'){
					_Menu.parseMenuJson(menuJson[i].childrenMenus);
				}else{
					menuStr.append("<li class='accordion'><a href='javascript:void(0);'><i class='"+menuJson[i].muicon+"'></i><span class='hidden-tablet'> " + menuJson[i].muname + "</span><span style='float: right;'><i class='icon-plus-sign'></i></span></a>");
					menuStr.append("<ul class='nav nav-tabs nav-stacked'>");
					_Menu.parseMenuJson(menuJson[i].childrenMenus);
					menuStr.append("</ul></li>");
				}
			}else{
				if(upmuid == '0'){
					continue;
				}
				menuStr.append("<li muid="+menuJson[i].muid+"><a class='ajax-link' href='"+Malone.root()+menuJson[i].mulink+"'><i class='"+menuJson[i].muicon+"'></i><span class='hidden-tablet'> " + menuJson[i].muname + "</span></a></li>");
			}
		}
	}
}
//解析菜单，对比菜单中的三级菜单
function compare(cururl , localurl){
	cururl = cururl.substring(0,cururl.lastIndexOf('/'));
	localurl = localurl.substring(0,localurl.lastIndexOf('/'));
	if(cururl == localurl){
		return true;
	}else{
		return false;
	}
}

