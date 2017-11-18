/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.memberadd.entity;

import org.hibernate.validator.constraints.Length;
import com.game.manager.modules.sys.entity.User;

import com.game.manager.common.persistence.DataEntity;

/**
 * 会员开户Entity
 * @author freeman
 * @version 2017-11-17
 */
public class MemberAccount extends DataEntity<MemberAccount> {
	
	private static final long serialVersionUID = 1L;
	private String parentAgentId;		// 上级代理账号
	private String parentAgentIds;		// 
	private User user;		// 用户id
	private String orgId;		// 机构、盘口id
	private String accountType;		// 会员类型
	private String secPassword;		// 安全密码
	private String qqNo;		// qq号码
	private String mobileNo;		// 手机号码
	private String blance;		// 余额
	private String blanceFrozen;		// 冻结余额
	private String status;		// 账户状态
	
	public MemberAccount() {
		super();
	}

	public String getParentAgentIds() {
		return parentAgentIds;
	}

	public void setParentAgentIds(String parentAgentIds) {
		this.parentAgentIds = parentAgentIds;
	}

	public MemberAccount(String id){
		super(id);
	}

	@Length(min=0, max=50, message="上级代理账号长度必须介于 0 和 50 之间")
	public String getParentAgentId() {
		return parentAgentId;
	}

	public void setParentAgentId(String parentAgentId) {
		this.parentAgentId = parentAgentId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=50, message="机构、盘口id长度必须介于 0 和 50 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=0, max=1, message="会员类型长度必须介于 0 和 1 之间")
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@Length(min=0, max=50, message="安全密码长度必须介于 0 和 50 之间")
	public String getSecPassword() {
		return secPassword;
	}

	public void setSecPassword(String secPassword) {
		this.secPassword = secPassword;
	}
	
	@Length(min=0, max=50, message="qq号码长度必须介于 0 和 50 之间")
	public String getQqNo() {
		return qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}
	
	@Length(min=0, max=50, message="手机号码长度必须介于 0 和 50 之间")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getBlance() {
		return blance;
	}

	public void setBlance(String blance) {
		this.blance = blance;
	}
	
	public String getBlanceFrozen() {
		return blanceFrozen;
	}

	public void setBlanceFrozen(String blanceFrozen) {
		this.blanceFrozen = blanceFrozen;
	}
	
	@Length(min=0, max=1, message="账户状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}