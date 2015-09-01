package com.malone.util.geetest;

/**
 * 使用Get的方式返回：challenge和capthca_id 此方式以实现前后端完全分离的开发模式，如果是多实例，可以以此为模板，多定义几个GeetestConfig文件。分别初始化即可 
 * 官网地址：http://www.geetest.com/
 * @author zheng
 *
 */
public class GeetestConfig {

	// TODO: replace the these two string with your own captcha's id/key
	private static final String captcha_id = "60ec939f2047dc35ed24490a3e3c26bf";
	private static final String private_key = "4a85cbc160df643b1df094e97ade7226";

	public static final String getCaptcha_id() {
		return captcha_id;
	}

	public static final String getPrivate_key() {
		return private_key;
	}

}
