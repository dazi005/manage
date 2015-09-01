document.write("<script language='javascript' charset='utf-8' src='" + Malone.root() + "/sys/secret/geetest.3.0.16.js'></script>");
/**
 * 加载验证控件 Gt.loadGt({product:'float',id:'xx',onSuccess:aaa})
 * aJax验证  Gt.gtAjaxVerify();
 * 刷新验证  Gt.reflesh();
 */
var Gt = {
	config : {},
	//加载极验验证
	loadGt : function(config){
		Gt.config = config;
		//先验证服务状态并加载控件
		Gt.gtState();
		
	},
	//验证服务状态并加载控件
	gtState : function(){
		$.ajax({
			url : Malone.root() + "/geetest/GeetestStateServlet",
			type : "get",
			dataType : 'JSON',
			success : function(result) {
				if (result.success) {
					window.gt_captcha_obj = new window.Geetest({
						gt : result.gt,
						challenge : result.challenge,
						product : Gt.config.product
					});
					gt_captcha_obj.appendTo("#" + Gt.config.id);
					gt_captcha_obj.onSuccess(function() {
						//ajax验证
						//Gt.gtAjaxVerify();
						//前端验证成功，回调函数
						Gt.config.onSuccess();
					});

				} else {
					$("#" + Gt.config.id).html('验证控件加载失败,请刷新页面重试');
				}
			}
		})
	},
	//Ajax验证
	gtAjaxVerify : function(){
		var vResult;
		$.ajax({
			url : Malone.root() + "/geetest/GeetestVerifyServlet.do",//todo:set the servelet of your own
			type : "post",
			data : window.gt_captcha_obj.getValidate(),
			async : false,
			dataType : 'text',
			success : function(sdk_result) {
				vResult = sdk_result;
			}
		});
		return vResult;
	},
	//刷新验证
	reflesh : function(){
		window.gt_captcha_obj.refresh();
	} 
}