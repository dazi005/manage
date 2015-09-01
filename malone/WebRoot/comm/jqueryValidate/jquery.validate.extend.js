/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
$.extend($.validator.messages, {
	required: "该字段为必填字段",
	remote: "请修正此栏位",
	email: "请输入有效的电子邮件",
	url: "请输入有效的网址",
	date: "请输入有效的日期",
	dateISO: "请输入有效的日期 (YYYY-MM-DD)",
	number: "请输入正确的数字",
	digits: "只可输入数字",
	creditcard: "请输入有效的信用卡号码",
	equalTo: "你的输入不相同",
	extension: "请输入有效的后缀",
	maxlength: $.validator.format("最多 {0} 个字"),
	minlength: $.validator.format("最少 {0} 个字"),
	rangelength: $.validator.format("请输入长度为 {0} 至 {1} 之間的字串"),
	range: $.validator.format("请输入 {0} 至 {1} 之间的数值"),
	max: $.validator.format("请输入不大于 {0} 的数值"),
	min: $.validator.format("请输入不小于 {0} 的数值")
});
/*
 * 设置默认错误提示风格
 */
$.validator.setDefaults({
	onfocusout:false, //是否在获取焦点时验证
	onkeyup:false,
	onclick:false,
	debug:true,
	showErrors: function(map, list) {
		$.each(list, function(index, error) {
			$.message_box({level:'error',content:error.message,time:1500});
			return false;
		});
	}
});
//手机号码验证   
jQuery.validator.addMethod("phone", function(value, element) {
	var length = value.length;
	var mobile = /^(13[0-9]|15[1|0|2|3|5|6|7|8|9]|14[5|7]|18[0|1|2|3|5|8|6|9|7])\d{8}$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "手机号码格式错误");
//电话号码验证   
jQuery.validator.addMethod("mobile", function(value, element) {
	var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");
//联系电话(手机/电话皆可)验证     
jQuery.validator.addMethod("isTel", function(value,element) {     
    var length = value.length;     
    var mobile = /^(13[0-9]|15[1|0|2|3|5|6|7|8|9]|14[5|7]|18[0|1|2|3|5|8|6|9|7])\d{8}$/;     
    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/g;         
    return this.optional(element) || tel.test(value) || (length==11 && mobile.test(value));     
}, "请正确填写您的联系电话");  
//邮政编码验证   
jQuery.validator.addMethod("zipCode", function(value, element) {
	var tel = /^[0-9]{6}$/;
	return this.optional(element) || (tel.test(value));
}, "邮政编码格式错误");
//身份证号码验证  
jQuery.validator.addMethod("isIdCardNo", function(value, element) {   
  return this.optional(element) || isIdCardNo(value);      
}, "请输入正确的身份证号码");
// QQ号码验证   
jQuery.validator.addMethod("qq", function(value, element) {
	var tel = /^[1-9]\d{4,9}$/;
	return this.optional(element) || (tel.test(value));
}, "qq号码格式错误");

// IP地址验证   
jQuery.validator
		.addMethod(
				"ip",
				function(value, element) {
					var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
					return this.optional(element)
							|| (ip.test(value) && (RegExp.$1 < 256
									&& RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
				}, "Ip地址格式错误");

// 字母和数字的验证   
jQuery.validator.addMethod("chrnum", function(value, element) {
	var chrnum = /^([a-zA-Z0-9]+)$/;
	return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

// 中文的验证   
jQuery.validator.addMethod("chinese", function(value, element) {
	var chinese = /^[\u4e00-\u9fa5]+$/;
	return this.optional(element) || (chinese.test(value));
}, "只能输入中文");

// 下拉框验证   
$.validator.addMethod("selectNone", function(value, element) {
	return value == "";
}, "必须选择一项");
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
/** 验证身份证函数 **/
function isIdCardNo(sId){ 
    var iSum=0 ;
    var info="" ;
    if(!/^\d{17}(\d|x)$/i.test(sId)) 
    	return false; //"你输入的身份证长度或格式错误" 
    sId=sId.replace(/x$/i,"a"); 
    if(aCity[parseInt(sId.substr(0,2))]==null) 
    	return false; //"你的身份证地区非法"
    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
    var d=new Date(sBirthday.replace(/-/g,"/")) ;
    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))
    	return false; //"身份证上的出生日期非法"
    for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
    if(iSum%11!=1) 
    	return false; //"你输入的身份证号非法"
    return true;//aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女") 
}