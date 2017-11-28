/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.contract.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.game.common.persistence.DataEntity;

/**
 * 开设分公司Entity
 * @author freeman
 * @version 2017-11-22
 */
public class ContractConfig extends DataEntity<ContractConfig> {
	private static final long serialVersionUID = -7077081619430524791L;
	private Contract contractId;		// 合同ID 父类
	private BigDecimal rangeStart;		// 以元为单位，界面上格式化万
	private BigDecimal rangeEnd;		// 以元为单位，界面上格式化万
	private BigDecimal beniftRate;		// 分红比例，小数存储，如0.5
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	

	public ContractConfig() {
		super();
	}

	public ContractConfig(String id){
		super(id);
	}

	public ContractConfig(Contract contractId){
		this.contractId = contractId;
	}

	@Length(min=1, max=50, message="合同ID长度必须介于 1 和 50 之间")
	public Contract getContractId() {
		return contractId;
	}

	public void setContractId(Contract contractId) {
		this.contractId = contractId;
	}
	
	public BigDecimal getRangeStart() {
		return rangeStart;
	}

	public void setRangeStart(BigDecimal rangeStart) {
		this.rangeStart = rangeStart;
	}
	
	public BigDecimal getRangeEnd() {
		return rangeEnd;
	}

	public void setRangeEnd(BigDecimal rangeEnd) {
		this.rangeEnd = rangeEnd;
	}
	
	public BigDecimal getBeniftRate() {
		return beniftRate;
	}

	public void setBeniftRate(BigDecimal beniftRate) {
		this.beniftRate = beniftRate;
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