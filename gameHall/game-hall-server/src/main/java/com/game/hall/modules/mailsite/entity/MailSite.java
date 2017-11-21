/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.mailsite.entity;

import org.hibernate.validator.constraints.Length;

import com.game.hall.common.persistence.DataEntity;

/**
 * 站内信Entity
 * @author antonio
 * @version 2017-11-21
 */
public class MailSite extends DataEntity<MailSite> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户ID
	private String label;		// 标签
	private String title;		// 标题
	private String from;		// 发信人
	private String to;		// 收信人
	private String context;		// 信件正文
	private String readFlag;		// 读标志
	
	public MailSite() {
		super();
	}

	public MailSite(String id){
		super(id);
	}

	@Length(min=1, max=50, message="用户ID长度必须介于 1 和 50 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String user) {
		this.userId = user;
	}
	
	@Length(min=1, max=50, message="标签长度必须介于 1 和 50 之间")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Length(min=1, max=200, message="标题长度必须介于 1 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=50, message="发信人长度必须介于 1 和 50 之间")
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	@Length(min=1, max=50, message="收信人长度必须介于 1 和 50 之间")
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	@Length(min=1, max=2000, message="信件正文长度必须介于 1 和 2000 之间")
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	@Length(min=0, max=1, message="读标志长度必须介于 0 和 1 之间")
	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	
}