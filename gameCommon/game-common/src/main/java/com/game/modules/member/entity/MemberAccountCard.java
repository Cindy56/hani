/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.member.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.game.common.persistence.DataEntity;
import com.game.modules.sys.entity.User;

import java.util.Date;

/**
 * 会员银行卡管理Entity
 * @author freeman
 * @version 2017-11-14
 */
public class MemberAccountCard extends DataEntity<MemberAccountCard> {
	private static final long serialVersionUID = 8465130544768646095L;
	private User user;		// 用户id
	private String accountId;		// 账户id
	private String bankCode;		// 开户行代码
	private String bankCardNo;		// 银行卡账号
	private String bankCardHolder;		// 银行卡户名
	private String bankBranchProvince;		// 开户行省份
	private String bankBranchCity;		// 开户行城市
	private String bankBranchName;		// 开户行全称
	private String status;		// 银行卡状态：0审核中1正常2冻结
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	private String accountType;		//会员类型：1代理，2会员
	private String qqNo;		//qq号码，用户
	private String mobileNo;		//手机号码
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MemberAccountCard() {
		super();
	}

	public MemberAccountCard(String id){
		super(id);
	}

	@NotNull(message="用户id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=50, message="账户id长度必须介于 1 和 50 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=1, max=50, message="开户行代码长度必须介于 1 和 50 之间")
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
	
	@Length(min=1, max=50, message="银行卡户名长度必须介于 1 和 50 之间")
	public String getBankCardHolder() {
		return bankCardHolder;
	}

	public void setBankCardHolder(String bankCardHolder) {
		this.bankCardHolder = bankCardHolder;
	}
	
	@Length(min=1, max=50, message="开户行省份长度必须介于 1 和 50 之间")
	public String getBankBranchProvince() {
		return bankBranchProvince;
	}

	public void setBankBranchProvince(String bankBranchProvince) {
		this.bankBranchProvince = bankBranchProvince;
	}
	
	@Length(min=1, max=50, message="开户行城市长度必须介于 1 和 50 之间")
	public String getBankBranchCity() {
		return bankBranchCity;
	}

	public void setBankBranchCity(String bankBranchCity) {
		this.bankBranchCity = bankBranchCity;
	}
	
	@Length(min=1, max=500, message="开户行全称长度必须介于 1 和 500 之间")
	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	
	@Length(min=1, max=1, message="银行卡状态：0审核中1正常2冻结长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getQqNo() {
		return qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}