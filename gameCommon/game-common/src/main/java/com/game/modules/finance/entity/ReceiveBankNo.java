/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.finance.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.game.common.persistence.DataEntity;

/**
 * 收款人银行卡管理Entity
 * @author David
 * @version 2017-11-28
 */
public class ReceiveBankNo extends DataEntity<ReceiveBankNo> {
	
	private static final long serialVersionUID = 1L;
	private String bankNo;		// bank_no
	private String bankCardNo;		// bank_card_no
	private String userName;		// user_name
	private Date createTime;		// create_time
	private String status;		// status
	
	public ReceiveBankNo() {
		super();
	}

	public ReceiveBankNo(String id){
		super(id);
	}

	@Length(min=1, max=50, message="bank_no长度必须介于 1 和 50 之间")
	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	@Length(min=1, max=50, message="bank_card_no长度必须介于 1 和 50 之间")
	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	
	@Length(min=1, max=50, message="user_name长度必须介于 1 和 50 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="create_time不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=1, max=1, message="status长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}