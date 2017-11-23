/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.game.modules.finance.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.game.common.persistence.DataEntity;
import com.game.modules.sys.entity.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账变流水Entity
 * @author jerry
 * @version 2017-11-09
 */
public class FinanceTradeDetail extends DataEntity<FinanceTradeDetail> {
	private static final long serialVersionUID = -8951881613820090205L;
	private User user;		// 用户id
	private String userName;		// 用户名称
	private String accountId;		// 账号id
	private String orgId;
	private String busiNo;		// 业务编号，一个编号对应多条明细：如果类型为投注，就为注单编号，一个注单在派奖后，生成奖金、本人返水，多个上级返水如果是充值，就是充值单编号如果是提现，就为提现记录编号
	private String tradeType;		// 账变交易类型：
	private BigDecimal amount;		// 账变金额
	private BigDecimal accountBlanceBefore;		// 账变前金额
	private BigDecimal accountBlanceAfter;		// 账变后金额
	private Date beginCreateDate;		// 开始 create_date
	private Date endCreateDate;		// 结束 create_date
	
	
	
	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public FinanceTradeDetail() {
		super();
	}

	public FinanceTradeDetail(String id){
		super(id);
	}

	@NotNull(message="用户id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=50, message="用户名称长度必须介于 0 和 50 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=1, max=50, message="账号id长度必须介于 1 和 50 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=1, max=50, message="业务编号，长度必须介于 1 和 50 之间")
	public String getBusiNo() {
		return busiNo;
	}

	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	
	@Length(min=1, max=10, message="账变交易类型长度必须介于 1 和 10 之间")
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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