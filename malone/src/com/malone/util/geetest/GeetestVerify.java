/**
 * 
 */
package com.malone.util.geetest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * @author pengl
 * 极验验证
 * 2015-6-18上午9:04:26
 */
@Component("geetestVerify")
public class GeetestVerify {
	
	public boolean isValidGeetest(HttpServletRequest request){
		
		GeetestLib geetest = GeetestLib.getGtSession(request);
		int gt_server_status_code = GeetestLib
				.getGtServerStatusSession(request);

		String gtResult = GeetestLib.fail_res;

		if (gt_server_status_code == 1) {
			try{
				gtResult = geetest.enhencedValidateRequest(request);
			}catch (Exception e) {
				gtResult = "fail";
			}
			
		}
		
		if("success".equals(gtResult)){
			return true;
		}else{
			return false;
		}
		
	}
	
}
