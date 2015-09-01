var dtable;
$(function(){
	_LogConfig.setSearchParam();
	_LogConfig.loadTable();
    $(".datepicker").change(function(){
    	_LogConfig.loadTable();
	});
    $("input[name='opertype']").change(function(){
    	_LogConfig.loadTable();
	});
})


var _LogConfig = {
	//设置搜索条件
	setSearchParam : function(){
		$("#startTime").val($.cookie("startTime"));
		$("#endTime").val($.cookie("endTime"));
		var opertype = $.cookie("opertype");
		if(opertype){
			opertype =  opertype.split(",")
			for(var i =0 ; i< opertype.length; i++){
				 var allcheck = $("input:checkbox[value='"+opertype[i]+"']").attr('checked','true');
				 $.uniform.update(allcheck);
			}
		}
	},
	//获取搜索条件
	getSearchParam : function(){
		var ckbox = new Array();
		$("input[name='opertype']:checked").each(function(){
			ckbox.push($(this).val());
		});
		var data = {
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val(),
			opertype : ckbox.join(",")
		};
		return data;
	},
	//加载表格
	loadTable : function(){
		var param  = _LogConfig.getSearchParam();
		$.cookie("opertype",param.opertype);
		$.cookie("startTime",param.startTime);
		$.cookie("endTime",param.endTime);
		dtable = $("#logTable").showtable({
	    	trSelectEffect : true, //扩充参数 ：是否需要选中效果
			sAjaxSource : Malone.root() + '/sysmanage/logConfig/logListAjax?t'+new Date().getTime(),
			columns : ["logid","modular","opertype",
			   {
	   			 "mDataProp": "opercontext",
	   			 "bSearchable": false,
	   			 "bStorable": false,
	   			 "fnRender": function (obj) {
	   				 var opercontext = obj.aData.opercontext;
	   				 var result = "";
	   				 if(opercontext != null  && opercontext.length > 13){
						result = opercontext.substr(0 ,13) + "...";
					 }else{
						result =  opercontext;
					 }
	   				return "<span title='"+opercontext+"'>"+result+"</span>";
	   			 }
	   		   },{
	     			 "mDataProp": "operdate",
	       			 "bSearchable": false,
	       			 "bStorable": true,
	       			 "fnRender": function (obj) {
	       				 var operdate = new Date(obj.aData.operdate);
	       				return operdate.format("yyyy-MM-dd hh:mm:ss");
	       			 }
	       		   },"ostype","browser","ipaddress"
		   	],
		   	"aaSorting" : [[4, "desc"]],
		   	oLanguage : {
		   		"sSearch" : "操作内容"
		   	},
		   	data : param,
		   	fnDrawCallback : _LogConfig.drawCallback,
		   	bServerSide : true,  //服务端分页
			bDestroy:true
		});
	},
	drawCallback : function(){
		//渲染 title
	   $('#logTable span[title]').tooltip();
	  /*  $('#logTable tbody tr').each( function() {
			var nTds = $('td', this);
			var opercontext = $(nTds[3]).find('span').attr('title');
			this.setAttribute( 'title', '操作内容：' + opercontext );
		});*/
	},
	//清空时间框
	resertTime : function(){
		$("#startTime").val("");
		$("#endTime").val("");
		$("#endTime").change();
	}
}
