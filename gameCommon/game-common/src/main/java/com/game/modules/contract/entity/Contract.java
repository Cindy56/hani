/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.contract.entity;

import com.game.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.game.modules.sys.entity.User;
import java.util.Date;
import java.util.List;
import com.google.common.collect.Lists;

import com.game.common.persistence.DataEntity;

/**
 * 开设分公司Entity
 * @author freeman
 * @version 2017-11-22
 */
public class Contract extends DataEntity<Contract> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 机构id
	private String orgName;		// 机构名称
	private User user;		// 用户id
	private String userName;		// 用户登录名称
	private String accountId;		// 账户id
	private String openType;		// 类型
	private String benefitType;		// 分红模式
	private String benefitCycle;		// 分红周期
	private String rentAmount;		// 平台租金
	private String openAmount;		// 开户费
	private String contractTime;		// 签约周期
	private String remark;		// 备注
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	private String qqNo;	//qq号码
	private String mobileNo;	//手机号码
	private String secPassword;	//安全密码
	
	private List<ContractConfig> contractConfigList = Lists.newArrayList();		// 子表列表
	
	
	
	public Contract() {
		super();
	}

	public Contract(String id){
		super(id);
	}

	@NotNull(message="机构id不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=1, max=50, message="机构名称长度必须介于 1 和 50 之间")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	@NotNull(message="用户id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=50, message="用户登录名称长度必须介于 1 和 50 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=1, max=50, message="账户id长度必须介于 1 和 50 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=1, max=1, message="类型长度必须介于 1 和 1 之间")
	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}
	
	@Length(min=1, max=1, message="分红模式长度必须介于 1 和 1 之间")
	public String getBenefitType() {
		return benefitType;
	}

	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}
	
	@Length(min=1, max=1, message="分红周期长度必须介于 1 和 1 之间")
	public String getBenefitCycle() {
		return benefitCycle;
	}

	public void setBenefitCycle(String benefitCycle) {
		this.benefitCycle = benefitCycle;
	}
	
	public String getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(String rentAmount) {
		this.rentAmount = rentAmount;
	}
	
	public String getOpenAmount() {
		return openAmount;
	}

	public void setOpenAmount(String openAmount) {
		this.openAmount = openAmount;
	}
	
	@Length(min=1, max=4, message="签约周期长度必须介于 1 和 4 之间")
	public String getContractTime() {
		return contractTime;
	}

	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}
	
	@Length(min=0, max=4000, message="备注长度必须介于 0 和 4000 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		
	public List<ContractConfig> getContractConfigList() {
		return contractConfigList;
	}

	public void setContractConfigList(List<ContractConfig> contractConfigList) {
		this.contractConfigList = contractConfigList;
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

	public String getSecPassword() {
		return secPassword;
	}
	public void setSecPassword(String secPassword) {
		this.secPassword = secPassword;
	}
	
}