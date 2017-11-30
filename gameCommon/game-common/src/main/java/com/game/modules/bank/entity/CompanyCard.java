/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.bank.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.game.common.persistence.DataEntity;

/**
 * 银行卡管理Entity
 * @author David
 * @version 2017-11-29
 */
public class CompanyCard extends DataEntity<CompanyCard> {
	
	private static final long serialVersionUID = 1L;
	private String companyId;		// 公司id
	private String bankCode;		// 银行代码
	private String bankCardNo;		// 银行卡号
	private String bankCardHolder;		// 银行卡持有人
	private String bankBranchProvince;		// 开户行省份
	private String bankBranchCity;		// 开户行城市
	private String bankBranchName;		// 开户行（支行）名称
	private String status;		// 银行卡状态：0审核中1正常2冻结
	private Date beginCreateDate;		// 开始 create_date
	private Date endCreateDate;		// 结束 create_date
	
	public CompanyCard() {
		super();
	}

	public CompanyCard(String id){
		super(id);
	}

	@Length(min=1, max=50, message="公司id长度必须介于 1 和 50 之间")
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@Length(min=1, max=50, message="银行代码长度必须介于 1 和 50 之间")
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	@Length(min=1, max=50, message="银行卡号长度必须介于 1 和 50 之间")
	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	
	@Length(min=1, max=50, message="银行卡持有人长度必须介于 1 和 50 之间")
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
	
	@Length(min=1, max=50, message="开户行（支行）名称长度必须介于 1 和 50 之间")
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
		
}