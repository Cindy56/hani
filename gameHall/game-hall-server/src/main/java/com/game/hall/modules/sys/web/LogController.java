/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.game.common.persistence.Page;
import com.game.common.web.BaseController;
import com.game.hall.modules.sys.service.LogService;
import com.game.modules.sys.entity.Log;

/**
 * 日志Controller
 * 
 * @author ThinkGem
 * @version 2013-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;

	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = { "list", "" })
	public String list(Log log, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Log> page = logService.findPage(new Page<Log>(request, response), log);
		model.addAttribute("page", page);
		return "modules/sys/logList";
	}

}
