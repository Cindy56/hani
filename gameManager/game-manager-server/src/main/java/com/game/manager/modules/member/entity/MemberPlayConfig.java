/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.member.entity;

import com.game.manager.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.game.manager.common.persistence.DataEntity;

/**
 * 会员玩法奖金配置Entity
 * @author freeman
 * @version 2017-11-20
 */
public class MemberPlayConfig extends DataEntity<MemberPlayConfig> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// user_id
	private String userName;		// user_name
	private String accountId;		// account_id
	private String playConfig;		// 奖金设置: 大字段，json格式，存储玩法奖金设置
	
	public MemberPlayConfig() {
		super();
	}

	public MemberPlayConfig(String id){
		super(id);
	}

	@NotNull(message="user_id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=50, message="user_name长度必须介于 1 和 50 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=1, max=50, message="account_id长度必须介于 1 和 50 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getPlayConfig() {
		return playConfig;
	}

	public void setPlayConfig(String playConfig) {
		this.playConfig = playConfig;
	}
	
}