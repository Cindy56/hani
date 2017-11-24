/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.todo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.common.config.Global;
import com.game.common.persistence.Page;
import com.game.common.utils.StringUtils;
import com.game.common.web.BaseController;
import com.game.modules.todo.entity.TodoTask;
import com.game.modules.todo.service.TodoTaskService;

/**
 * 待办任务管理Controller
 * @author freeman
 * @version 2017-11-23
 */
@Controller
@RequestMapping(value = "${adminPath}/todo/todoTask")
public class TodoTaskController extends BaseController {

	@Autowired
	private TodoTaskService todoTaskService;
	
	@ModelAttribute
	public TodoTask get(@RequestParam(required=false) String id) {
		TodoTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = todoTaskService.get(id);
		}
		if (entity == null){
			entity = new TodoTask();
		}
		return entity;
	}
	
	@RequiresPermissions("todo:todoTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(TodoTask todoTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TodoTask> page = todoTaskService.findPage(new Page<TodoTask>(request, response), todoTask); 
		model.addAttribute("page", page);
		return "modules/todo/todoTaskList";
	}

	@RequiresPermissions("todo:todoTask:view")
	@RequestMapping(value = "form")
	public String form(TodoTask todoTask, Model model) {
		model.addAttribute("todoTask", todoTask);
		return "modules/todo/todoTaskForm";
	}

	@RequiresPermissions("todo:todoTask:edit")
	@RequestMapping(value = "save")
	public String save(TodoTask todoTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, todoTask)){
			return form(todoTask, model);
		}
		todoTaskService.save(todoTask);
		addMessage(redirectAttributes, "保存待办任务成功");
		return "redirect:"+Global.getAdminPath()+"/todo/todoTask/?repage";
	}
	
	@RequiresPermissions("todo:todoTask:edit")
	@RequestMapping(value = "delete")
	public String delete(TodoTask todoTask, RedirectAttributes redirectAttributes) {
		todoTaskService.delete(todoTask);
		addMessage(redirectAttributes, "删除待办任务成功");
		return "redirect:"+Global.getAdminPath()+"/todo/todoTask/?repage";
	}

}