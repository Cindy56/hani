/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.game.common.persistence.Page;
import com.game.common.service.CrudService;
import com.game.modules.todo.entity.TodoTask;
import com.game.modules.todo.service.TodoTaskService;
import com.game.trade.modules.todo.dao.TodoTaskDao;

/**
 * 待办任务管理Service
 * @author freeman
 * @version 2017-11-23
 */
@Service("todoTaskService")
@Transactional(readOnly = true)
public class TodoTaskServiceImpl extends CrudService<TodoTaskDao, TodoTask> implements TodoTaskService {

	public TodoTask get(String id) {
		return super.get(id);
	}
	
	public List<TodoTask> findList(TodoTask todoTask) {
		return super.findList(todoTask);
	}
	
	public Page<TodoTask> findPage(Page<TodoTask> page, TodoTask todoTask) {
		return super.findPage(page, todoTask);
	}
	
	@Transactional(readOnly = false)
	public void save(TodoTask todoTask) {
		super.save(todoTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(TodoTask todoTask) {
		super.delete(todoTask);
	}
	
}