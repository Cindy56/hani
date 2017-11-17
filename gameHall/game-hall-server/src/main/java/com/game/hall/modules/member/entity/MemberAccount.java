/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.hall.modules.member.entity;

import java.util.Date;

import com.game.hall.common.persistence.DataEntity;


/**
 * test1Entity
 * 
 * @author test1
 * @version 2017-11-14
 */

// extends DataEntity<LotteryTimeNum>
public class MemberAccount  {

	private static final long serialVersionUID = 1L;
	private String parentAgentId;		// 上级代理账号id
/*	private User user;		// 用户表主键id
	private Office orgId;		// 机构id，盘口id
*/	private String accountType;		// 会员类型：
	private String secPassword;		// 安全密码
/*	private String bankCode;		// 开户行
	private String bankCardNo;		// 银行卡账号
	private String bankCardHolder;		// 开卡人名称
	private String bankBranchProvince;		// 开户行省份
	private String bankBranchCity;		// 开户行城市
	private String bankBranchName;		// 开户行全称
*/	private String qqNo;		// qq号码
	private String mobileNo;		// 手机号码
	private String blanceNo;		// 账户余额
	
	
	public String getParentAgentId() {
		return parentAgentId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setParentAgentId(String parentAgentId) {
		this.parentAgentId = parentAgentId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getSecPassword() {
		return secPassword;
	}
	public void setSecPassword(String secPassword) {
		this.secPassword = secPassword;
	}

	public String getQqNo() {
		return qqNo;
	}
	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}
	public String getBlanceNo() {
		return blanceNo;
	}
	public void setBlanceNo(String blanceNo) {
		this.blanceNo = blanceNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String blanceFrozen;		// 冻结金额
	private String status;		// 状态



	public String getBlanceFrozen() {
		return blanceFrozen;
	}
	public void setBlanceFrozen(String blanceFrozen) {
		this.blanceFrozen = blanceFrozen;
	}


	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
}