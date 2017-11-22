package com.game.manager.modules.sys.listener;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContextException;
import org.springframework.web.context.WebApplicationContext;

import com.game.common.persistence.DataEntityUserService;
import com.game.manager.modules.sys.service.SystemService;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Loader;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
/*		try {
			ClassPool pool = ClassPool.getDefault();  
			Loader loader = new Loader(pool);
			ClassClassPath classPath = new ClassClassPath(DataEntityUser.class);  
			pool.insertClassPath(classPath);  
			System.out.println("=======classPath=======" + classPath);
			
//			CtClass baseEntityClass = pool.getCtClass(DataEntityUser.class.getName());
			Class baseEntityClass = loader.loadClass(DataEntityUser.class.getName());
//			baseEntityClass.defrost();
//			baseEntityClass.freeze();
			baseEntityClass.getDeclaredMethod("getCurrentUser")
			CtMethod methodGetCurrentUser = baseEntityClass.getDeclaredMethod("getCurrentUser");
			//{if(null==currentUser){currentUser=com.game.manager.modules.sys.utils.UserUtils.getUser();}return currentUser;}
			methodGetCurrentUser.setBody("{return com.game.manager.modules.sys.utils.UserUtils.getUser();}");
			baseEntityClass.writeFile();
			baseEntityClass.toClass();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationContextException("Error:重写getCurrentUser()方法失败");
		}*/
		
		if (!SystemService.printKeyLoadMessage()){
			throw new ApplicationContextException("Error:获取Key加载信息失败");
		}
		return super.initWebApplicationContext(servletContext);
	}
}
