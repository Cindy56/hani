package com.game.hall.modules.order.entity;

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */


import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.game.hall.common.persistence.DataEntity;
import com.game.hall.modules.sys.entity.Office;
import com.game.hall.modules.sys.entity.User;


public class tradeDetail {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private User user;		
	private String user_name;		
	private Office org_id;		
	private String account_id;		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Office getOrg_id() {
		return org_id;
	}
	public void setOrg_id(Office org_id) {
		this.org_id = org_id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getBusi_no() {
		return busi_no;
	}
	public void setBusi_no(String busi_no) {
		this.busi_no = busi_no;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getAccount_blance_before() {
		return account_blance_before;
	}
	public void setAccount_blance_before(BigDecimal account_blance_before) {
		this.account_blance_before = account_blance_before;
	}
	public BigDecimal getAccount_blance_after() {
		return account_blance_after;
	}
	public void setAccount_blance_after(BigDecimal account_blance_after) {
		this.account_blance_after = account_blance_after;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String busi_no;		
	private BigDecimal amount;		
	private BigDecimal account_blance_before;		
	private BigDecimal account_blance_after;		
	
	
}