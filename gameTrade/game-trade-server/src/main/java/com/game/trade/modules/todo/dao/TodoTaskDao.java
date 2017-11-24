/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.trade.modules.todo.dao;

import com.game.common.persistence.CrudDao;
import com.game.common.persistence.annotation.MyBatisDao;
import com.game.modules.todo.entity.TodoTask;

/**
 * 待办任务管理DAO接口
 * @author freeman
 * @version 2017-11-23
 */
@MyBatisDao
public interface TodoTaskDao extends CrudDao<TodoTask> {
	
}