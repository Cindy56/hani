package com.game.hall.modules.order.entity;

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */


import java.math.BigDecimal;

import com.game.modules.sys.entity.Office;
import com.game.modules.sys.entity.User;


public class tradeDetail {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private User user;			
	private Office orgId;		
	private String accountId;	
	private String busiNo;		
	private BigDecimal amount;		
	private BigDecimal accountBlanceBefore;		
	private BigDecimal accountBlanceAfter;
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
	public Office getOrgId() {
		return orgId;
	}
	public void setOrgId(Office orgId) {
		this.orgId = orgId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getAccountBlanceBefore() {
		return accountBlanceBefore;
	}
	public void setAccountBlanceBefore(BigDecimal accountBlanceBefore) {
		this.accountBlanceBefore = accountBlanceBefore;
	}
	public BigDecimal getAccountBlanceAfter() {
		return accountBlanceAfter;
	}
	public void setAccountBlanceAfter(BigDecimal accountBlanceAfter) {
		this.accountBlanceAfter = accountBlanceAfter;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	

	
	
}