$.alert = function(options){
	var _html = "<div class='modal-header'><button type='button' class='close' data-dismiss='modal'>&times;</button><h3>"+options.title+"</h3></div><div class='modal-body'>"+options.content+"</div><div class='modal-footer'><a href='javascript:void(0);' class='btn' data-dismiss='modal'>关闭</a></div>";
	$("#comm_modal").html(_html);
	$('#comm_modal').modal('show');
}
$.confirm = function(options){
	var _html = "<div class='modal-header'><button type='button' class='close' data-dismiss='modal'>&times;</button><h3>"+options.title+"</h3></div><div class='modal-body'>"+options.content+"</div><div class='modal-footer'><a href='javascript:void(0);' id='confirmBtn' class='btn'>确定</a><a href='javascript:void(0);' class='btn' data-dismiss='modal'>取消</a></div>";
	$("#comm_modal").html(_html);
	$("#confirmBtn").click(options.callback);
	$('#comm_modal').modal('show');
}
$.ajaxAlert = function(options){
	var _html = "<div class='modal-header'><button type='button' class='close' data-dismiss='modal'>&times;</button><h3>"+options.title+"</h3></div><div class='modal-body' id='modalBody'></div><div class='modal-footer'><a href='javascript:void(0);' class='btn' data-dismiss='modal'>关闭</a></div>";
	$("#comm_modal").html(_html);
	$("#modalBody").load(options.ajaxUrl,function(){
		docReady(); //重新渲染
		$('#comm_modal').modal('show');
	});
}

/**
 * 消息框弹出
 * options 【level , time , content】
 */
var timer ;
$.message_box = function(options){
	var _this = $("#comm_box");
	var level = options.level ? options.level : 'info'; //消息框级别 【info/success/error/warn】
	var time = options.time ? options.time : 2500; //消息框定时关闭 单位毫秒
	var _html = "<button class='close' type='button'>×</button>";
	if(level == 'info'){
		_this.attr('class','alert alert-block alert-info');
		_html += "<h4>信息</h4>";
	}else if(level == 'warn'){
		_this.attr('class','alert alert-block alert-block');
		_html += "<h4>警告</h4>";
	}else if(level == 'success'){
		_this.attr('class','alert alert-block alert-success');
		_html += "<h4>成功</h4>";
	}else if(level == 'error'){
		_this.attr('class','alert alert-block alert-error');
		_html += "<h4>错误</h4>";
	}
	_html += "<span>"+options.content+"</span>";
	clearTimeout(timer);
	_this.html(_html);
	_this.find('button.close').click(function(){
		_this.hide();
	});
	_this.slideDown();
	timer = setTimeout(function() {
		_this.fadeOut();
    }, time);
}