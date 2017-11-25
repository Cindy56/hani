/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.finance.entity;

import com.game.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.game.common.persistence.DataEntity;

/**
 * 账户充值管理Entity
 * @author David
 * @version 2017-11-25
 */
public class FinanceRecharge extends DataEntity<FinanceRecharge> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户id
	private String userName;		// 用户名
	private String orgId;		// 公司id
	private String rechargeNo;		// 充值单编号
	private String paymentChannelId;		// 充值通道外键
	private String bankCode;		// 充值银行代码
	private String bankName;		// 充值银行名称
	private String bankCardNo;		// 充值银行卡号
	private String rechargeAmount;		// 充值金额
	private String validateCode;		// 验证码
	private Date rechargeDate;		// 充值时间
	private String thirdPayNo;		// 第三方支付平台凭证单号
	private Date thirdPayDate;		// 第三方支付平台到账时间
	private String auditUserId;		// audit_user_id
	private String auditUserName;		// audit_user_name
	private Date auditDate;		// audit_date
	private String status;		// 状态:0支付中、1取消支付，2第三方平台到账，3财务审核中，4财务审核通过，5平台到账 )
	private Date beginRechargeDate;		// 开始 充值时间
	private Date endRechargeDate;		// 结束 充值时间
	private Date beginAuditDate;		// 开始 audit_date
	private Date endAuditDate;		// 结束 audit_date
	
	public FinanceRecharge() {
		super();
	}

	public FinanceRecharge(String id){
		super(id);
	}

	@NotNull(message="用户id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=50, message="用户名长度必须介于 1 和 50 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=1, max=50, message="公司id长度必须介于 1 和 50 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=1, max=50, message="充值单编号长度必须介于 1 和 50 之间")
	public String getRechargeNo() {
		return rechargeNo;
	}

	public void setRechargeNo(String rechargeNo) {
		this.rechargeNo = rechargeNo;
	}
	
	@Length(min=1, max=50, message="充值通道外键长度必须介于 1 和 50 之间")
	public String getPaymentChannelId() {
		return paymentChannelId;
	}

	public void setPaymentChannelId(String paymentChannelId) {
		this.paymentChannelId = paymentChannelId;
	}
	
	@Length(min=0, max=50, message="充值银行代码长度必须介于 0 和 50 之间")
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	@Length(min=0, max=50, message="充值银行名称长度必须介于 0 和 50 之间")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Length(min=0, max=50, message="充值银行卡号长度必须介于 0 和 50 之间")
	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	
	public String getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(String rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	
	@Length(min=0, max=10, message="验证码长度必须介于 0 和 10 之间")
	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(Date rechargeDate) {
		this.rechargeDate = rechargeDate;
	}
	
	@Length(min=0, max=50, message="第三方支付平台凭证单号长度必须介于 0 和 50 之间")
	public String getThirdPayNo() {
		return thirdPayNo;
	}

	public void setThirdPayNo(String thirdPayNo) {
		this.thirdPayNo = thirdPayNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getThirdPayDate() {
		return thirdPayDate;
	}

	public void setThirdPayDate(Date thirdPayDate) {
		this.thirdPayDate = thirdPayDate;
	}
	
	@Length(min=0, max=50, message="audit_user_id长度必须介于 0 和 50 之间")
	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}
	
	@Length(min=0, max=50, message="audit_user_name长度必须介于 0 和 50 之间")
	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	@Length(min=0, max=1, message="状态:0支付中、1取消支付，2第三方平台到账，3财务审核中，4财务审核通过，5平台到账 )长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getBeginRechargeDate() {
		return beginRechargeDate;
	}

	public void setBeginRechargeDate(Date beginRechargeDate) {
		this.beginRechargeDate = beginRechargeDate;
	}
	
	public Date getEndRechargeDate() {
		return endRechargeDate;
	}

	public void setEndRechargeDate(Date endRechargeDate) {
		this.endRechargeDate = endRechargeDate;
	}
		
	public Date getBeginAuditDate() {
		return beginAuditDate;
	}

	public void setBeginAuditDate(Date beginAuditDate) {
		this.beginAuditDate = beginAuditDate;
	}
	
	public Date getEndAuditDate() {
		return endAuditDate;
	}

	public void setEndAuditDate(Date endAuditDate) {
		this.endAuditDate = endAuditDate;
	}
		
}