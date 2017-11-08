/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.manager.modules.member.entity;

import org.hibernate.validator.constraints.Length;
import com.game.manager.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.game.manager.common.persistence.DataEntity;

/**
 * 会员管理Entity
 * @author David
 * @version 2017-11-08
 */
public class MemberAccount extends DataEntity<MemberAccount> {
	
	private static final long serialVersionUID = 1L;
	private String parentAgentId;		// 上级代理账号id
	private User user;		// 用户表主键id
	private String orgId;		// 机构id，盘口id
	private String accountType;		// 会员类型：1代理，2会员
	private String secPassword;		// 安全密码，salt后的MD5摘要值
	private String bankCode;		// 开户行代码，从数据字典里取值
	private String bankCardNo;		// 银行卡账号
	private String bankCardHolder;		// 银行卡开卡人名称
	private String bankBranchProvince;		// 开户行省份
	private String bankBranchCity;		// 开户行城市
	private String bankBranchName;		// 开户行全称
	private String qqNo;		// qq号码，用户
	private String mobileNo;		// 手机号码
	private String blance;		// 余额
	private String blanceFrozen;		// 冻结余额
	private String status;		// 冻结,正常
	
	public MemberAccount() {
		super();
	}

	public MemberAccount(String id){
		super(id);
	}

	@Length(min=1, max=50, message="上级代理账号id长度必须介于 1 和 50 之间")
	public String getParentAgentId() {
		return parentAgentId;
	}

	public void setParentAgentId(String parentAgentId) {
		this.parentAgentId = parentAgentId;
	}
	
	@NotNull(message="用户表主键id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=50, message="机构id，盘口id长度必须介于 1 和 50 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=1, max=1, message="会员类型：1代理，2会员长度必须介于 1 和 1 之间")
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@Length(min=1, max=50, message="安全密码，salt后的MD5摘要值长度必须介于 1 和 50 之间")
	public String getSecPassword() {
		return secPassword;
	}

	public void setSecPassword(String secPassword) {
		this.secPassword = secPassword;
	}
	
	@Length(min=1, max=50, message="开户行代码，从数据字典里取值长度必须介于 1 和 50 之间")
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	@Length(min=1, max=50, message="银行卡账号长度必须介于 1 和 50 之间")
	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	
	@Length(min=1, max=500, message="银行卡开卡人名称长度必须介于 1 和 500 之间")
	public String getBankCardHolder() {
		return bankCardHolder;
	}

	public void setBankCardHolder(String bankCardHolder) {
		this.bankCardHolder = bankCardHolder;
	}
	
	@Length(min=0, max=50, message="开户行省份长度必须介于 0 和 50 之间")
	public String getBankBranchProvince() {
		return bankBranchProvince;
	}

	public void setBankBranchProvince(String bankBranchProvince) {
		this.bankBranchProvince = bankBranchProvince;
	}
	
	@Length(min=0, max=50, message="开户行城市长度必须介于 0 和 50 之间")
	public String getBankBranchCity() {
		return bankBranchCity;
	}

	public void setBankBranchCity(String bankBranchCity) {
		this.bankBranchCity = bankBranchCity;
	}
	
	@Length(min=0, max=500, message="开户行全称长度必须介于 0 和 500 之间")
	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	
	@Length(min=0, max=50, message="qq号码，用户长度必须介于 0 和 50 之间")
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
	
	@Length(min=1, max=1, message="冻结,正常长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}