package com.game.manager.modules.sys.security;

import javax.servlet.http.HttpServletRequest;

import com.game.manager.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.game.manager.modules.sys.utils.UserUtils;
public class MobileUtils {
	/**
	 * 是否是Ajax异步请求
	 * @param request
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		
		String accept = request.getHeader("accept");
		String xRequestedWith = request.getHeader("X-Requested-With");
		Principal principal = UserUtils.getPrincipal();

		// 如果是异步请求或是手机端，则直接返回信息
		return ((accept != null && accept.indexOf("application/json") != -1 
			|| (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
			|| (principal != null && principal.isMobileLogin())));
	}
}
