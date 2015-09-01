/**
 * 前台公共脚本封装类
 * Vesion 1.0
 * @author pengl
 */
(function(Malone){
	Malone.verify = {};
	/** 获取项目根路径 **/
	Malone.root = function(){
		var curWwwPath = window.document.location.href;
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		var localhostPath = curWwwPath.substring(0,pos);
		var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		return (localhostPath + projectName);
	}
	/** 文本域非空 带错误样式 **/
	Malone.verify.notNull = function(_this){
		var o = $(_this);
		if(o.val().trim().length==0){
			o.parent().parent().addClass('error');
		}else{
			o.parent().parent().removeClass('error');
		}
	}
	/** 手机号码验证 **/
	Malone.verify.isPhone = function(phone){
		var reg=/^(13[0-9]|15[1|0|2|3|5|6|7|8|9]|14[5|7]|18[0|1|2|3|5|8|6|9|7])\d{8}$/;
		if(!reg.test(phone)){
			return false;
		}else{
			return true;
		}
	}
	/** 邮箱验证 **/
	Malone.verify.isEmail = function(email){
		var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(!reg.test(email)){
			return "请输入有效的E_mail";
		}else{
			return "0";
		}
	}
	/** 验证是否全部中文/包含中文/全部英文 **/
	Malone.verify.isChn = function(str,model){
		var reg;
		if(model=='allChinese'){
			reg = /^[\u4E00-\u9FA5]+$/; //全都是汉字
		}else if(model=='hasChinese'){
			reg = /.*[\u4e00-\u9fa5]+.*$/; //含有一个或多个汉字
		}else if(model=='allEnglish'){
			reg = /^[A-Za-z]+$/; //全都是英文
		}else{
			alert('isChn函数缺少参数');
			return;
		}
		if(reg.test(str)){
		    return true;
		}
		return false; //否则返回false
	}
	/** 验证是否是整数 **/
	Malone.verify.isInteger = function(str){
		var reg =/^[-]?\d*$/g ;
		if(!reg.test(str)){
			return "请输入有效的整数";
		}else{
			return "0";
		}
	}
	/** 过滤html元素 **/
	Malone.removeHTMLTag = function(str){
		 str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
         str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
         //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
         str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
         return str;
	}
	/** 图形验证码更换 **/
	Malone.kaptchaImage = function(imageId){
		$('#'+imageId).hide().attr('src', Malone.root()+'/kaptcha/kaptcha-image?' + Math.floor(Math.random()*100) ).fadeIn();
	}
	/** 
	 * json 数据压缩转移 
	 * 3：压缩并转义
	 * 4：去除转义
	 * **/
	Malone.yasuo = function(text , i){
		if (ii == 1 || ii == 3) {
			text = text.split("\n").join(" ");
			var t = [];
			var inString = false;
			for ( var i = 0, len = text.length; i < len; i++) {
				var c = text.charAt(i);
				if (inString && c === inString) {
					// TODO: \\"
					if (text.charAt(i - 1) !== '\\') {
						inString = false;
					}
				} else if (!inString && (c === '"' || c === "'")) {
					inString = c;
				} else if (!inString && (c === ' ' || c === "\t")) {
					c = '';
				}
				t.push(c);
			}
			text = t.join('');
		}
		if (ii == 2 || ii == 3) {
			text = text.replace(/\\/g, "\\\\").replace(/\"/g, "\\\"");
		}
		if (ii == 4) {
			text = text.replace(/\\\\/g, "\\").replace(/\\\"/g, '\"');
		}
		txtA.value = text;
	}
})(window.Malone=window.Malone||{});
/** 序列化表单 转换成对象 * */
$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};
/** 发送ajax请求前 **/
function beforeSend(){
	$.alert({title:'数据正在处理中...',content:"<img src='"+Malone.root()+"/comm/charisma/img/ajax-loaders/ajax-loader-6.gif'/>"});
}
/** 取消按钮 **/
function cancelEdit(url){
	if (confirm("确定要放弃操作，返回列表页面吗")) {
		window.location.href = url;
	}
}
/** 去除空格 **/
String.prototype.trim = function(){
    if(this==null||this==""){
        return "";
    }else{
        var local = "";
        for(var i=0;i<this.length;i++){
            if(this.charCodeAt(i)!=32){
                local += this.charAt(i);
            }
        }
        return local;
    }
}
/** 拼接字符串 **/
function StringBuffer(){
	this._string_ = new Array();
}
StringBuffer.prototype.append = function(str){
	this._string_.push(str);
}
StringBuffer.prototype.toString = function(){
	return this._string_.join('');
}
/**
 * 日期格式化
 * @param format
 * @returns
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
} 
/**
 * 计算字符串长度
 */
String.prototype.strLen = function() {
    var len = 0;
    for (var i = 0; i < this.length; i++) {
        if (this.charCodeAt(i) > 255 || this.charCodeAt(i) < 0) len += 2; else len ++;
    }
    return len;
}
/**
 * 判断某个字符是否是汉字
 */
String.prototype.isCHS = function(i){
    if (this.charCodeAt(i) > 255 || this.charCodeAt(i) < 0)
        return true;
    else
        return false;
}
/**
 * 截取字符串（从start字节到end字节）
 */
String.prototype.subCHString = function(start, end){
    var len = 0;
    var str = "";
    this.strToChars();
    for (var i = 0; i < this.length; i++) {
        if(this.charsArray[i][1])
            len += 2;
        else
            len++;
        if (end < len)
            return str;
        else if (start < len)
            str += this.charsArray[i][0];
    }
    return str;
}
/**
 * 截取字符串（从start字节截取length个字节）
 */
String.prototype.subCHStr = function(start, length){
    return this.subCHString(start, start + length);
}