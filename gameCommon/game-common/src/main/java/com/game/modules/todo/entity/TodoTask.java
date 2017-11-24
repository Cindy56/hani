/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.todo.entity;

import org.hibernate.validator.constraints.Length;
import com.game.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.game.common.persistence.DataEntity;

/**
 * 待办任务管理Entity
 * @author freeman
 * @version 2017-11-23
 */
public class TodoTask extends DataEntity<TodoTask> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 任务标题
	private String content;		// 任务正文
	private String type;		// 任务类型
	private User senderId;		// 申请者
	private User receiverId;		// 处理者
	private String status;		// 任务状态
	
	public TodoTask() {
		super();
	}

	public TodoTask(String id){
		super(id);
	}

	@Length(min=1, max=100, message="任务标题长度必须介于 1 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=4000, message="任务正文长度必须介于 1 和 4000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=1, max=1, message="任务类型长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@NotNull(message="申请者不能为空")
	public User getSenderId() {
		return senderId;
	}

	public void setSenderId(User senderId) {
		this.senderId = senderId;
	}
	
	@NotNull(message="处理者不能为空")
	public User getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(User receiverId) {
		this.receiverId = receiverId;
	}
	
	@Length(min=1, max=1, message="任务状态长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}