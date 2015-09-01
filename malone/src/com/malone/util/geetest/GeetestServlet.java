package com.malone.util.geetest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author pengl
 * 2015-6-18下午5:22:36
 */
@Controller
@RequestMapping("/geetest")
public class GeetestServlet{
	
	@Resource(name="geetestVerify")
	private GeetestVerify geetestVerify;
	
	@RequestMapping(value = "/GeetestStateServlet",method=RequestMethod.GET)
	@ResponseBody
	public String GeetestStateServlet(HttpServletRequest request){
		GeetestLib gtSdk = new GeetestLib();
		gtSdk.setCaptchaId(GeetestConfig.getCaptcha_id());
		gtSdk.setPrivateKey(GeetestConfig.getPrivate_key());

		gtSdk.setGtSession(request);

		String resStr = "{}";

		if (gtSdk.preProcess() == 1) {
			// 服务状态正常
			resStr = gtSdk.getSuccessPreProcessRes();
			gtSdk.setGtServerStatusSession(request, 1);

		} else {
			// 服务dwon掉
			resStr = gtSdk.getFailPreProcessRes();
			gtSdk.setGtServerStatusSession(request, 0);
		}
		return resStr;
	}
	
	@RequestMapping(value = "/GeetestVerifyServlet",method=RequestMethod.POST)
	@ResponseBody
	public String GeetestVerifyServlet(HttpServletRequest request){
		if(geetestVerify.isValidGeetest(request)){
			return "0";
		}else{
			return "1";
		}
	}

}
