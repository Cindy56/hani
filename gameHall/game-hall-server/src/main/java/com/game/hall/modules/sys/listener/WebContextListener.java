package com.game.hall.modules.sys.listener;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContextException;
import org.springframework.web.context.WebApplicationContext;

import com.game.hall.modules.sys.service.SystemService;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (!SystemService.printKeyLoadMessage()){
			throw new ApplicationContextException("Error:获取Key加载信息失败");
		}
		return super.initWebApplicationContext(servletContext);
	}
}
