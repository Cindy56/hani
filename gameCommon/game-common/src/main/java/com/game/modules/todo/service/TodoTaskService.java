/**
 * 
 */
package com.game.modules.todo.service;

import java.util.List;

import com.game.common.persistence.Page;
import com.game.modules.todo.entity.TodoTask;

/**
 * @author freeman
 * 2017年11月23日 下午7:52:40
 */
public interface TodoTaskService {

	public TodoTask get(String id);
	
	public List<TodoTask> findList(TodoTask todoTask) ;
	
	public Page<TodoTask> findPage(Page<TodoTask> page, TodoTask todoTask);
	
	public TodoTask save(TodoTask todoTask) ;
	
	public void delete(TodoTask todoTask) ;
}
